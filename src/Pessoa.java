import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Pessoa implements Registro{

    //Declaração de variáveis
    public int id;
    public int cpf;
    public String nome;
    public String email;

    //Construtor vazio
    public Pessoa{
        this.id = -1;
        this.cpf = -1;
        this.nome = "";
        this.email = "";
    }

    //Construtor
    public Pessoa(int id, int cpf, String nome, String email) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    //
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeInt(cpf);
        dos.writeUTF(nome);
        dos.writeUTF(email);
        return baos.toByteArray();
    }

    //
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        id = dis.readInt();
        cpf = dis.readInt();
        nome = dis.readUTF();
        email = dis.readUTF();
    }

    //Retorna o id da pessoa
    public int getId() {
        return id;
    }

    //Recebe um inteiro como Parâmetro e o define como id da pessoa
    public void setId(int id) {
        this.id = id;
    }
}