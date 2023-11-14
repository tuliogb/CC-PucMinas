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



Celula* primeiro; Celula* ultimo;
int tamanho = 0, mostrar = 0; bool print = false;

void inserirElementos();
void verifica(int id);
void setaId(int id);
void parteDados(const char *linha);
void setaDados(char *linha);
void mostraDados(Jogador jogador);
void mostrarTodos();
void removerInicio();
void removerInicioPrint();
void manipulaElementos();
void decodifica(char *entrada); 



void inserirElementos(){
    char input[100];
    scanf("%s", input);

    while(strcmp(input, "FIM")!= 0){
        int id = atoi(input);
        verifica(id);
        scanf("%s", input);
    }
}

void verifica(int id){
    if(tamanho==5){
        removerInicio();
        setaId(id); 
    }else{
        setaId(id);
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
    if(tamanho==0){
        primeiro = newCelula(jogador);
        ultimo = primeiro;
        tamanho++;
    }else{
        ultimo->prox = newCelula(jogador);
        ultimo = ultimo->prox;
        tamanho++;
    }

    float media=0; int i=0, soma=0; 
    for (Celula* p=primeiro; i<tamanho; i++, p=p->prox){
        soma = soma + p->elemento.altura;
    }
    media = soma/(float)tamanho;
    printf("%.0f\n",media);

}

void mostraDados(Jogador jogador){
    printf("[%i] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
        mostrar,
        jogador.nome,   
        jogador.altura,
        jogador.peso,
        jogador.anoNascimento,
        jogador.universidade,
        jogador.cidadeNascimento,
        jogador.estadoNascimento    
    );
    mostrar++;
}

void mostrarTodos(){
    Celula* i;
    for(i=primeiro; i!=NULL; i=i->prox){
        mostraDados(i->elemento);
    }
}

void removerInicio(){
    Celula* tmp = primeiro;
    primeiro = primeiro->prox; tamanho--;
    tmp->prox = NULL;
}

void removerInicioPrint(){
    if(print) printf("(R) %s\n", primeiro->elemento.nome);
    Celula* tmp = primeiro;
    primeiro = primeiro->prox; tamanho--;
    tmp->prox = NULL;
}

void manipulaElementos(){
    int qtd; scanf("%i",&qtd);
    char entrada[20];
    print = true;

    while (getchar() != '\n');

    for (int i=0;i<qtd;i++){
        fgets(entrada, sizeof(entrada), stdin);                     // O FGETS COLOCA UM "\N" NO FINAL DA STRING;
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