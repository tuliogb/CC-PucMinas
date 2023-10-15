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
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;

        primeiro.prox = tmp;
        if(primeiro==ultimo) ultimo=tmp;
        else tmp.prox.ant=tmp;

        tmp = null;
    }

    void mostrar(){
        for(Celula i=primeiro.prox;i!=null;i=i.prox){
            MyIO.println(i.elemento);                     //PORQUE SE EU COLOCO + ',' SAI NUMERO DIFERENTE;
        }
    }

    void inverte(){ 
        Celula i=primeiro.prox,j=ultimo;

        while(i!=j && j.prox!=i){                     // i!=j pra quando for array[impar] e j.prox!=i pra quando for par;
            int tmp = i.elemento;
            i.elemento = j.elemento;
            j.elemento = tmp;

            i=i.prox;
            j=j.ant;
        }
    }

    void inserirValores(int x,int y){
        for(int i=x;i<y;i++){
            inserirInicio(i);
        }
    }

    //FAZER QUICK E SHELLSORT

    public static void main(String[] args) {
        Celula celula = new Celula();
        
        celula.criarLista();
        celula.inserirValores(1,6);
        celula.inverte();
        celula.mostrar();
    }
}