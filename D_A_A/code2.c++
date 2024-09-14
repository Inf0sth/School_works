#include <iostream>
#include <random>
using namespace std;

void bubblesort(int* data, int n){
  for ( int i=0; i<n; i++){ // 1 asignación, 1 comparación, 1 incremento
    for ( int i=0; i<n-1; i++){ // 1 asignación, 1 comparación, 1 incremento
      if ( data[i]>data[i+1]){ // 1 comparación
	int temp=data[i]; // 1 asignación
	data[i]=data[i+1]; // 1 asignación
	data[i+1]=temp; // 1 asignación
      }
    }
  }
}

int main(){
  int option = 0; // 1 asignación
  int array[100] = {0}; // 1 asignación
  int j; // 1 asignación
  random_device rd;
  mt19937 gen(rd());
  uniform_int_distribution<> dist(0, 1000);
  for (int i = 0; i < 100; i++){ // 1 asignación, 1 comparación, 1 incremento
    j = dist(gen); // 1 asignación
    array[i] = j; // 1 asignación
  }
  cout << "Array unsorted: \n"; // 1 salida
  for (int i = 0; i < 100; i++){ // 1 asignación, 1 comparación, 1 incremento
    cout << array[i] << "|"; // 1 salida
  }
  cout << endl;
  int n=sizeof(array)/sizeof(array[0]);
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
  bubblesort(array,n);
  if (option == 1){ // 1 comparación
    for ( int i=0; i<n;i++){ // 1 asignación, 1 comparación, 1 incremento
      cout <<array[i]<<"|"; // 1 salida
    }
  }
  else{
    for ( int i=99; i>=0;i--){ // 1 asignación, 1 comparación, 1 decremento
      cout <<array[i]<<"|"; // 1 salida
    }
  }
  return 0;
}
