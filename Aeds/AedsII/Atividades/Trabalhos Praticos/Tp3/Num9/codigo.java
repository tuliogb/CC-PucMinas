public class Matriz {

    private Celula inicio;
    private  int linha, coluna, contador = 1; static int tam=0;
    public static Matriz[] lista = new Matriz[20];


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



    public Matriz (int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }


    public void CriaMatriz(){
        inicio = new Celula(0);

        for (Celula i=inicio; contador<coluna; i=i.dir,contador++){
            i.dir = new Celula(0);
            i.dir.esq = i;
        }
        contador = 1;

        for (int c=0;c<linha-1;c++){
            inicio.inf = new Celula(0);
            inicio.inf.sup = inicio;
            inicio = inicio.inf;                                                

            for (Celula i=inicio;contador<coluna;i=i.dir,contador++){
                i.dir = new Celula(0);
                i.dir.esq = i;
            }
            contador=1;
        }
        for (int i=0;i<linha-1;i++)  inicio = inicio.sup;

        for (int n=0; n<linha-1; n++){
            for (Celula um=inicio, dois=inicio.inf; dois!=null; um=um.dir, dois=dois.dir){
                um.inf = dois;  dois.sup=um;
            }
            inicio = inicio.inf;
        }
        for (int i=0;i<linha-1;i++)  inicio = inicio.sup;
    }


    public void mostraMatriz(){
        for (int c=0; c<linha; c++){
            for(Celula i=inicio;i!=null;i=i.dir){
                MyIO.print(i.elemento + " ");
            }
            MyIO.print("\n");
            if(inicio.inf != null) inicio = inicio.inf;                           
        }
        for (int i=0;i<linha-1;i++)  inicio = inicio.sup;
    }


    public void inserirValores(){
        for (int c=0; c<linha; c++){
            String valor = MyIO.readLine();
            String[] parte = valor.split(" ");
            int k=0;

            for(Celula i=inicio;i!=null;i=i.dir, k++){
                i.elemento = Integer.parseInt(parte[k]);
            }
            if(inicio.inf != null) inicio = inicio.inf;
            k=0;                         
        }
        for (int i=0;i<linha-1;i++)  inicio = inicio.sup;
    }


    public Matriz somaMatriz(Matriz um, Matriz dois){                                   
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
        }else MyIO.println("Matriz Invalida para soma");

        return somada;
    }


    public Matriz multiplicaMatriz(Matriz um, Matriz dois){                 /* TA PERDENDO O PONTEIRO*/                            
        Matriz mult = new Matriz(um.linha, dois.coluna);

        if((um.coluna == dois.linha)){
            mult.CriaMatriz();

            for(int l=0;l<um.linha;l++){
                for (Celula i=um.inicio;i!=null;i=i.dir){ 
                    for (Celula j=dois.inicio, x=mult.inicio; j!=null;j=j.dir, x=x.dir){
                        x.elemento += i.elemento * j.elemento;
                    }
                    if(dois.inicio.inf != null) dois.inicio = dois.inicio.inf;
                }
                if(um.inicio.inf != null) um.inicio = um.inicio.inf;
                for (int i=0;i<um.coluna-1;i++) dois.inicio = dois.inicio.sup;
                if(mult.inicio.inf != null) mult.inicio = mult.inicio.inf;
            }

            for (int i=0;i<mult.linha-1;i++) mult.inicio = mult.inicio.sup;
            for (int i=0;i<um.linha-1;i++) um.inicio = um.inicio.sup;

        }else MyIO.println("Matriz Invalida para multiplicação");

        return mult;
    }


    public void DiagonalP(){
        if (linha == coluna){
            Celula c = inicio;
            for (int i=0; i<linha; i++){
                MyIO.print(c.elemento + " ");
                if(c.inf != null) c = c.inf.dir;
            }
        }else MyIO.println("Matriz Invalida");

        MyIO.print("\n");
    }


    public void DiagonalS(){
        if (linha == coluna){
            Celula c = inicio;
            for (int n=0;n<coluna-1; n++)   c = c.dir; 

            for (int i=0; i<linha; i++){
                MyIO.print(c.elemento + " ");
                if(c.esq != null) c = c.esq.inf;
            }
        }else MyIO.println("Matriz Invalida");

        MyIO.print("\n");
    }





    public static void main(String[] args) {

        int qtd = MyIO.readInt();
        int q=0;

        for (int i=0; i<qtd; i++){

            int linha = MyIO.readInt();
            int coluna = MyIO.readInt();
            Matriz um = new Matriz(linha,coluna);
            um.CriaMatriz();
            um.inserirValores();
            lista[tam] = um; tam++;


            int linha2 = MyIO.readInt();
            int coluna2 = MyIO.readInt();
            Matriz dois = new Matriz(linha2,coluna2);
            dois.CriaMatriz();
            dois.inserirValores();
            lista[tam] = dois; tam++;

        
            lista[q].DiagonalP();
            lista[q].DiagonalS();


            Matriz soma = new Matriz(lista[q].linha, lista[q+1].coluna);
            soma = soma.somaMatriz(lista[q], lista[q+1]);
            soma.mostraMatriz();

            
            Matriz multiplicacao = new Matriz(lista[q].linha, lista[q+1].coluna);
            multiplicacao = multiplicacao.multiplicaMatriz(lista[q], lista[q+1]);
            multiplicacao.mostraMatriz();


            q=q+2;        
        }
    }
}