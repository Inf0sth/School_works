// Proyecto final primera parte.
// Árbol de Huffman
// Alumnos:
// Bryan Sebastian Ruiz
// Araiza López Joel ALberto
// 5to semestre

#include <iostream>
#include <queue>
#include <unordered_map>
#include <vector>
using namespace std;

// Nodo del árbol de Huffman
struct HuffmanNode {
    char character;
    int frequency;
    HuffmanNode* left;
    HuffmanNode* right;

    HuffmanNode(char ch, int freq) : character(ch), frequency(freq), left(nullptr), right(nullptr) {}
};

// Comparador para la cola de prioridad
struct Compare {
    bool operator()(HuffmanNode* left, HuffmanNode* right) {
        return left->frequency > right->frequency;
    }
};

// Función para generar códigos de Huffman
void generateCodes(HuffmanNode* root, string code, unordered_map<char, string>& huffmanCodes) {
    if (root == nullptr) return;
    if (!root->left && !root->right) {
        huffmanCodes[root->character] = code;
    }
    generateCodes(root->left, code + "0", huffmanCodes);
    generateCodes(root->right, code + "1", huffmanCodes);
}

// Función para construir el árbol de Huffman
HuffmanNode* buildHuffmanTree(const unordered_map<char, int>& frequencies) {
    priority_queue<HuffmanNode*, vector<HuffmanNode*>, Compare> pq;
    // Crear un nodo para cada carácter y asignarlo en la pila
    for (const auto& pair : frequencies) {
        pq.push(new HuffmanNode(pair.first, pair.second));
    }

    // Construcción del árbol
    while (pq.size() > 1) {
        HuffmanNode* left = pq.top(); pq.pop();
        HuffmanNode* right = pq.top(); pq.pop();

        // Crear un nodo combinado
        int sum = left->frequency + right->frequency;
        HuffmanNode* node = new HuffmanNode('\0', sum);
        node->left = left;
        node->right = right;
        pq.push(node);
    }
    // Raiz del arbol
    return pq.top();
}

// Función para decodificar el texto codificado usando el árbol de Huffman
string huffmanDecoding(HuffmanNode* root, const string& encodedText) {
    string decodedText;
    HuffmanNode* currentNode = root;

    for (char bit : encodedText) {
        if (bit == '0')
            currentNode = currentNode->left;
        else
            currentNode = currentNode->right;

        if (!currentNode->left && !currentNode->right) {
            decodedText += currentNode->character;
            currentNode = root; // Iteramos para el siguiente caracter
        }
    }
    return decodedText;
}

// Función principal
void huffmanEncoding(const string& text, int option, HuffmanNode*& root, unordered_map<char, string>& huffmanCodes) {
    // Calcular frecuencias de caracteres
    unordered_map<char, int> frequencies;
    for (char ch : text) {
        frequencies[ch]++;
    }

    // Construir el árbol de Huffman
    if (root == nullptr) {
        root = buildHuffmanTree(frequencies);
        generateCodes(root, "", huffmanCodes);
    }

    if (option == 1) {
        // Cifrar el texto
        string encodedText;
        for (char ch : text) {
            encodedText += huffmanCodes[ch];
        }
        // Mostrar los códigos de Huffman y el texto cifrado
        cout << "Códigos de Huffman:\n";
        for (const auto& pair : huffmanCodes) {
            cout << pair.first << ": " << pair.second << '\n';
        }
        cout << "\nTexto original: " << text << '\n';
        cout << "Texto codificado: " << encodedText << '\n';
    } else if (option == 2) {
        // Descifrar el texto
        string decodedText = huffmanDecoding(root, text);
        // Mostrar el texto descifrado
        cout << "Texto decodificado: " << decodedText << '\n';
    }
}

// Programa principal
int main() {
    string text;
    int option;
    bool continues = true;
    HuffmanNode* root = nullptr;
    unordered_map<char, string> huffmanCodes;

    // Iteración mientras el usuario quiera ejecutar el programa
    while (continues) {
        cout << "Opciones:\n1) Cifrar mensaje\n2) Descifrar mensaje\n3) Salir\n";
        cout << "Elija su opción: ";
        cin >> option;
        cin.ignore();

        // Opcion para cifrar
        if (option == 1) {
            cout << "\nIngrese el mensaje a cifrar\n>> ";
            getline(cin, text);
            huffmanEncoding(text, option, root, huffmanCodes);
        } else if (option == 2) { // Opcion para descifrar
            cout << "\nIngrese el mensaje codificado para descifrar\n>> ";
            getline(cin, text);
            if (root == nullptr) {
                cout << "No hay un árbol de Huffman generado. Cifre un mensaje primero.\n";
            } else {
                huffmanEncoding(text, option, root, huffmanCodes);
            }
        } else if (option == 3) { // Terminar el programa
            continues = false;
        } else {
            cout << "\nOpción inválida\n";
        }
    }
    return 0;
}
