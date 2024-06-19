import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Questao 1 - Trabalho Pratico 4 - Aeds II - CC_PUCMINAS
 * @author Tulio Gomes Braga
 * @version 2 06/2025
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
 *  setaArvore: Equanto a entrada for diferente de "FIM", chama-se o medoto setaInput(passando a entrada lida).
 *  mostrarArvore: Percorre toda a estrutura de dados fazendo objeto.mostrarPersonagem().
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
        System.out.print("[" + this.id +" ## "+ this.name +" ## {");

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
}

class No{
    public No esq, dir;
    public Personagem elemento;


    No(){ this(null); }
    No(Personagem elemento){
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }
}

public class CtrlPersonagem{
    static No raiz = null;
    static int tamBase=0, maxTam=405, comparacoes=0;
    static Personagem[] Base =  new Personagem[maxTam];
    static Scanner sc = new Scanner(System.in);
    

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

    static void setaArvore(){
        String input = sc.nextLine();

        while(!input.equals("FIM")){
            setaInput(input);
            input = sc.nextLine();
        }
    }

    static void setaInput(String id){
        for(int i=0; i<tamBase; i++){
            if(Base[i].getId().equals(id)){
                raiz = inserir(raiz, Base[i]);
                i=tamBase;
            }
        }
    }

    static No inserir(No i, Personagem elemento){
        if(i==null) i = new No(elemento);
        else if(elemento.getName().compareTo(i.elemento.getName())<0) i.esq = inserir(i.esq, elemento);
        else if(elemento.getName().compareTo(i.elemento.getName())>0) i.dir = inserir(i.dir, elemento);
        return i;
    }

    static void mostrarArvore(No i){
        if(i!=null){
            i.elemento.mostrarPersonagem();
            mostrarArvore(i.esq);
            mostrarArvore(i.dir);
        }
    }

    static void pesquisaEntrada(){
        String input = sc.nextLine();

        while(!input.equals("FIM")){
            System.out.print(input + " => raiz ");
            System.out.println(pesquisar(raiz, input) ? "SIM" : "NAO");
            input = sc.nextLine();
        } 
    }

    static boolean pesquisar(No i, String chave){
        boolean resp = false;

        if(i==null){ /*System.out.print("Elemento nao encontrado");*/  comparacoes++;}
        else if(chave.compareTo(i.elemento.getName())<0){ System.out.print("esq "); resp = pesquisar(i.esq, chave);  comparacoes+=2;}
        else if(chave.compareTo(i.elemento.getName())>0){ System.out.print("dir "); resp = pesquisar(i.dir, chave);  comparacoes+=3;}
        else{ resp = true; comparacoes+=4;}

        return resp;
    }

    static void log(long tempoDeExecucao) throws Exception{
        FileWriter file = new FileWriter ("802512_arvoreBinaria.txt");
        PrintWriter caneta = new PrintWriter(file);
        caneta.println("802512" + '\t' + comparacoes + '\t' + tempoDeExecucao + "ms" + '\t');
        caneta.close();
    }

    public static void main(String[] args){ 
        try {
            long startTime = System.currentTimeMillis();

            setaBase();
            setaArvore();
            pesquisaEntrada();
            //mostrarArvore(raiz);

            long endTime = System.currentTimeMillis();
            log((endTime - startTime));
        } catch(Exception e){ System.out.println(e); }
    }
}