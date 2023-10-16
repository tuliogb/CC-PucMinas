#include <stdio.h>
#include <stdlib.h>

int main()
{
    float valor,dm,cm,mm;
    printf("Informe o valor! [em metros]\n");
    scanf("%f",&valor);

    dm=valor*10;
    cm=valor*100;
    mm=valor*1000;


    printf("Decimetros,centimetros e milimentros:\n");
    printf("%f\n",dm);
    printf("%f\n",cm);
    printf("%f\n",mm);


    return 0;
}
