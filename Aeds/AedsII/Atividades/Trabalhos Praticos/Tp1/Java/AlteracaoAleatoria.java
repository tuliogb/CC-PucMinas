
/*
    Dev: Tulio Gomes Braga
    Matricula: 1441272
    Curso: CC-PucMinas - 04/09/2023
*/

import java.util.Random;

public class AA {

    public static boolean SaoIguais(String c1, String c2) {
		boolean igual = true;
	
		if(c1.length() != c2.length()) igual = false;           

		int i = 0;
		while(igual && i < c1.length()) {                     
			if (c1.charAt(i) != c2.charAt(i)) igual = false;
			i++;     
		}
		return igual;
	}

    public static String altera(Random rand,String entrada) {
        String saida = ""; 
        
        char letra1 = (char) ('a' + (Math.abs(rand.nextInt()) % 26));
        char letra2 = (char) ('a' + (Math.abs(rand.nextInt()) % 26));                  

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) == letra1) saida += letra2;               
            else saida += entrada.charAt(i);  
        }
        return saida;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        rand.setSeed(4);        

        String input;
        boolean t;  

        input = MyIO.readLine();
        t = SaoIguais(input,"FIM");

        while (!t){
            MyIO.println(altera(rand, input));            
            input=MyIO.readLine();                        
            t = SaoIguais(input,"FIM");  
        }
    }
}
