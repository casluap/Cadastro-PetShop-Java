
import java.io.IOException;
import java.io.RandomAccessFile;

public class CadastroPet extends Pet {

    public CadastroPet() {
        super();
    }

    public CadastroPet(String nome, String raca, double peso, String sexo, int codigo) {
        super(nome, raca, peso, sexo, codigo);
    }

    public void leitura(RandomAccessFile arquivo) throws IOException {
        setNome(montaPalavra(arquivo, 30).trim());        
        setRaca(montaPalavra(arquivo, 30). trim ());
        setPeso(arquivo.readDouble());
        setSexo(montaPalavra(arquivo, 10).trim());
        setCodigo(arquivo.readInt());
    }

    private String montaPalavra(RandomAccessFile arquivo, int tamanho) throws IOException {
        char palavra[] = new char[tamanho];
        char temp;
        for (int i = 0; i < palavra.length; i++) {
            temp = arquivo.readChar();
            palavra[i] = temp;
        }
        return new String(palavra).replace('\0', ' ');
    }

    public void escrita(RandomAccessFile arquivo) throws IOException {
        escrevePalavra(arquivo, getNome(), 30);
        escrevePalavra(arquivo, getRaca(), 30);  
        arquivo.writeDouble(getPeso());   
        escrevePalavra(arquivo, getSexo(), 10);
        arquivo.writeInt(getCodigo());
    }

    private void escrevePalavra(RandomAccessFile arquivo, String palavra, int tamanho) throws IOException {
        StringBuffer buf = null;
        if (palavra != null) {
            buf = new StringBuffer(palavra);
        } else {
            buf = new StringBuffer(tamanho);
        }
        buf.setLength(tamanho);
        arquivo.writeChars(buf.toString());
    }
}
