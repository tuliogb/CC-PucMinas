#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

int tamanho = 0;
int comp=0,mov=0;
clock_t start,end;

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
            parteDados(jogador,&resp);
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

void mostrarTodos(Jogador *lista){
    for(int i=0;i<10;i++){
        mostraDados(&lista[i]);
    }
}

void troca(Jogador *lista,int x,int y){
    Jogador tmp = lista[x]; mov++;
    lista[x] = lista[y];
    lista[y] = tmp;
}

void ArqLog(){
    int matricula = 1441272;
    float tempo;
    tempo = (float)(end-start)/1000;

    FILE *file = fopen("matrícula_binaria.txt","w");
    fprintf(file,"%i\t%f\t%i\t%i",matricula,tempo,comp,mov);

}

void NomeEdata(Jogador *lista,int tam){
    for (int i=0;i<(tam-1);i++) {
      int menor=i;
      for (int j=(i+1);j<tam;j++){
         if (lista[menor].peso == lista[j].peso){
            if(strcmp(lista[menor].nome, lista[j].nome)>0) menor=j;
         }
      }
      troca(lista,menor,i);
   }
}




void construir(Jogador *array,int tamHeap){
    for(int i=tamHeap; i>1 && array[i].altura > array[i/2].altura;i/=2){
        troca(array,i,i/2);
    }
}

int getMaiorFilho(Jogador *array,int i,int tamHeap){
    int filho;
    if (2*i==tamHeap || array[2*i].altura > array[2*i+1].altura){
        filho=2*i;
    } else {
        filho=2*i+1;
    }
    return filho;
}

void reconstruir(Jogador *array, int tamHeap){
    int i=1;
    while(i<=(tamHeap/2)){
        int filho=getMaiorFilho(array,i,tamHeap);
        if(array[i].altura < array[filho].altura){
            troca(array,i,filho);
            i=filho;
        }else{
            i=tamHeap;
        }
    }
}

void heapsort(Jogador *array, int n){
    Jogador arrayTmp[n+1];
    for(int i=0;i<n;i++){
        arrayTmp[i+1] = array[i];
    }

    for(int tamHeap=2; tamHeap<=n;tamHeap++){
        construir(arrayTmp,tamHeap);
    }

    int tamHeap = n;
    while(tamHeap>1){
        troca(arrayTmp,1,tamHeap--);
        reconstruir(arrayTmp,tamHeap);
    }

    for(int i=0;i<n;i++){
        array[i] = arrayTmp[i+1];
    }
}





int main(){
    Jogador lista[500];
    InserirElementos(lista);

    heapsort(lista,tamanho);
    NomeEdata(lista,10);
    mostrarTodos(lista);

    return 0;
}

