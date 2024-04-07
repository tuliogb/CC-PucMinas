import java.io.FileReader;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


class Personagem {

    private String id, name, house, ancestry, species, patronus, hogwartsStaff, hogwartsStudent, actorName, eyeColour, gender, hairColour;
    private String[] alternate_names;
    private String[] alternate_actors;
    private LocalDate dateOfBirth;
    private int yearOfBirth;
    private boolean wizard, alive;


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
    public String getHogwartsStaff() {return hogwartsStaff;}

    public void setHogwartsStudent(String hogwartsStudent) {this.hogwartsStudent = hogwartsStudent;}
    public String getHogwartsStudent() {return hogwartsStudent;}

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

    public void setAlive(String alive) {boolean x = false;  if(alive.equals("true")) x=true;    this.alive = x;}
    public boolean getAlive() {return alive;}

    public void setWizard(String wizard) {boolean x = false; if(wizard.equals("true")) x=true;  this.wizard = x;}
    public boolean getWizard() {return wizard;}

    public void setDateOfBirth(String dateOfBirth) { 
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-M-yyyy"); 
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 

        if(!dateOfBirth.equals("") && !dateOfBirth.equals("dateOfBirth") && !dateOfBirth.equals("[]")) {
            if(dateOfBirth.charAt(2)=='-' && dateOfBirth.charAt(4)!='-') this.dateOfBirth = LocalDate.parse(dateOfBirth, format2);
            else this.dateOfBirth = LocalDate.parse(dateOfBirth, format1);
        }
    }
    public LocalDate getDateOfBirth() {return dateOfBirth;}

    public void setAlternateNames(String alternate_names) {
        if(!alternate_names.equals("[]")){
            alternate_names = alternate_names.replaceAll("['\"\\[\\]]", "");
            String[] parte = alternate_names.split(";");

            for(int i=0; i<parte.length; i++){
                this.alternate_names[i] = parte[i];
            }
        }
    }
    public String[] getAlternateNames() {return alternate_names;}

    public void setAlternateActors(String alternate_actors) {
        if(!alternate_actors.equals("[]")){
            alternate_actors = alternate_actors.replaceAll("['\"\\[\\]]", "");
            String[] parte = alternate_actors.split(";");

            for(int i=0; i<parte.length; i++){
                this.alternate_actors[i] = parte[i];
            }
        }
    }
    public String[] getAlternateActors() {return alternate_actors;}


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

    public Personagem(String id, String nome, String house, String ancestry, String species, String patronus, String hogwartsStaff, String hogwartsStudent, String actorName, String eyeColour, String gender, String hairColour, String[] alternate_names, String[] alternate_actors, LocalDate dateOfBirth, int yearOfBirth, boolean alive, boolean wizard) {
        this.id = id;
        this.name = nome;
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

    public void mostrarPersonagem(){
        System.out.print(this.id +" "+ this.name +" ");

        for(int i=0; i<this.alternate_names.length; i++){
            if(this.alternate_names[i]!=null) System.out.print(this.alternate_names[i] +" ");
            else i=this.alternate_names.length;
        }    

        System.out.print(this.house +" "+ this.ancestry +" "+ this.species +" "+ this.patronus +" "+ this.hogwartsStaff +" "+ this.hogwartsStudent +" "+ this.alive +" ");
        
        for(int i=0; i<this.alternate_actors.length; i++){
            if(this.alternate_actors[i]!=null) System.out.print(this.alternate_actors[i] +" ");
            else i=this.alternate_names.length;
        }    

        System.out.println(this.dateOfBirth +" "+ this.yearOfBirth +" "+ this.eyeColour +" "+ this.gender +" "+ this.hairColour +" "+ this.wizard);
    }
}

public class CtrlPersonagem{

    static int tam = 0;
    static int maxTam = 406;
    static Personagem[] Base =  new Personagem[maxTam];


    static void setaBase() throws Exception{
        String linha = "";
        FileReader file = new FileReader("characters.csv");
        BufferedReader bf = new BufferedReader(file);

        while((linha=bf.readLine()) != null){
            setaPersonagem(preparaLinha(linha));
        }

        bf.close();
        file.close();
    }

    static String preparaLinha(String linha){
        boolean start = false;
        char[] array = linha.toCharArray();

         for(int i=0; i<array.length-1; i++){
            if(array[i]=='"' && array[i+1]=='[') start = true;
            if(start){
                if(array[i]==',') array[i]=';';
            }
            if(array[i]==']' && array[i+1]=='"') start = false;
        }

        linha = new String (array);  
        return linha;
    }

    static void setaPersonagem(String linha){
        String[] parte = linha.split(",");
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

        Base[tam++]=p;
    }

    static void mostrarBase(){
        for (int i=0; i<tam; i++){
            Base[i].mostrarPersonagem();
        }
    }


    public static void main(String[] args){
        try {
            setaBase();
            mostrarBase();
        } catch(Exception e){ System.out.println(e);}
    }
}




