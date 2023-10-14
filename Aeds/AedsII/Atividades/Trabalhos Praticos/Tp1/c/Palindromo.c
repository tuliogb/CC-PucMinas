#include <stdio.h>
#include <string.h>

/*
    Dev: Tulio Gomes Braga
    Matricula: 1441272
    Curso: CC-PucMinas - 04/09/2023
    Versao: 1ºa
*/

void EouN(char *palavra) {
    int i, j;     
    int len = strlen(palavra);    

    for (i = 0, j = len - 1; i < j; i++, j--) {    
        if (palavra[i] != palavra[j]) {
            printf("NAO\n");
            return;
        }
    }
    printf("SIM\n");
}

int main() {
    
    char input[100];       
    int Loop = 1;           

    while (Loop) {
        fgets(input, sizeof(input), stdin);      
        input[strcspn(input, "\n")] = '\0';
        if (strcmp(input, "FIM") == 0) {
            Loop = 0;                       
        } else {
            EouN(input);           
        }
    }

    return 0;
}
