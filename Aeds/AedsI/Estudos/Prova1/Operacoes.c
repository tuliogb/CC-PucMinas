#include <stdio.h>

int main() {
   float num1, num2, result;
   char op;

   printf("Digite um valor: ");
   scanf("%f", &num1);

   printf("Digite um operador (+, -, *, /): ");
   scanf(" %c", &op);

   printf("Digite outro valor: ");
   scanf("%f", &num2);

   switch(op) {
      case '+':
         result = num1 + num2;
         break;
      case '-':
         result = num1 - num2;
         break;
      case '*':
         result = num1 * num2;
         break;
      case '/':
         result = num1 / num2;
         break;
      default:
         printf("Operador inválido!");
   }

   printf("Resultado: %f", result);

   return 0;
}
