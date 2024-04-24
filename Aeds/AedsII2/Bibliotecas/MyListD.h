#include <stdio.h>
#include <stdlib.h>


typedef struct Celula{      // Cria uma estrutura Celula
    int elemento;
    struct Celula* ant;
    struct Celula* prox;
} Celula;                   // Gero um apelido para essa estrutura


Celula *primeiro, *ultimo;
int tam = 0;


Celula* newCelula(int elemento){
    Celula* nova = (Celula*)malloc(sizeof(Celula));
    nova->prox = NULL;
    nova->ant = NULL;
    nova->elemento = elemento;

    return nova; tam++;
}



void inserirInicio(int elemento){
    Celula *nova = newCelula(elemento);
    nova->prox = primeiro->prox;
    nova->ant = primeiro;
    primeiro->prox =  nova;

    if(primeiro != ultimo) nova->prox->ant = nova;
    else ultimo = nova;

    nova = NULL;
}

void inserirFim(int elemento){
    Celula *nova = newCelula(elemento);
    ultimo->prox = nova;
    ultimo->prox->ant = ultimo;
    ultimo = ultimo->prox;
    
    nova = NULL;
}

void inserir(int elemento, int posicao){    //CORRIGIR
    Celula *nova = newCelula(elemento);
    
    if(posicao==0) inserirInicio(elemento);
    else if(posicao==tam+1) inserirFim(elemento);
    else if(posicao>0 && posicao<tam+1){
        Celula* i; 
        for(int x=0; x<posicao-1; x++, i=i->prox);

        nova->prox = i->prox;
        nova->ant = i;
        i->prox =  nova;

        if(i == ultimo) ultimo = nova;
        else nova->prox->ant = nova;

        nova = NULL;
    }
}


void mostrarLista(){
    for(Celula* i=primeiro->prox; i!=NULL; i=i->prox){
        printf("%i, ", i->elemento);
    }
}


