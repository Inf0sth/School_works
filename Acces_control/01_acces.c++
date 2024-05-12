#include <iostream>

int main(){
    std::string key = "key123";
    std::string userkey;
    std::cout << "Enter the key: ";
    std::cin >> userkey;
    if (userkey == key){std::cout << "Acces granted!";}
    else{std::cout << "That's not the key!!";}
    return 0;
}