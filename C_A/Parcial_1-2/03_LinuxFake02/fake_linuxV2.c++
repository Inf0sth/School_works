//Second version
#include <iostream>
#include <list>

struct Users
{
    std::string User;
    std::list<std::string> Files;
};

int main(){
    std::list<Users> users;
    Users user;
    user.User = "Hola";
    users.push_back(user);

    user.User = "Adios";
    users.push_back(user);

    for (auto i : users){
        std::cout << i.User << ' ';
    }
    return 0;
}