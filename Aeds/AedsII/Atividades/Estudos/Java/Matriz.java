public class Matriz {

    private Celula inicio;
    private int linha, coluna, cont = 1;



    public Matriz (int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
     }

     public void CriaMatriz(){
        inicio = new Celula(0);

        for (Celula i=inicio;cont<coluna;i=i.dir,cont++){
            i.dir = new Celula(cont);
            i.dir.esq = i;
        }
        cont = 1;

        for (int c=0;c<linha-1;c++){
            inicio.inf = new Celula(0);
            inicio.inf.sup = inicio;
            inicio = inicio.inf;                                                /* PERDI O MEU INICIO, MAS (INICIO = INICIO.SUP)*linha-1 ocorrencias RECUPERA */

            for (Celula i=inicio;cont<coluna;i=i.dir,cont++){
                i.dir = new Celula(cont);
                i.dir.esq = i;
            }
            cont=1;
        }
        for (int i=0;i<linha-1;i++)  inicio = inicio.sup;
     }


     public void mostraMatriz(){
        for (int c=0; c<linha; c++){
            for(Celula i=inicio;i!=null;i=i.dir){
                MyIO.print(i.elemento + "/");
            }
            MyIO.print("\n");
            inicio = inicio.inf;
        }
     }



    public static void main(String[] args) {
        Matriz matriz = new Matriz(3, 3);
        matriz.CriaMatriz();
        matriz.mostraMatriz();
        
    }
}

