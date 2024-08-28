import java.time.LocalDate;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;


public class Cliente implements Registro{

    public int id;
    public int cep;
    public String nome, cpf;    
    public LocalDate dataNascimento;

    public Cliente(){
        this("", "", LocalDate.now(), 0);
    }

    public Cliente(String nome, String cpf, LocalDate dataNascimento, int cep){
        this.id = -1;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
    }

    public void setId(int id){ 
        this.id = id; 
    }

    public int getId(){ 
        return id; 
    }

    public byte[] toByteArray() throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.id);
        dos.writeUTF(this.nome);
        dos.write(this.cpf.getBytes());
        dos.writeInt((int) this.dataNascimento.toEpochDay());
        dos.writeInt(this.cep);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] b) throws IOException{
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        byte[] cpf = new byte[11];

        this.id = dis.readInt();
        this.nome = dis.readUTF();
        dis.read(cpf);
        this.cpf = new String(cpf);
        this.dataNascimento = LocalDate.ofEpochDay(dis.readInt());
        this.cep = dis.readInt();
    }

}
