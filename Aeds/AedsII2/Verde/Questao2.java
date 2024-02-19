public class Questao2 {

    static int LetrasMaiusculas(String input, int i){
        int resp=0;

        if(i<input.length()){
            if(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z'){
                resp=1;
            }
            resp = resp + LetrasMaiusculas(input,i+1);
        }

        return resp;
    }


    static void start(){
        String input = MyIO.readLine();
        
        while(!input.equals("FIM")){
            MyIO.println(LetrasMaiusculas(input,0));
            input = MyIO.readLine();
        }
    }




    public static void main(String[] args) {
        start();
    }
}
