class salario{
	static int dicionario, descricao;

	static String[] profissao = new String[1000];
	static int[] salario = new int[1000];



	static void start(){
		String input = MyIO.readLine();
		String[] parte = input.split(" ");

		dicionario = Integer.parseInt(parte[0]);
		descricao = Integer.parseInt(parte[1]);

		setaDicionario();
		setaDescricao();
	}

	static void setaDicionario(){
		String input = "";

		for(int i=0; i<dicionario; i++){
			input  = MyIO.readLine();

			String[] parte = input.split(" ");
			profissao[i] = parte[0];
			salario[i] = Integer.parseInt(parte[1]);
		}
	}

	static void setaDescricao(){
		int valor = 0;
		String input = MyIO.readLine();

		for(int k=0; k<descricao; k++){

			while( !input.equals(".") ){
				String[] partes = input.split(" ");

				for(int i=0; i<partes.length; i++){
					for(int j=0; j<dicionario; j++){
						if(partes[i].equals(profissao[j])){
							valor+=salario[j];
						}
					}
				}
				input = MyIO.readLine();
			}
			
			MyIO.println(valor);
			valor=0;
			if(k<descricao-1) input = MyIO.readLine();
		}
	}



	public static void main(String[] args){			// Executar: type .\pubJava.in.txt | java salario > result.txt
		start();
	}
}