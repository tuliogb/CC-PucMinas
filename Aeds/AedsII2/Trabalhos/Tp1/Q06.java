 /*
 * Trabalho Pratico 1 - Exercicio 6
 * Tulio Gomes Braga
 * 802512
 */

class Is {
    
    static void start(){
        String input = MyIO.readLine();

        while(!input.equals("FIM")){

            vogal(input);

            input = MyIO.readLine();
            consoante(input);

            input = MyIO.readLine();
            inteiro(input);

            input = MyIO.readLine();
            real(input);

            input = MyIO.readLine();
        }
    }

    static void vogal(String input){
        boolean resp = true;
        int tam = input.length();

        for(int i=0; i<tam; i++){
            char c = input.charAt(i);

            if(c!='a' && c!='e' && c!='i' && c!='o' && c!='u'){
                resp=false;
                i=tam;
            }
        }
        if(resp) MyIO.print("SIM ");
        else MyIO.print("NAO ");
    }

    static void consoante(String input){
        boolean resp = true;
        int tam = input.length();

        for(int i=0; i<tam; i++){
            char c = input.charAt(i);

            if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u'){
                resp=false;
                i=tam;
            }
        }
        if(resp) MyIO.print("SIM ");
        else MyIO.print("NAO ");
    }

    static void inteiro(String input){
        try{
            int x = Integer.parseInt(input);
            MyIO.print("SIM ");

        }catch(Exception e){ MyIO.print("NAO ");}
    }

    static void real(String input){
        try{
            Double x = Double.parseDouble(input);
            MyIO.println("SIM");

        }catch(Exception e){ MyIO.println("NAO");}
    }



    public static void main(String[] args) {
        start();
    }
}
