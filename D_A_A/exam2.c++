#include <iostream>
using namespace std;

struct AVLNode {
    int key;
    AVLNode* left;
    AVLNode* right;
    int height;
};

int height(AVLNode* node) {
    return node ? node->height : 0;
}

int getBalance(AVLNode* node) {
    return node ? height(node->left) - height(node->right) : 0;
}

AVLNode* createNode(int key) {
    AVLNode* node = new AVLNode();
    node->key = key;
    node->left = node->right = nullptr;
    node->height = 1;
    return node;
}

int max(int a, int b) {
    return (a > b) ? a : b;
}

AVLNode* rightRotate(AVLNode* y) {
    AVLNode* x = y->left;
    AVLNode* T2 = x->right;

    x->right = y;
    y->left = T2;

    y->height = max(height(y->left), height(y->right)) + 1;
    x->height = max(height(x->left), height(x->right)) + 1;

    return x;
}

AVLNode* leftRotate(AVLNode* x) {
    AVLNode* y = x->right;
    AVLNode* T2 = y->left;

    y->left = x;
    x->right = T2;

    x->height = max(height(x->left), height(x->right)) + 1;
    y->height = max(height(y->left), height(y->right)) + 1;

    return y;
}

AVLNode* insert(AVLNode* node, int key) {
    if (!node)
        return createNode(key);

    if (key < node->key)
        node->left = insert(node->left, key);
    else if (key > node->key)
        node->right = insert(node->right, key);
    else
        return node;

    node->height = 1 + max(height(node->left), height(node->right));
    int balance = getBalance(node);

    if (balance > 1 && key < node->left->key)
        return rightRotate(node);
    if (balance < -1 && key > node->right->key)
        return leftRotate(node);
    if (balance > 1 && key > node->left->key) {
        node->left = leftRotate(node->left);
        return rightRotate(node);
    }
    if (balance < -1 && key < node->right->key) {
        node->right = rightRotate(node->right);
        return leftRotate(node);
    }

    return node;
}

AVLNode* minValueNode(AVLNode* node) {
    AVLNode* current = node;
    while (current->left != nullptr)
        current = current->left;
    return current;
}

AVLNode* deleteNode(AVLNode* root, int key) {
    if (!root)
        return root;

    if (key < root->key)
        root->left = deleteNode(root->left, key);
    else if (key > root->key)
        root->right = deleteNode(root->right, key);
    else {
        if (!root->left || !root->right) {
            AVLNode* temp = root->left ? root->left : root->right;

            if (!temp) {
                temp = root;
                root = nullptr;
            } else
                *root = *temp;
            delete temp;
        } else {
            AVLNode* temp = minValueNode(root->right);
            root->key = temp->key;
            root->right = deleteNode(root->right, temp->key);
        }
    }

    if (!root)
        return root;

    root->height = 1 + max(height(root->left), height(root->right));
    int balance = getBalance(root);

    if (balance > 1 && getBalance(root->left) >= 0)
        return rightRotate(root);
    if (balance > 1 && getBalance(root->left) < 0) {
        root->left = leftRotate(root->left);
        return rightRotate(root);
    }
    if (balance < -1 && getBalance(root->right) <= 0)
        return leftRotate(root);
    if (balance < -1 && getBalance(root->right) > 0) {
        root->right = rightRotate(root->right);
        return leftRotate(root);
    }

    return root;
}

void preOrder(AVLNode* root) {
    if (root) {
        cout << root->key << " ";
        preOrder(root->left);
        preOrder(root->right);
    }
}

void inOrder(AVLNode* root) {
    if (root) {
        inOrder(root->left);
        cout << root->key << " ";
        inOrder(root->right);
    }
}

void postOrder(AVLNode* root) {
    if (root) {
        postOrder(root->left);
        postOrder(root->right);
        cout << root->key << " ";
    }
}

int main() {
    AVLNode* root = nullptr;
    int option, key;

    while (true) {
        cout << "\nÁrbol AVL - Menú\n";
        cout << "1. Insertar nodo\n";
        cout << "2. Eliminar nodo\n";
        cout << "3. Recorrido en preorden\n";
        cout << "4. Recorrido en inorden\n";
        cout << "5. Recorrido en posorden\n";
        cout << "6. Salir\n";
        cout << "Elija una opción: ";
        cin >> option;

        switch (option) {
            case 1:
                cout << "Ingrese el valor a insertar: ";
                cin >> key;
                root = insert(root, key);
                break;
            case 2:
                cout << "Ingrese el valor a eliminar: ";
                cin >> key;
                root = deleteNode(root, key);
                break;
            case 3:
                cout << "Recorrido en preorden: ";
                preOrder(root);
                cout << endl;
                break;
            case 4:
                cout << "Recorrido en inorden: ";
                inOrder(root);
                cout << endl;
                break;
            case 5:
                cout << "Recorrido en posorden: ";
                postOrder(root);
                cout << endl;
                break;
            case 6:
                return 0;
            default:
                cout << "Opción no válida. Intente nuevamente.\n";
        }
    }
    return 0;
}
