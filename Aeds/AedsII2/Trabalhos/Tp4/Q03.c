#include <time.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAXTAM 405

/**
 * Questao 3 - Trabalho Pratico 4 - Aeds II - CC_PUCMINAS
 * @author Tulio Gomes Braga
 * @version 1 06/2024
 * 
 * Explicações dos Métodos:
 *   Personagem: Responsavel pela cricao dos atributos dos personagens.
 *   mostraPersonagem: Apresenta na saida padrao os atributos formatados.
 *   mostraLista: Percorre toda a lista fazendo struct.mostrarPersonagem().
 *   mostrarBase: Percorre toda a base fazendo struct.mostrarPersonagem().
 *   setLocalDate: Recebe o parametro, trata, e o seta corretamente como Local Date.
 *   setaPersonagem: Particiona a linha e seta os atributos.
 *   setaLista: Percorre a base procurando o elemento compativel com chave de entrada, quando achar adiciona na lista.
 *   setaBase: Abre o arquivo uma vez e passa linha por linha para setar todos os personagens.
 *   lerEntrada: Enquanto a entrada for diferente de fim passa a chave pra ser procurada.
*/

typedef struct{
    int dia, mes, ano;
} LocalDate;

typedef struct{
    char id[50], name[50], alternate_names[200], house[50], ancestry[50], species[50], patronus[50], hogwartsStaff[50], hogwartsStudent[50], actorName[50], alternate_actors[100], eyeColour[50], gender[50], hairColour[50];
    LocalDate dateOfBirth;
    int yearOfBirth;
    bool alive, wizard; 
} Personagem;

typedef struct No{
    int nivel;
    struct No *esq, *dir;
    Personagem elemento;
}No;

No* novoNo(Personagem elemento){
    No *novo =  (No*)malloc(sizeof(No));
    novo->elemento = elemento;
    novo->esq = NULL;
    novo->dir = NULL;
    novo->nivel = 1;

    return novo;
}

Personagem Base[MAXTAM];
clock_t startTime, endTime;
int baseTam=0, entradas=0, comparacoes=0;
No *raiz = NULL;


//////////////////////////////////////////////////////// EXERCICIO 3

bool pesquisarNo(No *i, char *input){
    bool resp = false;

    if(i==NULL){ resp = false; comparacoes++;}
    else if(strcmp(input, i->elemento.name)<0){ printf(" esq"); resp = pesquisarNo(i->esq, input); comparacoes++;}
    else if(strcmp(input, i->elemento.name)>0){ printf(" dir"); resp = pesquisarNo(i->dir, input); comparacoes++;}
    else resp = true;
    
    return resp;
}

void pesquisar(){
    char input[100];
    scanf(" %[^\n\r]", input);

    while(strcmp(input, "FIM")!=0){
        printf("%s => raiz", input);
        printf("%s", pesquisarNo(raiz, input) ? " SIM\n" : " NAO\n");
        scanf(" %[^\n\r]", input);
    }  
}

int maxAbs(int x, int y){
    return x>y ? x : y;
}

int getNivel(No *i){
    return i==NULL ? 0 : i->nivel;
}

int setNivel(No* i){
    return 1 + maxAbs(getNivel(i->dir), getNivel(i->esq));
}

No* rotacionarEsq(No* i){
    No* noDir = i->dir;
    No* noDirEsq = noDir->esq;

    noDir->esq = i;
    i->dir = noDirEsq;

    i->nivel = setNivel(i);
    noDir->nivel = setNivel(noDir);
    return noDir;
}

No* rotacionarDir(No* i){
    No* noEsq = i->esq;
    No* noEsqDir = noEsq->dir;

    noEsq->dir = i;
    i->esq = noEsqDir;

    i->nivel = setNivel(i);
    noEsq->nivel = setNivel(noEsq);
    return noEsq;
}

