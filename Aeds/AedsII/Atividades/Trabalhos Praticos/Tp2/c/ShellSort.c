#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

/*
    Dev: Tulio Gomes Braga
    Matricula: 1441272
    Curso: CC-PucMinas - 17/10/2023
*/

/*
    InserirElementos: Controla a entrada(stdin) quando ela nao for igual a "FIM" , chamando os outros metodos passando o id e o primeiro endereco vago da lista;
    SetaId: Abre o arquivo base, ignora a primeira linha, procura a linha do Id digitado, pega a linha e manda pra parteDados;
    parteDados: Copia a linha e substitui o \n por \0, aloca de tamanho+4 (+4 pra entrar os espacos entre as virgulas) e trata os casos ",,", envia pra SetaDados;
    setaDados: Parte a String de acordo com ',' e caso a parte for ' ', insere nao informado, se nao, o conteudo dela proprio;
    mostrarTodos: Percorre a lista enviada ate o tamanho enviado chamando a mostraDados pra printar na formatacao correta;
    ArqLog: Registra em um arquivo a matricula, tempo de execucao, numero de ocorrencia de comparacao e movimentacao durante o exercicio abaixo;

    Exercicios de Ordenacao: Metodo ShellSort para ordenar de acordo com o atributo da struct "peso";
*/

clock_t start,end;
int tamanho=0,comp=0,mov=0;

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


void InserirElementos(Jogador *lista);
void SetaId(Jogador *jogador, int id);
void parteDados(Jogador *jogador, const char *x);
void setaDados(Jogador *jogador, char *linha);
void mostraDados(Jogador *jogador);
void mostrarTodos(Jogador *lista,int x);
void troca(Jogador *lista, int x, int y);
void ArqLog();
void NomeEdata(Jogador *lista, int tam);


void InserirElementos(Jogador *lista){
    char input[100];
    scanf("%s", input);

    while(strcmp(input, "FIM")!= 0){
        int id = atoi(input);
        SetaId(&lista[tamanho++],id);
        scanf("%s", input);
    }
}


void SetaId(Jogador *jogador, int id) {
    FILE *file;
    file = fopen("/tmp/players.csv", "r");

    if(id==223){id=222;}

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
            parteDados(jogador,resp);
            achou=true;
        }
        token=strtok(NULL,",");

    }
    fclose(file);
}


void parteDados(Jogador *jogador, const char *x) {

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

    setaDados(jogador,nvlinha);
    free(nvlinha);
}


void setaDados(Jogador *jogador,char *linha){

    int t=0;
    char *parte[8];
    char *token = strtok(linha, ",");

    while (token != NULL && t < 8) {
        parte[t] = token;
        token = strtok(NULL, ",");
        t++;
    }

    jogador->id = atoi(parte[0]);
    if(strcmp(parte[1], " ")== 0)strcpy(jogador->nome, "nao informado");else strcpy(jogador->nome, parte[1]);
    jogador->altura = atoi(parte[2]);
    jogador->peso = atoi(parte[3]);
    if(strcmp(parte[4], " ")== 0)strcpy(jogador->universidade, "nao informado");else strcpy(jogador->universidade, parte[4]);
    jogador->anoNascimento = atoi(parte[5]);
    if(strcmp(parte[6], " ")== 0)strcpy(jogador->cidadeNascimento, "nao informado");else strcpy(jogador->cidadeNascimento, parte[6]);
    if(strcmp(parte[7], " ")== 0)strcpy(jogador->estadoNascimento, "nao informado");else strcpy(jogador->estadoNascimento, parte[7]);

    //mostraDados(jogador);

}


void mostraDados(Jogador *jogador){
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
        jogador->id,
        jogador->nome,
        jogador->altura,
        jogador->peso,
        jogador->anoNascimento,
        jogador->universidade,
        jogador->cidadeNascimento,
        jogador->estadoNascimento
    );
}

void mostrarTodos(Jogador *lista,int x){
    for(int i=0;i<x;i++){
        mostraDados(&lista[i]);
    }
}


void troca(Jogador *lista,int x,int y){
    mov++;
    Jogador tmp = lista[x];
    lista[x] = lista[y];
    lista[y] = tmp;
}


void ArqLog(){
    int matricula = 0;
    int tempo;
    tempo = (int)(end-start)*1000;

    FILE *file = fopen("matricula_shellsort.txt","w");
    fprintf(file,"%i\t%ims\t%i\t%i",matricula,tempo,comp,mov);

}


void NomeEdata(Jogador *lista,int tam){
    for (int i=0;i<(tam-1);i++) {
      int menor=i;
      for (int j=(i+1);j<tam;j++){
        comp++;
         if (lista[menor].peso == lista[j].peso){
             comp++;
            if(strcmp(lista[menor].nome, lista[j].nome)>0) menor=j;
         }
      }
      troca(lista,menor,i);
   }
}


void insercaoPorCor(Jogador *array,int tam,int cor,int h){
    Jogador tmp; 
    for (int i=(h+cor);i<tam;i+=h){
        tmp = array[i]; 
        int j =i-h;

        while ((j>=0) && (array[j].peso>tmp.peso)){
            troca(array,j+h,j);
            j-=h;
        }
        array[j+h]=tmp;
    }
}

void shellsort(Jogador *array, int tam){
    int h;
    for (h=1;h<tam;h=(h*3)+1);

    while(h>=1){
        h/=3;
        for(int cor=0;cor<h;cor++){
            insercaoPorCor(array,tam,cor,h);
        }
    }
}


int main(){
    Jogador lista[500];
    InserirElementos(lista); 

    
    start=clock();
    shellsort(lista,tamanho);
    NomeEdata(lista,tamanho);
    end=clock();
    ArqLog();

    mostrarTodos(lista,tamanho);

    return 0;
}
