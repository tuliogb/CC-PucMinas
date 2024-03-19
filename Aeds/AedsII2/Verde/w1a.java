class Prova1a{                  // 25 minutos

    static String[] linguas = new String[200];
    static String[] fnatal = new String[200]; 
    static int n = 0;

    static void lerEntrada(){
        n = MyIO.readInt();



        for(int i=0; i<n; i++){
            linguas[i] = MyIO.readLine();
            fnatal[i] = MyIO.readLine();
        }
    }

    static void lerSaida(){
        int m = MyIO.readInt();

        String nome = "";
        String lingua = "";

        for(int i=0; i<m; i++){
            nome = MyIO.readLine();
            lingua = MyIO.readLine();

            resolve(nome, lingua);
        }
    }

    static void resolve(String nome, String lingua){
        int x = 0;
        
        for(int i=0; i<n; i++){
            if(linguas[i].equals(lingua)) x=i;
        }

        System.out.println(nome);
        System.out.println(fnatal[x]);
    }

    public static void main(String[] args){
        lerEntrada();
        lerSaida();
    }
}