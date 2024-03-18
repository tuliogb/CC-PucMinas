#include <stdio.h>
#include <stdlib.h>
#include <string.h>


typedef struct {
    char nome[20];
    int idade; 

} Doendes;

Doendes lista[30];

Doendes lider[10];
Doendes entregador[10];
Doendes piloto[10];

int qtdTimes = 0;
int doendes = 0;


void apresentaTimes(){
    int k=0; 
    for(int i=0; i<qtdTimes; i++,k++){
        lider[k] = lista[i];
    }
    k=0;
    for(int i=qtdTimes; i<(qtdTimes*2); i++,k++){
        entregador[k] = lista[i];
    }
    k=0;
    for(int i=qtdTimes*2; i<(qtdTimes*3); i++,k++){
        piloto[k] = lista[i];
    }


    for(int i=0; i<qtdTimes; i++){
        printf("Time %i\n%s %i\n%s %i\n%s %i\n", i+1,lider[i].nome, lider[i].idade, entregador[i].nome, entregador[i].idade, piloto[i].nome, piloto[i].idade);
        printf("\n");
    }
    
}

void swap(int x, int y){
    Doendes temp = lista[x];
    lista[x] = lista[y];
    lista[y] = temp;
}

void ordenaLista(){
    for(int i=0; i<doendes-1; i++){
        int menor=i;

        for(int j=i+1; j<doendes; j++){
            if(lista[j].idade > lista[menor].idade) menor=j; 
            else if(lista[j].idade == lista[menor].idade){ if( strcmp(lista[j].nome,lista[menor].nome)<0 ) menor=j; }
        }
        swap(menor,i);
    }
}

void lerEntrada(){
    scanf("%i",&doendes);
    getchar();
    qtdTimes = doendes/3;

    for(int i=0; i<doendes; i++){
        scanf("%s", lista[i].nome); 
        scanf("%i", &lista[i].idade);                              
        getchar();
    }

} //strcpy(nomes[tamM], input);



int main(){
    lerEntrada();
    ordenaLista();
    apresentaTimes();
}