import java.util.*;


class No{
    public No esq, dir;
    public int nivel;
    public int elemento;


    No(int elemento){
        this.elemento = elemento;
        this.nivel = 1;
        this.esq = null;
        this.dir = null;
    }

    void setNivel(){
        this.nivel = 1 + Math.max(getNivel(dir), getNivel(esq));
    }

    int getNivel(No i){
        return (i==null) ? 0 : i.nivel;
    }

}

public class ArvoreAvl {

    public No raiz;
    static Scanner sc = new Scanner(System.in);


    void inserirDados() {
        String input = "";

        while(sc.hasNextLine()){
            input = sc.nextLine();
            raiz = inserir(raiz, Integer.parseInt(input));
            mostrarArvore(raiz); System.out.println();
        }
    }

    No inserir(No i, int elemento){
        if(i==null) i = new No(elemento);
        else if(elemento < i.elemento) i.esq = inserir(i.esq, elemento);
        else if(elemento > i.elemento) i.dir = inserir(i.dir, elemento);
       
        return balancear(i);
    }
    
    No balancear(No i){
        if(i!=null){
            int fator = i.getNivel(i.dir) - i.getNivel(i.esq);

            if(Math.abs(fator) <= 1) i.setNivel();
            else if(fator == 2){
                if(i.getNivel(i.dir.dir) - i.getNivel(i.dir.esq) == -1) i.dir = rotacionarDir(i.dir); 
                i = rotacionarEsq(i);
            }
            else if(fator == -2){
                if(i.getNivel(i.esq.dir) - i.getNivel(i.esq.esq) == 1) i.esq = rotacionarEsq(i.esq); 
                i = rotacionarDir(i);
            }
        }
        return i;
    }

	No rotacionarDir(No no) {
		No noEsq = no.esq;
		No noEsqDir = noEsq.dir;

		noEsq.dir = no;
		no.esq = noEsqDir;
		no.setNivel(); 
		noEsq.setNivel(); 

		return noEsq;
	}

	No rotacionarEsq(No no) {
		No noDir = no.dir;
		No noDirEsq = noDir.esq;

		noDir.esq = no;
		no.dir = noDirEsq;
		no.setNivel(); 
		noDir.setNivel(); 

		return noDir;
	}

    void mostrarArvore(No i) {
        if (i!=null){
            System.out.print(i.elemento + " ");
            mostrarArvore(i.esq);
            mostrarArvore(i.dir);
        }
    }



    public static void main(String[] args) {
        ArvoreAvl um = new ArvoreAvl();
        um.inserirDados();
        um.mostrarArvore(um.raiz);
    }
}
