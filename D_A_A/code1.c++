#include <iostream>
#include <random>
#include <algorithm>
using namespace std;

int binary_search(int arr[], int num) {
    int left = 0;
    int right = 99;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == num)
            return mid;
        if (arr[mid] < num)
            left = mid + 1;
        else
            right = mid - 1;
    }
    return -1;
}

int lineal_search(int arr[], int num) {
    for (int i = 0; i < 100; i++) {
        if (arr[i] == num)
            return i;
    }
    return -1;
}

void see_array(int arr[]){
    for (int i = 0; i < 100; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
}

int main() {
    int num_int;
    int num_random;
    int j;
    int array_int[100] = {0};
    int array_random[100] = {0};
    int pos_int;
    int pos_random;
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dist(0, 1000);
    for (int i = 0; i < 100; i++) {
        j = dist(gen);
        array_random[i] = j;
    }
    for (int i = 1; i <= 100; i++) {
        array_int[i - 1] = i;
    }
    cout << "Array de enteros: \n";
    see_array(array_int);
    cout << "\nIngrese el numero a buscar en el array de enteros: \n";
    cin >> num_int;
    pos_int = lineal_search(array_int, num_int);

    if (pos_int != -1)
        cout << "Numero encontrado en el indice: " << pos_int << endl;
    else
        cout << "Numero no encontrado en el array de enteros\n";
    sort(array_random, array_random + 100);
    cout << "\nArray random ordenado: \n";
    see_array(array_random);
    cout << "\nIngrese el numero a buscar en el array random: \n";
    cin >> num_random;
    pos_random = binary_search(array_random, num_random);

    if (pos_random != -1)
        cout << "Numero encontrado en el indice: " << pos_random << endl;
    else
        cout << "Numero no encontrado en el array random\n";

    return 0;
}
