public class Arvore{

    public int elemento;
    public static int media,cont,maior;
    public Arvore raiz,esq,dir;

    public Arvore(){
        raiz = null;
    }

    public Arvore(int elemento){
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }
    
    void inserir(int elemento){
        cont++;
        raiz = inserir(elemento,raiz); 
    }  
    
    Arvore inserir(int x, Arvore raiz){
      if(raiz==null) raiz = new Arvore(x);
      else if(x < raiz.elemento) raiz.esq = inserir(x,raiz.esq);
      else if(x > raiz.elemento) raiz.dir = inserir(x,raiz.dir);
      
      return raiz;  
    }

    void mostrarArvore(Arvore raiz) {
        if (raiz != null) {
            mostrarArvore(raiz.esq);                    // Recursivamente percorre a subárvore esquerda
            System.out.print(raiz.elemento + " ");      // Exibe o elemento atual
            mostrarArvore(raiz.dir);                    // Recursivamente percorre a subárvore direita
        }
    }


    void mediaArvore(Arvore raiz) {
        if (raiz != null) {
            mediaArvore(raiz.esq);                    // Recursivamente percorre a subárvore esquerda
            media = media + raiz.elemento;              // soma o elemento correte a media
            mediaArvore(raiz.dir);                    // Recursivamente percorre a subárvore direita
        }
    }

    void maiorArvore(Arvore raiz) {
        if (raiz != null) {
            maiorArvore(raiz.esq);                                   // Recursivamente percorre a subárvore esquerda
            if(raiz.elemento > maior) maior = raiz.elemento;         // soma o elemento correte a media
            maiorArvore(raiz.dir);                                   // Recursivamente percorre a subárvore direita
        }
    }

    void maior(Arvore raiz){
        if(raiz.dir == null) MyIO.println(raiz.elemento);
        else maior(raiz.dir);
    }

    void menor(Arvore raiz){
        if(raiz.esq == null) MyIO.println(raiz.elemento);
        else menor(raiz.esq);
    }


    
    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        
        arvore.inserir(10);
        arvore.inserir(12);
        arvore.inserir(7);
        arvore.inserir(11);
        arvore.inserir(15);
        arvore.inserir(6);
        arvore.inserir(8);
        arvore.inserir(9);




        arvore.maior(arvore.raiz.dir);
        arvore.maior(arvore.raiz.esq);
        arvore.menor(arvore.raiz.esq);
        arvore.menor(arvore.raiz.dir);
        
    }
}