/**
 * CiframentoR -> Exercicio 10
 * Tulio Gomes Braga - 802512
 */
public class PalindromoR {
    
    static void start(){
        String input = MyIO.readLine();
    
        while(!input.equals("FIM")){
            palindromoR(input,0,input.length()-1);
            input = MyIO.readLine();
        }
    }

    static void palindromoR(String input, int i, int j){
        if(i<j){
            if(input.charAt(i)==input.charAt(j)){
                palindromoR(input, i+1,j-1);
            }else MyIO.println("NAO"); 
        
        }else MyIO.println("SIM");
    }

    public static void main(String[] args) {
        start();
    }
}