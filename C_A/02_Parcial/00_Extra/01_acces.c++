#include <iostream>

int main(){
    std::string key = "key123";
    std::string userkey;
    std::cout << "\nEnter the key: ";
    std::cin >> userkey;
    if (userkey == key){std::cout << "\nAcces granted!";}
    else{std::cout << "\nThat's not the key!!";}
    return 0;
}