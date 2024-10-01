import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Ingrediente> ingredientes;

    public Estoque() {
        this.ingredientes = new ArrayList<>();
    }

    // Método CadastrarIngrediente
    public void cadastrarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    // Método para editar ingrediente
    public void editarIngrediente(int id, String novoNome, double novoValor, int novaQuantidade) {
        for (Ingrediente ing : ingredientes) {
            if (ing.getId() == id) {
                ing.setNome(novoNome);
                ing.setValor(novoValor);
                ing.setQuantidade(novaQuantidade);
                break;
            }
        }
    }

    // Métodos para manipulação do estoque
    public Ingrediente getIngredienteById(int id) {
        for (Ingrediente ing : ingredientes) {
            if (ing.getId() == id) {
                return ing;
            }
        }
        return null;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }
}
