/*
Olimpíadas de Natal

Segundo a tradição, as provas das Olimpíadas de Natal são muito interessantes e diferentes. Dentre as provas existentes, podemos citar por exemplo, o levantamento de saco de brinquedos, a escalada de pinheiros, o hipismo com renas, o arremesso de duendes (com e sem o consentimento deles - esse segundo é mais perigoso) e o tempo de mergulho em buraco no gelo sem equipamentos, entre outros.

Parte do melhoramento da competição proposto por Noel para este ano será a disponibilização do quadro de medalhas dos países participantes através de um placar eletrônico. Você foi convidado para viajar até as montanhas de Korvatunturi na Lapônia, Finlândia, onde será a sede destes jogos, para, a partir de uma relação das provas e os países vencedores, desenvolver o sistema para este placar. Serão quase trezentos países participantes e até 1000 modalidades de provas, no máximo.

Entrada

A entrada contém um único caso de teste que consiste em uma relação com o resultado de todas as provas realizadas nas Olimpíadas de Natal. Cada prova ou modalidade contém quatro linhas de informação: a primeira linha contém a descrição da prova, a segunda linha contém o país que ficou campeão nesta modalidade, a terceira linha contém o país vice-campeão e a última linha contém o país que ficou com a medalha de bronze na referida prova. O final da entrada é determinado por EOF.

Saída

Como saída, deve ser impresso o quadro de medalhas das Olimpíadas de Natal. A primeira linha contém a informação "Quadro de Medalhas". Cada uma das próximas linhas conterá o nome de um país seguido pelo respectivo número de medalhas de ouro, prata e bronze que este país conquistou, separadas por um espaço em branco. O critério de desempate é, na ordem, o número de medalhas de ouro seguido pelo número de medalhas de prata e de bronze. Se países empatarem nestes três critérios, a listagem será por ordem ascendente do nome do país participante.
*/


public class Jogador{
    static Jogador[] paises = new Jogador[10];
    
    static String nome;
    static int ouro=0, prata=0, bronze=0, tamanho=0;
    
    public Jogador(){
        this(null);
    }

    public Jogador(String nome){
        this.nome = nome;
        this.ouro = 0;
        this.prata = 0;
        this.bronze = 0;
    }

    public static void lerDados(){

    String modalidade = "";
    String ouro = "";
    String prata = "";
    String bronze = "";


            modalidade = MyIO.readLine();
            ouro = MyIO.readLine();
            prata = MyIO.readLine();
            bronze = MyIO.readLine();

                /*OURO*/ 
                boolean achou = false;
                for (int i=0;i<tamanho;i++){
                    if(paises[i].nome.equals(ouro)){
                        paises[i].ouro+=1;
                        achou = true;
                    }
                }
                if(!achou){
                    paises[tamanho] = new Jogador(ouro);
                    paises[tamanho].ouro+=1;
                    tamanho++;
                }

                /*PRATA*/ 
                achou = false;
                for (int i=0;i<tamanho;i++){
                    if(paises[i].nome.equals(prata)){
                        paises[i].prata+=1;
                        achou = true;
                    }
                }
                if(!achou){
                    paises[tamanho] = new Jogador(prata);
                    paises[tamanho].prata+=1;
                    tamanho++;
                }


                /*BRONZE*/ 
                achou = false;
                for (int i=0;i<tamanho;i++){
                    if(paises[i].nome.equals(bronze)){
                        paises[i].bronze+=1;
                        achou = true;
                    }
                }
                if(!achou){
                    paises[tamanho] = new Jogador(bronze);
                    paises[tamanho].bronze+=1;
                    tamanho++;
                }
        
            
    }

    public static void mostrar(){
        for (int i=0;i<1;i++){
            MyIO.println(paises[i].nome + " " + paises[i].ouro + " " + paises[i].prata + " " + paises[i].bronze);
        }
    }
    public static void main(String[] args){
        lerDados();
        mostrar();
    }
}