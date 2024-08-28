import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;

public class Arquivo<T extends Registro>{

    final int headerSize = 4;
    Constructor<T> construtor;
    RandomAccessFile arquivo;

    
    public Arquivo(Constructor<T> construtor, String nomeArquivo) throws IOException{
        this.construtor = construtor;
        abrirArquivo(nomeArquivo);
    }

    private void abrirArquivo(String nomeArquivo) throws IOException{
        arquivo = new RandomAccessFile(nomeArquivo, "rw");

        if(arquivo.length() < headerSize){
            arquivo.seek(0);
            arquivo.writeInt(0);
        }
    }

    // ---------------------------------------------------------------------------------------------- CRUD
    // ------------------------------------------------------------------------ LAPIDE + TAMSHORT + OBJETO


    protected int create(T objeto) throws IOException{
        arquivo.seek(0);
        int proxId = arquivo.readInt()+1;
        arquivo.seek(0);
        arquivo.writeInt(proxId);
        objeto.setId(proxId);
        arquivo.seek(arquivo.length());

        byte[] b = objeto.toByteArray();
        arquivo.writeByte(' ');
        arquivo.writeShort(b.length);
        arquivo.write(b);

        return objeto.getId();
    }

    protected T read(int id) throws Exception{
        byte[] b;
        byte lapide;
        T objeto = null;
        boolean fim=false;
        short tamRegistro;
        arquivo.seek(headerSize);

        while(arquivo.getFilePointer()<arquivo.length() && !fim){
            lapide = arquivo.readByte();
            objeto = construtor.newInstance();
            tamRegistro = arquivo.readShort();
            b = new byte[tamRegistro];
            arquivo.read(b);

            if(lapide==' '){
                objeto.fromByteArray(b);
                if(objeto.getId()==id){ fim = true; }
                else objeto = null;
            }
        }

        return objeto;
    }

    protected boolean update(T novoObjeto) throws Exception{
        byte[] b;
        byte lapide;
        Long endereco;
        T objeto = null;
        boolean fim=false;
        short tamRegistro;
        arquivo.seek(headerSize);

        while(arquivo.getFilePointer()<arquivo.length() && !fim){
            objeto = construtor.newInstance();
            endereco = arquivo.getFilePointer();
            lapide = arquivo.readByte();
            tamRegistro = arquivo.readShort();
            b = new byte[tamRegistro];
            arquivo.read(b);

            if(lapide==' '){
                objeto.fromByteArray(b);
                
                if(objeto.getId() == novoObjeto.getId()){
                    byte[] bb = novoObjeto.toByteArray();
                    short tamNovoRegistro = (short) bb.length;

                    if(tamNovoRegistro <= tamRegistro){
                        arquivo.seek(endereco+3);
                        arquivo.write(bb);
                    }
                    else{
                        arquivo.seek(endereco);
                        arquivo.write('*');

                        arquivo.seek(arquivo.length());
                        arquivo.writeByte(' ');
                        arquivo.writeShort(tamNovoRegistro);
                        arquivo.write(bb);
                    }
                    fim=true;
                }
            }   
        }
        
        return fim; 
    }

    protected boolean delete(int id) throws Exception{
        byte[] b;
        byte lapide;
        Long endereco;
        T objeto = null;
        boolean fim=false;
        short tamRegistro;
        arquivo.seek(headerSize);

        while(arquivo.getFilePointer()<arquivo.length() && !fim){
            endereco = arquivo.getFilePointer();
            lapide = arquivo.readByte();
            objeto = construtor.newInstance();
            tamRegistro = arquivo.readShort();
            b = new byte[tamRegistro];
            arquivo.read(b);

            if(lapide==' '){
                objeto.fromByteArray(b);
                if(objeto.getId()==id){
                    arquivo.seek(endereco);
                    arquivo.write('*');
                    fim = true; 
                }
            }
        }

        return fim;
    }

}