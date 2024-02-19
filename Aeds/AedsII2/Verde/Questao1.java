public class Questao1 {
    
    /*
     * Crie um método ITERATIVO em Java que receba como parâmetro uma string e retorne seu número de caracteres maiúsculos. 
     * Em seguida, teste o método anterior usando redirecionamento de entrada e saída. A entrada padrão é composta por várias linhas sendo que a última 
     * contém a palavra FIM. A saída padrão contém um número inteiro para cada linha de entrada
    */

    static int LetrasMaiusculas(String input){
        int resp = 0;

        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) >= 'A' && input.charAt(i)<='Z'){
                resp++;
            }
        }
        return resp;
    }

    static void start(){
        String input = MyIO.readLine();
        
        while(!input.equals("FIM")){
            MyIO.println(LetrasMaiusculas(input));
            input = MyIO.readLine();
        }
    }

    public static void main(String[] args) {
        start();
    }
}