No* balancear(No* i){
    if(i!=NULL){
        int fator = getNivel(i->dir) - getNivel(i->esq);

        if(fator>=-1 && fator<=1)  i->nivel = setNivel(i);
        else if(fator==2){
            int fatorFilhoDir = getNivel(i->dir->dir) - getNivel(i->dir->esq);
            if(fatorFilhoDir==-1) i->dir = rotacionarDir(i->dir);
            i = rotacionarEsq(i);
            comparacoes+=2;
        }else if(fator==-2){
            int fatorFilhoEsq = getNivel(i->esq->dir) - getNivel(i->esq->esq);
            if(fatorFilhoEsq==1) i->esq = rotacionarEsq(i->esq);
            i = rotacionarDir(i);
            comparacoes+=2;
        }
    }
    return i;
}

No* inserirNo(No* i, Personagem elemento){
    if(i==NULL){ i = novoNo(elemento); }
    else if(strcmp(elemento.name, i->elemento.name)<0){ i->esq = inserirNo(i->esq, elemento); comparacoes++; }
    else if(strcmp(elemento.name, i->elemento.name)>0){ i->dir = inserirNo(i->dir, elemento); comparacoes++; }
    return balancear(i);
}

void inserir(Personagem elemento){
    raiz = inserirNo(raiz, elemento);
}

void Arqlog(){
    double ticks = (double)(endTime - startTime);    // Ticks de clock da cpu (unidades clock, "pulsos")
    double duracaoDeUmTick = 1.0 / CLOCKS_PER_SEC;  // Duração de um tick em segundos
    double tempo = ticks * duracaoDeUmTick;

    FILE *file = fopen("802512_avl.txt", "w");
    fprintf(file, "802512\t%i\t%.6fs", comparacoes, tempo);
}

////////////////////////////////////////////////////////////////////


void mostraPersonagem(Personagem p, int i){
    printf("[%i ## %s ## %s ## {%s} ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %02i-%02i-%i ## %i ## %s ## %s ## %s ## %s]\n" , 
        i,
        p.id, 
        p.name, 
        p.alternate_names,
        p.house,
        p.ancestry,
        p.species,
        p.patronus,
        p.hogwartsStaff,
        p.hogwartsStudent,
        p.actorName,
        p.alive ? "true" : "false",
        p.dateOfBirth.dia, p.dateOfBirth.mes, p.dateOfBirth.ano,
        p.yearOfBirth,
        p.eyeColour,
        p.gender,
        p.hairColour,
        p.wizard ? "true" : "false"
    );
}

void mostrarBase(){
    for(int i=0; i<baseTam; i++){
        mostraPersonagem(Base[i], i);
    }
}

void setaLocalDate(Personagem *p, char data[]){ 
    char dia[3], mes[3], ano[5];

    strncpy(dia, data, 2);
    dia[2] = '\0'; 
    p->dateOfBirth.dia = atoi(dia);

    if(data[4]!='-'){   strncpy(mes, data+3, 2);    mes[2]='\0';    p->dateOfBirth.mes = atoi(mes);}
    else{ strncpy(mes, data+3, 1);    mes[1]='\0';    p->dateOfBirth.mes = atoi(mes);}

    if(data[4]!='-'){   strncpy(ano, data+6, 4);    ano[4]='\0';    p->dateOfBirth.ano = atoi(ano);}
    else{ strncpy(ano, data+5, 4);  ano[4]='\0';    p->dateOfBirth.ano = atoi(ano);}
}

