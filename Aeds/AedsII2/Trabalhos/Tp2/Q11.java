import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Questao 11 - Trabalho Pratico 2 - Aeds II - CC_PUCMINAS
 * @author Tulio Gomes Braga
 * @version 2 04/2025
 * 
 * 
 * Explicações dos Métodos:
 * 
 * Personagem: Responsavel pela cricao dos atributos e operacoes especificas de personagem.
 *  Personagem(): Metodo construtor sem parametro.
 *  Personegem(parametros): Metodo construtor com parametro.
 *  Metodos set: Responsaveis por receber o parmetro e atribuir o mesmo ao atribuito.
 *  Metodos get: Responsaveis por retornar o atributo. 
 *  Clone: Copia os dados de outro personagem para um novo.
 *  mostrarPersonagem: Apresenta na saida padrao os atributos formatados.
 * 
 * CtrlPersonagem: Responsavel pelas operacoes envovendo personagens.
 *  setaBase: Abre o arquivo uma vez e passa linha por linha para setar todos os personagens.
 *  setaPersonagem: Particiona a linha e chama os metodos sets correspondentes aos atributos.
 *  mostrarBase: Percorre toda a base fazendo objeto.mostrarPersonagem().
 *  setaLista: Equanto a entrada for diferente de "FIM", chama-se o medoto setaInput(passando a entrada lida).
 *  mostraLista: Percorre toda a lista fazendo objeto.mostrarPersonagem().
 *  
 *  swap: Recebe posicoes e troca os elementos.
 *  getMaior: Retorna a maior data da lista de personagens.
 *  countingSort: Metodo de ordenacao.
 *  ordenaNomes: Coloca em ordem de nomes aqueles que tem o mesmo ano.
 *  log: Exibe em um arquivo numero de comparacoes, movimentacoes e o tempo de execucao do algoritimo.
 *  
 */

class Personagem {

    private String id, name, house, ancestry, species, patronus, hogwartsStaff, hogwartsStudent, actorName, eyeColour, gender, hairColour;
    private String[] alternate_names;
    private String[] alternate_actors;
    private LocalDate dateOfBirth;
    private int yearOfBirth;
    private boolean wizard, alive;


    public Personagem() {
        this.id = null;
        this.name = null;
        this.house = null;
        this.ancestry = null;
        this.species = null;
        this.patronus = null;
        this.hogwartsStaff = null;
        this.hogwartsStudent = null;
        this.actorName = null;
        this.eyeColour = null;
        this.gender = null;
        this.hairColour = null;
        this.alternate_names = new String[10];
        this.alternate_actors = new String[10];
        this.dateOfBirth = null;
        this.yearOfBirth = 0;
        this.wizard = false;
        this.alive = false;
    }

    public Personagem(String id, String name, String house, String ancestry, String species, String patronus, String hogwartsStaff, String hogwartsStudent, String actorName, String eyeColour, String gender, String hairColour, String[] alternate_names, String[] alternate_actors, LocalDate dateOfBirth, int yearOfBirth, boolean alive, boolean wizard) {
        this.id = id;
        this.name = name;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStaff = hogwartsStaff;
        this.hogwartsStudent = hogwartsStudent;
        this.actorName = actorName;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.alternate_names = alternate_names;
        this.alternate_actors = alternate_actors;
        this.dateOfBirth = dateOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.alive = alive;
        this.wizard = wizard;
    }

    public void setId(String id) {this.id = id;}
    public String getId() {return id;}

    public void setName(String name) {this.name = name;}
    public String getName() {return name;}

    public void setHouse(String house) {this.house = house;}
    public String getHouse() {return house;}

    public void setAncestry(String ancestry) {this.ancestry = ancestry;}
    public String getAncestry() {return ancestry;}

    public void setSpecies(String species) {this.species = species;}
    public String getSpecies() {return species;}

    public void setPatronus(String patronus) {this.patronus = patronus;}
    public String getPatronus() {return patronus;}

    public void setHogwartsStaff(String hogwartsStaff) {this.hogwartsStaff = hogwartsStaff;}
    public String getHogwartsStaff() {return hogwartsStaff.equals("VERDADEIRO") ? "true" : "false";}

    public void setHogwartsStudent(String hogwartsStudent) {this.hogwartsStudent = hogwartsStudent;}
    public String getHogwartsStudent() {return hogwartsStudent.equals("VERDADEIRO") ? "true" : "false";}

