class Q1{  

    static void start(){
        int n = MyIO.readInt();
        int qtdCaixas = 0;
        String bombons = "";

        for(int i=0; i<n; i++){
            qtdCaixas = MyIO.readInt();
            bombons = MyIO.readLine();

            setaDoces(qtdCaixas,bombons);
        }
    }

    static void setaDoces(int qtd, String bombons){
        String[] array = bombons.split(" ");
        int[] doces = new int[array.length];

        for(int i=0; i<array.length; i++){
            doces[i] = Integer.parseInt(array[i]);            
            //System.out.println(array[i]);
        }
        verificaBombom(doces);
    }

    static void verificaBombom(int[] array){
        int menor = 0;

        for(int i=0; i<array.length; i++){
            if(array[i] < array[menor]) menor = i;
        }
        soluciona(array,menor);
    }

    static void soluciona(int[] array, int menor){
        int comidos = 0;

        for(int i=0; i<array.length; i++){
            if(array[i]>array[menor]){
                comidos+= array[i]-array[menor];
            } 
        }

        System.out.println(comidos);
    }



    public static void main(String[] args){
        start();
    }
}