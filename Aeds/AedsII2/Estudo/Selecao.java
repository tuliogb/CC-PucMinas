class Selecao{

    int[] array = {10,2,100,20,7,32,43,59}


    static void selecao(){
        int tam = array.length;

        for(int i=0; i<tam-1; i++){
            int menor = i;
            for(int j=i+1; j<tam; j++){
                if(array[menor]>array[j]) menor=j;
            }
            //swap(menor,i);
        }
    }
}

/*
    Melhor: Teta(n²)
    Pior: Teta(n²)
*/