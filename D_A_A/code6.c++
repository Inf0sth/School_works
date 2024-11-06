#include <iostream>
#include <vector>
using namespace std;

const int ORDER = 6;

class BTreeNode {
public:
    vector<int> keys;
    vector<BTreeNode*> children;
    bool isLeaf;
    int numKeys;

    BTreeNode(bool isLeaf) {
        this->isLeaf = isLeaf;
        keys.resize(ORDER - 1);
        children.resize(ORDER);
        numKeys = 0;
    }

    void insertNonFull(int key);
    void splitChild(int i, BTreeNode* y);
    void remove(int key);
    int findKey(int key);
    void removeFromLeaf(int idx);
    void removeFromNonLeaf(int idx);
    int getPred(int idx);
    int getSucc(int idx);
    void fill(int idx);
    void borrowFromPrev(int idx);
    void borrowFromNext(int idx);
    void merge(int idx);
    friend class BTree;
};

class BTree {
private:
    BTreeNode* root;

public:
    BTree() {
        root = nullptr;
    }

    void insert(int key);
    void search(int key);
    void remove(int key);
    void traverse(BTreeNode* node);
    void menu();
};

void BTree::search(int key) {
    BTreeNode* node = root;
    while (node != nullptr) {
        int i = 0;
        while (i < node->numKeys && key > node->keys[i])
            i++;
        if (i < node->numKeys && node->keys[i] == key) {
            cout << "El elemento " << key << " fue encontrado." << endl;
            return;
        }
        if (node->isLeaf) {
            cout << "El elemento " << key << " no fue encontrado." << endl;
            return;
        }
        node = node->children[i];
    }
    cout << "El elemento " << key << " no fue encontrado." << endl;
}

void BTree::insert(int key) {
    if (root == nullptr) {
        root = new BTreeNode(true);
        root->keys[0] = key;
        root->numKeys = 1;
    } else {
        if (root->numKeys == ORDER - 1) {
            BTreeNode* newNode = new BTreeNode(false);
            newNode->children[0] = root;
            newNode->splitChild(0, root);

            int i = 0;
            if (newNode->keys[0] < key)
                i++;
            newNode->children[i]->insertNonFull(key);

            root = newNode;
        } else {
            root->insertNonFull(key);
        }
    }
}

void BTreeNode::insertNonFull(int key) {
    int i = numKeys - 1;

    if (isLeaf) {
        while (i >= 0 && keys[i] > key) {
            keys[i + 1] = keys[i];
            i--;
        }
        keys[i + 1] = key;
        numKeys++;
    } else {
        while (i >= 0 && keys[i] > key)
            i--;

        if (children[i + 1]->numKeys == ORDER - 1) {
            splitChild(i + 1, children[i + 1]);

            if (keys[i + 1] < key)
                i++;
        }
        children[i + 1]->insertNonFull(key);
    }
}

void BTreeNode::splitChild(int i, BTreeNode* y) {
    BTreeNode* z = new BTreeNode(y->isLeaf);
    z->numKeys = ORDER / 2 - 1;

    for (int j = 0; j < ORDER / 2 - 1; j++)
        z->keys[j] = y->keys[j + ORDER / 2];

    if (!y->isLeaf) {
        for (int j = 0; j < ORDER / 2; j++)
            z->children[j] = y->children[j + ORDER / 2];
    }

    y->numKeys = ORDER / 2 - 1;

    for (int j = numKeys; j >= i + 1; j--)
        children[j + 1] = children[j];

    children[i + 1] = z;

    for (int j = numKeys - 1; j >= i; j--)
        keys[j + 1] = keys[j];

    keys[i] = y->keys[ORDER / 2 - 1];
    numKeys++;
}

void BTreeNode::remove(int key) {
    int idx = findKey(key);

    if (idx < numKeys && keys[idx] == key) {
        if (isLeaf)
            removeFromLeaf(idx);
        else
            removeFromNonLeaf(idx);
    } else {
        if (isLeaf) {
            cout << "La clave " << key << " no se encuentra en el árbol.\n";
            return;
        }

        bool isLastChild = (idx == numKeys);

        if (children[idx]->numKeys < ORDER / 2)
            fill(idx);

        if (isLastChild && idx > numKeys)
            children[idx - 1]->remove(key);
        else
            children[idx]->remove(key);
    }
}

int BTreeNode::findKey(int key) {
    int idx = 0;
    while (idx < numKeys && keys[idx] < key)
        ++idx;
    return idx;
}

void BTreeNode::removeFromLeaf(int idx) {
    for (int i = idx + 1; i < numKeys; ++i)
        keys[i - 1] = keys[i];
    numKeys--;
}

