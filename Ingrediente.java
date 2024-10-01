public class Ingrediente {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private double valor;
    private int quantidade;

    public Ingrediente(String nome, double valor, int quantidade) {
        this.id = contadorId++;
        setNome(nome);
        setValor(valor);
        setQuantidade(quantidade);
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do ingrediente não pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo.");
        }
        this.valor = valor;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        }
        this.quantidade = quantidade;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getValor() { return valor; }
    public int getQuantidade() { return quantidade; }
}
