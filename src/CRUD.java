import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.ByteArrayOutputStream;

public class CRUD<T extends Registro> {
    //Declaração de variáveis
    private RandomAccessFile arq;
    private String nomeArq;
    private Constructor<T> construtor;

    //Construtor
    public CRUD(Constructor<T> construtor, String nomeArq) {
        this.nomeArq = nomeArq;
        this.arq = new RandomAccessFile(this.nomeArq,"rw");
        this.construtor = construtor;
    }

    public int create(T objeto) throws IOException {
        arq.seek(0);
        int ultimoID;
        if(arq.length() == 0) {
            ultimoID = 0;
        }
        else {
            arq.seek(0);
            ultimoID = arq.readInt();
        }
        objeto.setID(ultimoID + 1);
        byte[] b = objeto.toByteArray();

        //escreve Lápide
        arq.writeChar(' ');

        //escreve Tamanho do array
        arq.writeInt(b.length);

        //escreve Array
        arq.write(b);
    }

    public T read(int id) throws IOException {
        arq.seek(0);
        //pular cabeçalho
        while(arq.getFilePointer() != arq.length()) {
            char lapide = arq.readChar();
            int tam = arq.readInt();
            if(lapide != '*') {
                byte[] b = new byte[tam];
                arq.read(b, 0, tam);
                T objeto = this.construtor.newIntance();
                objeto.fromByteArray(b);
                if(objeto.getID() == id) {
                    return objeto;
                }
            }
        }

        return null;
    }

    public boolean update(T novoObjeto) throws IOException {
        arq.seek(0);
        while(arq.getFilePointer() != arq.length()) {
            long pos = arq.getFilePointer();
            char lapide = arq.readChar();
            int tam = arq.readInt();
            if(lapide != '*') {
                byte[] b = new byte[tam];
                arq.read(b, 0, tam);
                T objeto = this.construtor.newIntance();
                objeto.fromByteArray(b);
                if(objeto.getID() == novoObjeto.getID()) {
                    byte[] novoB = novoObjeto.toByteArray();
                    if(novoB.length <= tam) {
                        arq.write(novoB);
                    }
                    else {
                        arq.seek(pos);
                        arq.writeChar(' ');
                        arq.writeInt(novoB.length);
                        arq.write(novoB);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean delete(int id) throws IOException {
        arq.seek(0);
        //pular cabeçalho
        while(arq.getFilePointer() != arq.length()) {
            long pos = arq.getFilePointer();
            char lapide = arq.readChar();
            int tam = arq.readInt();
            if(lapide != '*') {
                byte[] b = new byte[tam];
                arq.read(b, 0, tam);
                T objeto = this.construtor.newIntance();
                objeto.fromByteArray(b);
                if(objeto.getID() == id) {
                    arq.writeChar('*');
                    return true;
                }
            }
        }

        return false;
    }

}