#include <iostream>
#include <list>
#include <fstream>
using namespace std;

enum Perm {r, w, x}; // r=0 , w=1, x=2

struct Files{
    string Name;
    Perm perm;
};

struct Users{
    string User;
    list<Files> files;
};

int main()
{
    list<Users> users;
    Users user, *localUser;
    int salir, menu, optUser;
    string username, tmp, user_root, file_user;
    ofstream fUser, ofRoot;
    ifstream ifRoot ("root");
    Files tmpFile;

    string biosPass;
    cout << "Ingresar master password_: ";
    cin >> biosPass;
    if( biosPass != "input")
    {
        return 2;
    }

    if( ifRoot.is_open() ) // existe
    {
        // Cargar usuarios
        while ( getline (ifRoot,user_root) )
        {
            cout << "Loading user: " << user_root << '\n';
            user.User = user_root;
            user.files.clear();

            ifstream ifUser (user_root+".usr");
            while ( getline (ifUser, file_user) )
            {
                cout << file_user << " - " ;
                
                tmpFile.Name = file_user;
                tmpFile.perm = r;
                user.files.push_back(tmpFile);
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
        cout << "MENU" << endl << "1) Listar users"<<endl<<"2) Login" <<endl;
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
                    user.files.clear();

                    users.push_back(user);
                    localUser = &(users.back());
                }        
                
                do
                {
                    cout << "Opciones:" << endl << "\t1) Listar"<<endl<<"\t2) Crear" << endl << "\t3) Salir" << endl << "Selecciona una opcion:";
                    cin >> menu;

                    switch(menu)
                    {
                        case 1: // listar archivos
                            for (auto i : localUser->files)
                            {
                                cout << i.Name << " - " << i.perm << " | ";
                            }
                            cout << endl << endl;
                        break;

                        case 2: // crear archivo
                            //cout << "02" << endl;
                            cout << "Ingresa archivo:" ;
                            cin >> tmp;
                            tmpFile.Name = tmp;
                            tmpFile.perm = r;
                            localUser->files.push_back(tmpFile);
                        break;

                        case 3: // salir
                        default:
                            cout << "03" << endl;

                            for (auto i : users)
                            {
                                cout << "user_: " << i.User << endl;
                                
                                for(auto j : i.files)
                                {
                                    cout << "\t" << j.Name << " - " << j.perm << endl;
                                }
                                
                                cout << endl;
                        break;

                        case 4: // salir
                        default:
                            cout << "04" << endl;
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
        cout << "Created : " << i.User+".usr" << endl;
        
        for(auto j : i.files)
        {
            cout << "\t" << j.Name << " - " << j.perm << endl;
            fUser << j.Name << endl; //  Guarda el nombreArchivo en el archivo
        }

        cout << endl;
        fUser.close(); // Cierra archivo
    }
    ofRoot.close();
    cout << endl << endl;

    return 0;
}