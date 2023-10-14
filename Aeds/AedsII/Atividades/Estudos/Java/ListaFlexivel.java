class Celula {

    public int elemento;
    public Celula prox;
    public static Celula primeiro, ultimo;   /*Tem que ser do tipo celula pra apontar pra celula*/
    public int n = 0;

    public Celula(int elemento){
        this.elemento = elemento;
        this.prox = null;
    }

    public Celula(){
        this(0);
    }

    void inserirFim(int x){
        ultimo.prox = new Celula(x);  /*Instancia de Celula foi criada e ultimo prox deixa de ser nulo e aponta pro novo vagao*/
        ultimo = ultimo.prox;         /*O endereco que ultimo.prox aponte (vagao criado) sera aonde o ponteiro ultimo ira apontar*/
        n++;
    }

    void inserir(int x,int p){
        if(p<0||p>n) MyIO.print("Index Invalido");
        else if(p==0) inserirInicio(x);
        else if(p==n) inserirFim(x);
        else{
            Celula i = primeiro;
            for(int j=0;j<p;j++,i=i.prox);
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
            n++;
        }
    }

    void inserirInicio(int x){
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;

        if(primeiro==ultimo) ultimo = tmp;
        tmp = null; 
        n++;
    }

    void removerFim(){
        Celula i; 
        for(i=primeiro;i.prox != ultimo;i=i.prox);  /*O ponteiro i vai percorrendo ate chegar uma casa antes do ultima*/
        ultimo = i; /*O penultimo elemento se torna o ultimo*/
        i.prox = null; /*O elemento que erra penultimo aponta pra nulo, se tornando o ultimo elemento*/

    }

    void mostrar(){
        for(Celula i=primeiro.prox;i!=null;i=i.prox){  /*Comeca apontando pro primeiro elemento inserido que primeiro prox aponta*/
            MyIO.print(i.elemento + ",");                   /*em cada laco ele aponta pro proximo do que ele aponta atualmente*/
        }
    }

    void ligarContrario(){
        //fazer uma copia?
    }

    public static void main(String[] args) {
        Celula celula = new Celula();
        ultimo = primeiro = new Celula();

        celula.inserirFim(10);

    }
}