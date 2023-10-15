class Espelho{

	 static void espelho (int n1, int n2){
		String resp = "";
	       
	       	for(int i=n1;i<=n2;i++){
			resp+=i;
		}
		for(int i=resp.length()-1;i>=0;i--){
			resp = resp + resp.charAt(i);
		}
		MyIO.println(resp);
	 }

		
	public static void main(String[] args){
	//	int c = MyIO.readInt(); /*quantidade e vezes que o loop vai ocorrer */

		for(int i=0;i<3;i++){

				
			int n1 = MyIO.readInt();
			int n2 = MyIO.readInt();

			espelho(n1,n2);

		}
	}

}
