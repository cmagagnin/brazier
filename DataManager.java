import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class DataManager {
    private static final String DATA_FILE = "data.json";
    private Gson gson;

    // Listas de objetos para persistência
    private List<Cliente> clientes;
    private List<Ingrediente> ingredientes;
    private List<Lanche> lanches;
    private List<Pedido> pedidos;
    private Caixa caixa;

    public DataManager() {
        gson = new Gson();
        carregarDados();
    }

    // Método para carregar dados do arquivo JSON
    public void carregarDados() {
        try (Reader reader = new FileReader(DATA_FILE)) {
            Type tipoDados = new TypeToken<Dados>(){}.getType();
            Dados dados = gson.fromJson(reader, tipoDados);
            this.clientes = dados.clientes;
            this.ingredientes = dados.ingredientes;
            this.lanches = dados.lanches;
            this.pedidos = dados.pedidos;
            this.caixa = dados.caixa;
        } catch (IOException e) {
            // Se o arquivo não existir ou ocorrer um erro, inicializar listas vazias
            this.clientes = new ArrayList<>();
            this.ingredientes = new ArrayList<>();
            this.lanches = new ArrayList<>();
            this.pedidos = new ArrayList<>();
            this.caixa = new Caixa();
        }
    }

    // Método para salvar dados no arquivo JSON
    public void salvarDados() {
        try (Writer writer = new FileWriter(DATA_FILE)) {
            Dados dados = new Dados(clientes, ingredientes, lanches, pedidos, caixa);
            gson.toJson(dados, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Classe interna para representar os dados
    private class Dados {
        private List<Cliente> clientes;
        private List<Ingrediente> ingredientes;
        private List<Lanche> lanches;
        private List<Pedido> pedidos;
        private Caixa caixa;

        public Dados(List<Cliente> clientes, List<Ingrediente> ingredientes, List<Lanche> lanches, List<Pedido> pedidos, Caixa caixa) {
            this.clientes = clientes;
            this.ingredientes = ingredientes;
            this.lanches = lanches;
            this.pedidos = pedidos;
            this.caixa = caixa;
        }
    }

    // Getters para acesso às listas
    public List<Cliente> getClientes() { return clientes; }
    public List<Ingrediente> getIngredientes() { return ingredientes; }
    public List<Lanche> getLanches() { return lanches; }
    public List<Pedido> getPedidos() { return pedidos; }
    public Caixa getCaixa() { return caixa; }
}
