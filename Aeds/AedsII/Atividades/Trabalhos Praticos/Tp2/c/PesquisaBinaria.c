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

    Exercicios de Ordenacao: Metodo PesquisaBinaria para procurar o elemento informado pela entrada;
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


void troca(Jogador *lista,int x,int y){
    mov++;
    Jogador tmp = lista[x];
    lista[x] = lista[y];
    lista[y] = tmp;
}


void ArqLog(){
    int matricula = 802512;
    int tempo;
    tempo = (int)(end-start)*1000;

    FILE *file = fopen("matricula_pesquisaBinaria.txt","w");
    fprintf(file,"%i\t%ims\t%i\t%i",matricula,tempo,comp,mov);

}


void NomeEdata(Jogador *lista,int tam){
    for (int i=0;i<(tam-1);i++) {
      int menor=i;
      for (int j=(i+1);j<tam;j++){
        comp++;
         if (lista[menor].anoNascimento == lista[j].anoNascimento){
             comp++;
            if(strcmp(lista[menor].nome, lista[j].nome)>0) menor=j;
         }
      }
      troca(lista,menor,i);
   }
}


void OrganizaArray(Jogador *lista,int x){
    if(x<tamanho){
        int menor=x;
        for(int j=x+1;j<tamanho;j++){
            comp++;
            if(strcmp(lista[menor].nome, lista[j].nome)>0) menor=j; // RETORNA 0 SE FOR IGUAL, NEGATIVO 1 MENOR, POSITIVO 1 MAIOR;
        }
        troca(lista,menor,x);
        OrganizaArray(lista,x+1);
    }
}


bool PesquisaBinaria(Jogador *lista,char *x){
    bool resp=false;

    int esq=0, dir=tamanho-1, meio=0;

    while(esq<=dir){
        meio=(esq+dir)/2;
        comp++;
        if(strcmp(x,lista[meio].nome)==0){
            resp=true;
            esq=tamanho;
        }else if(strcmp(x,lista[meio].nome)>0) esq = meio+1;
        else dir=meio-1;
        comp++;
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

    start=clock();
    OrganizaArray(lista,0);
    TemEsseNome(lista);
    end=clock();
    
    ArqLog();
    return 0;
}
