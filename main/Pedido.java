package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Pedido {
    private static int contadorId = 1;
    private int id;
    private Cliente cliente;
    private List<ItemPedido> lanches; // Lanche e quantidade
    private double valorTotal;
    private String formaPagamento; // pix, cartão crédito, cartão débito, espécie
    private String statusPagamento; // Pendente, Pago
    private String statusPedido; // Fila, Produção, Concluido, Cancelado
    private double frete;

    public Pedido(Cliente cliente, String tipoFrete, double valorFrete) {
        this.id = contadorId++;
        this.cliente = cliente;
        this.lanches = new ArrayList<>();
        this.statusPagamento = "Pendente";
        this.statusPedido = "Fila";
        if (tipoFrete.equalsIgnoreCase("delivery")) {
            this.frete = valorFrete;
        } else {
            this.frete = 0;
        }
    }

    // Método para adicionar lanche ao pedido
    public void adicionarLanche(Lanche lanche, int quantidade, List<Ingrediente> list) {
        lanches.add(new ItemPedido(lanche, quantidade));
        // Atualizar estoque
        for (Entry<Ingrediente, Integer> entry : lanche.getIngredientes().entrySet()) {
            Ingrediente ingrediente = entry.getKey();
            int quantidadeNecessaria = entry.getValue() * quantidade;
            if (ingrediente.getQuantidade() >= quantidadeNecessaria) {
                ingrediente.setQuantidade(ingrediente.getQuantidade() - quantidadeNecessaria);
            } else {
                throw new IllegalArgumentException("Estoque insuficiente para o ingrediente: " + ingrediente.getNome());
            }
        }
    }

    // Método para calcular valor total
    public void calcularValorTotal() {
        valorTotal = frete;
        for (ItemPedido item : lanches) {
            valorTotal += item.getLanche().getValorFinal() * item.getQuantidade();
        }
    }

    // Método Desconto
    public void aplicarDesconto(double porcentagem) {
        valorTotal -= valorTotal * (porcentagem / 100);
    }

    // Método Pagamento
    public void realizarPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
        this.statusPagamento = "Pago";
    }

    // Método StatusPedido
    public void atualizarStatusPedido(String novoStatus, Estoque estoque) {
        if (novoStatus.equalsIgnoreCase("Cancelado") && !this.statusPedido.equalsIgnoreCase("Cancelado")) {
            // Devolver itens ao estoque
            for (ItemPedido item : lanches) {
                Lanche lanche = item.getLanche();
                int quantidadePedido = item.getQuantidade();
                for (Entry<Ingrediente, Integer> entry : lanche.getIngredientes().entrySet()) {
                    Ingrediente ingrediente = entry.getKey();
                    int quantidadeDevolver = entry.getValue() * quantidadePedido;
                    ingrediente.setQuantidade(ingrediente.getQuantidade() + quantidadeDevolver);
                }
            }
        }
        this.statusPedido = novoStatus;
    }

    // Getters e Setters
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<ItemPedido> getLanches() { return lanches; }
    public double getValorTotal() { return valorTotal; }
    public String getFormaPagamento() { return formaPagamento; }
    public String getStatusPagamento() { return statusPagamento; }
    public String getStatusPedido() { return statusPedido; }
    public double getFrete() { return frete; }

    // Classe interna para representar itens do pedido
    public class ItemPedido {
        private Lanche lanche;
        private int quantidade;

        public ItemPedido(Lanche lanche, int quantidade) {
            this.lanche = lanche;
            this.quantidade = quantidade;
        }

        public Lanche getLanche() { return lanche; }
        public int getQuantidade() { return quantidade; }
    }
}