void setaPersonagem(char* linha){
    char input[300], data[11]; strcpy(input,linha);
    int x=0, y=0, l=0;

    while(input[x]!=';'){ Base[baseTam].id[y] = input[x]; x++; y++;}    Base[baseTam].id[y]='\0'; x++; y=0;
    while(input[x]!=';'){ Base[baseTam].name[y] = input[x]; x++; y++;}  Base[baseTam].name[y]='\0'; x++; y=0;
    while(input[x]!=';'){ if(input[x]!='[' && input[x]!=']' && input[x]!=39){ Base[baseTam].alternate_names[y] = input[x]; y++;} x++;}  Base[baseTam].alternate_names[y]='\0'; x++; y=0;   
    while(input[x]!=';'){ Base[baseTam].house[y] = input[x]; x++; y++;} Base[baseTam].house[y]='\0'; x++; y=0;
    while(input[x]!=';'){ Base[baseTam].ancestry[y] = input[x]; x++; y++;}  Base[baseTam].ancestry[y]='\0'; x++; y=0;
    while(input[x]!=';'){ Base[baseTam].species[y] = input[x]; x++; y++;}   Base[baseTam].species[y]='\0'; x++; y=0;
    while(input[x]!=';'){ Base[baseTam].patronus[y] = input[x]; x++; y++;}  Base[baseTam].patronus[y]='\0'; x++; y=0;
    while(input[x]!=';'){ if(input[x]=='F'){ strcpy(Base[baseTam].hogwartsStaff,"false"); x+=5;} else{strcpy(Base[baseTam].hogwartsStaff,"true"); x+=10;}} x++; y=0;
    while(input[x]!=';'){ if(input[x]=='F'){ strcpy(Base[baseTam].hogwartsStudent,"false"); x+=5;} else{strcpy(Base[baseTam].hogwartsStudent,"true"); x+=10;}} x++; y=0;
    while(input[x]!=';'){ Base[baseTam].actorName[y] = input[x]; x++; y++;}  Base[baseTam].actorName[y]='\0'; x++; y=0;
    while(input[x]!=';'){ if(input[x]=='V'){ Base[baseTam].alive=true; x+=10;} else{ Base[baseTam].alive=false; x+=5;}} x++; y=0;
    while(input[x]!=';'){ Base[baseTam].alternate_actors[y] = input[x]; x++; y++;}  Base[baseTam].alternate_actors[y]='\0'; x++; y=0;
    while(input[x]!=';'){ data[y] = input[x]; x++; y++;} setaLocalDate(&Base[baseTam],data); x++; y=0;
    while(input[x]!=';'){ data[y] = input[x]; x++; y++;} data[y]='\0'; Base[baseTam].yearOfBirth = atoi(data); x++; y=0;
    while(input[x]!=';'){ Base[baseTam].eyeColour[y] = input[x]; x++; y++;}  Base[baseTam].eyeColour[y]='\0'; x++; y=0;
    while(input[x]!=';'){ Base[baseTam].gender[y] = input[x]; x++; y++;}  Base[baseTam].gender[y]='\0'; x++; y=0;
    while(input[x]!=';'){ Base[baseTam].hairColour[y] = input[x]; x++; y++;}  Base[baseTam].hairColour[y]='\0'; x++; y=0;
    if(input[x]=='V'){ Base[baseTam].wizard=true; x+=9;} else{ Base[baseTam].wizard=false; x+=4;}

    baseTam++;
}

void setaArvore(char input[]){
    bool achou = false;

    for(int i=0; i<MAXTAM && !achou; i++){
        if(strcmp(Base[i].id, input)==0){
            inserir(Base[i]);
            achou = true;
        }
    }
}

void setaEntrada(){
    char input[100];
    scanf("%s", input);

    while(strcmp(input, "FIM")!=0){
        setaArvore(input);
        scanf("%s", input);
    }
}

void setaBase(){
    FILE *file =  fopen("/tmp/characters.csv","r");
    char input[300];
    fgets(input, sizeof(input), file);
    
    while(fgets(input, sizeof(input), file) != NULL){
        setaPersonagem(input);
    }
}

void mostrarNo(No *i) {
    if (i != NULL) {
        mostrarNo(i->esq);
        mostraPersonagem(i->elemento, 0);
        mostrarNo(i->dir);
    }
}


int main(){
    startTime = clock();
    setaBase();
    setaEntrada();
    //mostrarNo(raiz);
    pesquisar();
    endTime = clock();

    Arqlog();
}