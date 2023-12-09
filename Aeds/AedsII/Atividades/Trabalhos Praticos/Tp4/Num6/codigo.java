import java.io.BufferedReader;
import java.io.FileReader;

public class Trie {

    public static No raiz;
    public static int comp=0;


    public Trie(){
        raiz = new No();
    }

    public static void inserir(String nome){
        inserir(nome, raiz, 0);
    }

    private static void inserir(String nome, No no, int i){
        if(no.prox[nome.charAt(i)]==null){
            no.prox[nome.charAt(i)] = new No(nome.charAt(i));

            if(i==nome.length()-1) no.prox[nome.charAt(i)].folha = true;
            else inserir(nome, no.prox[nome.charAt(i)], i+1);                             // MANDA A RAIZ ATUAL PRA LIGAR A PROXIMA LETRA JUNTA DESSA

        } else if (no.prox[nome.charAt(i)].folha==false && i<nome.length()-1)   inserir(nome, no.prox[nome.charAt(i)], i+1);
    }

    public void mostrar(){
        mostrar("", raiz);
    }

    public void mostrar(String s, No no) {
        if(no.folha == true)    MyIO.println(s + no.elemento);
        else {
            for(int i = 0; i < no.prox.length; i++){
                if(no.prox[i] != null){
                    mostrar(s+no.elemento, no.prox[i]);
                }
            }
        }
    }

    public void pesquisarElementos(){
        String input =  MyIO.readLine();
        while(!input.equals("FIM")){
            pesquisarElemento(input, raiz, 0, false);
            input = MyIO.readLine();
        }
    }

    private void pesquisarElemento(String nome, No no, int k, boolean achou) {
        if (k==nome.length()-1){
            if(no.elemento==nome.charAt(k)) MyIO.println(nome + " SIM");
            else MyIO.println(nome + " NAO");
        }else{
            for (int i = 0; i < no.prox.length; i++) {
                if (no.prox[i] != null && no.prox[i].elemento==nome.charAt(k)) {
                    achou = true;
                    pesquisarElemento(nome, no.prox[i], k+1, true);
                
                }else achou = false;
            }
        }
    }

    /* ----------------------------------------------------------------------------------------------------------- INICIO CLASSE JOGADOR */
    public static class Jogador{

        private Integer id,altura,peso,anoNascimento;
        private String nome,universidade,cidadeNascimento,estadoNascimento;

        public String getEstadoNascimento() {
            return estadoNascimento;
        }
        public String getCidadeNascimento() {
            return cidadeNascimento;
        }
        public String getUniversidade() {
            return universidade;
        }
        public String getNome() {
            return nome;
        }
        public int getAnoNascimento() {
            return anoNascimento;
        }
        public int getPeso() {
            return peso;
        }
        public int getAltura() {
            return altura;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public void setAltura(int altura) {
            this.altura = altura;
        }
        public void setPeso(int peso) {
            this.peso = peso;
        }
        public void setAnoNascimento(int anoNascimento) {
            this.anoNascimento = anoNascimento;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }
        public void setUniversidade(String universidade) {
            this.universidade = universidade;
        }
        public void setCidadeNascimento(String cidadeNascimento) {
            this.cidadeNascimento = cidadeNascimento;
        }
        public void setEstadoNascimento(String estadoNascimento) {
            this.estadoNascimento = estadoNascimento;
        }

        public Jogador(){
            this.id = null;
            this.nome = null;
            this.altura = null;
            this.peso = null;
            this.universidade = null;
            this.anoNascimento = null;
            this.cidadeNascimento = null;
            this.estadoNascimento = null;
        }

        public Jogador cloneJogador() {
            Jogador clone = new Jogador();
            clone.id = this.getId();
            clone.nome = this.getNome();
            clone.altura = this.getAltura();
            clone.peso = this.getPeso();
            clone.universidade = this.getUniversidade();
            clone.anoNascimento = this.getAnoNascimento();
            clone.cidadeNascimento = this.getCidadeNascimento();
            clone.estadoNascimento = this.getEstadoNascimento();
            return clone;
        }

        public void inserirElementos(){
            String input = MyIO.readLine();
            while (!input.equals("FIM")) {
                try {
                    int id = Integer.parseInt(input);
                    Jogador jogador = new Jogador();

                    jogador.SetaId(id);
                    inserir(jogador.nome);

                } catch (Exception e) {}
                input = MyIO.readLine();
            } 
        }

        public void SetaId(int x) throws Exception {
            FileReader file = new FileReader("players.csv"); 
            BufferedReader buffer = new BufferedReader(file);
            String linha;

            while ((linha = buffer.readLine()) != null) {
                String numId = linha.substring(0, linha.indexOf(","));

                try{
                    if(Integer.parseInt(numId) == x) {
                        this.setaDados(linha);
                        //this.mostraDados();
                        break;
                    }
                }catch(NumberFormatException e){}
            }
            buffer.close();
            file.close();
        }

        public void setaDados(String linha){
            String[] parte = linha.split(",");

            this.setId(Integer.parseInt(parte[0]));
            this.setNome(parte[1]);
            this.setAltura(Integer.parseInt(parte[2]));
            this.setPeso(Integer.parseInt(parte[3]));
            if(parte.length>4 && !parte[4].isEmpty()) this.setUniversidade(parte[4]); else this.setUniversidade("nao informado");
            this.setAnoNascimento(Integer.parseInt(parte[5]));
            if (parte.length>6 && !parte[6].isEmpty()) this.setCidadeNascimento(parte[6]); else this.setCidadeNascimento("nao informado");
            if (parte.length>7 && !parte[7].isEmpty()) this.setEstadoNascimento(parte[7]); else this.setEstadoNascimento("nao informado");

        }

        public void mostraDados(){
            MyIO.println("[" + this.getId() + " ## " + this.getNome() + " ## " + this.getAltura() + " ## " + this.getPeso() + " ## " + this.getAnoNascimento() + " ## " 
            + this.getUniversidade() + " ## " + this.getCidadeNascimento() + " ## " + this.getEstadoNascimento() + "]");
        }
    }
    /* -------------------------------------------------------------------------------------------------------------- FIM CLASSE JOGADOR */

    /* ---------------------------------------------------------------------------------------------------------------- INICIO CLASSE NO */
    public static class No {
        public char elemento;
        public final int tamanho = 255;                                 // MAX ASCII PRA CARACTER
        public No[] prox;                                               // CADA NO TERA UM ARRAY DE 255 POSSIBILIDADES
        public boolean folha;
        
        public No (){
           this(' ');
        }
     
        public No (char elemento){
           this.elemento = elemento;
           prox = new No [tamanho];                                     // CADA NO TEM A POSSIBILIDADE DE 255 APONTAMENTOS
           for (int i = 0; i < tamanho; i++) prox[i] = null;            // SETA AS 255 SUBSEQUENTES POSICOES COMO NULL
           folha = false;
        }
     
        public static int converte (char x){
           return (int)x;
        }
     }
    /* ------------------------------------------------------------------------------------------------------------------- FIM CLASSE NO */



    public static void main(String[] args) {
        Trie arvore = new Trie();
        Jogador jogador = new Jogador();

        jogador.inserirElementos();
        arvore.pesquisarElementos();
    }
}