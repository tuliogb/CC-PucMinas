// Verificar se é par ou impar

const char * even_or_odd(int n){
  return (n & 1)?("Odd"):("Even");  // Poderia ser (number & 0x01) >> 1 em hexadecimal.
}


/*
O operador & é usado para fazer uma operação bit a bit com n e o número 1. Quando você faz n & 1, você está basicamente 
verificando se o último bit (o bit menos significativo) de n é 1 ou 0. Se for 1, isso significa que o número é ímpar, porque 
todos os números ímpares têm o último bit definido como 1 em binário. Se for 0, o número é par, porque os números pares têm o 
último bit definido como 0 em binário.



O operador & é um operador bitwise que executa a operação "E" bit a bit em dois operandos.
Ele compara cada bit individual dos operandos e produz um resultado onde cada bit é definido como 1 apenas se os bits correspondentes em ambos os operandos forem 1.
Por exemplo: 5 & 3 produz 1 porque 5 em binário é 101 e 3 em binário é 011. O resultado da operação "E" bit a bit é 001, que é 1 em decimal.
*/




// Passar uma String pra numero sem usar atoi

int string_to_number(const char *src) {
  int x;
  sscanf(src,"%d",&x);
  return x;
}