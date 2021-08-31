public interface Registro {
    public int getID();
    public void setID(int n);
    public byte[] toByteArray() throws java.io.IOException;
    public void fromByteArray(byte[] ba) throws java.io.IOException;
}