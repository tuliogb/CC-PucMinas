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

        while (cont<linha){
            inicio.inf = new Celula(0);
            inicio.inf.sup = inicio;
            inicio = inicio.inf;                                                /* PERDI O MEU INICIO, MAS (INICIO = INICIO.SUP)*linha-1 ocorrencias RECUPERA */

            for (Celula i=inicio;cont<coluna;i=i.dir,cont++){
                i.dir = new Celula(cont);
                i.dir.esq = i;
            }
        }
        for (int i=0;i<linha-1;i++)  inicio = inicio.sup;
     }


     public void mostraMatriz(){
        for (int c=0; c<linha; c++){
            for(Celula i=inicio;i.dir!=null;i=i.dir){
                MyIO.print(i.elemento + "/");
            }
            inicio = inicio.inf;
        }
     }



    public static void main(String[] args) {
        Matriz matriz = new Matriz(3, 3);
        matriz.CriaMatriz();
        matriz.mostraMatriz();
        
    }
}

/*
CLASS CELULA  - >> CONSTRUTORES

class Celula {

    public int elemento;
    public Celula inf, sup, esq, dir;
 

    public Celula(){
       this(0);
    }
 
    public Celula(int elemento){
       this(elemento, null, null, null, null);
    }
 
    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
       this.elemento = elemento;
       this.inf = inf;
       this.sup = sup;
       this.esq = esq;
       this.dir = dir;
    }
 }

*/