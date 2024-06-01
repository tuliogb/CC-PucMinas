import java.util.*;

class Celula{
    int elemento;
    Celula esq, dir, sup, inf;

    Celula(){this(0);}
    Celula(int elemento){
        this.esq = null;
        this.dir = null;
        this.sup = null;
        this.inf = null;
        this.elemento = elemento;
    }
}

public class Matriz{

    Celula inicio;
    static int tam;
    int linha, coluna;
    static Scanner sc = new Scanner(System.in);

    Matriz(){
        this.inicio = null;
        this.linha = 0;
        this.coluna = 0;
    }

    static Celula matriz(int l, int c){
        Celula inicio = new Celula();
        Celula linha=inicio, coluna=inicio;

        for(int col=1; col<c; col++, coluna=coluna.dir){
            coluna.dir = new Celula();
            coluna.dir.esq = coluna;
        }
        coluna=inicio;

        for(int lin=l; lin>1; lin--){
            Celula marcaLinha = linha;
            coluna = linha.inf = new Celula();

            for(int col=0; col<c-1; col++, coluna=coluna.dir){
                coluna.dir = new Celula();
                coluna.dir.esq = coluna;
            }

            coluna = linha.inf;

            for(int col=0; col<c; col++, linha=linha.dir, coluna=coluna.dir){
                linha.inf = coluna;
                coluna.sup = linha;
            }

            linha = marcaLinha.inf;
            coluna = linha.inf;
        }

        return inicio;
    }

    static void mostrar(Matriz x) {
        Celula linhaAtual = x.inicio;
        Celula colunaAtual;

        while (linhaAtual != null) {
            colunaAtual = linhaAtual;

            while (colunaAtual != null) {
                System.out.printf("%d ", colunaAtual.elemento);
                colunaAtual = colunaAtual.dir;
            }
            System.out.println();
            linhaAtual = linhaAtual.inf;
        }
    }

    static void setaValor(Celula inicio){
        Celula linhaAtual = inicio;
        Celula colunaAtual = inicio;

        while (linhaAtual != null) {
            colunaAtual = linhaAtual;

            String input = sc.nextLine();
            String[] parte = input.split(" ");

            for(int i=0; i<parte.length; i++){
                colunaAtual.elemento = Integer.parseInt(parte[i]);
                colunaAtual = colunaAtual.dir;
            }
            linhaAtual = linhaAtual.inf;
        }
    }

    static void diagonalPrincipal(Celula inicio){
        Celula i=inicio; 
        
        while(i!=null){
            System.out.print(i.elemento + " ");
            if(i.dir!=null) i=i.dir.inf;
            else i=null;
        }
        System.out.print("\n");
    }

    static void diagonalSecundaria(Celula inicio){
        Celula i=inicio;

        for(/**/; i.dir!=null; i=i.dir);

        while(i!=null){
            System.out.print(i.elemento + " ");
            if(i.esq!=null) i=i.esq.inf;
            else i=null;
        }
        System.out.print("\n");
    }

    static Matriz soma(Matriz um, Matriz dois) {
        Matriz nova = new Matriz();
        nova.inicio = matriz(um.linha, um.coluna);
        nova.linha = um.linha;
        nova.coluna = um.coluna;

        Celula colum = um.inicio, coldois = dois.inicio;
        Celula linhaum = um.inicio, linhadois = dois.inicio;

        if (um.coluna == dois.coluna && um.linha == dois.linha) {
            Celula colnova = nova.inicio;
            Celula linhanova = nova.inicio;

            while (linhaum != null) {
                colum = linhaum;
                coldois = linhadois;
                colnova = linhanova;

                while (colum != null) {
                    colnova.elemento = colum.elemento + coldois.elemento;

                    colum = colum.dir;
                    coldois = coldois.dir;
                    colnova = colnova.dir;
                }

                linhaum = linhaum.inf;
                linhadois = linhadois.inf;
                linhanova = linhanova.inf;
            }
        }
        return nova;
    }

    static Matriz multiplicacao(Matriz um, Matriz dois) {
        Matriz nova = new Matriz();
        nova.inicio = matriz(um.linha, dois.coluna);
        nova.linha = um.linha;
        nova.coluna = dois.coluna;

        Celula inicioum = um.inicio, iniciodois = dois.inicio;
        Celula colum = um.inicio, coldois = dois.inicio;
        Celula linhaum = um.inicio, linhadois = dois.inicio;

        if (um.coluna == dois.linha){
            Celula inicionova = nova.inicio;
            Celula colnova = nova.inicio;
            Celula linhanova = nova.inicio;

            while (linhaum != null) {

                while (linhadois != null) {

                    while (coldois != null) {
                        colnova.elemento += colum.elemento * coldois.elemento;
                        coldois = coldois.dir;
                        colnova = colnova.dir;
                    }
                    colum = colum.dir;
                    colnova = linhanova;
                    linhadois = linhadois.inf;
                    coldois = linhadois;
                }

                linhanova = linhanova.inf;
                linhaum = linhaum.inf;
                linhadois = iniciodois;

                colum = linhaum;
                coldois = linhadois;
                colnova = linhanova;
            }
        }
        return nova;
    }



    static void start(){
        
        int n = Integer.parseInt(sc.nextLine());

        for(int i=0; i<n; i++){
            int linha1 = Integer.parseInt(sc.nextLine());
            int coluna1 = Integer.parseInt(sc.nextLine());

            Matriz um = new Matriz();
            um.inicio = matriz(linha1, coluna1);
            um.linha = linha1;
            um.coluna = coluna1;
            setaValor(um.inicio);

            diagonalPrincipal(um.inicio);
            diagonalSecundaria(um.inicio);


            int linha2 = Integer.parseInt(sc.nextLine());
            int coluna2 = Integer.parseInt(sc.nextLine());

            Matriz dois = new Matriz();
            dois.inicio = matriz(linha2, coluna2);
            dois.linha = linha2;
            dois.coluna = coluna2;
            setaValor(dois.inicio);

            Matriz tres = soma(um, dois);
            mostrar(tres);
            Matriz quatro = multiplicacao(um, dois);
            mostrar(quatro);
        }
    }

    public static void main(String[] args){
        start();
    }
}