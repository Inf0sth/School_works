#include <stdio.h>
#include "lexy.h"

int matriz [9][17] =
{
    {1,1,1,1,1,1,1,1,1,1,3,4,5,6,7,8,8},
    {1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
};
const size_t alfabeto_len = 16;
const size_t estados_len = 9;

int alfabeto[alfabeto_len] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '/', '\0', '\n'};

int estado = 0;

int getAlfabeto (int a)
{
    size_t i;

    for (i = 0; i < alfabeto_len; i ++)
    {
        if (a == alfabeto[i])
            break;
    }

    //printf("alfabeto:%d", (int)i);
    return i;
}

enum t_token lexyer ()
{
    char c;
    estado = 0;

    while ((c=getc(stdin))!=EOF)
    {
        estado = matriz[estado][getAlfabeto(c)];

        switch(estado)
        {
        case 2:
            ungetc(c, stdin);
            printf("numero ");
            return numero;
        break;

        case 3:
            printf("suma ");
            return suma;
        break;

        case 4:
            printf("resta ");
            return resta;
        break;

        case 5:
            printf("multiplicacion ");
            return multiplicacion;
        break;

        case 6:
            printf("divicion ");
            return divicion;
        break;

        case 7:
            printf("error_lexico ");
            return error_lexico;
        break;

        case 8:
            printf("fin_de_linea ");
            return fin_de_linea;
        break;

        default:
            //printf("nani?!");
        break;
        }
    }
    return eof;
}