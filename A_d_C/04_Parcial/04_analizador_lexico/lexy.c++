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

tokens lexyer ()
{
    char c;
    estado = 0;
    tokens token;
    int largo = 0;

    while ((c=getc(stdin))!=EOF)
    {
        largo ++;
        token.valor [largo-1] = c;
        token.valor [largo] = '\0';
        estado = matriz[estado][getAlfabeto(c)];

        switch(estado)
        {
        case 2:
            ungetc(c, stdin);
            token.valor [largo-1] = '\0';
            largo--;
            //printf("numero ");
            token.len=largo;
            token.token=numero;
            return token;
        break;

        case 3:
            //printf("suma ");
            token.len=largo;
            token.token=suma;
            return token;
        break;

        case 4:
            //printf("resta ");
            token.len=largo;
            token.token=resta;
            return token;
        break;

        case 5:
            //printf("multiplicacion ");
            token.len=largo;
            token.token=multiplicacion;
            return token;
        break;

        case 6:
            //printf("division ");
            token.len=largo;
            token.token=division;
            return token;
        break;

        case 7:
            //printf("error_lexico ");
            token.len=largo;
            token.token=error_lexico;
            return token;
        break;

        case 8:
            //printf("fin_de_linea ");
            token.len=largo;
            token.token=fin_de_linea;
            return token;
        break;

        default:
            //printf("nani?!");
        break;
        }
    }
    token.len=largo;
    token.token=eof;
    return token;
}

char* token2string (t_token t){
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