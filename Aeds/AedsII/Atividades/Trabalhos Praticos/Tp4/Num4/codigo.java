import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Alvinegra {
   private NoAN raiz; 
   public static int comp=0;
   public static Long tempo = (long) 0;

   public Alvinegra() {
      raiz = null;
   }

   public boolean pesquisar(String elemento) {
      MyIO.print(elemento + " raiz");
      return pesquisar(elemento, raiz);
   }

   private boolean pesquisar(String elemento, NoAN i) {
      boolean resp;
      if (i == null) {
         resp = false;
      } else if (elemento.compareTo(i.elemento.nome) == 0) {
         comp++;
         resp = true;
      } else if (elemento.compareTo(i.elemento.nome) < 0) {
         comp++;
         MyIO.print(" esq");
         resp = pesquisar(elemento, i.esq);
      } else {
         MyIO.print(" dir");
         resp = pesquisar(elemento, i.dir);
      }
      return resp;
   }

   private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
      if (pai.cor == true) {

         if (pai.elemento.nome.compareTo(avo.elemento.nome) > 0) { 
            if (i.elemento.nome.compareTo(pai.elemento.nome) > 0) {
               avo = rotacaoEsq(avo);
            } else {
               avo = rotacaoDirEsq(avo);
            }
         } else { // rotacao a direita ou esquerda-direita
            if (i.elemento.nome.compareTo(pai.elemento.nome) < 0) {
               avo = rotacaoDir(avo);
            } else {
               avo = rotacaoEsqDir(avo);
            }
         }
         if (bisavo == null) {
            raiz = avo;
         } else if (avo.elemento.nome.compareTo(bisavo.elemento.nome) < 0) {
            bisavo.esq = avo;
         } else {
            bisavo.dir = avo;
         }

         avo.cor = false;
         avo.esq.cor = avo.dir.cor = true;
      }
   }

   public void inserir(Jogador elemento) {
    if (raiz == null) {
       raiz = new NoAN(elemento);

    } else if (raiz.esq == null && raiz.dir == null) {
       if (elemento.nome.compareTo(raiz.elemento.nome) < 0) {
          raiz.esq = new NoAN(elemento);

       } else {
          raiz.dir = new NoAN(elemento);
       }

    } else if (raiz.esq == null) {
       if (elemento.nome.compareTo(raiz.elemento.nome) < 0) {
          raiz.esq = new NoAN(elemento);

       } else if (elemento.nome.compareTo(raiz.dir.elemento.nome) < 0) {
          raiz.esq = new NoAN(raiz.elemento);
          raiz.elemento = elemento;

       } else {
          raiz.esq = new NoAN(raiz.elemento);
          raiz.elemento = raiz.dir.elemento;
          raiz.dir.elemento = elemento;
       }
       raiz.esq.cor = raiz.dir.cor = false;

    } else if (raiz.dir == null) {
       if (elemento.nome.compareTo(raiz.elemento.nome) > 0) {
          raiz.dir = new NoAN(elemento);

       } else if (elemento.nome.compareTo(raiz.esq.elemento.nome) > 0) {
          raiz.dir = new NoAN(raiz.elemento);
          raiz.elemento = elemento;

       } else {
          raiz.dir = new NoAN(raiz.elemento);
          raiz.elemento = raiz.esq.elemento;
          raiz.esq.elemento = elemento;
       }
       raiz.esq.cor = raiz.dir.cor = false;

    } else {
       inserir(elemento, null, null, null, raiz);
    }
    raiz.cor = false;
 }

   private void inserir(Jogador elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
      if (i == null) {
         comp++;
         if (elemento.nome.compareTo(pai.elemento.nome) < 0) {
            i = pai.esq = new NoAN(elemento, true);
         } else {
            i = pai.dir = new NoAN(elemento, true);
         }
         if (pai.cor == true) {
            balancear(bisavo, avo, pai, i);
         }
      } else {

         if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
            i.cor = true;
            i.esq.cor = i.dir.cor = false;
            if (i == raiz) {
               i.cor = false;
            } else if (pai.cor == true) {
               balancear(bisavo, avo, pai, i);
            }
         }
         comp++;
         if (elemento.nome.compareTo(i.elemento.nome) < 0) {
            inserir(elemento, avo, pai, i, i.esq);
         } else if (elemento.nome.compareTo(i.elemento.nome) > 0) {
            inserir(elemento, avo, pai, i, i.dir);
         } else {}
      }
   }

   private NoAN rotacaoDir(NoAN no) {
      NoAN noEsq = no.esq;
      NoAN noEsqDir = noEsq.dir;

      noEsq.dir = no;
      no.esq = noEsqDir;

      return noEsq;
   }

   private NoAN rotacaoEsq(NoAN no) {
      NoAN noDir = no.dir;
      NoAN noDirEsq = noDir.esq;

      noDir.esq = no;
      no.dir = noDirEsq;
      return noDir;
   }

   private NoAN rotacaoDirEsq(NoAN no) {
      no.dir = rotacaoDir(no.dir);
      return rotacaoEsq(no);
   }

   private NoAN rotacaoEsqDir(NoAN no) {
      no.esq = rotacaoEsq(no.esq);
      return rotacaoDir(no);
   }

   public void pesquisarElementos(){
    String input =  MyIO.readLine();
    while(!input.equals("FIM")){
        if(pesquisar(input)) MyIO.println(" SIM");
        else MyIO.println(" NAO");
        input = MyIO.readLine();
    }
   }

   public void caminharCentral() {
      System.out.print("[ ");
      caminharCentral(raiz);
      System.out.println("]");
   }

   private void caminharCentral(NoAN i) {
      if (i != null) {
         caminharCentral(i.esq); 
         System.out.print(i.elemento.nome + ((i.cor) ? "(p) " : "(b) ")); 
         caminharCentral(i.dir); 
      }
   }
   

    /* -------------------------------------------------------------------------------------------------------------- INICIO CLASSE NOAN */
    public class NoAN {
        public boolean cor;
        public Jogador elemento;
        public NoAN esq, dir;
    
        public NoAN() {
        this(null);
        }
    
        public NoAN(Jogador elemento) {
        this(elemento, false, null, null);
        }
    
        public NoAN(Jogador elemento, boolean cor) {
        this(elemento, cor, null, null);
        }
    
        public NoAN(Jogador elemento, boolean cor, NoAN esq, NoAN dir) {
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        }
    }
    /* ----------------------------------------------------------------------------------------------------------------- FIM CLASSE NOAN */

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
            Alvinegra arvore = new Alvinegra();
            while (!input.equals("FIM")) {
                try {
                    int id = Integer.parseInt(input);
                    Jogador jogador = new Jogador();

                    jogador.SetaId(id);
                    arvore.inserir(jogador);

                } catch (Exception e) {}
                input = MyIO.readLine();
            } 
            //arvore.caminharCentral();
            arvore.pesquisarElementos();
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
		String nomeArq = "802512_alvinegra.txt";
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

        Jogador jogador = new Jogador();
        jogador.inserirElementos();

        long tempoFinal = System.nanoTime();
        tempo = (tempoFinal - tempoInicio);
        ArqLog(tempo);
    }

}
