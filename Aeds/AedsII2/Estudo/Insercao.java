class Insercao{

    int[] array = {10,2,100,20,7,32,43,59}


    static void insercao(){
        int tam  = array.length;

        for(int i=1; i<tam; i++){
            int temp = array[i];
            int j = i-1;

            while((j>=0) && array[j]>temp){
                array[j+1]=array[j];                // Contrario do selecao;
                j--;
            }
            array[j+1]=temp;
        }
    }
}

/*
    Melhor: Teta(n)
    Pior: Teta(n²)
*/