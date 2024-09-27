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
  for (int i = 0; i < 100 - 1; i++){// 1 aignacion, 1 comparacion, 1 ipncremento
    int min_idx = i; // 1 asignacion
    for (int j = i + 1; j < 100; j++) {// 1 aignacion, 1 comparacion, 1 incremento
      if (array[j] < array[min_idx]) { // 1 comparacion
	min_idx = j; // 1 asignacion
      }
    }
    int temp = array[i]; // 1 asignacion
    array[i] = array[min_idx]; // 1 asignacion
    array[min_idx] = temp; // 1 asignacion
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
