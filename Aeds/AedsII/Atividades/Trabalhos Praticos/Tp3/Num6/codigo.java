import java.io.BufferedReader;
import java.io.FileReader;


class Jogador {

    /*
     * Aluno(Dev): Tulio Gomes Braga;
     * Usuario: 1441272;
     * 
     * Descrição do conteudo:
     * 
     * Atributos da classe jogador, seguindo a tabela "players.csv";
     * Metodos sets e gets para manipular os valores do objeto;
     * Construtor: esse metodo fica responsavel por cria um objeto com valores nulos;
     * Clone: objeto que chama tem seus atributos copiados pra uma nova instância de objeto;
     * Inserir Elementos: Recebe valores ate ser igual a "FIM", cria um novo objeto, tenta transformar em inteiro e chama os proximos metodos;
     * SetaId: Recebe um id, abre a leitura no arquivo e vai percorrendo até o id lido seja igual ao recebido, caso for pega a linha correspondente;
     * SetaDados: Recebe uma linha, fragmenta de acordo com a virgula e insere os argumentos daquele objeto corrente;
     * MostraDados: Mostra todos os atributos do objeto corrente;
     * MostrarLista: Metodo pra percorrer toda nossa lista e printar os dados;
     * Escreve: Recebe o objeto corrente e printa o retorno de outro metodo;
     * toString: Similar ao mostrar dados, retorna os dados concatenados em String;
     * 
     * Exercicio: Manipulando o array a partir de criterios da lista flexivel, no qual os elementos estao unidos por um ponteiro prox.
    */


    public static int tamanho=0, tamRemovidos=0;

    public static Jogador primeiro,ultimo, removidos, primeiroRev;
    public Jogador prox;

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
        this.prox = null;
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

    public void inserirElementos(){
        String input = MyIO.readLine();
        while (!input.equals("FIM")) {
            try {
                Integer id = Integer.parseInt(input);
                Jogador jogador = new Jogador();

                jogador.SetaId(id);                           
                ultimo.prox = jogador;
                ultimo = ultimo.prox;
                tamanho++;
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

    public void MostraLista() {
        int c=0;
		for(Jogador i=primeiro.prox; i!=null; i=i.prox) {           /* SE EU COLOCO ATE I.PROX!=NULL ELE VAI ANDAR 1 ELEMENTO NA FRENTE */
            i.Escrever(c);
            c++;
		}
	}

    public void Escrever(int c) {
		String linha = this.toString(c);
		MyIO.println(linha);
	}

    public String toString(int c) {
		String txt = "[" + c + "] " + "## ";
        //txt += this.getId() + " ## ";
		txt += this.getNome() + " ## ";
		txt += this.getAltura() + " ## ";
		txt += this.getPeso() + " ## ";
		txt += this.getAnoNascimento() + " ## ";
		txt += this.getUniversidade() + " ## ";
		txt += this.getCidadeNascimento() +  " ## ";
		txt += this.getEstadoNascimento() + " ##";
		return txt;
	}

    /*------------------------------------------------------------------------------------------------------------------------------------------ FIM INSERCAO DE DADOS */
    /*------------------------------------------------------------------------------------------------------------------------------------------ INICIO MANIPULACAO DE DADOS */

    public void manipulaElementos(){
        int qtd = MyIO.readInt();
        primeiroRev = removidos = new Jogador();

        for(int i=0;i<qtd;i++){
            String input = MyIO.readLine();
            decodificaInput(input);
        }
        mostraExcluidos();
    }

    public static void decodificaInput(String input){
        /*
         * I inserir
         * R remover 
         */
        String[] parte = input.split(" ");

        if(parte.length == 1){
            if(parte[0].equals("R")) removerFim();
        }
        else if(parte.length == 2){
            int idJogador =  Integer.parseInt(parte[1]);
            if(parte[0].equals("I")) inserirFim(idJogador);;
        }
    }
    
    public static void inserirFim(int idJogador){
        try{
            Jogador jogador = new Jogador();
            jogador.SetaId(idJogador);

            ultimo.prox = jogador;
            ultimo = ultimo.prox;

            tamanho++;
        }catch(Exception e){}
    } 

    public static void removerFim(){
        Jogador i;
        for (i=primeiro;i.prox!=ultimo;i=i.prox);          /* PROX.PROX PRA EU CONFERIR A CASA ANTES DE ANDAR COM A REFERENCIA */
        
        ArmazenaRemovidos(i.prox); 
        ultimo = i; i = ultimo.prox = null;
        tamanho--; tamRemovidos++;
    }


    /* ------------------------------------------------------------------------------------------------------------------------------------------- FIM MANIPULACAO */
    /* ------------------------------------------------------------------------------------------------------------------------------------------- INICIO REMOVIDOS */


    public static void ArmazenaRemovidos(Jogador jogador){
        Jogador novoRemov = jogador.cloneJogador();
        removidos.prox = novoRemov;
        removidos = removidos.prox;

        novoRemov = null;
    }

    public static void mostraExcluidos() {
		for(Jogador i=primeiroRev.prox; i!=null; i=i.prox) {
            i.EscreverEx();
		}
	}

    public void EscreverEx() {
		String linha = this.toStringEx();
		MyIO.println(linha);
	}

    public String toStringEx() {
		String txt = "(R) ";
		txt += this.getNome();
		return txt;
	}

    /* ------------------------------------------------------------------------------------------------------------------------------------------- FIM REMOVIDOS */
    /* ------------------------------------------------------------------------------------------------------------------------------------------- INICIO MAIN */


    public static void main(String[] args) {            // Compilar:    javac .\Jogador.java        Executar:    type .\pub.in | java Jogador > result.txt
        Jogador jogador = new Jogador();                // Compilar:    javac Jogador.java          Executar:    java Jogador < pub.in > result.txt
        ultimo = primeiro = new Jogador();             

        jogador.inserirElementos();
        jogador.manipulaElementos();
        jogador.MostraLista();
    }
    
    /* ----------------------------------------------------------------------------------------------------------------------------------------------- FIM MAIN */
}