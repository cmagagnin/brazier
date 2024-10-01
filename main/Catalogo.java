package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Catalogo {
    private List<Lanche> lanches;

    public Catalogo() {
        this.lanches = new ArrayList<>();
    }

    // Método para CadastrarLanche
    public void cadastrarLanche(Lanche lanche) {
        lanches.add(lanche);
    }

    // Método para editar lanche
    public void editarLanche(int id, String novoNome, Map<Ingrediente, Integer> novosIngredientes, double novoValorFinal) {
        for (Lanche l : lanches) {
            if (l.getId() == id) {
                l.setNomeLanche(novoNome);
                l.getIngredientes().clear();
                l.getIngredientes().putAll(novosIngredientes);
                l.calcularValorDeCusto();
                l.calcularValorSugerido();
                l.setValorFinal(novoValorFinal);
                break;
            }
        }
    }

    public Lanche getLancheById(int id) {
        for (Lanche l : lanches) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    public List<Lanche> getLanches() {
        return lanches;
    }
}
