#include <iostream>
#include <random>

int main(){
    std::string keypass;
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<> dis(100, 200);
    int i = dis(gen);
    keypass = "key"+i;
    std::cout << keypass;
}