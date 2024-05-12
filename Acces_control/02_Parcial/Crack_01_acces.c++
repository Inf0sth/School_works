#include <iostream>
#include <cstdlib>

int main() {
    std::string exec = "./01_passkey | ./01_acces";
    int attemp = 1;
    do
    {
        system(exec.c_str());
        attemp++;
    } while (attemp <= 200);
}
