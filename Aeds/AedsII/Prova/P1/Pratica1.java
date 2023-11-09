/*
A compressão de dados é o processo de codificar arquivos com o intuito de reduzir seu tamanho para economizar espaço de armazenamento e/ou facilitar a transmissão mais eficiente através de redes de comunicação. Isso é feito através de algoritmos que reescrevem ou representam os dados de uma forma mais compacta, sem perder a capacidade de recuperar a informação original.


Uma das estratégias mais simples é a codificação conforme a frequência em que cada caractere ocorre. Por exemplo, a codificação de “AaaaaXBbBbBbBbBbd” pode ser represenada como “A4[a]X5[Bc]d”. Logo, toda sequência com k letras iguais ‘x’ é representada como k[x], em que ‘x’ é uma letra qualquer do alfabeto. Contudo, nem sempre esta codificação resultará em redução, podendo, inclusive, aumentar seu tamanho devido ao acréscimo dos caracteres “[” e “]” na palavra codificada. Por exemplo, a codificação de “aabbcc” resulta em “2[a]2[b]2[c]”.


Considerando a codificação explicada acima, desenvolva um algoritmo que decodifique um texto de entrada.

Você pode assumir que o texto de entrada é sempre válido; não há espaços em branco extras, os colchetes estão bem formados, etc. Além disso, você pode assumir que os dados originais não contêm nenhum dígito e que os dígitos são apenas para os números repetidos. Por exemplo, não haverá entradas como 3a ou 2[4].


Os casos de teste são gerados de forma que o comprimento da saída nunca exceda 10^5.


Exemplo: Input: s = "3[a]2[bc]" Output: "aaabcbc"
*/