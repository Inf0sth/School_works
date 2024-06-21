#include <iostream>
#include <list>
#include <fstream>
#include <sstream>
#include <vector>
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

vector<string> split(const string& str, char delimiter) {
    vector<string> tokens;
    string token;
    istringstream tokenStream(str);
    while (getline(tokenStream, token, delimiter)) {
        tokens.push_back(token);
    }
    return tokens;
}

int main()
{
    list<Users> users;
    Users user, *localUser;
    int salir, menu, optUser;
    string username, tmp, user_root, file_user;
    ofstream fUser, ofRoot;
    ifstream ifRoot("root");
    Files tmpFile;

    string biosPass;
    cout << "Ingrese la contraseña maestra: ";
    cin >> biosPass;
    if (biosPass != "input")
    {
        return 2;
    }

    if (ifRoot.is_open())
    {
        // Cargar usuarios
        while (getline(ifRoot, user_root))
        {
            cout << "Cargando usuario: " << user_root << '\n';
            user.User = user_root;
            user.files.clear();

            ifstream ifUser(user_root + ".usr");
            while (getline(ifUser, file_user))
            {
                auto tokens = split(file_user, '|');
                if (tokens.size() == 2) {
                    tmpFile.Name = tokens[0];
                    string file_perm = tokens[1];

                    if (file_perm == " r")
                        tmpFile.perm = r;
                    else if (file_perm == " w")
                        tmpFile.perm = w;
                    else if (file_perm == " x")
                        tmpFile.perm = x;

                    user.files.push_back(tmpFile);
                }
            }
            cout << endl;
            users.push_back(user);
        }
        ifRoot.close();
    }
    else // No existe
    {
        // Crear archivo
        ofRoot.open("root");
        ofRoot.close();
    }

    do
    {
        cout << "MENU" << endl << "1) Listar usuarios" << endl << "2) Acceder" << endl;
        cin >> optUser;

        switch (optUser)
        {
            case 1:
                for (auto i : users)
                {
                    cout << "usuario_: " << i.User << endl;
                }
                break;

            case 2:
                cout << "Ingresa nombre de usuario:";
                cin >> username;

                localUser = nullptr;

                for (auto &i : users) // usar referencia para obtener el puntero correcto
                {
                    if (i.User == username)
                    {
                        localUser = &i;
                        cout << "El usuario existe" << endl;
                        break;
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
                    cout << "Opciones:" << endl << "\t1) Listar" << endl << "\t2) Crear" << endl << "\t3) Salir" << endl << "Selecciona una opcion:";
                    cin >> menu;

                    switch (menu)
                    {
                        case 1: // listar archivos
                            for (auto &i : localUser->files)
                            {
                                cout << i.Name << " - " << i.perm << " | ";
                            }
                            cout << endl << endl;
                            break;

                        case 2: // crear archivo
                            cout << "Ingresa archivo:";
                            cin >> tmp;
                            tmpFile.Name = tmp;
                            tmpFile.perm = x;
                            localUser->files.push_back(tmpFile);
                            break;

                        case 3: // salir
                            cout << "Salvando archivos del usuario y saliendo..." << endl;
                            break;

                        default:
                            cout << "Opción inválida" << endl;
                            break;
                    }
                } while (menu != 3);
                break;

            default:
                cout << "Opción inválida" << endl;
                break;
        }

        cout << "Quieres salir? (0:yes 1:no) :";
        cin >> salir;
        cout << endl;

    } while (salir == 1);

    // save files
    ofRoot.open("root");
    for (auto &i : users)
    {
        cout << "usuario_: " << i.User << endl;
        ofRoot << i.User << endl;
        fUser.open(i.User + ".usr"); // Crea Archivo
        cout << "Creado : " << i.User + ".usr" << endl;

        for (auto &j : i.files)
        {
            cout << "\t" << j.Name << " - " << j.perm << endl;
            fUser << j.Name << "|" << j.perm << endl; // Guarda el nombreArchivo y permiso en el archivo
        }

        cout << endl;
        fUser.close(); // Cierra archivo
    }
    ofRoot.close();
    cout << endl << endl;

    return 0;
}