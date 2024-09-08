import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;

import java.util.ArrayList;
import java.util.List;

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

    /*
     * Modelo: Enfase em maior volume de ordenamento(RAM) para um menor uso na mesclagem(CPU)
     * Considerando um Bd de 10mb vamos usar blocos de 1mb e 2 arquivos de intercalação.
     * Log2(250000/2500) = 2 intercalações
    */

    protected boolean ordenar(){
        boolean fim = false;

        try{
            List<byte[]> bloco = new ArrayList<>();
            int blocoTam = 1024 * 1024;  

            byte[] b;
            byte lapide;
            int arqAtual=0;
            short tamRegistro;
            arquivo.seek(headerSize);

            while(arquivo.getFilePointer()<arquivo.length()){
                long tamBlocoCorrente=0;
                
                while(arquivo.getFilePointer()<arquivo.length() && tamBlocoCorrente<blocoTam){
                    lapide = arquivo.readByte();
                    tamRegistro = arquivo.readShort();
                    b = new byte[tamRegistro];
                    int qtdLida = arquivo.read(b);

                    if(qtdLida<tamRegistro)
                        throw new IOException("Número de bytes lidos incorreto: esperado " + tamRegistro + ", obtido " + qtdLida);

                    if(lapide==' '){
                        bloco.add(b);
                        tamBlocoCorrente += (tamRegistro+3);
                    }
                }
                repartimento(bloco, arqAtual);
                bloco.clear();
                arqAtual++;
            }
            fim=true;

        }catch(Exception e){ e.printStackTrace(); }
        return fim;
    }

    protected void repartimento(List<byte[]> bloco, int numArquivo){
        RandomAccessFile aux = null;

        try{
            aux = new RandomAccessFile(numArquivo % 2 == 0 ? "BaseDeDados/aux1.db" : "BaseDeDados/aux2.db", "rw");
            int tam = bloco.size();
            aux.seek(aux.length());

            for(int i=0; i<tam; i++){
                aux.writeByte(' ');
                aux.writeShort(bloco.get(i).length);
                aux.write(bloco.get(i));
            }

        }catch(IOException e){ e.getMessage(); }
        finally{ if(aux!=null) try { aux.close(); } catch (IOException e) { e.printStackTrace(); }}
    }

    protected void intercalação(){
        RandomAccessFile aux = null;
        int numArquivo=0;

        try{

            aux = new RandomAccessFile(numArquivo % 2 == 0 ? "BaseDeDados/aux1.db" : "BaseDeDados/aux2.db", "rw");

        }catch(IOException e){ e.getMessage(); }
        finally{ if(aux!=null) try { aux.close(); } catch (IOException e) { e.printStackTrace(); }}
    }
}