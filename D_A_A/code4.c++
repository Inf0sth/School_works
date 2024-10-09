#include <iostream>
#include <random>
using namespace std;

int main(){
  int option; // 1 asignacion
  int array[100] = {0}; // 1 asignacion
  int random_num; // 1 asignacion
  random_device rd; // 1 asignacion
  mt19937 gen(rd()); 
  uniform_int_distribution<> dist(0, 1000);
  for (int i = 0; i < 100; i++){ // 1 aignacion, 1 comparacion, 1 incremento
    random_num = dist(gen); // 1 asignacion
    array[i] = random_num; // 1 asignacion
  }
  cout << "Array unsorted: \n"; // 1 salida
  for (int i = 0; i < 100; i++){// 1 aignacion, 1 comparacion, 1 incremento
    cout << array[i] << "|"; // 1 salida
  }
  cout << endl; // 1 salida
  for (int i = 1; i < 100; i++) { // 1 aignacion, 1 comparacion, 1 incremento
    int key = array[i]; // 1 asignacion
    int j = i - 1; // 1 asignacion
    while (j >= 0 && array[j] > key) { // 2 comparacion
      array[j + 1] = array[j]; // 1 asignacion
      j = j - 1; // 1 asignacion
    }
    array[j + 1] = key; // 1 asignacion
  }
  while (option != 1 and option != 2){ // 2 comparación
    cout << "Choose an order: " << endl; // 1 salida
    cout << "1) From lowest to highest" << endl; // 1 salida
    cout << "2) From highest to lowest" << endl << ": "; // 1 salida
    cin >> option; // 1 asignación
    if (option == 0 or option > 2){ // 2 comparación
      cout << "Invalid option!!"; // 1 salida
    }
    cout << endl; // 1 salida
  }
  if (option == 1){ // 1 comparacion
    for(int i=0; i<100; i++){ // 1 aignacion, 1 comparacion, 1 incremento
      cout << array[i]<<"|"; // 1 salida
    }
  }
  else{
    for(int i=99;i>=0;i--){// 1 aignacion, 1 comparacion, 1 incremento
      cout<<array[i]<<"|"; // 1 salida
    }
  }
  return 0;
}
