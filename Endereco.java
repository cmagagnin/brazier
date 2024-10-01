public class Endereco {
    private String rua;
    private int numero;
    private String bairro;
    private String complemento;

    public Endereco(String rua, int numero, String bairro, String complemento) {
        setRua(rua);
        setNumero(numero);
        setBairro(bairro);
        this.complemento = complemento;
    }

    // Validações nos setters
    public void setRua(String rua) {
        if (rua == null || rua.trim().isEmpty()) {
            throw new IllegalArgumentException("Rua não pode ser vazia.");
        }
        this.rua = rua;
    }

    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("Número deve ser positivo.");
        }
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        if (bairro == null || bairro.trim().isEmpty()) {
            throw new IllegalArgumentException("Bairro não pode ser vazio.");
        }
        this.bairro = bairro;
    }

    // Getters
    public String getRua() { return rua; }
    public int getNumero() { return numero; }
    public String getBairro() { return bairro; }
    public String getComplemento() { return complemento; }
}
