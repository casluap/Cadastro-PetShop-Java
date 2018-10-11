
public class Pet {

    private String nome;
    private String raca;
    private double peso;
    private String sexo;
    private int codigo;

    public Pet() {
    }

    public Pet(String nome, String raca, double peso, String sexo, int codigo) {
        setNome(nome);
        setRaca(raca);
        setPeso(peso);
        setSexo(sexo);
        setCodigo(codigo);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return ("Nome: " + getNome() + " \\ Raça: " + getRaca()
                + "\\ Sexo: " + getSexo() + " \\ Peso: "
                + getPeso() + " \\ Código : " + getCodigo());
    }
}
