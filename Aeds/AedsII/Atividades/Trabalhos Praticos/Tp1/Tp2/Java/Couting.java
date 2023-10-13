import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


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
     * CriaLog: Cria um arquivo de acordo com o nome dado, insere a matricula, tempo e comp dentro dele;
     * Main: Cria o local de armazenamento dos objetos -->> "ArrayList" <<-- chama os outros metodos;
     * 
     * Exercicio: metodo de ordenacao coutingSort >> atributo altura;
    */

    private Integer id,altura,peso,anoNascimento;
    private String nome,universidade,cidadeNascimento,estadoNascimento;

    public static Integer comp = 0;
    public static Integer mov = 0;
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

    public static void inserirElementos(ArrayList<Jogador> lista){
        String input = MyIO.readLine();
        while (!input.equals("FIM")) {
            try {
                Integer id = Integer.parseInt(input);
                Jogador jogador = new Jogador();
                jogador.SetaId(id);
                lista.add(jogador);
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

    public static void MostraLista(ArrayList<Jogador> lista){
		for(int i = 0; i < lista.size(); i++) {
			lista.get(i).Escrever();
		}
	}

    public void Escrever() {
		String linha = this.toString();
		MyIO.println(linha);
	}

    public String toString() {
		String txt = "[";
		txt += this.getId() + " ## ";
		txt += this.getNome() + " ## ";
		txt += this.getAltura() + " ## ";
		txt += this.getPeso() + " ## ";
		txt += this.getAnoNascimento() + " ## ";
		txt += this.getUniversidade() + " ## ";
		txt += this.getCidadeNascimento() +  " ## ";
		txt += this.getEstadoNascimento() + "]";
		return txt;
	}

	public static void ArqLog(long tempo) {
		String nomeArq = "matrícula_countingsort.txt";
		String mtr = "1441272";
		
		try {
            File arq = new File(nomeArq);
            arq.createNewFile();
        	try {
    			FileWriter file = new FileWriter(nomeArq, false); 
    			BufferedWriter buffer = new BufferedWriter(file);

    			buffer.write(mtr + '\t' + comp + '\t' + mov + '\t' +  tempo/1000 + "s");
    			buffer.close();

    		}catch(IOException e) {e.printStackTrace();}
        }catch(IOException e){e.printStackTrace();}
	}


    public static void CountingSort(ArrayList<Jogador> lista) {
		for (int i=1;i<lista.size();i++) {
			Jogador atual = lista.get(i);
			int j=i-1;
			while (j>=0 && lista.get(j).getNome().compareTo(atual.getNome())>0) {
				lista.set(j+1, lista.get(j));
				j=j-1;
				mov++;
				comp++;
			}
			lista.set(j+1,atual);
			mov++;
		}
        Couting(lista);
	}

    public static void Couting(ArrayList<Jogador> lista){
        int tam = lista.size();
        int[] c = new int[getMaior(lista)+1];
		Jogador[] corrente = new Jogador[tam];

		for (int i=0;i<tam;i++) {
			c[lista.get(i).getAltura()]++;
		}

		for (int i=1;i<c.length;i++) {
			c[i] += c[i-1];
		}

		for (int i=tam-1;i>=0;i--) {
			corrente[c[lista.get(i).getAltura()]-1] = lista.get(i);
			mov++;
			c[lista.get(i).getAltura()]--;
		}

		for (int i=0; i<tam;i++) {
			lista.set(i, corrente[i]);
			mov++;
		}
    }


    public static int getMaior(ArrayList<Jogador> lista){
		int maior = lista.get(0).getAltura();

		for (int i = 1; i < lista.size(); i++){
			if (maior < lista.get(i).getAltura()){
				maior = lista.get(i).getAltura();
				mov++;
				comp++;
			}
		}
		return maior;
	}



    public static void main(String[] args){
        ArrayList<Jogador> lista = new ArrayList<>();
        inserirElementos(lista);

        long tempoInicio = System.nanoTime();
        CountingSort(lista);
        long tempoFinal = System.nanoTime();
        tempo = (tempoFinal - tempoInicio);
        ArqLog(tempo);

        MostraLista(lista); 
    }

}
