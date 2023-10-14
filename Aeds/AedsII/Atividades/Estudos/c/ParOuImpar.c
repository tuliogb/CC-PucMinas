#include <stdio.h>

/*
    Dev: Tulio Gomes Braga
    Matricula: 1441272
    Curso: CC-PucMinas - 15/08/2023
*/

int leiaNumero(){
  int num;
  scanf("%i",&num);
  return num;
}

int main(void) {

  if(leiaNumero()%2==0) printf("par");
  else printf("impar");
  
  return 0;
}