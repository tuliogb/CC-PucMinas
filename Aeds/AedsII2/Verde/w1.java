import java.util.*;

class Celula{
    public Celula ant, prox;
    public int entrada;
    public int saida; 

    Celula(String horario){
        String[] parte = horario.split(" ");
        this.ant = null;
        this.prox = null;
        this.entrada = Integer.parseInt(parte[0]);
        this.saida = Integer.parseInt(parte[1]);
    }
}

class w1{

    static Scanner sc = new Scanner(System.in);
    static int qtdMotorista=0, vagas=0;
    static Celula primeiro, ultimo;
    

    static void start(){
        String input = sc.nextLine();

        while(input.charAt(0)!='0' && input.charAt(input.length()-1)!='0'){
            String[] parte = input.split(" ");
            qtdMotorista = Integer.parseInt(parte[0]);
            vagas = Integer.parseInt(parte[1]);

            setaHorarios();
            gestaoEstacionamento();

            input = sc.nextLine();
        }
    }

    static void setaHorarios(){
        String input = "";

        for(int i=0; i<qtdMotorista; i++){
            input = sc.nextLine();
            ultimo.prox = new Celula(input);
            ultimo.prox.ant = ultimo;
            ultimo = ultimo.prox;
        }
    }
    
    static void gestaoEstacionamento(){
        Celula k=primeiro.prox, j=primeiro.prox.prox;
        Boolean resp = true;

        for(int i=0; i<vagas-1 && resp; i++, k=k.prox, j=j.prox){
            if(k.saida < j.saida) resp = false;
        }
        
        if(ultimo.saida > ultimo.ant.ant.saida) resp = false;               // Oque esta esperando pra entrar na vaga vai prender alguem?


        System.out.println(resp ? "Sim" : "Nao");
    }

    static void mostrarEstacionamento(){
        for(Celula i=primeiro; i!=null; i=i.prox){
            System.out.println(i.entrada +" "+ i.saida);
        }
    }

    public static void main(String[] args){
        primeiro = ultimo = new Celula("0 0");
        start();
        //mostrarEstacionamento();
    }
}
