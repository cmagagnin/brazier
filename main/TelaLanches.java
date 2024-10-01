package main;

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
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);  // Espaçamento entre componentes

        JLabel nomeLancheLabel = new JLabel("Nome do Lanche:");
        JTextField nomeLancheField = new JTextField(15);
        JLabel ingredientesLabel = new JLabel("ID Ingredientes (ex: 1:2, 3:1):");
        JTextField ingredientesField = new JTextField(20);

        // Posicionando os campos na grid
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nomeLancheLabel, gbc);

        gbc.gridx = 1;
        add(nomeLancheField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(ingredientesLabel, gbc);

        gbc.gridx = 1;
        add(ingredientesField, gbc);

        // Botão de cadastro
        JButton cadastrarButton = new JButton("Cadastrar Lanche");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(cadastrarButton, gbc);

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

                    nomeLancheField.setText("");
                    ingredientesField.setText("");

                    JOptionPane.showMessageDialog(null, "Lanche cadastrado com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar lanche: " + ex.getMessage());
                }
            }
        });

        // Campo para visualizar lanches
        lanchesTextArea = new JTextArea(10, 30);
        lanchesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(lanchesTextArea);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        // Botão de visualização
        JButton visualizarButton = new JButton("Visualizar Lanches");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(visualizarButton, gbc);

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaLanches();
            }
        });
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