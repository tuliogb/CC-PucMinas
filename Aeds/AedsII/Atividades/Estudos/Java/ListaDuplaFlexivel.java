class Celula{

    int elemento;
    Celula primeiro,prox,ant,ultimo;



    public Celula(){
        this(0);
    }
    public Celula(int elemento){
        this.elemento = elemento;
        this.prox = null;
        this.ant = null;
    }

    void criarLista(){
        primeiro = new Celula();
        ultimo = primeiro;
    }
    void inserirFim(int x){
        ultimo.prox = new Celula(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }
    void inserirInicio(int x){
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        tmp.ant = primeiro;
        primeiro.prox.ant = tmp;
        primeiro.prox = tmp;
        tmp = null;
    }


    void mostrar(){
        for(Celula i=primeiro.prox;i!=null;i=i.prox){
            MyIO.println(i.elemento);                     //PORQUE SE EU COLOCO + ',' SAI NUMERO DIFERENTE;
        }
    }

    void inverte(){
        
    }


    public static void main(String[] args) {
        Celula celula = new Celula();
        
        celula.criarLista();
        celula.inserirFim(1);
        celula.inserirInicio(2);
        celula.inserirInicio(3);
        celula.inserirInicio(4);
        celula.inserirInicio(5);
        celula.inverte();
        celula.mostrar();
    }
}