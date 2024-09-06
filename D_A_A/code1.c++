#include <iostream>
#include <list>
#include <random>
using namespace std;

int main() {
    int i;
    int j;
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dist(0, 100);
    list<int> myList;
    for (i = 1; i <= 1000; i++) {
        j = dist(gen);
        myList.push_front(j);
    }
    for (int num : myList) {
        cout << num << " ";
    }
    return 0;
}
