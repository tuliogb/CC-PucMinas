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

    
    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        
        arvore.inserir(3);
        arvore.inserir(1);
        arvore.inserir(5);
        arvore.inserir(4);
        arvore.inserir(8);
        arvore.inserir(2);


        //arvore.mostrarArvore(arvore.raiz);
        //arvore.mediaArvore(arvore.raiz);
        //System.out.println(media/cont);
        arvore.maiorArvore(arvore.raiz);
        System.out.println(maior);
        
    }
}