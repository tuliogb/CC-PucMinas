import java.io.FileWriter;
import java.io.RandomAccessFile;

/**
 * CiframentoR -> Exercicio 8
 * Tulio Gomes Braga - 802512
 */
class Arquivo{
    
    static void escrita(){
        Double input;
        int n = MyIO.readInt();

        try{
            FileWriter writer = new FileWriter("valores.txt");

            for(int i=0; i<n; i++){
                input = MyIO.readDouble();
                writer.write(input.toString()+"\n");
            }
            writer.close();
        }catch(Exception e){}
    }

    static void leitura(){
        try{
            RandomAccessFile leitor = new RandomAccessFile("valores.txt", "r");
            leitor.seek(leitor.length()-2);                                                     

            while(leitor.getFilePointer()>0){                                                   
                StringBuilder linha = new StringBuilder();

                while(leitor.getFilePointer()>0 && leitor.read() != '\n'){
                    linha.insert(0, (char) leitor.read());
                    leitor.seek(leitor.getFilePointer()-1);
                }
                MyIO.println(linha.toString());
            }

            leitor.close();
        }catch(Exception e){MyIO.println("ERRO");}
    }



    public static void main(String[] args) {
        escrita();
        leitura();
    }
}