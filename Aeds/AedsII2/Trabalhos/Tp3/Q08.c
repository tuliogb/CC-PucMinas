#include <time.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAXTAM 405

/**
 * Questao 8 - Trabalho Pratico 3 - Aeds II - CC_PUCMINAS
 * @author Tulio Gomes Braga
 * @version 1 05/2024
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

typedef struct {
    char id[50], name[50], alternate_names[200], house[50], ancestry[50], species[50], patronus[50], hogwartsStaff[50], hogwartsStudent[50], actorName[50], alternate_actors[100], eyeColour[50], gender[50], hairColour[50];
    LocalDate dateOfBirth;
    int yearOfBirth;
    bool alive, wizard; 
} Personagem;

typedef struct CtrlPersonagem{
    Personagem elemento;
    struct CtrlPersonagem *prox, *ant;
}CtrlPersonagem;


Personagem Base[MAXTAM];
int baseTam=0, entradas=0, comparacoes=0, movimentacoes=0;
CtrlPersonagem *primeiro, *ultimo;
clock_t startTime, endTime;



CtrlPersonagem* startNoCabeca(){
    CtrlPersonagem* nova = (CtrlPersonagem*)malloc(sizeof(CtrlPersonagem));
    nova->prox = NULL;
    nova->ant = NULL;

    return nova;
}

CtrlPersonagem* newCelula(Personagem elemento){
    CtrlPersonagem* nova = (CtrlPersonagem*)malloc(sizeof(CtrlPersonagem));
    nova->elemento = elemento;
    nova->prox = NULL;
    nova->ant = NULL;

    return nova;
}





/////////////////////////////////////////////////////////// Exercicio 8

void swap(CtrlPersonagem* x, CtrlPersonagem* y){
    Personagem tmp = x->elemento;
    x->elemento = y->elemento;
    y->elemento = tmp;
    movimentacoes+=3;
}

bool compareHouse(Personagem pivo, CtrlPersonagem* temp, int selecao){
    bool resp = false; 

    if(selecao == 0){
        if(strcmp(pivo.house, temp->elemento.house)>0) resp = true;
        else if(strcmp(pivo.house, temp->elemento.house)<0) resp = false;
        else{
            if(strcmp(pivo.name, temp->elemento.name)>0) resp = true;
            else resp = false;
            comparacoes++;
        }
        comparacoes+=2;
    }
    else if(selecao == 1){
        if(strcmp(pivo.house, temp->elemento.house)<0) resp = true;
        else if(strcmp(pivo.house, temp->elemento.house)>0) resp = false;
        else{
            if(strcmp(pivo.name, temp->elemento.name)<0) resp = true;
            else resp = false;
            comparacoes++;
        }
        comparacoes+=2;
    }
    return resp;
}

Personagem mediaPersoangem(CtrlPersonagem* esq, CtrlPersonagem* dir){
    int x=0;
    Personagem resp;
    CtrlPersonagem* i=esq;

    for(/**/; i!=NULL && i!=dir; i=i->prox) x++;  
    if(x!=0) x=x/2;
    for(/**/; x>0 && i!=NULL; x--, i=i->ant); 

    if(i!=NULL) resp=i->elemento;
    else resp = esq->elemento;

    return resp;
}

void quickSort(CtrlPersonagem* esq, CtrlPersonagem* dir){
    CtrlPersonagem *i=esq, *j=dir;
    Personagem pivo = mediaPersoangem(esq, dir);

    while(j->prox!=i && j->prox!=i->ant){
        
        while(i->prox!=dir && compareHouse(pivo, i, 0))  i=i->prox; 
        while(j->ant!=esq && compareHouse(pivo, j, 1))  j=j->ant;  

        if(j->prox!=i && j->prox!=i->ant){
            swap(i,j);
            i=i->prox;
            j=j->ant;
        }
    }
    if(j!=esq) quickSort(esq,j);
    if(i!=dir) quickSort(i,dir);
}

void desempataNome(CtrlPersonagem* esq, CtrlPersonagem* dir) {
    CtrlPersonagem *i=esq, *j=dir;
    Personagem pivo = mediaPersoangem(esq, dir);

    while(j->prox!=i && j->prox!=i->ant){
        while(i->prox!=dir && compareHouse(pivo, i, 0))  i=i->prox; 
        while(j->ant!=esq && compareHouse(pivo, j, 1))  j=j->ant;  

        if(j->prox!=i && j->prox!=i->ant){
            if (strcmp(i->elemento.house, j->elemento.house)==0 && strcmp(i->elemento.name, j->elemento.name)>0) {
                swap(i,j);
                i=i->prox;
                j=j->ant;
            } else {
                i=i->prox;
                j=j->ant;
            }
        }
    }
    if(j!=esq) desempataNome(esq, j);
    if(i!=dir) desempataNome(i, dir);
}

void Arqlog(){
    double ticks = (double)(endTime - startTime);    // Ticks de clock da cpu (unidades clock, "pulsos")
    double duracaoDeUmTick = 1.0 / CLOCKS_PER_SEC;  // Duração de um tick em segundos
    double tempo = ticks * duracaoDeUmTick;

    FILE *file = fopen("802512_quicksort2.txt", "w");
    fprintf(file, "802512\t%i\t%i\t%.6fs", comparacoes, movimentacoes, tempo);
}

///////////////////////////////////////////////////////////////////////




void mostraPersonagem(Personagem p){
    printf("[%s ## %s ## {%s} ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %02i-%02i-%i ## %i ## %s ## %s ## %s ## %s]\n" , 
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

void mostraLista(){
    for(CtrlPersonagem* i=primeiro->prox; i!=NULL; i=i->prox){
        mostraPersonagem(i->elemento);
    }   
    /*
    printf("\n");
    for(CtrlPersonagem* i=ultimo; i!=primeiro; i=i->ant){
        mostraPersonagem(i->elemento);
    }
    */
}

void mostrarBase(){
    for(int i=0; i<baseTam; i++){
        mostraPersonagem(Base[i]);
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

void setaLista(char input[]){
    bool achou = false;

    for(int i=0; i<MAXTAM && !achou; i++){
        if(strcmp(Base[i].id, input)==0){
            ultimo->prox = newCelula(Base[i]);
            ultimo->prox->ant = ultimo;
            ultimo = ultimo->prox;
            achou = true;
        }
    }
}

void setaEntrada(){
    char input[100];
    scanf("%s", input);

    while(strcmp(input, "FIM")!=0){
        setaLista(input);
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


int main(){
    primeiro = ultimo = startNoCabeca();
    
    startTime = clock();
    setaBase();
    setaEntrada();
    quickSort(primeiro->prox, ultimo);
    desempataNome(primeiro->prox, ultimo);
    mostraLista();
    endTime = clock();

    Arqlog();
}