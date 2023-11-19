import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


class Jogador {

    public static class Celula {
        public Celula prox,ant;
        public Jogador elemento;

        public Celula(){
            this(null);
        }

        public Celula(Jogador elemento){
            this.elemento = elemento;
            this.ant = this.prox = null;
        }
    }

    static int tamanho=0;
    static Celula primeiro, ultimo;
    static int comparacoes=0, movimentacoes=0;
    public static Long tempo = (long) 0;

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

    /*----------------------------------------------------------------------------------------------------------------------------------------- INSERCAO DE DADOS INICIO */

    public static void inserirElementos(){
        String input = MyIO.readLine();
        while (!input.equals("FIM")) {
            try {
                Integer id = Integer.parseInt(input);
                Jogador jogador = new Jogador();
                jogador.SetaId(id);
                setaCelula(jogador);

            } catch (Exception e) {}
            input = MyIO.readLine();
        } 
    }

    public static void setaCelula(Jogador jogador){
        Celula celula = new Celula(jogador);
        ultimo.prox = celula;
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
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

    public static void MostraLista() {
		for(Celula i=primeiro.prox; i!=null; i=i.prox) {           /* SE EU COLOCO ATE I.PROX!=NULL ELE VAI ANDAR 1 ELEMENTO NA FRENTE */
            i.elemento.mostraDados();
		}
	}

    /*----------------------------------------------------------------------------------------------------------------------------------- FIM INSERCAO DE DADOS */
    /* ------------------------------------------------------------------------------------------------------------------------------------------- INICIO ORDENAÇÃO */
    
    public static void ordenaElementos(){
        quicksort(primeiro.prox,ultimo);
        OrgNome(tamanho);
    }
    
    public static void quicksort(Celula esq, Celula dir){
        Celula pivo=esq, i=esq.prox, j=dir;

        while(j.prox!=i && j.prox!=i.ant){
            while(i!=null && i.elemento.estadoNascimento.compareTo(pivo.elemento.estadoNascimento)<0){ i=i.prox; comparacoes++; }
            while(j.elemento.estadoNascimento.compareTo(pivo.elemento.estadoNascimento)>0){ j=j.ant; comparacoes++; }
            if (j.prox!=i){
                swap(i, j);
                if(i.prox!=null) i=i.prox;
                if(j.ant!=null) j=j.ant;
            }
        }
        swap(pivo, j);

        if (j!=esq) quicksort(esq, j.ant);
        if (j!=dir) quicksort(j.prox, dir);
    }   
    
    public static void OrgNome(int tam) {
        for (Celula i = primeiro.prox; i.prox != null; i = i.prox) {
            Celula menor = i;
            for (Celula j = i.prox; j != null; j = j.prox) {
                if (i.elemento.estadoNascimento.equals(j.elemento.estadoNascimento)) {
                    if (menor.elemento.nome.compareTo(j.elemento.nome) > 0) {
                        menor = j;
                    }
                }
            }
            swap(menor, i);
        }
    }

    public static void swap(Celula i, Celula j){
        Jogador tmp =  i.elemento;
        i.elemento = j.elemento;
        j.elemento = tmp;
        movimentacoes+=3;
    }

    /* ------------------------------------------------------------------------------------------------------------------------------------------- FIM ORDENAÇÃO */

	public static void ArqLog(long tempo) {
		String nomeArq = "matrícula_insercao.txt";
		String mtr = "1441272";
		
		try {
            File arq = new File(nomeArq);
            arq.createNewFile();
        	try {
    			FileWriter file = new FileWriter(nomeArq, false); 
    			BufferedWriter buffer = new BufferedWriter(file);

    			buffer.write("Matricula:" + mtr + '\t' + "Tempo de Execução: " + tempo/1000 + "ms" + '\t' + 
                "Numero de Comparações: " + comparacoes + '\t' + '\t' + "Numero de Movimentações: " + movimentacoes);
    			buffer.close();

    		}catch(IOException e) {e.printStackTrace();}
        }catch(IOException e){e.printStackTrace();}
	}


    public static void main(String[] args) {                    // Compilar:    javac .\Jogador.java        Executar:    type .\pub.in | java Jogador > result.txt
        primeiro = ultimo = new Celula();                       // Compilar:    javac Jogador.java          Executar:    java Jogador < pub.in > result.txt      
        inserirElementos();  
        
        long start = System.nanoTime();
        ordenaElementos();
        long fim = System.nanoTime();
        tempo = fim-start;
        
        MostraLista();
        ArqLog(tempo);
    }
}