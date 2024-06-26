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
    std::string username;
    std::string filename;
    int salir, menu;

    do
    {
        // Usar new para e implementar listas para cada usuario, inicializando ambos juntos.
        std::cout << "\nUsername: ";
        std:: cin >> username;

        user.User = username;
        //user.Files = new file;
        
        do
        {
            std::cout << "\nOpciones: \n\t1) Listar\n\t2) Crear\n\t3) Salir\n>>";
            std::cin >> menu;
            switch (menu)
            {
            case 1:
                for (auto i : user.Files){
                    std::cout << i << " - ";
                }
                break;
            case 2:
                std::cout << "\nEnter file name: ";
                std::cin >> filename;
                user.Files.push_back(filename);
                break;
            case 3:
                break;
            default:
                break;
            }

        } while (menu != 3);
        
        users.push_back(user);
        
        for (auto i : users){
            std::cout << i.User << " - ";
        }

        std::cout << "\n\nQuieres salir? (1:Yes 2:No) : ";
        std::cin >> salir; 
    } while (salir != 1);
    
    return 0;
}