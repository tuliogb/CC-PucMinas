/*
 * Trabalho Pratico 1 - Exercicio 1
 * Tulio Gomes Braga
 * 802512
 */

class Palindromo{


    static void start(){
        String input = MyIO.readLine();

        while(!input.equals("FIM")) {
            ePalindromo(input);
            input = MyIO.readLine();
        }
    }


    static void ePalindromo(String input){
        boolean resp = true;
        
        for(int i=0, j=input.length()-1; i<j; i++,j--){
            if(input.charAt(i)!=input.charAt(j)){
                resp=false;
            }
        }

        resultado(resp);
    }


    static void resultado(boolean resp){
        if(resp) MyIO.println("SIM");
        else MyIO.println("NAO");
    }



    public static void main(String[] args){
        start();
    }
}