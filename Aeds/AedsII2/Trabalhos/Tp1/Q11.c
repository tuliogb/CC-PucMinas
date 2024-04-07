#include <stdio.h>
#include <string.h>

void palindromoR(char *input, int i, int j){
    if(i<j){
        if(input[i]==input[j]) palindromoR(input, i+1, j-1);
        else printf("NAO\n");
    
    }else printf("SIM\n");
}

void start(){
    char input[100];
    fgets(input, sizeof(input), stdin);

    while(strcmp(input,"FIM")!=0){
        palindromoR(input,0,strlen(input)-2);
        fgets(input, sizeof(input), stdin);
    }
}

int main(){
    start();
}