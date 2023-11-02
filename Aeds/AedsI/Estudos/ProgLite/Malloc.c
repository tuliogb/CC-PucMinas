#include <stdio.h>
#include <stdlib.h>

int main() {
    int* caixa = (int)malloc(sizeof(int));
    /*
    * Nesse caso estou guardado em caixa o endereco da posicao reservada;
    */  

    int** matriz = (int**)malloc(4*sizeof(int*));
    /*
    * Estou criando um array de 4 posicoes pra guardar ponteiros pra int
    * | . | . | . | . |     >>> cada '.' é um ponteiro que aponta pra um tipo int
    *  
    */
    matriz[0] = (int*)malloc(10 * sizeof(int));
    /*
    * | * | . | . | . |     >>> o primeiro ponteiro recebe o endereco de um array de 10 posicoes do tipo int
    */


    /*
          > |---|---|---|---|   (Array 2)
          |
          |
    +---+---+---+---+---+
    | * | * | * | * | * |                               representacao de um array de ponteiros pra array de inteiros
    +---+---+---+---+---+
      |  
      | 
      v   
    |---|---|---|---|   (Array 1)


    */
    
    return 0;
}