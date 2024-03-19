class WarUp2{

    static String[] array = new String[1000];
    static int resp = 0;

    static void start(){
        int tam = MyIO.readInt();
        String input = "";

        for(int i=0; i<tam; i++){
            input = MyIO.readLine();
            array[i] = input;
        }

        ordena(tam);
        contador(tam);
        //mostrar(tam);
        solucao();
    }

    static void ordena(int tam){
        for(int i=0; i<tam-1; i++){
            int menor = i;

            for(int j=i+1; j<tam; j++){
                if(array[j].compareTo(array[menor]) < 0) menor = j;
            }
            swap(i,menor);
        }
    }

    static void swap(int i, int menor){
        String x = array[i];
        array[i] = array[menor];
        array[menor] = x;
    }

    static void mostrar(int tam){
        for(int i=0; i<tam; i++){
            MyIO.println(array[i]);
        }
    }

    static void contador(int tam){
        for(int i=0; i<tam; i++){
            if(  !(array[i].equals(array[i+1])) ) resp++;
        }
    }

    static void solucao(){
        MyIO.print("Falta(m) ");
        MyIO.print(151-resp);
        MyIO.println(" pomekon(s).");
    }


    public static void main(String[] args){
        start();
    }
}