class No{
    No esq, dir;
    char letra;
    //No2 raiz;

    No(){ this("vazio"); }
    No(String elemento){
        this.letra = elemento.charAt(0);
        this.esq = null;
        this.dir = null;
    }
}

public class Arvore{
    public No raiz;

    Arvore(){
        this.raiz = null;
    }

    void inserir(String elemento){
        raiz = inserir(raiz, elemento);
    }

    No inserir(No i, String elemento){
        if(i==null) i = new No(elemento);
        else if(elemento.charAt(0) < i.letra)  i.esq = inserir(i.esq, elemento);
        else if(elemento.charAt(0) > i.letra)  i.dir = inserir(i.dir, elemento);
        else System.out.println("REPETIDO");

        return i;
    }

    void mostrar(No i){
        if(i!=null){
            System.out.println(i.letra);
            mostrar(i.esq);
            mostrar(i.dir);
        }
    }

    void mostrarFolhas(No i){
        if(i!=null){
            mostrar(i.esq);
            if(i.esq==null && i.dir==null) System.out.println(i.letra);
            mostrar(i.dir);
        }
    }

    String pesquisar(String elemento){
        return pesquisar(raiz, elemento) ? "SIM" : "NAO";
    }

    boolean pesquisar(No i, String elemento){
        boolean resp;
        if(i==null) resp = false;
        else if(elemento.charAt(0) < i.letra) resp = pesquisar(i.esq, elemento);
        else if(elemento.charAt(0) > i.letra) resp = pesquisar(i.dir, elemento);
        else resp = true;

        return resp;
    }

    void mostrarFolhas(No i){
        if(i!=null){
            mostrar(i.esq);
            
            mostrar(i.dir);
        }
    }


    public static void main(String[] args){
        Arvore um  = new Arvore();
        
        um.inserir("Marcelo");
        um.inserir("Emanuel");
        um.inserir("Samuel");
        um.inserir("Fernando");
        um.inserir("Tulio");
        um.inserir("Asaf");
        um.inserir("Pedro");
        um.inserir("Miguel");

        //um.mostrar(um.raiz);
        //System.out.println(um.pesquisar("ta"));
    }
}