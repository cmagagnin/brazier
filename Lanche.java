import java.util.HashMap;
import java.util.Map;

public class Lanche {
    private static int contadorId = 1;
    private int id;
    private String nomeLanche;
    private Map<Ingrediente, Integer> ingredientes; // Ingrediente e quantidade
    private double valorCusto;
    private double valorSugerido;
    private double valorFinal;

    public Lanche(String nomeLanche) {
        this.id = contadorId++;
        setNomeLanche(nomeLanche);
        this.ingredientes = new HashMap<>();
    }

    public void setNomeLanche(String nomeLanche) {
        if (nomeLanche == null || nomeLanche.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do lanche não pode ser vazio.");
        }
        this.nomeLanche = nomeLanche;
    }

    // Método para adicionar ingrediente e quantidade
    public void adicionarIngrediente(Ingrediente ingrediente, int quantidade) {
        if (ingrediente == null) {
            throw new IllegalArgumentException("Ingrediente não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva.");
        }
        ingredientes.put(ingrediente, quantidade);
    }

    // Método para calcular ValorDeCusto
    public void calcularValorDeCusto() {
        valorCusto = 0;
        for (Map.Entry<Ingrediente, Integer> entry : ingredientes.entrySet()) {
            valorCusto += entry.getKey().getValor() * entry.getValue();
        }
    }

    // Método para calcular ValorSugerido
    public void calcularValorSugerido() {
        valorSugerido = valorCusto * 1.34; // 34% sobre o valor de custo
    }

    // Método ExibirLanche
    public void exibirLanche() {
        System.out.println("ID: " + id);
        System.out.println("Nome do Lanche: " + nomeLanche);
        System.out.println("Ingredientes:");
        for (Map.Entry<Ingrediente, Integer> entry : ingredientes.entrySet()) {
            System.out.println("- " + entry.getKey().getNome() + " x" + entry.getValue());
        }
        System.out.println("Valor de Custo: R$ " + String.format("%.2f", valorCusto));
        System.out.println("Valor Sugerido: R$ " + String.format("%.2f", valorSugerido));
        System.out.println("Valor Final: R$ " + String.format("%.2f", valorFinal));
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNomeLanche() { return nomeLanche; }
    public Map<Ingrediente, Integer> getIngredientes() { return ingredientes; }
    public double getValorCusto() { return valorCusto; }
    public double getValorSugerido() { return valorSugerido; }
    public double getValorFinal() { return valorFinal; }
    public void setValorFinal(double valorFinal) { this.valorFinal = valorFinal; }
}
