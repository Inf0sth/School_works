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

        std::cout << "\nAnother op?\n1) yes\n2) no\n> ";
        std::cin >> i;
    }
    return 0;
}