    public void setActorName(String actorName) {this.actorName = actorName;}
    public String getActorName() {return actorName;}

    public void setEyeColour(String eyeColour) {this.eyeColour = eyeColour;}
    public String getEyeColour() {return eyeColour;}

    public void setGender(String gender) {this.gender = gender;}
    public String getGender() {return gender;}

    public void setHairColour(String hairColour) {this.hairColour = hairColour;}
    public String getHairColour() {return hairColour;}

    public void setYearOfBirth(String yearOfBirth) { if(!yearOfBirth.equals("") && !yearOfBirth.equals("yearOfBirth")) this.yearOfBirth = Integer.parseInt(yearOfBirth);}
    public int getYearOfBirth() {return yearOfBirth;}

    public void setAlive(String alive) {boolean x = false;  if(alive.equals("VERDADEIRO")) x=true;    this.alive = x;}
    public boolean getAlive() {return alive;}

    public void setWizard(String wizard) {boolean x = false; if(wizard.equals("VERDADEIRO")) x=true;  this.wizard = x;}
    public boolean getWizard() {return wizard;}

    public void setDateOfBirth(String dateOfBirth) { 
        if(!dateOfBirth.equals("") && !dateOfBirth.equals("dateOfBirth") && !dateOfBirth.equals("[]")){
            String[] parte = dateOfBirth.split("-");
            this.dateOfBirth = LocalDate.of(Integer.parseInt(parte[2]), Integer.parseInt(parte[1]), Integer.parseInt(parte[0])); 
        }
    }
    public String getDateOfBirth() { DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy"); String dt = this.dateOfBirth.format(f); return dt;}
    public LocalDate getDate() { return dateOfBirth; }

    public void setAlternateNames(String alternate_names) {
        if(!alternate_names.equals("[]")){
            alternate_names = alternate_names.replaceAll("[\\'\\[\\]]", ""); //replaceAll("[\\s'\\[\\]]", "");
            String[] parte = alternate_names.split(",");

            for(int i=0; i<parte.length; i++){
                this.alternate_names[i] = parte[i]; System.out.print("");
            }
        }
    }
    public String[] getAlternateNames() {return alternate_names;}

    public void setAlternateActors(String alternate_actors) {
        if(!alternate_actors.equals("[]")){
            alternate_actors = alternate_actors.replaceAll("[\\s'\\[\\]]", "");
            String[] parte = alternate_actors.split(",");

            for(int i=0; i<parte.length; i++){
                this.alternate_actors[i] = parte[i];
            }
        }
    }
    public String[] getAlternateActors() { return alternate_actors;}

    public void clone(Personagem p) {
        this.id = p.id;
        this.name = p.name;
        this.house = p.house;
        this.ancestry = p.ancestry;
        this.species = p.species;
        this.patronus = p.patronus;
        this.hogwartsStaff = p.hogwartsStaff;
        this.hogwartsStudent = p.hogwartsStudent;
        this.actorName = p.actorName;
        this.eyeColour = p.eyeColour;
        this.gender = p.gender;
        this.hairColour = p.hairColour;
        this.alternate_names = p.alternate_names;
        this.alternate_actors = p.alternate_actors;
        this.dateOfBirth = p.dateOfBirth;
        this.yearOfBirth = p.yearOfBirth;
        this.alive = p.alive;
        this.wizard = p.wizard;
    }

    public void mostrarPersonagem(){
        System.out.print("["+ this.id +" ## "+ this.name +" ## {");

        for(int i=0; i<this.alternate_names.length; i++){
            if(this.alternate_names[i]!=null) System.out.print(this.alternate_names[i]);
            else i=this.alternate_names.length;
            if(i<10 && this.alternate_names[i+1]!=null) System.out.print(","); 
        }  

        System.out.print("} ## " + this.house +" ## "+ this.ancestry +" ## "+ this.species +" ## "+ this.patronus +" ## "+ this.getHogwartsStaff() +" ## "+ this.getHogwartsStudent() +" ## "+ this.actorName +" ## "+ this.alive +" ## ");
        /*
        for(int i=0; i<this.alternate_actors.length; i++){
            if(this.alternate_actors[i]!=null) System.out.print(this.alternate_actors[i] +" ## ");
            else i=this.alternate_names.length;
        }    
        */ 
        System.out.println(this.getDateOfBirth() +" ## "+ this.yearOfBirth +" ## "+ this.eyeColour +" ## "+ this.gender +" ## "+ this.hairColour +" ## "+ this.wizard +"]");
    }

    public void lerPersonagem(){}
    
}

public class CtrlPersonagem{

