#include <iostream>
#include <list>
#include <fstream>
using namespace std;
 
struct Users{
    string User;
    list<string> Files;
};
 
int main()
{
    list<Users> users;
    Users user, *localUser;
    int salir, menu, optUser;
    string username, tmp, user_root, file_user;
    ofstream fUser, ofRoot;
    ifstream ifRoot ("root");

    string biosPass;
    cout << "Ingresar master password: ";
    cin >> biosPass;
    if (biosPass != "input") {
      return 2;
    }
    if( ifRoot.is_open() ) // existe
    {
        // Cargar usuarios
         while ( getline (ifRoot,user_root) )
        {
            cout << "Loading user: " << user_root << '\n';
            user.User = user_root;
            user.Files.clear();
            ifstream ifUser (user_root+".usr");
            while (getline (ifUser, file_user)) {
              cout << file_user << " - ";
              user.Files.push_back(file_user);
            }
            cout << endl;
            users.push_back(user);
 
        }
        ifRoot.close();
    }
    else // No existe
    {
        // Crear archivo
        ofRoot.open ("root");
        ofRoot.close(); 
    }
 
    do
    {
        cout << "MENU" << endl << "1) Listar"<<endl<<"2) Login user" <<endl;
        cin >> optUser;
 
        switch(optUser)
        {
            case 1:
            for (auto i : users)
            {
                cout << "user_: " << i.User << endl;
            }
            break;
 
            case 2:
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
            break;
        }
 
 
        cout << "Quieres salir? (0:yes 1:no) :" ;
        cin >> salir;
        cout << endl;
 
    } while (salir == 1);
 
    // save files
    ofRoot.open ("root");
    for (auto i : users)
    {
        cout << "user_: " << i.User << endl;
        ofRoot << i.User << endl;
        fUser.open(i.User+".usr"); // Crea Archivo
 
        for(auto j : i.Files)
        {
            cout << "\t" << j << endl;
            fUser << j << endl; //  Guarda el nombreArchivo en el archivo
        }
 
        cout << endl;
        fUser.close(); // Cierra archivo
    }
    ofRoot.close();
    cout << endl << endl;
 
    return 0;
}
