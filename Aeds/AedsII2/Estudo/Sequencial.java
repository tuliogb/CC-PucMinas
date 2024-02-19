class Sequencial{

    int[] array = {1,2,3,4,5}

    static boolean Sequencial(int x){
        int tam = array.length;
        boolean resp = false

        for(int i=0; i<tam; i++){
            if(array[i]==x) {
                resp = true;
                i=tam;
            }
        }
    
        return resp;
    }
}

/*
    Melhor: Teta(1)             Primeira Posicao
    Pior: Teta(tam)             Verifica Todas
*/