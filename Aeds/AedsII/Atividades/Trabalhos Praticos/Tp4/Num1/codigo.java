public class Arvore{

    public int elemento;
    public static Arvore raiz;
    public Arvore esq,dir;

    Arvore(){
        raiz = null;
    }
    Arvore(int elemento){
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }
    

    void inserir(int elemento){
        raiz = inserir(elemento,raiz); 
    }  
    Arvore inserir(int x, Arvore raiz){
      if(raiz==null) raiz = new Arvore(x);
      else if(x < raiz.elemento) raiz.esq = inserir(x,raiz.esq);
      else if(x > raiz.elemento) raiz.dir = inserir(x,raiz.dir);
      
      return raiz;  
    }


    void remover(int elemento){
        raiz = remover(elemento,raiz);
    }
    /*
     * Caso os filhos (esq e dir) forem nulos aquela raiz vai receber nulo e o elemento sera apagado;
     * Caso o filho (esq) for nullo mas o outro (dir) nao for, o filho da direira ira tomar a posicao do pai (raiz);
     * Caso o filho (dir) for nullo mas o outro (esq) nao for, o filho da esquerda ira tomar a posicao do pai (raiz);
     * Caso nenhum for nulo terei que pegar o maior da esquerda e substituir pelo
     */
    Arvore remover(int elemento, Arvore raiz){
        if(raiz==null) MyIO.println("Erro: No Raiz vazio");
        else if(elemento>raiz.elemento) raiz = remover(elemento, raiz.dir);
        else if(elemento<raiz.elemento) raiz = remover(elemento, raiz.esq);
        else if(raiz.dir==null) raiz = raiz.esq;
        else if(raiz.esq==null) raiz = raiz.dir;
        else raiz.esq = MaiorEsquerda(raiz, raiz.esq);
        
        return raiz;
    }


    void mostrarArvore(Arvore raiz) {
        if(raiz != null) {
            mostrarArvore(raiz.esq);                    
            System.out.print(raiz.elemento + " ");      
            mostrarArvore(raiz.dir);                    
        }
    }

    Arvore MaiorEsquerda(Arvore NoVelho, Arvore i){            // O i é o raiz esq que basicamente a cada laco vai acrescentando .dir
        if(i.dir==null){                                       // Se for nulo é porque chegou no maior elemento da esquerda;
            NoVelho.elemento = i.elemento;
            i = i.esq;                                         // O meu i vai apontar pra i esq e depois do retorno o i(i.dir do anterior) nao vai apontar pra i mais e sim pra i.esq;
        }else i.dir = MaiorEsquerda(NoVelho, i.dir);           // Ira retornar voltar ligando a direita.
 
        return i;
    }// Esse metodo vai ate o elemento maior e coloca o ponteiro que aponta pra ele pra apontar pra esquerda dele.
    

    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        
        arvore.inserir(50);
        arvore.inserir(60);
        arvore.inserir(20);
        arvore.inserir(30);
        arvore.inserir(35);
        arvore.inserir(25);
        arvore.inserir(40);
        arvore.inserir(10);
        arvore.inserir(37);
        arvore.inserir(38);

        arvore.mostrarArvore(raiz);
        arvore.remover(50);
        arvore.mostrarArvore(raiz);
    }
}