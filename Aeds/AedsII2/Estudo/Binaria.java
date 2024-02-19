class Binaria{

    int[] array = {1,2,3,4,5}

    static boolean Binarial(int x){
        int tam = array.length;
        boolean resp = false
        int esq=0; dir=tam-1, meio, diferenca;

        while(esq<=dir){
            meio = (dir+esq)/2;
            diferenca = x - array[meio];

            if(diferenca = 0){
                resp = true;
                esq=tam;
            }
            else if(diferenca > 0){
                esq = meio+1;
            }
            else{
                dir=meio-1;
            }
        }
    
        return resp;
    }
}

/*
    Melhor: Teta(1)
    Pior: Teta(log(tam))
*/