    static int tamBase=0, entradas=0, comparacoes=0, movimentacoes=0, maxTam=405;
    static Personagem[] Base =  new Personagem[maxTam];
    static Personagem[] Lista = new Personagem[maxTam];


    static void setaBase() throws Exception{
        String linha = "";
        FileReader file = new FileReader("/tmp/characters.csv");
        BufferedReader bf = new BufferedReader(file);

        while((linha=bf.readLine()) != null){
            setaPersonagem(linha);
        }

        bf.close();
        file.close();
    }

    static void setaPersonagem(String linha){
        String[] parte = linha.split(";");
        Personagem p = new Personagem();

        p.setId(parte[0]);
        p.setName(parte[1]);
        p.setAlternateNames(parte[2]);
        p.setHouse(parte[3]);
        p.setAncestry(parte[4]);
        p.setSpecies(parte[5]);
        p.setPatronus(parte[6]);
        p.setHogwartsStaff(parte[7]);
        p.setHogwartsStudent(parte[8]);
        p.setActorName(parte[9]);
        p.setAlive(parte[10]);
        p.setAlternateActors(parte[11]);
        p.setDateOfBirth(parte[12]);
        p.setYearOfBirth(parte[13]);
        p.setEyeColour(parte[14]);
        p.setGender(parte[15]);
        p.setHairColour(parte[16]);
        p.setWizard(parte[17]);

        Base[tamBase++]=p;
    }

    static void mostrarBase(){
        for (int i=0; i<tamBase; i++){
            Base[i].mostrarPersonagem();
        }
    }

    static void setaLista(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("FIM")){
            setaInput(input);
            input = sc.nextLine();
        }
    }

    static void setaInput(String id){
        for(int i=0; i<tamBase; i++){
            if(Base[i].getId().equals(id)){
                Lista[entradas++] = Base[i];
                i=tamBase;
            }
        }
    }

    static void mostrarLista(){
        for (int i=0; i<entradas; i++){
            Lista[i].mostrarPersonagem();
        }
    }


    ///////////////////////////////////////////////////////////////////// EXERCICIO

    static void swap(int x, int y){
        Personagem tmp = Lista[x];
        Lista[x] = Lista[y];
        Lista[y] = tmp;
        movimentacoes+=3;
    }

    static int getMaior(){
        int maior = 0;

        for(int i=0; i<entradas; i++){
            if(Lista[i].getYearOfBirth() > maior) maior = Lista[i].getYearOfBirth();
        }
        return maior;
    }

    static void countingSort(){
        int[] count = new int[getMaior()+1];
        Personagem[] ordenado = new Personagem[entradas];

        for (int i=0; i<count.length; count[i]=0, i++);
        for (int i=0; i<entradas; count[Lista[i].getYearOfBirth()]++, i++);
        for (int i=1; i<count.length; count[i]+=count[i-1], i++);
        for (int i=entradas-1; i>=0; ordenado[count[Lista[i].getYearOfBirth()]-1] = Lista[i], count[Lista[i].getYearOfBirth()]--, movimentacoes++, i--);

        for(int i=0; i<entradas; Lista[i] = ordenado[i], movimentacoes++, i++);
        ordenaNomes();
    }

    static void ordenaNomes(){
        for(int i=0; i<entradas-1; i++){
            int menor = i;
            for(int j=i+1; j<entradas; j++){
                comparacoes++;
                if(Lista[menor].getYearOfBirth() == Lista[j].getYearOfBirth() && Lista[menor].getName().compareTo(Lista[j].getName()) > 0) menor=j;
            }
            swap(i,menor);
        }
    }

    static void log(long tempoDeExecucao) throws Exception{
        FileWriter file = new FileWriter ("802512_countingsort.txt");
        PrintWriter caneta = new PrintWriter(file);
        caneta.println("802512" + '\t' + comparacoes + '\t' + movimentacoes + '\t' + tempoDeExecucao + "ms" + '\t');
        caneta.close();
    }

    ////////////////////////////////////////////////////////////////////////////////


    public static void main(String[] args){
        try {
            long startTime = System.currentTimeMillis();
            setaBase();
            setaLista();
            countingSort();
            mostrarLista();
            long endTime = System.currentTimeMillis();

            log((endTime - startTime));

        } catch(Exception e){ System.out.println("Erro no codigo: " + e);}
    }
}


