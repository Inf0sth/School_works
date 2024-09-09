#include <iostream>
#include <random>
using namespace std;

int main() {
    int k;
    int j;
    int array_int[100] = {0};
    int array_random[100] = {0};
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dist(0, 1000);
    for (int i = 0; i < 100; i++) {
        j = dist(gen);
        array_random[i] = j;
    }
    for (int i = 1; i <= 100; i++) {
      array_int[i-1] = i;
    }
    for (int i =0; i < 100; i++){
      cout << array_random[i] << " ";
    }
    cout << array_int;
    cout << array_random;
    return 0;
}
