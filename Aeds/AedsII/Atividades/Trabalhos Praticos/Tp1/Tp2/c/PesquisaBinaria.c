#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/*
    Dev: Tulio Gomes Braga
    Matricula: 1441272
    Curso: CC-PucMinas - 14/10/2023
*/


int tamanho = 0;

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
    for(int i=0;i<tamanho;i++){
        mostraDados(&lista[i]);
    }
}





void OrganizaArray(Jogador *lista,int x){
    if(x<tamanho){
        int menor=x;
        for(int j=x+1;j<tamanho;j++){
            if(strcmp(lista[menor].nome, lista[j].nome)>0) menor=j; // RETORNA 0 SE FOR IGUAL, NEGATIVO 1 MENOR, POSITIVO 1 MAIOR;
        }
        troca(lista,menor,x);
        OrganizaArray(lista,x+1);
    }
}


void troca(Jogador *lista,int x,int y){
    Jogador tmp = lista[x];
    lista[x] = lista[y];
    lista[y] = tmp;
}


bool PesquisaBinaria(Jogador *lista,char *x){
    bool resp=false;

    int esq=0, dir=tamanho-1, meio=0;

    while(esq<=dir){
        meio=(esq+dir)/2;
        if(strcmp(x,lista[meio].nome)==0){
            resp=true;
            esq=tamanho;
        }else if(strcmp(x,lista[meio].nome)>0) esq = meio+1;
        else dir=meio-1;
    }

    return resp;
}


void TemEsseNome(Jogador *lista){
    int c;
    while ((c = getchar()) != '\n' && c != EOF) { }

    char input[50];
    fgets(input, 50, stdin);
    input[strcspn(input, "\n")] = '\0';


    while(strcmp(input, "FIM")!= 0){
        if(PesquisaBinaria(lista,input)) printf("SIM\n");
        else printf("NAO\n");
        fgets(input, 50, stdin);
        input[strcspn(input, "\n")] = '\0';
    }
}



int main(){
    Jogador lista[500];
    InserirElementos(lista);
    OrganizaArray(lista,0);
    TemEsseNome(lista);
    return 0;
}
