import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Constructor;

public class CRUD{

    private RandomAccessFile arq;
    private final String nomeArq = "usuario.db";

    /**
     * Construtor da Classe   
     */
    public CRUD() {
        try {
            this.arq = new RandomAccessFile(nomeArq,"rw");
            if(arq.length() == 0) {
                arq.writeInt(0);
            }
        } catch(IOException E){
            System.out.println("Erro! "+E.getMessage());
        }
    }


    /**
     * Insere Cliente no arquivo
     * @param cliente Cliente a ser inserido
     */
    public int create(Cliente objeto) throws IOException {
        arq.seek(0);
        int ultimoID = arq.readInt();
        objeto.setId(ultimoID + 1);
        arq.seek(0);
        arq.writeInt(objeto.getId());

        //cria byteArray do objeto
        arq.seek(arq.length());
        byte[] b = objeto.toByteArray();

        //escreve L�pide
        arq.writeByte(' ');

        //escreve Tamanho do array
        arq.writeInt(b.length);

        //escreve Array
        arq.write(b);

        return objeto.getId();
    
    }

    /** 
     * Le Cliente do arquivo
     * @param id Id do cliente a ser lido
     * @return Objeto lido
     */
    public Cliente read(int id) throws IOException {
         arq.seek(4);
        //pular cabeçalho

        byte lapide;
        byte[] b;
        int tam;
        Cliente objeto;
        while(arq.getFilePointer() < arq.length()) {
            lapide = arq.readByte();
            tam = arq.readInt();
            b = new byte[tam];
            arq.read(b);
            if(lapide != '*') {
                objeto = new Cliente();
                objeto.fromByteArray(b);
                if(objeto.getId() == id) {
                    return objeto;
                }
            }
        }

        return null;
    }

    /**
     * Atualiza um cliente inserido no arquivo
     * @param cliente Cliente a ser atualizado
     * @return booleano caso exista cliente no arquivo
     */
    public boolean update(Cliente novoObjeto) throws IOException {
        arq.seek(4);
        long pos;
        byte lapide;
        byte[] b;
        byte[] novoB;
        int tam;
        Cliente objeto;
        while(arq.getFilePointer() < arq.length()) {
            pos = arq.getFilePointer();
            lapide = arq.readByte();
            tam = arq.readInt();
            b = new byte[tam];
            arq.read(b);
            if(lapide != '*') {
                objeto = new Cliente();
                objeto.fromByteArray(b);
                if(objeto.getId() == novoObjeto.getId()) {
                    novoB = novoObjeto.toByteArray();
                    if(novoB.length < tam) {
                        arq.seek(pos + 5);
                        arq.write(novoB);
                    }
                    else {
                        arq.seek(pos);
                        arq.writeByte(' ');
                        arq.seek(arq.length());
                        arq.writeByte('*');
                        arq.writeInt(novoB.length);
                        arq.write(novoB);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Deleta cliente do arquivo
     * @param id Id do cliente a ser removido
     */
    public boolean delete(int id) throws IOException {
        arq.seek(4);
        //pular cabeçalho
        long pos;
        byte lapide;
        int tam;
        byte[] b;
        Cliente objeto;
        while(arq.getFilePointer() < arq.length()) {
            pos = arq.getFilePointer();
            lapide = arq.readByte();
            tam = arq.readInt();
            b = new byte[tam];
            arq.read(b);
            if(lapide != '*') {
                objeto = new Cliente();
                objeto.fromByteArray(b);
                if(objeto.getId() == id) {
                    arq.seek(pos);
                    arq.writeByte('*');
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Verifica se arq ja foi criado
     * @return true caso arq != null
     */
    public boolean getArqIsNull(){
        return (arq == null) ?  false : true;
    }

}