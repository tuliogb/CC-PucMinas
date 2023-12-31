/*
Na escola onde você estuda, a hora do recreio é a mais aguardada pela grande maioria dos alunos. Não só porque as vezes as aulas são cansativas, mas sim porque a merenda servida é muito boa, preparada por um chefe italiano muito caprichoso.

Quando bate o sinal para a hora do recreio, todos os alunos saem correndo da sua sala para chegar o mais cedo possível na cantina, tanta é a vontade de comer. Um de seus professores notou, porém, que havia ali uma oportunidade.

Utilizando um sistema de recompensa, seu professor de matemática disse que a ordem da fila para se servir será dada não pela ordem de chegada, mas sim pela soma das notas obtidas em sala de aula. Assim, aqueles com maior nota poderão se servir antes daqueles que tem menor nota. Sua tarefa é simples: dada a ordem de chegada dos alunos na cantina, e as suas respectivas notas na matéria de matemática, reordene a fila de acordo com as notas de matemática, e diga quantos alunos não precisaram trocar de lugar nessa reordenação.

Entrada

A primeira linha contém um inteiro N, indicando o número de casos de teste a seguir. Cada caso de teste inicia com um inteiro M (1 ≤ M ≤ 1000), indicando o número de alunos. Em seguida haverá M inteiros distintos Pi (1 ≤ Pi ≤ 1000), onde o i-ésimo inteiro indica a nota do i-ésimo aluno.
Os inteiros acima são dados em ordem de chegada, ou seja, o primeiro inteiro diz respeito ao primeiro aluno a chegar na fila, o segundo inteiro diz respeito ao segundo aluno, e assim sucessivamente.

Saída

Para cada caso de teste imprima uma linha, contendo um inteiro, indicando o número de alunos que não precisaram trocar de lugar mesmo após a fila ser reordenada.
*/


/*
    Explicacao Walisson: Ordenar a fila por nota dos alunos decrescente
    
    Primeira entrada é o laco de consultas que irao ser feitas.
    Em cada laco deve ler o numero de notas e as notas
*/


public class Recreio{

	public static int[] array = new int[4];
	public static int[] clone = new int[4];
	
	public static void verificaNota(){
		int qtd = MyIO.readInt();
		
		String entrada = MyIO.readLine();
		String[] parte = entrada.split(" ");
		
		for  (int i=0;i<qtd;i++){
			array[i] = Integer.parseInt(parte[i]);				// Integer.toString(numero); 	>> PASSANDO NUMERO PRA STRING << 
		}
		
		for  (int i=0;i<qtd;i++){
			clone[i] = Integer.parseInt(parte[i]);				// CLONA O ARRAY PRA PODER COMPARAR DEPOIS DA ORGANIZACAO SE HOUVE ALGUMA TROCA 
		}
		
		ordenaArray(qtd);
		
		MyIO.println(qtd-comparaTrocas(qtd));
	}
	
	public static void ordenaArray(int qtd){
		for (int i=0;i<qtd-1;i++){
			int maior = i;
			for (int j=i+1;j<qtd;j++){
				if(array[maior] < array[j]) maior=j;
			}
			troca(maior,i);
		}
	}
	
	public static void troca(int i, int j){
		int temp = array[i];
      		array[i] = array[j];
      		array[j] = temp;
	}
	
	public static int comparaTrocas(int qtd){
		int resp = 0;
		
		for (int i=0;i<qtd;i++){
			if(array[i] != clone[i]){ resp++; }
		}
		
		return resp;
	}
	
	public static void main(String[] args){
		int qtd = MyIO.readInt();
		
		for (int i=0;i<qtd;i++){
			verificaNota();
		}
	}
}