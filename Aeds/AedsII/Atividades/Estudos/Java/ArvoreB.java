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

    Arvore inserir(int x,Arvore i){
        if (i==null){  i = new Arvore(x);  }
        else if (x < i.elemento){  i.esq = inserir(x, i.esq);  }
        else if (x > i.elemento){  i.dir = inserir(x, i.dir);  }

        return i;
    }


    public static void main(String[] args) {
        Arvore arvore = new Arvore();

        arvore.inserir(10);
        arvore.inserir(11);
        arvore.inserir(12);

        //System.out.println(arvore.raiz.elemento);   /* PRIMEIRA INSECAO (PONTEIRO RAIZ SE TORNOU I E APONTOU PRO NOVO E VOLTOU PRA RAIZ COM ELEMENTO INSERIDO*/

        System.out.println(arvore.raiz.dir.dir.elemento); /* A POSICAO DO ULTIMO ELEMENTO É RAIZ*ELEMENTOS-1 (QUANDO ESTAO EM SERIE)*/
    }
}