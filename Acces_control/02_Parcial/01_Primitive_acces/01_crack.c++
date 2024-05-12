#include <iostream>
#include <cstdlib>
#include <thread>
#include <chrono>

int main() {
    int i = 100;
    while (i <= 200){
        std::string exec = "echo \"key"+std::to_string(i)+"\""+"| ./01_acces";
        std::cout << "Password: key" << i-1 << std::endl;
        system(exec.c_str());        
        i++;
        std::this_thread::sleep_for(std::chrono::seconds(1));
    }
    return 0;
}
