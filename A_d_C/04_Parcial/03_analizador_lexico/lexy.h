#ifndef LEXY_H_INCLUDED
#define LEXY_H_INCLUDED

enum t_token { numero, suma, resta, eof, multiplicacion, division, error_lexico, fin_de_linea };

struct tokens
{
    char valor[10];
        int len;
    t_token token;
};

tokens lexyer ();

extern tokens lexyer ();
#endif // LEXY_H_INCLUDED