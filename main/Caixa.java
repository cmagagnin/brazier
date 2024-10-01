package main;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Caixa {
    private double saldo;
    private List<Transacao> entradas;
    private List<Transacao> saidas;

    public Caixa() {
        this.saldo = 0;
        this.entradas = new ArrayList<>();
        this.saidas = new ArrayList<>();
    }

    // Método Entrada
    public void registrarEntrada(double valor, String descricao) {
        saldo += valor;
        entradas.add(new Transacao(valor, descricao, new Date()));
    }

    // Método Saída
    public void registrarSaida(double valor, String descricao) {
        saldo -= valor;
        saidas.add(new Transacao(valor, descricao, new Date()));
    }

    // Atualizar saldo com base no pagamento do pedido
    public void atualizarSaldoPedido(Pedido pedido) {
        String descricao = "Recebimento pedido ID: " + pedido.getId();
        if (pedido.getStatusPagamento().equalsIgnoreCase("Pago")) {
            registrarEntrada(pedido.getValorTotal(), descricao);
        } else if (pedido.getStatusPagamento().equalsIgnoreCase("Pendente")) {
            registrarSaida(pedido.getValorTotal(), "Estorno do " + descricao);
        }
    }

    // Método RelatorioCaixa
    public void gerarRelatorio(String periodo) {
        // Implementação simplificada. Aqui você pode filtrar as transações por data e gerar o relatório com os totais.
        double totalEntradas = entradas.stream().mapToDouble(Transacao::getValor).sum();
        double totalSaidas = saidas.stream().mapToDouble(Transacao::getValor).sum();
        double saldoFinal = saldo;

        System.out.println("Relatório do Caixa - Período: " + periodo);
        System.out.println("Saldo Inicial: R$ " + String.format("%.2f", (saldoFinal - totalEntradas + totalSaidas)));
        System.out.println("Total de Entradas: R$ " + String.format("%.2f", totalEntradas));
        System.out.println("Total de Saídas: R$ " + String.format("%.2f", totalSaidas));
        System.out.println("Saldo Final: R$ " + String.format("%.2f", saldoFinal));
        // CustosIngredientes e Lucro podem ser calculados conforme a necessidade.
    }

    // Classe interna Transacao
    public class Transacao {
        private double valor;
        private String descricao;
        private Date dataHora;

        public Transacao(double valor, String descricao, Date dataHora) {
            this.valor = valor;
            this.descricao = descricao;
            this.dataHora = dataHora;
        }

        public double getValor() { return valor; }
        public String getDescricao() { return descricao; }
        public Date getDataHora() { return dataHora; }
    }

    // Getters
    public double getSaldo() { return saldo; }
    public List<Transacao> getEntradas() { return entradas; }
    public List<Transacao> getSaidas() { return saidas; }
}
