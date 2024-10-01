package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String DATA_FILE = "data.txt";  // Changed file name to data.txt to reflect manual handling

    // Listas de objetos para persistência
    private List<Cliente> clientes;
    private List<Ingrediente> ingredientes;
    private List<Lanche> lanches;
    private List<Pedido> pedidos;
    private Caixa caixa;

    public DataManager() {
        carregarDados();  // Load data from file
    }

    // Método para carregar dados do arquivo manualmente
    public void carregarDados() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            clientes = new ArrayList<>();
            ingredientes = new ArrayList<>();
            lanches = new ArrayList<>();
            pedidos = new ArrayList<>();
            caixa = new Caixa();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Cliente:")) {
                    clientes.add(parseCliente(line));
                } else if (line.startsWith("Ingrediente:")) {
                    ingredientes.add(parseIngrediente(line));
                } else if (line.startsWith("Lanche:")) {
                    lanches.add(parseLanche(line));
                } else if (line.startsWith("Pedido:")) {
                    pedidos.add(parsePedido(line));
                } else if (line.startsWith("Caixa:")) {
                    caixa = parseCaixa(line);
                }
            }
        } catch (IOException e) {
            // Se o arquivo não existir ou ocorrer um erro, inicializar listas vazias
            this.clientes = new ArrayList<>();
            this.ingredientes = new ArrayList<>();
            this.lanches = new ArrayList<>();
            this.pedidos = new ArrayList<>();
            this.caixa = new Caixa();
        }
    }

    // Método para salvar dados manualmente
    public void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Cliente cliente : clientes) {
                writer.write("Cliente:" + clienteToString(cliente) + "\n");
            }
            for (Ingrediente ingrediente : ingredientes) {
                writer.write("Ingrediente:" + ingredienteToString(ingrediente) + "\n");
            }
            for (Lanche lanche : lanches) {
                writer.write("Lanche:" + lancheToString(lanche) + "\n");
            }
            for (Pedido pedido : pedidos) {
                writer.write("Pedido:" + pedidoToString(pedido) + "\n");
            }
            writer.write("Caixa:" + caixaToString(caixa) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Métodos de conversão de objeto para string
    private String clienteToString(Cliente cliente) {
        return cliente.getId() + ";" + cliente.getNome() + ";" + cliente.getTelefone() + ";" + cliente.getCpf() + ";" +
                cliente.getRua() + ";" + cliente.getNumero() + ";" + cliente.getBairro() + ";" + cliente.getComplemento();
    }

    private String ingredienteToString(Ingrediente ingrediente) {
        return ingrediente.getId() + ";" + ingrediente.getNome() + ";" + ingrediente.getValor() + ";" + ingrediente.getQuantidade();
    }

    private String lancheToString(Lanche lanche) {
        StringBuilder ingredientesStr = new StringBuilder();
        for (Ingrediente ingrediente : lanche.getIngredientes().keySet()) {
            ingredientesStr.append(ingrediente.getId()).append(":").append(lanche.getIngredientes().get(ingrediente)).append(",");
        }
        return lanche.getId() + ";" + lanche.getNomeLanche() + ";" + ingredientesStr.toString() + ";" + lanche.getValorCusto() + ";" +
                lanche.getValorSugerido() + ";" + lanche.getValorFinal();
    }

    private String pedidoToString(Pedido pedido) {
        StringBuilder lanchesStr = new StringBuilder();
        for (Pedido.ItemPedido item : pedido.getLanches()) {
            lanchesStr.append(item.getLanche().getId()).append(":").append(item.getQuantidade()).append(",");
        }
        return pedido.getId() + ";" + pedido.getCliente().getId() + ";" + lanchesStr.toString() + ";" + pedido.getValorTotal() + ";" +
                pedido.getFormaPagamento() + ";" + pedido.getStatusPagamento() + ";" + pedido.getStatusPedido() + ";" + pedido.getFrete();
    }

    private String caixaToString(Caixa caixa) {
        return caixa.getSaldo() + "";
    }

    // Métodos de conversão de string para objeto
    private Cliente parseCliente(String line) {
        String[] data = line.substring(8).split(";");
        return new Cliente(data[1], data[2], data[3], data[4], Integer.parseInt(data[5]), data[6], data[7]);
    }

    private Ingrediente parseIngrediente(String line) {
        String[] data = line.substring(12).split(";");
        return new Ingrediente(data[1], Double.parseDouble(data[2]), Integer.parseInt(data[3]));
    }

    private Lanche parseLanche(String line) {
        String[] data = line.substring(7).split(";");
        Lanche lanche = new Lanche(data[1]);
        String[] ingredientesData = data[2].split(",");
        for (String ingData : ingredientesData) {
            String[] ingSplit = ingData.split(":");
            Ingrediente ingrediente = getIngredienteById(Integer.parseInt(ingSplit[0]));
            lanche.adicionarIngrediente(ingrediente, Integer.parseInt(ingSplit[1]));
        }
        lanche.calcularValorDeCusto();
        lanche.calcularValorSugerido();
        lanche.setValorFinal(Double.parseDouble(data[5]));
        return lanche;
    }

    private Pedido parsePedido(String line) {
        String[] data = line.substring(7).split(";");
        Cliente cliente = getClienteById(Integer.parseInt(data[1]));
        Pedido pedido = new Pedido(cliente, "delivery", Double.parseDouble(data[7]));
        String[] lanchesData = data[2].split(",");
        for (String lancheData : lanchesData) {
            String[] lancheSplit = lancheData.split(":");
            Lanche lanche = getLancheById(Integer.parseInt(lancheSplit[0]));
            pedido.adicionarLanche(lanche, Integer.parseInt(lancheSplit[1]), null);
        }
        pedido.calcularValorTotal();
        pedido.realizarPagamento(data[4]);
        pedido.atualizarStatusPedido(data[5], null);
        return pedido;
    }

    private Caixa parseCaixa(String line) {
        double saldo = Double.parseDouble(line.substring(6));
        Caixa caixa = new Caixa();
        caixa.registrarEntrada(saldo, "Saldo Inicial");
        return caixa;
    }

    // Métodos para buscar objetos
    Cliente getClienteById(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    Ingrediente getIngredienteById(int id) {
        for (Ingrediente ingrediente : ingredientes) {
            if (ingrediente.getId() == id) {
                return ingrediente;
            }
        }
        return null;
    }

    Lanche getLancheById(int id) {
        for (Lanche lanche : lanches) {
            if (lanche.getId() == id) {
                return lanche;
            }
        }
        return null;
    }

    // Getters para acesso às listas
    public List<Cliente> getClientes() { return clientes; }
    public List<Ingrediente> getIngredientes() { return ingredientes; }
    public List<Lanche> getLanches() { return lanches; }
    public List<Pedido> getPedidos() { return pedidos; }
    public Caixa getCaixa() { return caixa; }
    public List<Ingrediente> getEstoque() { return ingredientes; }
    
}
