#include <stdio.h>
#include <stdlib.h>

int main() {
    int nota, aprovados = 0, reprovados = 0, total = 0;

    do {
        printf("Informe a nota (0 para encerrar): ");
        scanf("%i", &nota);

        if (nota < 0 || nota > 100) {
            printf("Nota inválida!\n");
            continue;
        }

        if (nota >= 60) {
            aprovados++;
        } else {
            reprovados++;
        }

        total++;
        printf("%i",total);
    } while (nota != 0);

    if (total == 1) {
        printf("Foi informada apenas uma nota!\n");
    } else {
        float percAprovados = ((float)aprovados / (float)(total - 1)) * 100;
        float percReprovados = ((float)(reprovados-1) / (float)(total - 1)) * 100;

        printf("Percentual de alunos aprovados: %f\n", percAprovados);
        printf("Percentual de alunos reprovados: %f\n", percReprovados);
    }

    return 0;
}
