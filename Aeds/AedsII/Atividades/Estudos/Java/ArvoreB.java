class Arvore{
    int elemento;
    Arvore esq,dir,raiz;

    public Arvore(){
        raiz=null;
    }

    public Arvore(int elemento){
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }

    public Arvore(int elemento,Arvore esq,Arvore dir){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }


    public void inserir(int x){
        raiz = inserir(x,raiz);
    }   

    public Arvore inserir(int x,Arvore i){
        if (i==null){  i = new Arvore(x);  }
        else if (x < i.elemento){  i.esq = inserir(x, i.esq);  }
        else if (x > i.elemento){  i.dir = inserir(x, i.dir);  }

        return i;
    }

    public void mostrar(Arvore nodo) {
        if (nodo != null) {
            mostrar(nodo.esq); // Recursivamente percorre a subárvore esquerda
            System.out.print(nodo.elemento + " "); // Exibe o elemento atual
            mostrar(nodo.dir); // Recursivamente percorre a subárvore direita
        }
    }
    
    /*
    public Arvore VerificaNum(int x,Arvore i){
        if(i.elemento == x){ System.out.println("Elemento Encontrado"); }
        else if (x < i.elemento){  i.esq = VerificaNum(x, i.esq);  }
        else if (x > i.elemento){  i.dir = VerificaNum(x, i.dir);  }

        return i;
    }
    */


    public static void main(String[] args) {
        Arvore arvore = new Arvore();

        for(int i=0;i<7;i++){
            arvore.inserir(i);
        }

        arvore.mostrar(arvore.raiz);

        //arvore.VerificaNum(7,arvore.raiz);
        //System.out.println(arvore.raiz.elemento);   /* PRIMEIRA INSECAO (PONTEIRO RAIZ SE TORNOU I E APONTOU PRO NOVO E VOLTOU PRA RAIZ COM ELEMENTO INSERIDO*/
        //System.out.println(arvore.raiz.dir.dir.elemento); /* A POSICAO DO ULTIMO ELEMENTO É RAIZ*ELEMENTOS-1 (QUANDO ESTAO EM SERIE)*/
    }
}