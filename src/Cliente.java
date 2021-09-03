import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Cliente{
    
    public int id;
    public String cpf;
    public String nome;
    public String email;

    /**
     * Construtor vazio da Classe   
     */
    public Cliente(){
        this.id = -1;
        this.cpf = "";
        this.nome = "";
        this.email = "";
    }

    /**
     * Construtor da Classe
     * @param id Id do Cliente
     * @param cpf CPF do Cliente
     * @param nome Nome do Cliente
     * @param email Email do Cliente
     * @return String com as informações do Cliente
     */
    public Cliente(int id, String cpf, String nome, String email) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    /**
     * Imprime os atributos do Cliente
     */
    public String imprimeCliente() {
        return "\nID...: " + this.id + "\nCPF..: "+ this.cpf + "\nNome.: " + this.nome + "\nEmail: " + this.email;
    } 

    /**
     * Converte os atributos da classe em um vetor de bytes
     * @return Vetor de bytes com as informações do Cliente
     * @throws IOException caso haja problema com a manipulação do arquivo
     */
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(cpf);
        dos.writeUTF(nome);
        dos.writeUTF(email);
        return baos.toByteArray();
    }

    /**
     * Converte o vetor de bytes em atributos da classe
     * @param ba Vetor de bytes com as informações do Cliente
     * @throws IOException caso haja problema com a manipulação do arquivo
     */
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        id = dis.readInt();
        cpf = dis.readUTF();
        nome = dis.readUTF();
        email = dis.readUTF();
    }

    /**
     * Envia o id do Cliente
     * @return Id do Cliente
     */
    public int getId() {
        return id;
    }

    /**
     * Recebe um inteiro como Parâmetro e o define como id do cliente
     */
    public void setId(int id) {
        this.id = id;
    }
}