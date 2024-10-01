package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPedidos extends JFrame {
    private DataManager dataManager;
    private JTextArea pedidosTextArea;

    public TelaPedidos(DataManager dataManager) {
        this.dataManager = dataManager;
        setTitle("Gerenciar Pedidos");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);  // Espaçamento entre componentes

        JLabel clienteIdLabel = new JLabel("ID Cliente:");
        JTextField clienteIdField = new JTextField(10);
        JLabel lancheIdLabel = new JLabel("ID Lanche:");
        JTextField lancheIdField = new JTextField(10);
        JLabel quantidadeLabel = new JLabel("Quantidade:");
        JTextField quantidadeField = new JTextField(5);
        JLabel freteLabel = new JLabel("Frete:");
        JTextField freteField = new JTextField(10);

        // Posicionando os campos na grid
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(clienteIdLabel, gbc);

        gbc.gridx = 1;
        add(clienteIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lancheIdLabel, gbc);

        gbc.gridx = 1;
        add(lancheIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(quantidadeLabel, gbc);

        gbc.gridx = 1;
        add(quantidadeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(freteLabel, gbc);

        gbc.gridx = 1;
        add(freteField, gbc);

        // Botão de cadastro
        JButton criarPedidoButton = new JButton("Criar Pedido");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(criarPedidoButton, gbc);

        criarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int clienteId = Integer.parseInt(clienteIdField.getText());
                    int lancheId = Integer.parseInt(lancheIdField.getText());
                    int quantidade = Integer.parseInt(quantidadeField.getText());
                    double frete = Double.parseDouble(freteField.getText());

                    Cliente cliente = dataManager.getClienteById(clienteId);
                    Lanche lanche = dataManager.getLancheById(lancheId);

                    if (cliente == null) {
                        throw new Exception("Cliente com ID " + clienteId + " não encontrado.");
                    }
                    if (lanche == null) {
                        throw new Exception("Lanche com ID " + lancheId + " não encontrado.");
                    }

                    Pedido pedido = new Pedido(cliente, "delivery", frete);
                    pedido.adicionarLanche(lanche, quantidade, dataManager.getEstoque());
                    pedido.calcularValorTotal();

                    dataManager.getPedidos().add(pedido);
                    dataManager.salvarDados();

                    clienteIdField.setText("");
                    lancheIdField.setText("");
                    quantidadeField.setText("");
                    freteField.setText("");

                    JOptionPane.showMessageDialog(null, "Pedido criado com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar pedido: " + ex.getMessage());
                }
            }
        });

        // Campo para visualizar pedidos
        pedidosTextArea = new JTextArea(10, 30);
        pedidosTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(pedidosTextArea);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        // Botão de visualização
        JButton visualizarButton = new JButton("Visualizar Pedidos");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(visualizarButton, gbc);

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaPedidos();
            }
        });
    }

    private void atualizarListaPedidos() {
        StringBuilder sb = new StringBuilder();
        for (Pedido pedido : dataManager.getPedidos()) {
            sb.append("ID: ").append(pedido.getId())
              .append(" - Cliente: ").append(pedido.getCliente().getNome())
              .append(" - Valor Total: ").append(pedido.getValorTotal()).append("\n");
        }
        pedidosTextArea.setText(sb.toString());
    }
}