/*
Descrição

Bingo de São João será um carro zero-quilômetro. Todo mundo quer ser o primeiro a completar sua cartela, claro. São N cartelas identificadas de 1 até N que contêm, cada uma, K números distintos entre os números naturais de 1 até U, para K < U. Um número, claro, pode aparecer em mais de uma cartela e duas cartelas podem até ser iguais, ter o mesmo conjunto de números. Justamente por isso, veja que pode acontecer empate com mais de uma cartela sendo completada no mesmo instante.

Neste problema, serão dados na entrada os conjuntos de números de todas as cartelas e a sequência de números sorteados, que será uma permutação dos naturais de 1 até U. Seu programa deve determinar qual ou quais cartelas vão ser completadas primeiro e ganhar o carro.

Por exemplo, para N = 4, K = 5 e U = 10, com as cartelas dadas pela tabela abaixo, se a sequência de números sorteados for [7, 3, 5, 2, 6, 1, 9, 10, 4, 8], então haverá uma cartela vencedora, a número 3.

número da cartela 					
1 	3 	10 	8 	7 	2
2 	4 	1 	7 	10 	9
3 	9 	1 	5 	3 	6
4 	6 	8 	1 	5 	7

Entrada
A primeira linha da entrada contém três inteiros N, K e **Uv representando respectivamente o número de cartelas, quantos números cada cartela contém e o maior natural que pode ocorrer numa cartela. As N linhas seguintes contêm, cada uma, K inteiros distintos Ci, para 1 ≤ i ≤ K, representando o conjunto de números de cada cartela, da cartela 1 até a **N. A última linha da entrada contém U inteiros indicando a sequência de números sorteados, uma permutação dos naturais entre 1 e U.

Saída
Seu programa deve imprimir uma linha contendo os números identificadores das cartelas vencedoras do carro, em ordem crescente.
*/


/*
    Explicacao Walisson: Tamanho de linhas da tabela X Quantidade de numeros em cada linha X Quantidade de numeros sortiado; 
    4 entradas com os numeros porque sao 4 linhas;
    entrada com os numeros sortiados;
*/