// Tarea:
// De binario a pantalla con - | para formar un numero.
// Ejemplo:
// --
//|  |
// --
//|  |
// --
#include <iostream>

int main(){
    int i = 1;
    while (i == 1)
    {
        std::string binary;
        std::cout << "Enter the binary: ";
        std::cin >> binary;
        if (binary == "0000")
        {
            std::cout << "\n --\n|  |\n|  |\n --";
        }
        if (binary == "0001")
        {
            std::cout << "\n  |\n  |";
        }
        if (binary == "0010")
        {
            std::cout << "\n --\n   |\n -- \n|   \n --";
        }
        if (binary == "0011")
        {
            std::cout << "\n --\n   |\n --\n   |\n --";
        }
        if (binary == "0100")
        {
            std::cout << "\n|  |\n --\n   |";
        }
        if (binary == "0101")
        {
            std::cout << "\n --\n|   \n --\n   |\n --";
        }
        if (binary == "0110")
        {
            std::cout << "\n --\n|   \n --\n|  |\n --";
        }
        if (binary == "0111")
        {
            std::cout << "\n --\n   |\n --\n   |";
        }
        if (binary == "1000")
        {
            std::cout << "\n --\n|  |\n --\n|  |\n --";
        }
        if (binary == "1001")
        {
            std::cout << "\n --\n|  |\n --\n   |\n --";
        }
        if (binary == "1010")
        {
            std::cout << "\n --\n|  |\n --\n|  |";
        }
        if (binary == "1011")
        {
            std::cout << "\n|   \n --\n|  |\n --";
        }
        if (binary == "1100")
        {
            std::cout << "\n --\n|   \n|   \n --";
        }
        if (binary == "1101")
        {
            std::cout << "\n   |\n --\n|  |\n --";
        }
        if (binary == "1110")
        {
            std::cout << "\n --\n|   \n --\n|   \n --";
        }
        if (binary == "1111")
        {
            std::cout << "\n --\n|   \n --\n|   ";
        }
        std::cout << "\nAnother op?\n1) yes\n2) no\n> ";
        std::cin >> i;    
    }
    return 0;
}