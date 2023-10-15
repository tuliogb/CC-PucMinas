class Combinador{
	
	 static void combinacao(String a, String b){

		int tamanho = a.length() + b.length(); 
		String resp = "";

		for(int i=0;i<tamanho;i++){
		
			if(i<a.length()){
				if(a.charAt(i) == ' ');
				else resp = resp + a.charAt(i);
			}
			if(i<b.length()){
			       if(b.charAt(i) == ' ' );
			       else resp = resp + b.charAt(i);
			}
		}

		MyIO.print(resp);

	}


	public static void main(String[] args){

		String um = MyIO.readLine();
		String dois = MyIO.readLine();

		combinacao(um,dois);


	}


}

