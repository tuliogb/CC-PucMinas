import java.util.*;

class codPalavras{

	public static void start(){
		Scanner sc = new Scanner(System.in);
		String input = "";

		while( !(input=sc.nextLine()).equals("FIM") ){
			decifra(input);
		}
	}

	static void decifra(String input){
		String x = "";
		char qtd = 0;
		Boolean acabou = false;

		for(int i=0; i<input.length(); i++){

			if(input.charAt(i)=='['){
				qtd = input.charAt(i-1);
				i++;

				while(!acabou){
					x+=input.charAt(i);
					if(input.charAt(i+1)==']') acabou=true;
					else i++;
				}
				repete(x,qtd);
			}
			x = "";
			acabou=false;
		}
	}
	
	static void repete(String x, char c){
		int qtd = Character.getNumericValue(c);

		for(int i=0; i<qtd; i++){
			System.out.print(x);
		}
	}

	public static void main(String[] args){
		decifra("3[a]2[bc]");
	}
	
}