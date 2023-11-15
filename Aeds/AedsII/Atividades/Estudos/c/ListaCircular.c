#include <stdio.h>
#include <stdlib.h>


void inserir(int *lista, int elemento);
void remover(int *lista);
void mostrar(int *lista);
int tamanho=0, primeiro=0, ultimo=0, tam=6;
void inserir(int *lista, int elemento){
    if(((ultimo+1)%tam)==primeiro){                         // Usamos o %tam somente quando o ultimo chega na extremidade, caso contrario é mesma coisa de comparar ultimo==primeiro.
        remover(lista);
    }
    lista[ultimo] = elemento;
    ultimo = ((ultimo+1)%tam);
}

void remover(int *lista){
    if(primeiro==ultimo) printf("Erro\n");
    else{
        printf("Removido: %i\n", lista[primeiro]);
        primeiro = (primeiro+1)%tam;
    }
}

void mostrar(int *lista){
    for (int i=primeiro; i!=ultimo; i=(i+1)%tam){
        printf("%i/",lista[i]);
    }
}



int main(){
    int lista[tam];

    inserir(lista,1);
    inserir(lista,2);
    inserir(lista,3);
    inserir(lista,4);
    inserir(lista,5);
    inserir(lista,6);
    inserir(lista,7);
    inserir(lista,8);
    inserir(lista,9);

    mostrar(lista);
    return 0;
}