import java.io.IOException;

public interface Registro {
    public void setId(int id);
    public int getId();
    public byte[] toByteArray() throws IOException;
    public void fromByteArray(byte[] b) throws IOException;
}
