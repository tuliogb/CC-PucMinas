#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/*
    Dev: Tulio Gomes Braga
    Matricula: 1441272
    Curso: CC-PucMinas - 13/10/2023
*/

int tamanho=0, mostrar=0, primeiro=0, ultimo=0, tam=6;
bool print=false;

typedef struct {
    int id;
    int altura;
    int peso;
    int anoNascimento;
    char nome[100];
    char universidade[100];
    char cidadeNascimento[100];
    char estadoNascimento[100];
} Jogador;  Jogador lista[6];

void inserirElementos();
void verifica(int id);
void setaId(Jogador *jogador, int id);
void parteDados(Jogador *jogador, const char *linha);
void setaDados(Jogador *jogador,char *linha);
void mostraDados(Jogador *jogador);
void mostrarTodos();
void removerInicioPrint();
void removerInicio();
void calculaMedia();
void manipulaElementos();
void decodifica(char *entrada);


void inserirElementos(){
    char input[100];
    scanf("%s", input);

    while(strcmp(input, "FIM")!= 0){
        int id = atoi(input);
        if ((ultimo+1)%tam == primeiro){
            removerInicio(lista);
        }

        setaId(&lista[ultimo],id);
        ultimo = ((ultimo+1)%tam);
        calculaMedia();
;
        scanf("%s", input);
    }
}

void verifica(int id){
    if ((ultimo+1)%tam == primeiro){
        removerInicio(lista);
    }
    setaId(&lista[ultimo],id);
    ultimo = ((ultimo+1)%tam);
    calculaMedia();
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

    tamanho++;
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

void mostrarTodos(){
    for (int i=primeiro; i!=ultimo; i=(i+1)%tam){
        mostraDados(&lista[i]);
    }
}

void removerInicioPrint(){
    if(primeiro==ultimo) printf("Erro\n");
    else{
        if(print) printf("(R) %s\n", lista[primeiro].nome);
        primeiro = (primeiro+1)%tam;
        tamanho--;
    }
}

void removerInicio(){
    if(primeiro==ultimo) printf("Erro\n");
    else{
        primeiro = (primeiro+1)%tam;
        tamanho--;
    }
}

void calculaMedia(){
    float media=0;

    for(int i=primeiro; i!=ultimo; i=(i+1)%tam){
        media = media + lista[i].altura;
    }

    printf("%0.f\n", media/tamanho);
}

void manipulaElementos(){
    int qtd; scanf("%i",&qtd);
    char entrada[20];
    print = true;

    while (getchar() != '\n');

    for (int i=0;i<qtd;i++){
        fgets(entrada, sizeof(entrada), stdin);                   
        decodifica(entrada);
    }
}

void decodifica(char *entrada){
    int len = strlen(entrada);
    if (len>0 && entrada[len-1]=='\n'){entrada[len-1]='\0';}
    char *token = strtok(entrada," ");

    if(token!=NULL){
        if (strcmp(token, "I")==0){
            token = strtok(NULL, " ");
            verifica(atoi(token));
        }
        else if(strcmp(entrada, "R")==0){
            removerInicioPrint();
        }
    }
}



int main(){
    inserirElementos();
    manipulaElementos();
    mostrarTodos();
    return 0;
}