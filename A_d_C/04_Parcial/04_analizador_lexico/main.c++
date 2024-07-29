#include <iostream>
#include "lexy.h"
using namespace std;


int main()
{
    tokens toky;

    cout << "Ingresa valores_: " << endl;

    while ((toky=lexyer()).token != eof)
    {
        cout << toky.valor << " " << token2string(toky.token) << endl;
    }

    return 0;
}