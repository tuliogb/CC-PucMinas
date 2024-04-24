#include <stdio.h>
#include <stdlib.h>


typedef struct Celula{
    int elemento;
    struct Celula* prox;                // Porque se eu tirar o struct da erro ?
} Celula;

Celula* topo = NULL;
int tam = 0;

Celula* newCelula(int elemento){
    Celula* nova = (Celula*)malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    tam++;
    return nova; 
}


void inserir(int elemento){
    Celula* nova = newCelula(elemento);
    nova->prox = topo;
    topo = nova;
    nova = NULL;
}

void remover(){
    Celula* tmp = topo;
    topo = topo->prox;
    tmp->prox = NULL;
    free(tmp); tam--;
}

void mostrar(){
    printf("[ ");
    for(Celula* i=topo; i!=NULL; i=i->prox){
        printf("%i ", i->elemento);
    }
    printf("]\n");
}

void mostrarInverso(){
    int k=0;
    printf("[ ");

    for(int x=0; x<tam; x++){
        Celula* i=topo;
        for(k=0; k<tam-1-x; k++) i=i->prox;
        printf("%i ", i->elemento);
    }
    printf("]\n");
}

int soma(){
    int soma = 0;

    for(Celula* i=topo; i!=NULL; i=i->prox){
        soma=soma+i->elemento;
    }
    return soma;
}

float media(){
    return (float)soma()/tam;
}

int maiorElemento(int maior, Celula* i){
    if(i!=NULL){
        if(i->elemento > maior) maior = i->elemento;
        maior = maiorElemento(maior, i->prox);
    }
    return maior;
}

int maior(){
    return maiorElemento(0, topo);
}


