import java.time.LocalDate;


public class Sistema{

    public static void main(String[] args){

        Cliente c1 = new Cliente("Jose Augusto", "00530065694", LocalDate.of(1998, 4, 21), 40540228);
        Cliente c2 = new Cliente("Luiz Paulo", "02138567411", LocalDate.of(2003, 8, 15), 30535002);
        Cliente c3 = new Cliente("Stefen Curry Extends", "18620022299", LocalDate.of(2001, 1, 7), 35430111);
        Cliente c4 = new Cliente("Jordan Lifer Dayton", "56783899844", LocalDate.of(1995, 11, 28), 32323333);
        Cliente c5 = new Cliente("Maria Clara", "12345678901", LocalDate.of(1990, 5, 12), 50302033);
        Cliente c6 = new Cliente("Pedro Henrique", "98765432100", LocalDate.of(1988, 3, 25), 60404022);
        Cliente c7 = new Cliente("Ana Beatriz", "32165498709", LocalDate.of(2000, 7, 19), 70203044);
        Cliente c8 = new Cliente("Gabriel Souza", "55566677788", LocalDate.of(1993, 12, 8), 80502055);
        Cliente c9 = new Cliente("Felipe Torres", "33344455566", LocalDate.of(1997, 6, 30), 90404066);
        Cliente c10 = new Cliente("Alice Monteiro", "77788899900", LocalDate.of(2005, 9, 22), 60505077);
        Cliente c11 = new Cliente("Lucas Almeida", "44455566677", LocalDate.of(2002, 10, 10), 10602011);
        Cliente c12 = new Cliente("Sofia Pereira", "99988877766", LocalDate.of(1999, 2, 18), 20703033);
        Cliente c13 = new Cliente("Ricardo Mendes", "11122233344", LocalDate.of(1985, 11, 5), 90804022);
        Cliente c14 = new Cliente("Juliana Farias", "55544433322", LocalDate.of(1994, 4, 14), 30802055);

        try{
            Arquivo<Cliente> arqCliente = new Arquivo<>(Cliente.class.getConstructor(), "BaseDeDados/clientes.db");
            arqCliente.create(c1);
            arqCliente.create(c2);
            arqCliente.create(c3);
            arqCliente.create(c4);
            arqCliente.create(c5);
            arqCliente.create(c6);
            arqCliente.create(c7);
            arqCliente.create(c8);
            arqCliente.create(c9);
            arqCliente.create(c10);
            arqCliente.create(c11);
            arqCliente.create(c12);
            arqCliente.create(c13);
            arqCliente.create(c14);
        }
        catch(Exception e){ e.printStackTrace(); }
    }   
}