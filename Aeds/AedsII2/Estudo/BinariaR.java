class Binariar{

    int[] array = {1,2,3,4,5}

    static boolean BinarialR(int x, int esq, int dir){
        boolean resp = false;

        if(esq<=dir){
            int meio = (dir+esq)/2;
            int diferenca = x - array[meio];

            if(diferenca = 0){
                return true;
            }
            else if(diferenca > 0){
                BinariaR(x,meio+1,dir);
            }
            else{
                BinariaR(x,esq,meio-1);
            }
        }
        
        return resp;
    }
}

/*
    Melhor: Teta(1)
    Pior: Teta(log(tam))
*/