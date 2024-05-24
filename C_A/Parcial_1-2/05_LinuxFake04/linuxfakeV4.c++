#include <iostream>
#include <list>
using namespace std;

struct Users{
    string User;
    list<string> Files;
};

int main()
{
    list<Users> users;
    Users user, *localUser;
    string username;
    int salir, menu;
    string tmp;

    do
    {
        cout << "Ingresa username:" ;
        cin >> username;

        localUser = nullptr;

        for (auto i : users)
        {
            if( i.User == username )
            {
                localUser = &i;
                cout << "User exists" << endl;
                break;
                //return 1;
            }
        }

        if (localUser == nullptr)
        {
            user.User = username;
            user.Files.clear();

            users.push_back(user);
            localUser = &(users.back());
        }        
        
        do
        {
            cout << "Opciones:" << endl << "\t1) Listar"<<endl<<"\t2) Crear" << endl << "\t3) Salir" << endl << "Selecciona una opcion:";
            cin >> menu;

            switch(menu)
            {
                case 1: // listar
                    cout << "01" << endl;

                    for (auto i : localUser->Files)
                    {
                        cout << i << " - ";
                    }
                    cout << endl << endl;
                break;

                case 2: // crear
                    cout << "02" << endl;
                    cout << "Ingresa archivo:" ;
                    cin >> tmp;
                    localUser->Files.push_back(tmp);
                break;

                case 3: // salir
                default:
                    cout << "03" << endl;
                break;
            }
        } while (menu != 3);

        //users.push_back(user);

        cout << "Quieres salir? (0:yes 1:no) :" ;
        cin >> salir;
        cout << endl;

    } while (salir == 1);
    
    // display list
    for (auto i : users)
    {
        cout << i.User << " - ";

        // Mostrar todos los archivos
    }
    cout << endl << endl;

    return 0;
}