#include <stdio.h>

int main(){
    int x=10;
    int *y = &x;         // '*' indica que isso nao guardara o valor, mas sim o endereco para o tipo do valor;

    printf("%p\n",&x);    // Local na memoria onde esta guardado o 10;
    printf("%p\n",y);     // Endereco do local onde esta guardado o 10;
    printf("%p\n",&y);    // Endereco do local onde guarda o local que esta guardado o 10;
    printf("%i\n",*y);     // Busca o valor int dentro do conteudo, que no caso é o endereco de 10;           

}