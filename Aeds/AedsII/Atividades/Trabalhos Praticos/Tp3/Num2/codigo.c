#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/*
    Dev: Tulio Gomes Braga
    Matricula: 1441272
    Curso: CC-PucMinas - 13/10/2023
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


void inserirElementos(Jogador *lista);
void setaId(Jogador *jogador, int id);
void parteDados(Jogador *jogador, const char *linha);
void mostraDados(Jogador *jogador);
void mostrarTodos(Jogador *lista);
void manipulaElementos(Jogador *lista);



void inserirElementos(Jogador *lista){
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
        token = strtok(NULL, ",");          //MANTEM UM ESTADO INTERNO PARA RASTREAR A POSICAO ATUAL NA STRING
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

void manipulaElementos(Jogador *lista){
    int qtd; scanf("%i",&qtd);
    char entrada[20];

    while (getchar() != '\n');

    for (int i=0;i<qtd;i++){
        fgets(entrada, sizeof(entrada), stdin);                     // O FGETS COLOCA UM "\N" NO FINAL DA STRING;
        decodifica(lista,entrada);
    }
}

void decodifica(Jogador *lista,char *entrada){
    int len = strlen(tamanho);
    if (len>0 && entrada[len-1]=='\n'){
        entrada[len-1]='\0';
    }

    char *token = strtok(entrada," ");
    if(token!=NULL){
        if (strcmp(token,"II")==0){     printf("Caso II\n");     }
        else if(strcmp(token, "IF")==0){    printf("Caso IF\n");    }
        else if(strcmp(token,"I*")==0){     printf("Caso I*\n");    }
        else if(strcmp(token,"R*")==0){     printf("Caso R*\n");    }
        else{
            if(strcmp(entrada, "RI")==0){    printf("Caso RI\n");    }
            else if(strcmp(entrada,"RF")==0){     printf("Caso RF\n");    }
        }
    }
}



int main(){
    Jogador lista[500];
    //inserirElementos(lista);
    manipulaElementos(lista);
    //mostrarTodos(lista);
    return 0;
}












