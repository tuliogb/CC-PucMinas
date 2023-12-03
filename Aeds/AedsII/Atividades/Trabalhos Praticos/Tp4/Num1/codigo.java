import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CelulaAB {

    public static CelulaAB raiz;
    public CelulaAB dir, esq;
    public Jogador elemento;
    public static int comp=0;
    public static Long tempo = (long) 0;

    public CelulaAB(){
        this(null);
    }

    public CelulaAB(Jogador elemento){
        this.elemento=elemento;
        this.dir = this.esq = null;
    }

    public static void inserir(Jogador jogador){
        raiz = inserir(raiz,jogador);
    }

    static CelulaAB inserir(CelulaAB raiz, Jogador jogador){
        if(raiz==null) raiz = new CelulaAB(jogador);
        else if(jogador.nome.compareTo(raiz.elemento.nome)<0){ raiz.esq = inserir(raiz.esq,jogador); comp++; }
        else if(jogador.nome.compareTo(raiz.elemento.nome)>0){ raiz.dir = inserir(raiz.dir,jogador); comp++; }

        return raiz;
    }

    public static void mostrarCelulas(CelulaAB raiz){
        if(raiz!=null){
            mostrarCelulas(raiz.esq);
            raiz.elemento.mostraDados();
            mostrarCelulas(raiz.dir);
        }
    }

    public static void pesquisarElementos(){
        String input = MyIO .readLine();
        while(!input.equals("FIM")){
            MyIO.print(input + " raiz ");
            pesquisarElemento(input,raiz);
            input = MyIO .readLine();
        }
    }

    static void pesquisarElemento(String input, CelulaAB raiz){
        if (raiz!=null){
            if(input.compareTo(raiz.elemento.nome)<0) {
                MyIO.print("esq ");
                comp++;
                pesquisarElemento(input, raiz.esq);
            }
            else if(input.compareTo(raiz.elemento.nome)>0){
                MyIO.print("dir ");
                comp++;
                pesquisarElemento(input, raiz.dir);
            }
            else if(input.equals(raiz.elemento.nome)) MyIO.println("SIM");

        } else MyIO.println("NAO");
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
                    inserir(jogador);

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
		String nomeArq = "802512_arvoreBinaria.txt";
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
        Jogador jogador = new Jogador(); raiz = null;

        long tempoInicio = System.nanoTime();
        jogador.inserirElementos();
        //mostrarCelulas(raiz);
        pesquisarElementos();
        long tempoFinal = System.nanoTime();

        tempo = (tempoFinal - tempoInicio);
        ArqLog(tempo);
    }

}