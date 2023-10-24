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
     * Main: 
     * 
     * Exercicio:
    */

    public static int tamanho=0, MAX=130;
    private Integer id,altura,peso,anoNascimento;
    private String nome,universidade,cidadeNascimento,estadoNascimento;

    public static Integer comp = 0;
    public static Long tempo = (long) 0;

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

    /*---------------------------------------------------------------------------------------------------------------------------------------------------------------- INSERCAO DE DADOS INICIO */

    public static void inserirElementos(Jogador[] lista){
        String input = MyIO.readLine();
        while (!input.equals("FIM")) {
            try {
                Integer id = Integer.parseInt(input);
                Jogador jogador = new Jogador();
                jogador.SetaId(id);
                lista[tamanho] = jogador;
                tamanho++;
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

    public static void MostraLista(Jogador[] lista) {
		for(int i = 0; i < tamanho; i++) {
			lista[i].Escrever();
		}
	}

    public void Escrever() {
		String linha = this.toString();
		MyIO.println(linha);
	}

    public String toString() {
		String txt = "";
		txt += this.getId() + " ## ";
		txt += this.getNome() + " ## ";
		txt += this.getAltura() + " ## ";
		txt += this.getPeso() + " ## ";
		txt += this.getAnoNascimento() + " ## ";
		txt += this.getUniversidade() + " ## ";
		txt += this.getCidadeNascimento() +  " ## ";
		txt += this.getEstadoNascimento() + "#";
		return txt;
	}

    /*---------------------------------------------------------------------------------------------------------------------------------------------------------------- INSERCAO DE DADOS FIM */
    /*---------------------------------------------------------------------------------------------------------------------------------------------------------- MANIPULACAO DE DADOS INICIO */

    public static void manipulaElementos(Jogador[] lista){
        int qtd = MyIO.readInt();

        for(int i=0;i<qtd;i++){
            String input = MyIO.readLine();
            decodificaInput(input,lista);
        }
    }

    public static void decodificaInput(String input, Jogador[] lista){
        /*
         * II inserir no início
         * I* inserir em qualquer posição
         * IF inserir no fim
         * RI remover no início
         * R* remover em qualquer posição
         * RF remover no fim.
         */
        String[] parte = input.split(" ");

        if(parte.length == 1){
            switch (parte[0]){ 
                case "RI": removerInicio(); break; 
                case "RF": removerFim(); break; 
            }
        }
        else if(parte.length == 2){
            int idJogador =  Integer.parseInt(parte[1]);
            switch (parte[0]){
                case "II": inserirInicio(idJogador); break;     
                case "IF": inserirFim(idJogador); break; 
                case "R*": remover(idJogador); break;  
            }
        }
        else if(parte.length == 3){
            int posicao =  Integer.parseInt(parte[1]);
            int idJogador =  Integer.parseInt(parte[2]);
            inserir(idJogador, posicao);
        }

    }

    public static void inserirInicio(int idJogador){
        MyIO.println("Inserir inicio");
    }

    public static void inserir(int idJogador, int posicao){
         MyIO.println("Inserir");
    }

    public static void inserirFim(int idJogador){
         MyIO.println("Inserir Fim");
    }

    public static void removerInicio(){
         MyIO.println("Remover inicio");
    }

    public static void remover(int posicao){
         MyIO.println("Remover");
    }

    public static void removerFim(){
         MyIO.println("Remover Fim");
    }


    public static void main(String[] args) {            // Compilar:    javac .\Jogador.java 
        Jogador[] array = new Jogador[MAX];             // Executar:    type .\pub.in | java Jogador > result.txt */

        inserirElementos(array);
        manipulaElementos(array);
        //MostraLista(array);

    }

}