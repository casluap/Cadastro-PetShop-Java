
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

public class SistemaPet {

    private String nomeArquivo;
    private RandomAccessFile arquivo;

    public SistemaPet() {
        setNomeArquivo("PET.DAT");
        abrirArquivo();
    }

    public SistemaPet(String nomeArquivo) {
        setNomeArquivo(nomeArquivo);
        abrirArquivo();
    }

    public void finalize() {
        fecharArquivo();
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public RandomAccessFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(RandomAccessFile arquivo) {
        this.arquivo = arquivo;
    }

    public void abrirArquivo() {
        try {
            File fileArquivo = new File(getNomeArquivo());
            arquivo = new RandomAccessFile(fileArquivo, "rw");
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
    }

    public void fecharArquivo() {
        try {
            arquivo.close();
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
    }

    public boolean inserir(CadastroPet registro) {
        try {
            arquivo.seek(arquivo.length());
            registro.escrita(arquivo);
            return true;
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return false;
    }

    public int posicao(int chave) {
        int posicao = -1;
        CadastroPet registro = new CadastroPet();
        try {
            arquivo.seek(0);
            boolean achei = false;
            while ((getArquivo().getFilePointer() < getArquivo().length()) && (achei == false)) {
                registro.leitura(arquivo);
                if (registro.getCodigo() == chave) {
                    achei = true;
                }
                posicao = posicao + 1;
            }
            if (achei == true) {
                return posicao;
            } else {
                return -1;
            }
        } catch (EOFException eof) {
            System.out.println("Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return posicao;
    }

    public String listarLogico() {
        String linhalogico = "";
        CadastroPet registro = new CadastroPet();
        try {
            arquivo.seek(0);
            while (getArquivo().getFilePointer() < getArquivo().length()) {
                registro.leitura(arquivo);
                if (registro.getCodigo() != -1) {
                    linhalogico = linhalogico + registro.toString() + "\n";
                }
            }
        } catch (EOFException eof) {
            System.out.println("Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return linhalogico;
    }

    public String listarFisico() {
        String linhafisico = "";
        CadastroPet registro = new CadastroPet();
        try {
            arquivo.seek(0);
            while (getArquivo().getFilePointer() < getArquivo().length()) {
                registro.leitura(arquivo);
                linhafisico = linhafisico + registro.toString() + "\n";
            }
        } catch (EOFException eof) {
            System.out.println("Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return linhafisico;
    }

    public int quantidadeCadastro() {
        int contador = 0;
        try {
            CadastroPet registro = new CadastroPet();
            arquivo.seek(0);
            while (getArquivo().getFilePointer() < getArquivo().length()) {
                registro.leitura(arquivo);
                contador = contador + 1;
            }
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return (contador);
    }

    public boolean alterarChave(int chave, CadastroPet pet) {
        try {
            int posicao = posicao(chave);
            if (posicao != -1) {
                CadastroPet registro = pet;
                arquivo.seek(posicao * getTamanhoRegistro());
                registro.escrita(arquivo);
                return true;
            } else {
                return false;
            }
        } catch (EOFException eof) {
            System.out.println("Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return false;
    }

    public boolean alterarDescritivo(String descritivo, CadastroPet pet) {
        long posicao = 0;
        CadastroPet registro = new CadastroPet();
        try {
            arquivo.seek(posicao);
            boolean achei = false;
            while ((getArquivo().getFilePointer() < getArquivo().length()) && (achei == false)) {
                registro.leitura(arquivo);
                if (descritivo.equals(registro.getNome())) {
                    achei = true;
                }
                registro.getCodigo();
            }
            posicao = posicao(registro.getCodigo());
            if (posicao != -1) {
                registro = pet;
                arquivo.seek(getArquivo().getFilePointer() - 152);
                registro.escrita(arquivo);
                return true;
            } else {
                return false;
            }
        } catch (EOFException eof) {
            System.out.println("Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return false;
    }

    public CadastroPet consultarChave(int chave) {
        CadastroPet retorno = null;
        CadastroPet registro = new CadastroPet();
        try {
            arquivo.seek(0);
            registro.leitura(arquivo);
            while ((getArquivo().getFilePointer() < getArquivo().length()) && (registro.getCodigo() != chave)) {
                registro.leitura(arquivo);
            }
            if (registro.getCodigo() == chave) {
                retorno = registro;
            }
        } catch (EOFException eof) {
            System.out.println("Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return retorno;
    }

    public CadastroPet consultarDescritivo(String descritivo) {
        CadastroPet retorno = null;
        CadastroPet registro = new CadastroPet();
        try {
            arquivo.seek(0);
            registro.leitura(arquivo);
            while ((getArquivo().getFilePointer() < getArquivo().length()) && (!registro.getNome().equals(descritivo))) {
                registro.leitura(arquivo);
            }
            if (registro.getNome().equals(descritivo)) {
                retorno = registro;
            }
        } catch (EOFException eof) {
            System.out.println("Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return retorno;
    }

    public boolean excluirChave(int chave) {
        long posicao = 0;
        CadastroPet registro = new CadastroPet();
        try {
            arquivo.seek(posicao);
            boolean achei = false;
            while ((getArquivo().getFilePointer() < getArquivo().length()) && (achei == false)) {
                registro.leitura(arquivo);
                if (registro.getCodigo() == chave) {
                    achei = true;
                    break;
                }
                posicao = getArquivo().getFilePointer();
            }
            if (achei == true) {
                registro.setCodigo(-1);
                arquivo.seek(posicao);
                registro.escrita(arquivo);
                return true;
            }
        } catch (EOFException eof) {
            System.out.println("Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return false;
    }

    public boolean excluirDescritivo(String descritivoExcluir) {
        long posicao = 0;
        CadastroPet registro = new CadastroPet();
        try {
            arquivo.seek(posicao);
            boolean achei = false;
            while ((getArquivo().getFilePointer() < getArquivo().length()) && (achei == false)) {
                registro.leitura(arquivo);
                if (registro.getNome().equals(descritivoExcluir)) {
                    achei = true;
                    break;
                }
                posicao = getArquivo().getFilePointer();
            }
            if (achei == true) {
                registro.setCodigo(-1);
                arquivo.seek(posicao);
                registro.escrita(arquivo);
                return true;
            } else {
                return false;
            }
        } catch (EOFException eof) {
            System.out.println("Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return false;
    }
        
     public int getTamanhoRegistro() {
        return (152);
     }
    
    public void tamanhoArquivoBytes(SistemaPet cadastro){
        int tamanhoregistro = (cadastro.getTamanhoRegistro() * quantidadeCadastro());
        JOptionPane.showMessageDialog(null, "O tamanho do arquivo é de " + tamanhoregistro + " bytes"); 
    }

    public boolean limparRegistros() {
        try {
            arquivo.setLength(0);
            return true;
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
            return false;
        }
    }
}
