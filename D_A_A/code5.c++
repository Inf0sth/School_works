#include <iostream>
using namespace std;

struct Node {
    int data;
    Node* left;
    Node* right;

    Node(int value) : data(value), left(nullptr), right(nullptr) {}
};

Node* insert(Node* root, int data) {
    if (root == nullptr) {
        return new Node(data);
    }

    if (data < root->data) {
        root->left = insert(root->left, data);
    } else {
        root->right = insert(root->right, data);
    }

    return root;
}

void preOrder(Node* node) {
    if (node == nullptr) return;
    cout << node->data << " ";
    preOrder(node->left);
    preOrder(node->right);
}

void inOrder(Node* node) {
    if (node == nullptr) return;
    inOrder(node->left);
    cout << node->data << " ";
    inOrder(node->right);
}

void postOrder(Node* node) {
    if (node == nullptr) return;
    postOrder(node->left);
    postOrder(node->right);
    cout << node->data << " ";
}

int main() {
    Node* root = nullptr;

    root = insert(root, 14);
    insert(root, 10);
    insert(root, 5);
    insert(root, 1);
    insert(root, 3);
    insert(root, 7);
    insert(root, 12);
    insert(root, 18);
    insert(root, 16);
    insert(root, 17);
    insert(root, 20);

    cout << "Recorrido en Preorden: ";
    preOrder(root);
    cout << endl;

    cout << "Recorrido en Inorden: ";
    inOrder(root);
    cout << endl;

    cout << "Recorrido en Postorden: ";
    postOrder(root);
    cout << endl;

    return 0;
}
