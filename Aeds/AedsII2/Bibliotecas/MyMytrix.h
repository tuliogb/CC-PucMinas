#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct Celula{
    int elemento;
    struct Celula* sup;
    struct Celula* esq;
    struct Celula* dir;
    struct Celula* inf;
} Celula;

Celula* newCelula(int elemento){
    Celula* nova = (Celula*)malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->sup = NULL;
    nova->dir = NULL;
    nova->esq = NULL;
    nova->inf = NULL;

    return nova;
}

typedef struct Matriz{
    int linha;
    int coluna;
    Celula* inicio;
}Matriz;


Matriz* start(int l, int c){
    Celula* inicio = newCelula(0);
    Celula *linha = inicio, *coluna = inicio;

    for(int col=1; col<c; col++, linha=linha->dir){
        linha->dir = newCelula(0); 
        linha->dir->esq = linha; 
    } 
    linha = inicio;

    for(int lin=l; lin>1; lin--){
        Celula* tempLinha = linha;
        coluna = linha->inf = newCelula(0);

        for(int col=0; col<c-1; col++, coluna=coluna->dir){
            coluna->dir = newCelula(0);
            coluna->dir->esq = coluna;
        }
        coluna = linha->inf;

        for(int col=0; col<c; col++, linha=linha->dir, coluna=coluna->dir){
            linha->inf = coluna;
            coluna->sup = linha;
        }

        linha = tempLinha->inf;
        coluna = linha->inf;
    }

    Matriz* x = (Matriz*)malloc(sizeof(Matriz));
    x->inicio = inicio;
    x->linha = l;
    x->coluna = c;

    return x;
}

void setaValores(int* array, Matriz* x){
    int k = 0;
    Celula* linhaAtual = x->inicio;
    Celula* colunaAtual = x->inicio;

    while (linhaAtual != NULL) {
        colunaAtual = linhaAtual;

        while (colunaAtual != NULL) {
            colunaAtual->elemento = array[k];
            colunaAtual = colunaAtual->dir; 
            k++;
        }
        linhaAtual = linhaAtual->inf;
    }
}

void mostrar(Matriz* x){
    Celula* linhaAtual = x->inicio;
    Celula* colunaAtual = x->inicio;

    printf("\nMatriz %ix%i: \n", x->linha, x->coluna);

    while (linhaAtual != NULL) {
        colunaAtual = linhaAtual;

        while (colunaAtual != NULL) {
            printf("%d ", colunaAtual->elemento);
            colunaAtual = colunaAtual->dir; 
        }
        printf("\n");
        linhaAtual = linhaAtual->inf;
    }
}

float media(Matriz* x){
    int soma = 0, k = 0;
    Celula* linhaAtual = x->inicio;
    Celula* colunaAtual = x->inicio;

    while(linhaAtual!=NULL){
        linhaAtual = colunaAtual;

        while(colunaAtual!=NULL){
           soma=soma+colunaAtual->elemento; 
           colunaAtual = colunaAtual->dir;
           k++;
        }

        linhaAtual=linhaAtual->inf;
    }
    return soma/k;
}

Matriz* transposicao(Matriz* x){
    Matriz* nova = start(x->coluna, x->linha);

    Celula* linhax = x->inicio;
    Celula* colunax = x->inicio;

    Celula* linhaNova = nova->inicio;
    Celula* colunaNova = nova->inicio;
    

    while(linhaNova!=NULL){
        colunaNova = linhaNova;
        linhax = colunax;

        while(colunaNova!=NULL){
            colunaNova->elemento = linhax->elemento;

            colunaNova = colunaNova->dir;
            linhax = linhax->inf;
        }

        linhaNova = linhaNova->inf;
        colunax = colunax->dir;
    }

    return nova;
}

Matriz* soma(Matriz* um, Matriz* dois){
    Matriz* nova = start(um->linha, um->coluna);;

    Celula *colum = um->inicio, *coldois = dois->inicio;
    Celula *linhaum = um->inicio, *linhadois = dois->inicio;

    if(um->coluna == dois->coluna && um->linha == dois->linha){
        Celula *colnova = nova->inicio;
        Celula *linhanova = nova->inicio;

        while(linhaum!=NULL){
            colum = linhaum;
            coldois = linhadois;
            colnova = linhanova;

            while(colum!=NULL){
                colnova->elemento = colum->elemento + coldois->elemento;

                colum = colum->dir;
                coldois = coldois->dir;
                colnova = colnova->dir;
            }

            linhaum = linhaum->inf;
            linhadois = linhadois->inf;
            linhanova = linhanova->inf;
        }
    }
    return nova;
}

Matriz* multiplicacao(Matriz* um, Matriz* dois){
    Matriz *nova = start(um->linha, dois->coluna);

    Celula *inicioum = um->inicio, *iniciodois = dois->inicio;
    Celula *colum = um->inicio, *coldois = dois->inicio;
    Celula *linhaum = um->inicio, *linhadois = dois->inicio;

    if(um->coluna == dois->linha){
        Celula *inicionova = nova->inicio;
        Celula *colnova = nova->inicio;
        Celula *linhanova = nova->inicio;

        while(linhaum!=NULL){
            
            while(coldois!=NULL){

                while(colum!=NULL){
                    colnova->elemento = colnova->elemento + (colum->elemento * linhadois->elemento);
                    colum = colum->dir; 
                    linhadois->inf;
                }
                colum = linhaum;
                coldois = coldois->dir;
                linhadois = coldois;
                colnova = colnova->dir;
            }

            colnova = linhanova = inicionova->inf;
            colum  = linhaum = inicioum->inf;
            coldois = linhadois = iniciodois->inf;

            inicionova = inicionova->inf;
            inicioum = inicioum->inf;
            iniciodois = iniciodois->inf;
        }
    }
    return nova;
}