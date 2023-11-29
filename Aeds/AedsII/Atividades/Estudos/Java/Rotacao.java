class No {
    public int elemento; 
    public No esq, dir; 
    public int nivel; 
    public No(int elemento) {
        this(elemento, null, null, 1);
    }
    public No(int elemento, No esq, No dir, int nivel) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.nivel = nivel;
    }
   public void setNivel() {
        this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
    }
    public static int getNivel(No no) {
        return (no == null) ? 0 : no.nivel;
    }
}
class AVL {
    private No raiz;
    public AVL() {
        raiz = null;
    }
    public void inserir(int x)  {
        raiz = inserir(x, raiz);
    }
    private No inserir(int x, No i)  {
        if (i == null) {
            i = new No(x);
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
        } 
        return balancear(i);
    }
    private No balancear(No no)  {
        if (no != null) {
            int fator = No.getNivel(no.dir) - No.getNivel(no.esq);
            if (Math.abs(fator) <= 1) {
                no.setNivel();
            } else if (fator == 2) {
                int fatorFilhoDir = No.getNivel(no.dir.dir) - No.getNivel(no.dir.esq);
                if (fatorFilhoDir == -1) {
                    no.dir = rotacionarDir(no.dir);
                }
                no = rotacionarEsq(no);
            } else if (fator == -2) {
                int fatorFilhoEsq = No.getNivel(no.esq.dir) - No.getNivel(no.esq.esq);
               if (fatorFilhoEsq == 1) {
                    no.esq = rotacionarEsq(no.esq);
                }
                no = rotacionarDir(no);
            } 
        }
        return no;
    }
    private No rotacionarDir(No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;
        noEsq.dir = no;
        no.esq = noEsqDir;
        no.setNivel(); 
        noEsq.setNivel(); 
        return noEsq;
    }
    private No rotacionarEsq(No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;
        noDir.esq = no;
        no.dir = noDirEsq;
        no.setNivel(); 
        noDir.setNivel(); 
        return noDir;
    }
}
public class Principal {
    public static void main(String[] args) {
            AVL avl = new AVL();
            int array[] = {1,2,3};
            for(int item: array)
                avl.inserir(item);
              
    }
}