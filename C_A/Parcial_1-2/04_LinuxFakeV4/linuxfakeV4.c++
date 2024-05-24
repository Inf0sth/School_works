#include <iostream>
#include <list>

struct Users
{
    std::string User;
    std::list<std::string> Files;
};

int main(){
    std::list<Users> users;
    Users user, *localUser;
    std::string username;
    int salir, menu;
    std::string tmp;

    do
    {
        // Usar new para e implementar listas para cada usuario, inicializando ambos juntos.
        std::cout << "\nUsername: ";
        std:: cin >> username;

        localUser = nullptr;

        for (auto i : users)
        {
            if (i.User == username)
            {
                localUser = &i;
                std::cout << "User exists" << std::endl;
                break;
            }
        }
        
        if (localUser==nullptr)
        {
            user.User = username;
            user.Files.clear();
            users.push_back(user);
            localUser = &(users.back());
        }
        
        do
        {
            std::cout << "\nOpciones: \n\t1) Listar\n\t2) Crear\n\t3) Salir\n>>";
            std::cin >> menu;
            switch (menu)
            {
            case 1:
                for (auto i : localUser->Files){
                    std::cout << i << " - ";
                }
                std::cout << std::endl << std::endl;
                break;
            case 2:
                std::cout << "\nEnter file name: ";
                std::cin >> tmp;
                localUser->Files.push_back(tmp);
                break;
            case 3:
                break;
            default:
                break;
            }

        } while (menu != 3);
        
        users.push_back(user);

        std::cout << "\n\nQuieres salir? (1:Yes 2:No) : ";
        std::cin >> salir; 
    } while (salir != 1);

    for (auto i : users){
        std::cout << i.User << " - ";
    }
    std::cout << std::endl;
    return 0;
}