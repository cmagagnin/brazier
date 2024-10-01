import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaIngredientes extends JFrame {
    private DataManager dataManager;
    private JTextArea ingredientesTextArea;

    public TelaIngredientes(DataManager dataManager) {
        this.dataManager = dataManager;
        setTitle("Gerenciar Ingredientes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ingredientesTextArea = new JTextArea();
        ingredientesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ingredientesTextArea);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();
        JLabel valorLabel = new JLabel("Valor:");
        JTextField valorField = new JTextField();
        JLabel quantidadeLabel = new JLabel("Quantidade:");
        JTextField quantidadeField = new JTextField();

        inputPanel.add(nomeLabel);
        inputPanel.add(nomeField);
        inputPanel.add(valorLabel);
        inputPanel.add(valorField);
        inputPanel.add(quantidadeLabel);
        inputPanel.add(quantidadeField);

        JButton cadastrarButton = new JButton("Cadastrar Ingrediente");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = nomeField.getText();
                    double valor = Double.parseDouble(valorField.getText());
                    int quantidade = Integer.parseInt(quantidadeField.getText());

                    Ingrediente novoIngrediente = new Ingrediente(nome, valor, quantidade);
                    dataManager.getIngredientes().add(novoIngrediente);
                    dataManager.salvarDados();

                    atualizarListaIngredientes();

                    nomeField.setText("");
                    valorField.setText("");
                    quantidadeField.setText("");

                    JOptionPane.showMessageDialog(null, "Ingrediente cadastrado com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar ingrediente: " + ex.getMessage());
                }
            }
        });

        JButton visualizarButton = new JButton("Visualizar Ingredientes");
        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaIngredientes();
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(cadastrarButton, BorderLayout.SOUTH);
        panel.add(visualizarButton, BorderLayout.EAST);

        add(panel);
    }

    private void atualizarListaIngredientes() {
        StringBuilder sb = new StringBuilder();
        for (Ingrediente ingrediente : dataManager.getIngredientes()) {
            sb.append("ID: ").append(ingrediente.getId())
              .append(" - Nome: ").append(ingrediente.getNome())
              .append(" - Valor: ").append(ingrediente.getValor())
              .append(" - Quantidade: ").append(ingrediente.getQuantidade()).append("\n");
        }
        ingredientesTextArea.setText(sb.toString());
    }
}
