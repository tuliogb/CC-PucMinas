#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

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

}Celula;

Celula* newCelula(Jogador elemento){
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;

    return nova;    
}

Celula* tabela[25];
int tamanho = 0;


void inserirElementos();
void startTabela();
void setaId(int id);
void parteDados(const char *linha);
void setaDados(char *linha);
void insereHash(Jogador jogador);
void mostraDados(Jogador jogador);
void mostraTudo();
void pesquisaElementos();
void pesquisaElemento(char *input);


void inserirElementos(){
    startTabela();
    char input[100];
    scanf("%s", input);

    while(strcmp(input, "FIM")!= 0){
        int id = atoi(input);
        setaId(id);
        scanf("%s", input);
    }
}

void startTabela(){
    for (int i=0; i<25; i++) {
        tabela[i] = NULL;
    }
}

void setaId(int id) {
    FILE *file;
    file = fopen("players.csv", "r");

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
    if(strcmp(parte[1], " ")== 0) strcpy(jogador.nome, "nao informado"); else strcpy(jogador.nome, parte[1]);
    jogador.altura = atoi(parte[2]);
    jogador.peso = atoi(parte[3]);
    if(strcmp(parte[4], " ")== 0) strcpy(jogador.universidade, "nao informado"); else strcpy(jogador.universidade, parte[4]);
    jogador.anoNascimento = atoi(parte[5]);
    if(strcmp(parte[6], " ")== 0) strcpy(jogador.cidadeNascimento, "nao informado"); else strcpy(jogador.cidadeNascimento, parte[6]);
    if(strcmp(parte[7], " ")== 0) strcpy(jogador.estadoNascimento, "nao informado"); else strcpy(jogador.estadoNascimento, parte[7]);

    //mostraDados(jogador);
    insereHash(jogador);
}

void insereHash(Jogador jogador){
    int posicao = (jogador.altura%25);

    if (tabela[posicao]==NULL) tabela[posicao] = newCelula(jogador);
    else {
        Celula* temp = tabela[posicao];
        while (temp->prox!=NULL){
            temp = temp->prox;
        }
        temp->prox = newCelula(jogador);
    }
}
 
void mostraDados(Jogador jogador){
    printf("[%i] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
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

void mostraTudo(){
    for (int i=0; i<25; i++){
        Celula* tmp = tabela[i];

        while (tmp!=NULL){
            mostraDados(tmp->elemento);
            tmp = tmp->prox;
        }
    }
}

void pesquisaElementos(){
    char input[100];
    gets(input);

    while(strcmp(input, "FIM")!= 0){
        pesquisaElemento(input);
        gets(input);
    }   
}

void pesquisaElemento(char *input){
    bool achou = false;

    int len = strlen(input);
    char linha[len];
    strcpy(linha,input);


    for (int i=0; i<25; i++){
        Celula* tmp = tabela[i];

        for (; tmp!=NULL; tmp=tmp->prox){
            if(strcmp(linha, tmp->elemento.nome) == 0) achou=true;
        }
    }

    if (achou) printf ("%s SIM\n", linha);
    else printf ("%s NAO\n", linha);
}


int main(){ 
    inserirElementos();
    //mostraTudo();
    pesquisaElementos();

    return 0;
}
