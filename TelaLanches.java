import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TelaLanches extends JFrame {
    private DataManager dataManager;
    private JTextArea lanchesTextArea;

    public TelaLanches(DataManager dataManager) {
        this.dataManager = dataManager;
        setTitle("Gerenciar Lanches");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        lanchesTextArea = new JTextArea();
        lanchesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(lanchesTextArea);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        JLabel nomeLancheLabel = new JLabel("Nome do Lanche:");
        JTextField nomeLancheField = new JTextField();
        JLabel ingredientesLabel = new JLabel("ID Ingredientes (ex: 1:2, 3:1):");
        JTextField ingredientesField = new JTextField();

        inputPanel.add(nomeLancheLabel);
        inputPanel.add(nomeLancheField);
        inputPanel.add(ingredientesLabel);
        inputPanel.add(ingredientesField);

        JButton cadastrarButton = new JButton("Cadastrar Lanche");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nomeLanche = nomeLancheField.getText();
                    String ingredientesStr = ingredientesField.getText();
                    Lanche novoLanche = new Lanche(nomeLanche);
                    Map<Ingrediente, Integer> ingredientesMap = parseIngredientes(ingredientesStr);

                    for (Map.Entry<Ingrediente, Integer> entry : ingredientesMap.entrySet()) {
                        novoLanche.adicionarIngrediente(entry.getKey(), entry.getValue());
                    }

                    novoLanche.calcularValorDeCusto();
                    novoLanche.calcularValorSugerido();

                    dataManager.getLanches().add(novoLanche);
                    dataManager.salvarDados();

                    atualizarListaLanches();

                    nomeLancheField.setText("");
                    ingredientesField.setText("");

                    JOptionPane.showMessageDialog(null, "Lanche cadastrado com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar lanche: " + ex.getMessage());
                }
            }
        });

        JButton visualizarButton = new JButton("Visualizar Lanches");
        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaLanches();
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(cadastrarButton, BorderLayout.SOUTH);
        panel.add(visualizarButton, BorderLayout.EAST);

        add(panel);
    }

    private void atualizarListaLanches() {
        StringBuilder sb = new StringBuilder();
        for (Lanche lanche : dataManager.getLanches()) {
            sb.append("ID: ").append(lanche.getId())
              .append(" - Nome: ").append(lanche.getNomeLanche())
              .append(" - Valor Final: ").append(lanche.getValorFinal()).append("\n");
        }
        lanchesTextArea.setText(sb.toString());
    }

    private Map<Ingrediente, Integer> parseIngredientes(String ingredientesStr) throws Exception {
        Map<Ingrediente, Integer> ingredientesMap = new HashMap<>();
        String[] ingredientesArray = ingredientesStr.split(",");
        for (String ingredienteEntry : ingredientesArray) {
            String[] parts = ingredienteEntry.split(":");
            int ingredienteId = Integer.parseInt(parts[0]);
            int quantidade = Integer.parseInt(parts[1]);

            Ingrediente ingrediente = dataManager.getIngredienteById(ingredienteId);
            if (ingrediente == null) {
                throw new Exception("Ingrediente com ID " + ingredienteId + " não encontrado.");
            }
            ingredientesMap.put(ingrediente, quantidade);
        }
        return ingredientesMap;
    }
}
