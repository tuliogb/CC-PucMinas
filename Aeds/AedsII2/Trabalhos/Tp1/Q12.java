/**
 * CiframentoR -> Exercicio 12
 * Tulio Gomes Braga - 802512
 */
 public class CiframentoR {

    static void start(){
        String input = MyIO.readLine();
    
        while(!input.equals("FIM")){
            ciframentoR(input);
            input = MyIO.readLine();
        }
    }

    static void ciframentoR(String input){
        char c = (char)(input.charAt(0)+3);

        if(input.length()==1){
            MyIO.println(c);
        }else{
            MyIO.print(c);
            ciframentoR(input.substring(1));
        }
    }

    public static void main(String[] args) {
        start();
    }
}