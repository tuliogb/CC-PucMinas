 /*
 * Trabalho Pratico 1 - Exercicio 3
 * Tulio Gomes Braga
 * 802512
 */

class Cesar {

    static void start(){
        String input = MyIO.readLine();

        while(!input.equals("FIM")){
            MyIO.println(codificacao(input));
            input = MyIO.readLine();
        }
    }

    static String codificacao(String input){
        String cifra = "";

        for (int i=0; i<input.length(); i++){
            char letra = input.charAt(i);
            letra+=3;
            cifra+=letra;
        }

        return cifra;
    }


    public static void main(String[] args) {
        start();
    }
}
