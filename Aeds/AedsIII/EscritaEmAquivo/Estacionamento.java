import java.util.*;                 // O compilador faz a leitura das classes e so importa as bib. usadas.
import java.text.DecimalFormat;
import java.io.FileOutputStream;
import java.io.DataOutputStream;

class Estacionamento{

    protected int horas;
    protected String nome, placa, cpf;
    
    DecimalFormat df = new DecimalFormat("#,##0.00");

    

    Estacionamento(String cpf, int horas, String nome, String placa){
        this.cpf = cpf;
        this.horas = horas;
        this.nome = nome;
        this.placa = placa;
    }


    void armazenar(){
        try{
            FileOutputStream arq = new FileOutputStream("armazenamento/estacionamento.db");
            DataOutputStream dos = new DataOutputStream(arq);

            dos.writeInt(horas);
            dos.writeUTF(cpf);
            dos.writeUTF(nome);
            dos.writeUTF(placa);
        
        }catch(Exception e){}
    }

    void checkOut(){
        double valor = horas*1.99;
        System.out.println("CheckOut " +placa+ " concluido! " + nome + ", o valor a ser pago é de: " + df.format(valor));
    } 

}