import java.io.IOException;
import java.time.LocalDate;


public class Sistema{

    public static void main(String[] args){

        Cliente c1 = new Cliente("Jose Augusto", "00530065694", LocalDate.of(1998, 4, 21), 40540228);
        Cliente c2 = new Cliente("Luiz Paulo", "02138567411", LocalDate.of(2003, 8, 15), 30535002);
        Cliente c3 = new Cliente("Stefen Curry Extends", "18620022299", LocalDate.of(2001, 1, 7), 35430111);
        Cliente c4 = new Cliente("Jordan Lifer Dayton", "56783899844", LocalDate.of(1995, 11, 28), 32323333);

        try{
            Arquivo<Cliente> arqCliente = new Arquivo<>(Cliente.class.getConstructor(), "BaseDeDados/clientes.db");
            arqCliente.create(c1);
            arqCliente.create(c2);
            arqCliente.create(c3);

            Arquivo<Cliente> copiaCliente = new Arquivo<>(Cliente.class.getConstructor(), "BaseDeDados/copiaClientes.db");
            Cliente copiaId2 = arqCliente.read(2);
            Cliente copiaId3 = arqCliente.read(3);
            copiaCliente.create(copiaId2);
            copiaCliente.create(copiaId3);

            //arqCliente.delete(2);
            
            c2.nome = "Ka";
            arqCliente.update(c2);
        }
        catch(Exception e){ e.printStackTrace(); }
    }   
}