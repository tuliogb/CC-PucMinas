import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class No {

    public static No raiz;

    public No dir, esq;
    public No2 raiz2;
    public int chave;
    public boolean achou=false;
    public static int comp=0;
    public static Long tempo = (long) 0;

    public No(){
        this(0);
    } 

    public No(int chave){
        this.chave = chave;
        this.dir = this.esq = null;
        this.raiz2 = null;
    }



    public void formarArvore(){
        int[] array = {7, 3, 11, 1, 5, 9, 12, 0, 2, 4, 6, 8, 10, 13 , 14};

        for (int i=0; i<15; i++){
            setaAB1(array[i]);
        }
    } 

    public void setaAB1(int chave){
        raiz = construir(chave, raiz);
    }

    private No construir(int elemento, No raiz){
        if (raiz==null) raiz = new No(elemento);
        else if (elemento < raiz.chave) raiz.esq = construir(elemento, raiz.esq);
        else if (elemento > raiz.chave) raiz.dir = construir(elemento, raiz.dir);

        return raiz;
    }

    public void mostrarCelulas(No raiz){
        if(raiz!=null){
            mostrarCelulas(raiz.esq);

            MyIO.print(raiz.chave + "- "); 
            mostrarCelulasNo2(raiz.raiz2);
            MyIO.print("\n"); 

            mostrarCelulas(raiz.dir);
        }
    }

    public void mostrarCelulasNo2(No2 raiz){
        if(raiz!=null){
            mostrarCelulasNo2(raiz.esq);
            MyIO.print(raiz.chave + "-> ");
            mostrarCelulasNo2(raiz.dir);
        }
    }




    public static void inserirJogador(Jogador jogador){
        posicaoDeInserir(jogador, raiz);
    }

    public static void posicaoDeInserir(Jogador jogador, No raiz){
        if(raiz!=null){
            comp++;
            if ((jogador.altura % 15)==raiz.chave){
                raiz.raiz2 = setaNo2(jogador, raiz.raiz2);
            }
            else if ((jogador.altura % 15) < raiz.chave) posicaoDeInserir(jogador, raiz.esq);
            else if ((jogador.altura % 15) > raiz.chave) posicaoDeInserir(jogador, raiz.dir);
        }
    }

    public static No2 setaNo2(Jogador jogador, No2 raiz){
        if (raiz==null) raiz = new No2(jogador.nome, jogador);
        else if (jogador.nome.compareTo(raiz.chave)<0) raiz.esq = setaNo2(jogador, raiz.esq);
        else if (jogador.nome.compareTo(raiz.chave)>0) raiz.dir = setaNo2(jogador, raiz.dir);

        return raiz;
    }




    public void pesquisaJogador(No raiz){                                     
            String input = MyIO.readLine();
            while (!input.equals("FIM")) {
                MyIO.print(input + " raiz");
                pesquisaNome(input, raiz);

                if(achou) MyIO.println(" SIM");
                else MyIO.println(" NAO");
                achou=false;

                input = MyIO.readLine();
            } 
    }

    public void pesquisaNome(String Nome, No raiz){
        if(raiz!=null){
            pesquisaNome(Nome, raiz.raiz2);

            if(!achou) MyIO.print(" esq");
            pesquisaNome(Nome, raiz.esq);
            
            if(!achou) MyIO.print(" dir");
            pesquisaNome(Nome, raiz.dir); 
        }
    }

    public void pesquisaNome(String Nome, No2 raiz){
        if(raiz!=null){

            comp++;
            if(raiz.chave.equals(Nome)){
                achou=true;
            } 

            if(!achou) MyIO.print(" ESQ");
            pesquisaNome(Nome,raiz.esq);

            if(!achou) MyIO.print(" DIR");
            pesquisaNome(Nome,raiz.dir);
        }
    }



    /* --------------------------------------------------------------------------------------------------------------- INICIO CLASSE NO2 */
    public static class No2{

        public No2 esq, dir;
        public String chave;
        public Jogador elemento;

        public No2(){
            this("",null);
        }

        public No2(String chave, Jogador elemento){
            this.chave = chave;
            this.dir = this.esq = null;
            this.elemento = elemento;
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------ FIM CLASSE NO2 */

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
                    inserirJogador(jogador);

                } catch (Exception e) {}
                input = MyIO.readLine();
            } 
        }

        public void SetaId(int x) throws Exception {
            FileReader file = new FileReader("/tmp/players.csv"); 
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
    


	public static void ArqLog(long tempo) {
		String nomeArq = "802512_arvoreArvore.txt";
		String mtr = "802512";
		
		try {
            File arq = new File(nomeArq);
            arq.createNewFile();
        	try {
    			FileWriter file = new FileWriter(nomeArq, false); 
    			BufferedWriter buffer = new BufferedWriter(file);

    			buffer.write(mtr + '\t' + tempo*1000 + "s" + '\t' + comp);
    			buffer.close();

    		}catch(IOException e) {e.printStackTrace();}
        }catch(IOException e){e.printStackTrace();}
	}





    public static void main(String[] args) {
        long tempoInicio = System.nanoTime();
        No arvoreUm = new No();
        arvoreUm.formarArvore();

        Jogador jogador = new Jogador(); 
        jogador.inserirElementos();

        arvoreUm.pesquisaJogador(raiz);
        long tempoFinal = System.nanoTime();

        tempo = (tempoFinal - tempoInicio);
        ArqLog(tempo);

    }

}