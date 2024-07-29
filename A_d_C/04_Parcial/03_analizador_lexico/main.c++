#include <iostream>
#include "lexy.h"
using namespace std;
string token2string (t_token t){
    switch(t){
        case numero:
        return "numero ";

        case suma:
        return "suma";

        case resta:
        return "resta";

        case multiplicacion:
        return "multiplicacion";

        case division:
        return "division";

        case fin_de_linea:
        return "fin_de_linea";

        case error_lexico:
        return "error_lexico";

        case eof:
        return "eof";
        
        default:
        return "undefined";

    }
}

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