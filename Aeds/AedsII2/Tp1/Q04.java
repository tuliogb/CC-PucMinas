import java.util.Random;

 /*
 * Trabalho Pratico 1 - Exercicio 4
 * Tulio Gomes Braga
 * 802512
 */

class Aleatoria {

    static char a, b;

    static void start(){
        String input = MyIO.readLine();
        gerador();

        while(!input.equals("FIM")) {
            MyIO.println(alteracao(input));
            input = MyIO.readLine();
        }
    }

    static void gerador(){
        Random sort = new Random();

        sort.setSeed(4);                                                                // Inicia uma semente fixa, serao os mesmos resultados.
        a = (char) (sort.nextInt(26) + 'a');
        b = (char) (sort.nextInt(26) + 'a');                                            // 0 a 25 inclusive >> aleatorio + a = alguma letra
    }

    static String alteracao(String input){
        StringBuilder resp = new StringBuilder();                                       // A cada laco  de concatenacao é criada uma nova string, por isso usar StringBuilder

        for(int i=0; i<input.length(); i++){
            if(input.charAt(i)==a){
                resp.append(b);
            }else{
                resp.append(input.charAt(i));
            }

        }
        return resp.toString();
    }


    /*  Metodos StringBuilder:
    
        append(char c): Adiciona um caractere ao final do StringBuilder.
        append(String str): Adiciona uma string ao final do StringBuilder.
        insert(int offset, String str): Insere uma string na posição especificada no StringBuilder.
        delete(int start, int end): Remove os caracteres entre as posições start e end - 1.
        deleteCharAt(int index): Remove o caractere na posição especificada.
        replace(int start, int end, String str): Substitui os caracteres entre as posições start e end - 1 pela string especificada.
        reverse(): Inverte a ordem dos caracteres no StringBuilder.
        length(): Retorna o comprimento atual do StringBuilder.
        charAt(int index): Retorna o caractere na posição especificada.
        setCharAt(int index, char c): Define o caractere na posição especificada para o caractere especificado.
        substring(int start): Retorna uma nova string que é uma subcadeia da string atual, começando na posição start.
        substring(int start, int end): Retorna uma nova string que é uma subcadeia da string atual, começando na posição start e indo até a posição end - 1.
     */



    public static void main(String[] args) {
        start();
    }
    
}