void BTreeNode::removeFromNonLeaf(int idx) {
    int key = keys[idx];

    if (children[idx]->numKeys >= ORDER / 2) {
        int pred = getPred(idx);
        keys[idx] = pred;
        children[idx]->remove(pred);
    } else if (children[idx + 1]->numKeys >= ORDER / 2) {
        int succ = getSucc(idx);
        keys[idx] = succ;
        children[idx + 1]->remove(succ);
    } else {
        merge(idx);
        children[idx]->remove(key);
    }
}

int BTreeNode::getPred(int idx) {
    BTreeNode* current = children[idx];
    while (!current->isLeaf)
        current = current->children[current->numKeys];
    return current->keys[current->numKeys - 1];
}

int BTreeNode::getSucc(int idx) {
    BTreeNode* current = children[idx + 1];
    while (!current->isLeaf)
        current = current->children[0];
    return current->keys[0];
}

void BTreeNode::fill(int idx) {
    if (idx != 0 && children[idx - 1]->numKeys >= ORDER / 2)
        borrowFromPrev(idx);
    else if (idx != numKeys && children[idx + 1]->numKeys >= ORDER / 2)
        borrowFromNext(idx);
    else {
        if (idx != numKeys)
            merge(idx);
        else
            merge(idx - 1);
    }
}

void BTreeNode::borrowFromPrev(int idx) {
    BTreeNode* child = children[idx];
    BTreeNode* sibling = children[idx - 1];

    for (int i = child->numKeys - 1; i >= 0; --i)
        child->keys[i + 1] = child->keys[i];

    if (!child->isLeaf) {
        for (int i = child->numKeys; i >= 0; --i)
            child->children[i + 1] = child->children[i];
    }

    child->keys[0] = keys[idx - 1];

    if (!child->isLeaf)
        child->children[0] = sibling->children[sibling->numKeys];

    keys[idx - 1] = sibling->keys[sibling->numKeys - 1];
    child->numKeys++;
    sibling->numKeys--;
}

void BTreeNode::borrowFromNext(int idx) {
    BTreeNode* child = children[idx];
    BTreeNode* sibling = children[idx + 1];

    child->keys[child->numKeys] = keys[idx];

    if (!child->isLeaf)
        child->children[child->numKeys + 1] = sibling->children[0];

    keys[idx] = sibling->keys[0];

    for (int i = 1; i < sibling->numKeys; ++i)
        sibling->keys[i - 1] = sibling->keys[i];

    if (!sibling->isLeaf) {
        for (int i = 1; i <= sibling->numKeys; ++i)
            sibling->children[i - 1] = sibling->children[i];
    }

    child->numKeys++;
    sibling->numKeys--;
}

void BTreeNode::merge(int idx) {
    BTreeNode* child = children[idx];
    BTreeNode* sibling = children[idx + 1];

    child->keys[ORDER / 2 - 1] = keys[idx];

    for (int i = 0; i < sibling->numKeys; ++i)
        child->keys[i + ORDER / 2] = sibling->keys[i];

    if (!child->isLeaf) {
        for (int i = 0; i <= sibling->numKeys; ++i)
            child->children[i + ORDER / 2] = sibling->children[i];
    }

    for (int i = idx + 1; i < numKeys; ++i)
        keys[i - 1] = keys[i];

    for (int i = idx + 2; i <= numKeys; ++i)
        children[i - 1] = children[i];

    child->numKeys += sibling->numKeys + 1;
    numKeys--;

    delete sibling;
}

void BTree::remove(int key) {
    if (!root) {
        cout << "El árbol está vacío.\n";
        return;
    }
    root->remove(key);

    if (root->numKeys == 0) {
        BTreeNode* tmp = root;
        if (root->isLeaf)
            root = nullptr;
        else
            root = root->children[0];

        delete tmp;
    }
}

void BTree::menu() {
    int choice, key;
    while (true) {
        cout << "\nMenú de Árbol B de Orden " << ORDER << ":\n";
        cout << "1. Agregar\n";
        cout << "2. Buscar\n";
        cout << "3. Eliminar\n";
        cout << "4. Salir\n";
        cout << "Seleccione una opción: ";
        cin >> choice;

        switch (choice) {
        case 1:
            cout << "Ingrese la clave a agregar: ";
            cin >> key;
            insert(key);
            break;
        case 2:
            cout << "Ingrese la clave a buscar: ";
            cin >> key;
            search(key);
            break;
        case 3:
            cout << "Ingrese la clave a eliminar: ";
            cin >> key;
            remove(key);
            break;
        case 4:
            return;
        default:
            cout << "Opción inválida. Intente nuevamente.\n";
        }
    }
}

int main() {
    BTree tree;
    tree.menu();
    return 0;
}
