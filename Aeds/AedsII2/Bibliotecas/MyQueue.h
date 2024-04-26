#include <stdio.h>
#include <stdlib.h>


typedef struct Celula{      
    int elemento;
    struct Celula* ant;
    struct Celula* prox;
} Celula;                   


Celula *primeiro, *ultimo;
int tam = -1;


Celula* newCelula(int elemento){
    Celula* nova = (Celula*)malloc(sizeof(Celula));
    nova->prox = NULL;
    nova->ant = NULL;
    nova->elemento = elemento;

    tam++;
    return nova;
}

void inserir(int elemento){
    ultimo->prox = newCelula(elemento);
    ultimo = ultimo->prox;
}

void inserirSemNo(int elemento){
    if(ultimo==NULL){
        primeiro = ultimo = newCelula(elemento);
    }
    else{
        ultimo->prox = newCelula(elemento);
        ultimo = ultimo->prox;
    }
}

void remover(){
    Celula* tmp = primeiro;
    primeiro = primeiro->prox;
    free(tmp);
    tam--;
}

void removerSemUltimo(){
    Celula* i, *tmp;
    for(i=primeiro; i->prox->prox!=NULL; i=i->prox);
    tmp = i->prox;
    i->prox = NULL;
    free(tmp);
}

void removerLiteral(){
    Celula* tmp = primeiro->prox;
    primeiro->prox = primeiro->prox->prox;
    free(tmp);
    tam--;
}

void mostrar(){
    for(Celula* i=primeiro->prox; i!=NULL; i=i->prox){
        printf("%i, ", i->elemento);
    }
}

int maior(){
    int maior = primeiro->prox->elemento;
    for(Celula* i=primeiro->prox; i!=NULL; i=i->prox){
        if(i->elemento > maior) maior = i->elemento;
    }
    return maior;
}

void inverter(){
    Celula *i,*j;
    i = j = primeiro;
    int k=0;

    for(int x=0; x<tam/2; x++){
        j = primeiro;
        for(k=0; k<tam-x; k++, j=j->prox);
        i=i->prox;

        int tmp = j->elemento;
        j->elemento = i->elemento;
        i->elemento = tmp;

        k=0;
    }
}

Celula* toFila(Celula* topo){
    Celula* i = topo;

    while(i!=NULL){
        inserir(i->elemento);
    }

    return primeiro;
}



