#include <iostream>
#include <list>
using namespace std;

int main() {
    int i;
    list<int> myList;
    for (i = 1; i <= 100; i++) {
        myList.push_back(i);
    }
    for (int num : myList) {
        cout << num << " ";
    }
    return 0;
}
