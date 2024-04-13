// Code to convert a decimal number to a binary and print it in hexadecimal
// Class: Archiqteture of computers
// By Joel López
// Languaje: C++

#include <iostream>
#include <algorithm>

int main(){
    int i = 1;
    while (i == 1)
    {
        // Variables
        int decimal_number;
        int residue;
        std::cout << "Enter the decimal: ";
        std::cin >> decimal_number;
        std::string binary_number;
        // Cycle do-while to convert decimal to binary
        do
        {
            // Check the residue
            residue = decimal_number%2;
            if(residue > 0){ // if there's a residue, we add 1
                binary_number += "1";
            }
            else{ // if not's a residue, we add 0
                binary_number += "0";
            }
            // Divide by 2 the decimal number to check the next case
            decimal_number = decimal_number/ 2;
        } while (decimal_number > 0); // Do while as long as the decimal number is greater than zero
        // Reverse the string to have the binary number in the correct order
        std::reverse(binary_number.begin(), binary_number.end());
        std::cout << binary_number; // Output the binary to check
        // Get binary number and return hexadecimal number in terminal
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
        // Decide if the program continues
        std::cout << "\nAnother op?\n1) yes\n2) no\n> ";
        std::cin >> i;
    }
    return 0;
}
