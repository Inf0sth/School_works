#include <iostream>
#include <fstream>
#include <vector>
#include <string>
using namespace std;       

#define DELIM ','          

struct Registro {            
    string fecha, hora, usuario, ip, estado, tipoOperacion, directorio; 
};

class Archivo {            
    string nombre_arch = "actividad_servidor_AMERIKE.txt";
    vector<Registro> registros;
    void quicksort(int inicio, int fin);
    int particion(int inicio, int fin);
public:
    void cargarRegistros();
    void mostrar();   
    void ordenar();   
};

void Archivo::cargarRegistros() {
    registros.clear();
    string aux, total; 
    fstream arch(nombre_arch, ios::in); 
    if (arch.good()) { 
        while (getline(arch, aux)) { 
            Registro reg;
            int campo = 0; 
            total = ""; 
            for (unsigned int i = 0; i < aux.size(); i++) { 
                if (aux[i] != DELIM) { 
                    total += aux[i]; 
                } else { 
                    campo++; 
                    switch (campo) { 
                        case 1: reg.fecha = total; break;
                        case 2: reg.hora = total; break;
                        case 3: reg.usuario = total; break;
                        case 4: reg.ip = total; break;
                        case 5: reg.estado = total; break;
                        case 6: reg.tipoOperacion = total; break;
                        case 7: reg.directorio = total; break;
                    }
                    total = ""; 
                }
            }
            registros.push_back(reg); 
        }
    } else {
        cout << "Error al abrir el archivo\n"; 
    }
    arch.close(); 
}

void Archivo::mostrar() {
    for (const auto& reg : registros) {
        cout << "\nfecha: " << reg.fecha
             << "\tHora: " << reg.hora 
             << "\tUsuario: " << reg.usuario 
             << "\tIP: " << reg.ip 
             << "\tEstado: " << reg.estado 
             << "\tTipo de Operación: " << reg.tipoOperacion 
             << "\tDirectorio: " << reg.directorio; 
    }
}

int Archivo::particion(int inicio, int fin) {
    string pivot = registros[fin].tipoOperacion;
    int i = inicio - 1;
    for (int j = inicio; j < fin; j++) {
        if (registros[j].tipoOperacion < pivot) {
            i++;
            swap(registros[i], registros[j]);
        }
    }
    swap(registros[i + 1], registros[fin]);
    return i + 1;
}

void Archivo::quicksort(int inicio, int fin) {
    if (inicio < fin) {
        int pivote = particion(inicio, fin);
        quicksort(inicio, pivote - 1);
        quicksort(pivote + 1, fin);
    }
}

void Archivo::ordenar() {
    cargarRegistros();
    quicksort(0, registros.size() - 1);
    cout << "\nDatos ordenados por tipo de operación:\n";
    mostrar();
}

int main() { 
    Archivo a1; 
    int op = 0; 
    while (op != 3) { 
        cout << "\n1.- Mostrar\n2.- Ordenar\n3.- Salir\n"; 
        cin >> op; 
        switch (op) { 
            case 1: 
                a1.cargarRegistros();
                a1.mostrar(); 
                break;
            case 2: 
                a1.ordenar();
                break;
            case 3: 
                return 0; 
        }
    }
    return 0; 
}
