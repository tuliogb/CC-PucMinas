public class Matriz {

    public static class Celula {
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

    private Celula inicio;
    private  int linha, coluna, cont = 1;



    public Matriz (int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    public void CriaMatriz(){
        inicio = new Celula(0);

        for (Celula i=inicio;cont<coluna;i=i.dir,cont++){
            i.dir = new Celula(0);
            i.dir.esq = i;
        }
        cont = 1;

        for (int c=0;c<linha-1;c++){
            inicio.inf = new Celula(0);
            inicio.inf.sup = inicio;
            inicio = inicio.inf;                                                /* PERDI O MEU INICIO, MAS (INICIO = INICIO.SUP)*linha-1 ocorrencias RECUPERA */

            for (Celula i=inicio;cont<coluna;i=i.dir,cont++){
                i.dir = new Celula(0);
                i.dir.esq = i;
            }
            cont=1;
        }
        for (int i=0;i<linha-1;i++)  inicio = inicio.sup;
    }

    public void mostraMatriz(){
        for (int c=0; c<linha; c++){
            for(Celula i=inicio;i!=null;i=i.dir){
                MyIO.print("| " + i.elemento + " |");
            }
            MyIO.print("\n");
            if(inicio.inf != null) inicio = inicio.inf;                            /* NO ULTIMO LACO EU TO FAZENDO, ENTAO TA PONTANDO PRA NULO, TO PERDENDO O INICIO */
        }
        for (int i=0;i<linha-1;i++)  inicio = inicio.sup;
        MyIO.print("\n");
    }

    public void inserirValores(){
        for (int c=0; c<linha; c++){
            for(Celula i=inicio;i!=null;i=i.dir){
                i.elemento = MyIO.readInt();
            }
            if(inicio.inf != null) inicio = inicio.inf;                           
        }
        for (int i=0;i<linha-1;i++)  inicio = inicio.sup;
    }

    public Matriz somaMatriz(Matriz um, Matriz dois){                                   /* CONFERIR AMANHA */
        Matriz somada = new Matriz(linha, coluna);

        if((um.linha == dois.linha) && (um.coluna == dois.coluna)){
            somada.CriaMatriz();

            for (int c=0; c<linha; c++){
                for(Celula i=um.inicio, j=dois.inicio, s=somada.inicio;i!=null;i=i.dir, j=j.dir, s=s.dir){
                    s.elemento = i.elemento + j.elemento;
                }
                if(um.inicio.inf != null) um.inicio = um.inicio.inf;
                if(dois.inicio.inf != null) dois.inicio = dois.inicio.inf;  
                if(somada.inicio.inf != null) somada.inicio = somada.inicio.inf;                              
            }
            for (int i=0;i<linha-1;i++){
                um.inicio = um.inicio.sup;
                dois.inicio = dois.inicio.sup;
                somada.inicio = somada.inicio.sup; 
            }  
        }
        return somada;
    }

    public Matriz multiplicaMatriz(Matriz um, Matriz dois){                                   /* CONFERIR AMANHA */
        Matriz mult = new Matriz(um.linha, dois.coluna);

        if((um.coluna == dois.linha)){
            mult.CriaMatriz();

            for(int l=0;l<um.linha;l++){
                for (Celula i=um.inicio;i!=null;i=i.dir){ 
                    for (Celula j=dois.inicio, x=mult.inicio; j!=null;j=j.dir, x=x.dir){
                        MyIO.println("\nX.elemento antes de receber a multiplicacao: " + x.elemento);
                        x.elemento += i.elemento * j.elemento;
                        MyIO.println("Multipliquei:" + i.elemento + "*" + j.elemento + " amazenei em" + x.elemento);
                    }
                    if(dois.inicio.inf != null) dois.inicio = dois.inicio.inf;
                }
                if(um.inicio.inf != null) um.inicio = um.inicio.inf;
                for (int i=0;i<um.coluna-1;i++) dois.inicio = dois.inicio.sup;
                if(mult.inicio.inf != null) mult.inicio = mult.inicio.inf;
            }

            for (int i=0;i<um.linha-1;i++) mult.inicio = mult.inicio.sup;

        }

        return mult;
    }



    public static void main(String[] args) {
        Matriz matrizUm = new Matriz(2, 2);                 // type .\pub.in | java Jogador > result.txt
        Matriz matrizDois = new Matriz(2, 2);

        matrizUm.CriaMatriz();
        matrizDois.CriaMatriz();

        matrizUm.inserirValores();
        matrizDois.inserirValores();

        matrizUm.mostraMatriz();
        matrizDois.mostraMatriz();

        /* 
        Matriz soma = new Matriz(2,2);
        soma = soma.somaMatriz(matrizUm,matrizDois);
        MyIO.println("\nSOMA M1+M2");
        soma.mostraMatriz();
        */
         
        Matriz multiplicacao = new Matriz(2, 2);
        multiplicacao = multiplicacao.multiplicaMatriz(matrizUm, matrizDois);
        MyIO.println("\nMULTIPLICACAO M1*M2");
        multiplicacao.mostraMatriz();
    

    }
}