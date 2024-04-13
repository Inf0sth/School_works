#include <iostream>
#include <algorithm>

int main(){
    int i = 1;
    while (i == 1)
    {
        int decimal_number;
        int residue;
        std::cout << "Enter the decimal: ";
        std::cin >> decimal_number;
        std::string binary_number;
        do
        {
            residue = decimal_number%2;
            if(residue > 0){
                binary_number += "1";
            }
            else{
                binary_number += "0";
            }
            decimal_number = decimal_number/ 2;
            
        } while (decimal_number > 0);
        std::reverse(binary_number.begin(), binary_number.end());
        std::cout << binary_number;
        if (binary_number == "0")
        {
            std::cout << "\n --\n|  |\n|  |\n --";
        }
        if (binary_number == "1")
        {
            std::cout << "\n  |\n  |";
        }
        if (binary_number == "10")
        {
            std::cout << "\n --\n   |\n -- \n|   \n --";
        }
        if (binary_number == "11")
        {
            std::cout << "\n --\n   |\n --\n   |\n --";
        }
        if (binary_number == "100")
        {
            std::cout << "\n|  |\n --\n   |";
        }
        if (binary_number == "101")
        {
            std::cout << "\n --\n|   \n --\n   |\n --";
        }
        if (binary_number == "110")
        {
            std::cout << "\n --\n|   \n --\n|  |\n --";
        }
        if (binary_number == "111")
        {
            std::cout << "\n --\n   |\n --\n   |";
        }
        if (binary_number == "1000")
        {
            std::cout << "\n --\n|  |\n --\n|  |\n --";
        }
        if (binary_number == "1001")
        {
            std::cout << "\n --\n|  |\n --\n   |\n --";
        }
        if (binary_number == "1010")
        {
            std::cout << "\n --\n|  |\n --\n|  |";
        }
        if (binary_number == "1011")
        {
            std::cout << "\n|   \n --\n|  |\n --";
        }
        if (binary_number == "1100")
        {
            std::cout << "\n --\n|   \n|   \n --";
        }
        if (binary_number == "1101")
        {
            std::cout << "\n   |\n --\n|  |\n --";
        }
        if (binary_number == "1110")
        {
            std::cout << "\n --\n|   \n --\n|   \n --";
        }
        if (binary_number == "1111")
        {
            std::cout << "\n --\n|   \n --\n|   ";
        }
        std::cout << "\nAnother op?\n1) yes\n2) no\n> ";
        std::cin >> i;
    }
    return 0;
}
