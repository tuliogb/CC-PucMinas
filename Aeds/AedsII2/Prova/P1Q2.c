#include <stdio.h>


char input[100000];
int tam = 0;


void start(){
    char c;

    while( (c=getchar())!=EOF){
        input[tam] = c;
        tam++;
    }
}

void resposta(int n, char resp[], int k){      
    int tam = n-48;                                 

    for(int j=0; j<tam; j++){
        for(int i=0; i<k; i++){
            printf("%c", resp[i]);
        }
    }

}

void decodifica(){
    int qtd = 0;
    int k = 0;
    char resp[100000];

    for(int i=0; i<tam; i++){
        if(input[i]!= '\n'){

            if(input[i] == '['){
                qtd = input[i-1];
                while(input[i+1] != ']'){
                    resp[k] = input[i+1];
                    i++;
                    k++;
                }
                resp[k] = '0';
                resposta(qtd,resp,k);
                k=0;
            }

        }else printf("\n");
    }
}

int main(){
    start();
    decodifica();
}