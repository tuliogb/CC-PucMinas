#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int qtdCaixa=0, qtdPilha=0;


void mostrar(int matriz[][qtdPilha]){
    printf("\n");
    for(int i=0; i<qtdCaixa; i++){
        for(int x=0; x<qtdPilha; x++) printf("%i ", matriz[i][x]);
        printf("\n");
    }
    printf("\n");
}

void calculaDesempilhar(int matriz[][qtdPilha]){
    bool achou = false;
    int desempilhar=0, esquerda=0, direita=0, linha=0, coluna=0; // vao guardar a posicao do 1;

    for(int i=0; i<qtdCaixa && !achou; i++){    // acha a posicao da caixa um procurada
        for(int x=0; x<qtdPilha && !achou; x++){
            if(matriz[i][x]==1){
                linha=i;
                coluna=x;
                achou=true;
            }
        }
    }

    for(int z=coluna+1; z<qtdPilha; z++) if(matriz[linha][z]!=-1) desempilhar++;                        // quantidade acima 

    if(linha-1>=0) for(int z=coluna; z<qtdPilha; z++) if(matriz[linha-1][z]!=-1) esquerda++;            // quantidade a esquerda
    if(linha+1<qtdCaixa) for(int z=coluna; z<qtdPilha; z++) if(matriz[linha+1][z]!=-1) direita++;       // quantidade a direita

    if(esquerda>direita) desempilhar+=direita;                                                          // se a pilha direita for menor                           
    else if(esquerda<direita) desempilhar+=esquerda;                                                    // se a pilha esquerda for menor
    else desempilhar+=esquerda;                                                                         // desempate escolhendo esquerda

    printf("%i\n", desempilhar);
}

void setaPilha(){
    int linha=qtdCaixa, coluna=qtdPilha;
    int matriz[linha][coluna];

    for(int i=0; i<linha; i++){
        for(int x=0; x<coluna; x++) matriz[i][x]=-1;
    }

    int caixas=0;

    for(int i=0; i<qtdPilha; i++){
        scanf("%i", &caixas);
        for(int x=0; x<caixas; x++) scanf("%i", &matriz[i][x]);
    }
    calculaDesempilhar(matriz);
    //mostrar(matriz);
}

void lerDados(){
    while(scanf("%i %i", &qtdCaixa, &qtdPilha) && (qtdCaixa!=0 && qtdPilha!=0)){
        setaPilha(); 
    }
}


int main(){
    lerDados();
}