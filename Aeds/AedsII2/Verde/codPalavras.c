#include <stdio.h>
#include <string.h>
#include <stdbool.h>

void repete(char* exp, int n){
	for(int i=0; i<n; i++){
		printf("%s", exp[1]);
	}
}

void decifra(char input[]){
	char exp[10];
	int qtd;
	int tam = strlen(input);
	bool acabou = false;

	for(int i=0, j=0; i<tam; i++){
		if(input[i] == '['){
			qtd = input[i-1] - '0';			// Como o 0 é 48 eu posso tirar ele, ai fica somente o numero real;
			i++;

			while(!acabou){
				j++;
				exp[i-2] = input[i];
				if(input[i+1] == ']') acabou = true;
				else i++;
			}

			for(int i=0; i<qtd; i++){
				for(int c=0; c<j; c++){
					printf("%c", exp[c]);
				}
			}

		}
		acabou = false;
		j=0;
	}
}

int main(){
	decifra("2[ab]3[a]");
	return 0;
}