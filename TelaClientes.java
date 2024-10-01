
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
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        // Criação de campos e botões
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        clienteTextArea = new JTextArea();
        clienteTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(clienteTextArea);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();
        JLabel telefoneLabel = new JLabel("Telefone:");
        JTextField telefoneField = new JTextField();
        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField();
        JLabel ruaLabel = new JLabel("Rua:");
        JTextField ruaField = new JTextField();
        JLabel numeroLabel = new JLabel("Número:");
        JTextField numeroField = new JTextField();
        JLabel bairroLabel = new JLabel("Bairro:");
        JTextField bairroField = new JTextField();
        JLabel complementoLabel = new JLabel("Complemento:");
        JTextField complementoField = new JTextField();

        inputPanel.add(nomeLabel);
        inputPanel.add(nomeField);
        inputPanel.add(telefoneLabel);
        inputPanel.add(telefoneField);
        inputPanel.add(cpfLabel);
        inputPanel.add(cpfField);
        inputPanel.add(ruaLabel);
        inputPanel.add(ruaField);
        inputPanel.add(numeroLabel);
        inputPanel.add(numeroField);
        inputPanel.add(bairroLabel);
        inputPanel.add(bairroField);
        inputPanel.add(complementoLabel);
        inputPanel.add(complementoField);

        JButton cadastrarButton = new JButton("Cadastrar Cliente");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Capturando dados do formulário
                    String nome = nomeField.getText();
                    String telefone = telefoneField.getText();
                    String cpf = cpfField.getText();
                    String rua = ruaField.getText();
                    int numero = Integer.parseInt(numeroField.getText());
                    String bairro = bairroField.getText();
                    String complemento = complementoField.getText();

                    // Criando novo cliente
                    Cliente novoCliente = new Cliente(nome, telefone, cpf, rua, numero, bairro, complemento);
                    dataManager.getClientes().add(novoCliente);
                    dataManager.salvarDados();

                    // Atualizando a lista de clientes
                    atualizarListaClientes();
                    
                    // Limpando os campos do formulário
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

        // Botão para visualizar todos os clientes cadastrados
        JButton visualizarButton = new JButton("Visualizar Clientes");
        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaClientes();
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(cadastrarButton, BorderLayout.SOUTH);
        panel.add(visualizarButton, BorderLayout.EAST);

        add(panel);
    }

    // Método para atualizar o campo de texto com os clientes cadastrados
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
