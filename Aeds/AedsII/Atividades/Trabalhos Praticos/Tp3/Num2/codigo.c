#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/*
    Dev: Tulio Gomes Braga
    Matricula: 1441272
    Curso: CC-PucMinas - 13/10/2023
*/

int tamanho = 0, mostrar=0;

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
void decodifica(Jogador *lista,char *entrada);
void inserirInicio(Jogador *lista, char* entrada);
void inserir(Jogador *lista, int posicao, char* entrada);
void inserirFim(Jogador *lista, char *entrada);
void removerInicio(Jogador *lista);
void remover(Jogador *lista, int posicao);
void removerFim(Jogador *lista);




void inserirElementos(Jogador *lista){
    char input[100];
    scanf("%s", input);

    while(strcmp(input, "FIM")!= 0){
        int id = atoi(input);
        setaId(&lista[tamanho++],id);
        scanf("%s", input);
    }
}

void setaId(Jogador *jogador, int id) {
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
    printf("[%i] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
        mostrar,
        jogador->nome,
        jogador->altura,
        jogador->peso,
        jogador->anoNascimento,
        jogador->universidade,
        jogador->cidadeNascimento,
        jogador->estadoNascimento
    );
    mostrar++;
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
    int len = strlen(entrada);
    if (len>0 && entrada[len-1]=='\n'){entrada[len-1]='\0';}
    char *token = strtok(entrada," ");

    if(token!=NULL){
        if (strcmp(token,"II")==0){
            token = strtok(NULL, " ");
            inserirInicio(lista,token);
        }
        else if(strcmp(token, "IF")==0){
            token = strtok(NULL, " ");
            inserirFim(lista,token);
        }
        else if(strcmp(token,"I*")==0){
            token = strtok(NULL, " ");
            int posicao = atoi(token);
            token = strtok(NULL, " ");
            inserir(lista,posicao,token);
        }
        else if(strcmp(token,"R*")==0){
            token = strtok(NULL, " ");
            int posicao = atoi(token);
            remover(lista,posicao);
        }
        else{
            if(strcmp(entrada, "RI")==0){
                removerInicio(lista);
            }
            else if(strcmp(entrada,"RF")==0){
                removerFim(lista);
            }
        }
    }
}

void inserirInicio(Jogador *lista, char* entrada){
    int idJogador = atoi(entrada);                          /*ATOI ESTA COLOCANDO UM \N NO ID JOGADOR*/
    for (int i=tamanho;i>0;i--){
       lista[i]=lista[i-1];
    }
    setaId(&lista[0],idJogador);
    tamanho++;
}

void inserir(Jogador *lista, int posicao, char* entrada){
    if(posicao==0) inserirInicio(lista,entrada);
    else if(posicao==tamanho) inserirFim(lista,entrada);
    else{
        int idJogador = atoi(entrada);
        for (int i=tamanho;i>posicao;i--){
            lista[i]=lista[i-1];
        }
        setaId(&lista[posicao],idJogador);
        tamanho++;
    }
}

void inserirFim(Jogador *lista, char *entrada){
    int idJogador = atoi(entrada);
    setaId(&lista[tamanho],idJogador);
    tamanho++;
}

void removerInicio(Jogador *lista){
    mostraRemovido(&lista[0]);

    for (int i=0;i<tamanho;i++){
        lista[i]=lista[i+1];
    }
    tamanho--;
}

void remover(Jogador *lista, int posicao){
    mostraRemovido(&lista[posicao]);

    if(posicao==0) removerInicio(lista);
    else if(posicao==tamanho-1) removerFim(lista);
    else{
        for (int i=posicao;i<tamanho-1;i++){
            lista[i]=lista[i+1];
        }
    }
    tamanho--;
}

void removerFim(Jogador *lista){
    mostraRemovido(&lista[tamanho-1]);
    tamanho--;
}

void mostraRemovido(Jogador *jogador){
    printf("(R) %s\n", jogador->nome);
}

int main(){
    Jogador lista[500];
    inserirElementos(lista);
    manipulaElementos(lista);
    mostrarTodos(lista);
    return 0;
}












