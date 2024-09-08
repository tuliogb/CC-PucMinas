import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sistema{

    Arquivo<Cliente> arqCliente;
    static int clientesInseridos=0;
    
    void insereDado(String linha) throws Exception{
        String[] parte = linha.split(";");
        if(parte.length==4){

            String nome = parte[0];
            String cpf = parte[1].replaceAll(".-", "");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dataNascimento = LocalDate.parse(parte[2], formatter);
            String cepSemHifen = parte[3].replaceAll("-", "");
            int cep = Integer.parseInt(cepSemHifen);

            //System.out.println(nome +"  "+ cpf +"  "+ dataNascimento +"  "+ cep);
            Cliente cliente = new Cliente(nome, cpf, dataNascimento, cep);
            arqCliente.create(cliente);
            clientesInseridos++;
        }
    }
    
    void setaBase() throws Exception{
        String linha = "";
        arqCliente = new Arquivo<>(Cliente.class.getConstructor(), "BaseDeDados/clientes.db");
        FileReader file = new FileReader("dadosEntrada.csv");
        BufferedReader bf = new BufferedReader(file);

        while((linha=bf.readLine()) != null){
            insereDado(linha);
        }
        bf.close();
        file.close();
    }

    void ordenarPorNome(){
        arqCliente.ordenar();
    }

    public static void main(String[] args){
        try{
            Sistema baseClientes = new Sistema();
            baseClientes.setaBase();
            baseClientes.ordenarPorNome();
        }
        catch(Exception e){ e.printStackTrace(); }
    }   
}