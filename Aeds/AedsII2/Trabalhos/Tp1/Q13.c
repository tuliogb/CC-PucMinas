#include <stdio.h>
#include <stdlib.h>
#include <string.h>


void alteracaoR(char *input, char a, char b, int i){
    if(i<(strlen(input)-2)){
        if(input[i]==a) input[i] = b;
        alteracaoR(input,a,b,i+1);
    }
}

void aleatoria(char *input){
    srand(4);

    char a = 'a' + (rand()%26);
    char b = 'a' + (rand()%26);

    alteracaoR(input, a, b, 0);
    printf("%s",input);
}

void start(){
    char input[200];
    fgets(input, sizeof(input), stdin);

    while(strcmp(input,"FIM\n")!=0){
        aleatoria(input);
        fgets(input, sizeof(input), stdin);
    }
}

int main(){
    start();
}