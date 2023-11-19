#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

/*
    Dev: Tulio Gomes Braga
    Matricula: 1441272
    Curso: CC-PucMinas - 14/11/2023
*/

typedef struct {
    int id;
    int altura;
    int peso;
    int anoNascimento;
    char nome[100];
    char universidade[100];
    char cidadeNascimento[100];
    char estadoNascimento[100];

} Jogador;

typedef struct Celula {
    Jogador elemento;
    struct Celula* prox;
    struct Celula* ant; 

}Celula;

Celula* newCelula(Jogador elemento){
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    nova->ant=NULL;

    return nova;    
}


Celula* primeiro; Celula* ultimo;
int tamanho = 0, movimentacoes, comparacoes;
clock_t start,end;

void inserirElementos();
void setaId(int id);
void parteDados(const char *linha);
void setaDados(char *linha);
void mostraDados(Jogador jogador);
void mostrarTodos();
void orgElementos();
void quicksort(Celula* esq, Celula* dir);
void orgNome(int tam);
void swap(Celula* i, Celula* j);
void ArqLog();


void inserirElementos(){
    char input[100];
    scanf("%s", input);

    while(strcmp(input, "FIM")!= 0){
        int id = atoi(input);
        setaId(id);
        scanf("%s", input);
    }
}

void setaId(int id) {
    FILE *file;
    file = fopen("/tmp/players.csv", "r");

    char linha[100];
    char resp[100];
    bool achou = false;
    char *token;

    fgets(linha, sizeof(linha), file);

    while (fgets(linha, sizeof(linha), file) != NULL && !achou){
        strcpy(resp,linha);
        token = strtok(linha,",");
        int num = atoi(token);

        if(id==num){
            parteDados(resp);
            achou=true;
        }
        token=strtok(NULL,",");

    }
    fclose(file);
}

void parteDados(const char *x) {

    int len = strlen(x);
    char linha[len];
    strcpy(linha,x);

    linha[len-1]='\0';
    int j = 0;
    char *nvlinha = malloc(len + 4);


    for (int i = 0; i < len; i++) {
        if (linha[i]==',' && linha[i+1]==',') {
            nvlinha[j]=linha[i];
            nvlinha[j+1]=' ';
            nvlinha[j+2]=',';
            j+=3;
            i++;
        } else {
            nvlinha[j] = linha[i];
            j++;
        }
    }

    if(nvlinha[j-2]==','){
        nvlinha[j-1]=' ';
    }
    nvlinha[j]='\0';

    setaDados(nvlinha);
    free(nvlinha);
}

void setaDados(char *linha){
    Jogador jogador;

    int t=0;
    char *parte[8];
    char *token = strtok(linha, ",");

    while (token != NULL && t < 8) {
        parte[t] = token;
        token = strtok(NULL, ",");          //MANTEM UM ESTADO INTERNO PARA RASTREAR A POSICAO ATUAL NA STRING
        t++;
    }

    jogador.id = atoi(parte[0]);
    if(strcmp(parte[1], " ")== 0)strcpy(jogador.nome, "nao informado");else strcpy(jogador.nome, parte[1]);
    jogador.altura = atoi(parte[2]);
    jogador.peso = atoi(parte[3]);
    if(strcmp(parte[4], " ")== 0)strcpy(jogador.universidade, "nao informado");else strcpy(jogador.universidade, parte[4]);
    jogador.anoNascimento = atoi(parte[5]);
    if(strcmp(parte[6], " ")== 0)strcpy(jogador.cidadeNascimento, "nao informado");else strcpy(jogador.cidadeNascimento, parte[6]);
    if(strcmp(parte[7], " ")== 0)strcpy(jogador.estadoNascimento, "nao informado");else strcpy(jogador.estadoNascimento, parte[7]);

    //mostraDados(jogador);
    if(tamanho!=0){
        ultimo->prox = newCelula(jogador);
        ultimo->prox->ant = ultimo;
        ultimo = ultimo->prox;
        tamanho++;
    }else{
        primeiro = newCelula(jogador);
        ultimo = primeiro;
        tamanho++;
    }
}

void mostraDados(Jogador jogador){
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
        jogador.id,
        jogador.nome,   
        jogador.altura,
        jogador.peso,
        jogador.anoNascimento,
        jogador.universidade,
        jogador.cidadeNascimento,
        jogador.estadoNascimento    
    );
}

void mostrarTodos(){
    Celula* i;
    for(i=primeiro; i!=NULL; i=i->prox){
        mostraDados(i->elemento);
    }
}

void orgElementos(){
    quicksort(primeiro,ultimo);
    orgNome(tamanho);
}

void quicksort(Celula* esq, Celula* dir) {
    if (esq != NULL && dir != NULL && esq != dir && dir->prox != esq) {
        Celula* pivo = esq;
        Celula* i = esq->prox;
        Celula* j = dir;

        while (j->prox != i && j->prox != i->ant) {
            while (i != NULL && strcmp(i->elemento.estadoNascimento, pivo->elemento.estadoNascimento) < 0) {
                i = i->prox;
                comparacoes++;
            }
            while (strcmp(j->elemento.estadoNascimento, pivo->elemento.estadoNascimento) > 0) {
                j = j->ant;
                comparacoes++;
            }
            if (j->prox != i){
                swap(i, j);
                if (i->prox != NULL) i = i->prox;
                if (j->ant != NULL) j = j->ant;
            }
        }
        swap(pivo, j);

        if (j != esq) quicksort(esq, j->ant);
        if (j != dir) quicksort(j->prox, dir);
    }
}

void orgNome(int tam) {
    for (Celula* i = primeiro; i->prox != NULL; i = i->prox) {
        Celula* menor = i;
        for (Celula* j = i->prox; j != NULL; j = j->prox) {
            if (strcmp(i->elemento.estadoNascimento, j->elemento.estadoNascimento) == 0) {
                comparacoes++;
                if (strcmp(menor->elemento.nome, j->elemento.nome) > 0) {
                    menor = j;
                }
            }
        }
        swap(menor, i);
    }
}

void swap(Celula* i, Celula* j) {
    Jogador tmp = i->elemento;
    i->elemento = j->elemento;
    j->elemento = tmp;
    movimentacoes += 3; 
}

void ArqLog(){
    int matricula = 1441272;
    float tempo;
    tempo = (float)(end-start)*1000;

    FILE *file = fopen("matrícula_quicksort.txt","w");
    fprintf(file,"Matricula: %i\tTempo: %f\tComparacoes: %i\tMovimentacoes: %i",matricula, tempo, comparacoes, movimentacoes);
}


int main(){ 
    inserirElementos();
    orgElementos();
    mostrarTodos();
    ArqLog();

    return 0;
}