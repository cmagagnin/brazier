package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaClientes extends JFrame {
    private DataManager dataManager;
    private JTextArea clienteTextArea;

    public TelaClientes(DataManager dataManager) {
        this.dataManager = dataManager;
        setTitle("Gerenciar Clientes");
        setSize(800, 600); // largura, altura
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);  // Espaçamento entre componentes

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField(15);
        JLabel telefoneLabel = new JLabel("Telefone:");
        JTextField telefoneField = new JTextField(15);
        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField(15);
        JLabel ruaLabel = new JLabel("Rua:");
        JTextField ruaField = new JTextField(15);
        JLabel numeroLabel = new JLabel("Número:");
        JTextField numeroField = new JTextField(5);
        JLabel bairroLabel = new JLabel("Bairro:");
        JTextField bairroField = new JTextField(15);
        JLabel complementoLabel = new JLabel("Complemento:");
        JTextField complementoField = new JTextField(15);

        // Posicionando os campos na grid
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nomeLabel, gbc);

        gbc.gridx = 1;
        add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(telefoneLabel, gbc);

        gbc.gridx = 1;
        add(telefoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(cpfLabel, gbc);

        gbc.gridx = 1;
        add(cpfField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(ruaLabel, gbc);

        gbc.gridx = 1;
        add(ruaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(numeroLabel, gbc);

        gbc.gridx = 1;
        add(numeroField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(bairroLabel, gbc);

        gbc.gridx = 1;
        add(bairroField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(complementoLabel, gbc);

        gbc.gridx = 1;
        add(complementoField, gbc);

        // Botão de cadastro
        JButton cadastrarButton = new JButton("Cadastrar Cliente");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(cadastrarButton, gbc);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = nomeField.getText();
                    String telefone = telefoneField.getText();
                    String cpf = cpfField.getText();
                    String rua = ruaField.getText();
                    int numero = Integer.parseInt(numeroField.getText());
                    String bairro = bairroField.getText();
                    String complemento = complementoField.getText();

                    Cliente novoCliente = new Cliente(nome, telefone, cpf, rua, numero, bairro, complemento);
                    dataManager.getClientes().add(novoCliente);
                    dataManager.salvarDados();

                    nomeField.setText("");
                    telefoneField.setText("");
                    cpfField.setText("");
                    ruaField.setText("");
                    numeroField.setText("");
                    bairroField.setText("");
                    complementoField.setText("");

                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + ex.getMessage());
                }
            }
        });

        // Campo para visualizar clientes
        clienteTextArea = new JTextArea(5, 38);
        clienteTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(clienteTextArea);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        // Botão de visualização
        JButton visualizarButton = new JButton("Visualizar Clientes");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        add(visualizarButton, gbc);

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaClientes();
            }
        });
    }

    private void atualizarListaClientes() {
        StringBuilder sb = new StringBuilder();
        for (Cliente cliente : dataManager.getClientes()) {
            sb.append("ID: ").append(cliente.getId())
              .append(" - Nome: ").append(cliente.getNome())
              .append(" - CPF: ").append(cliente.getCpf())
              .append(" - Telefone: ").append(cliente.getTelefone()).append("\n");
        }
        clienteTextArea.setText(sb.toString());
    }
}