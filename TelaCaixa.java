
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCaixa extends JFrame {
    private DataManager dataManager;
    private JTextArea caixaTextArea;

    public TelaCaixa(DataManager dataManager) {
        this.dataManager = dataManager;
        setTitle("Caixa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);  // Espaçamento entre componentes

        // Botão de relatório
        JButton gerarRelatorioButton = new JButton("Gerar Relatório");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(gerarRelatorioButton, gbc);

        gerarRelatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarRelatorioCaixa();
            }
        });

        // Campo para exibir o relatório de caixa
        caixaTextArea = new JTextArea(15, 30);
        caixaTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(caixaTextArea);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);
    }

    private void atualizarRelatorioCaixa() {
        Caixa caixa = dataManager.getCaixa();
        StringBuilder sb = new StringBuilder();

        sb.append("Saldo Atual: R$ ").append(caixa.getSaldo()).append("\n");

        sb.append("Entradas:\n");
        for (Caixa.Transacao entrada : caixa.getEntradas()) {
            sb.append("Valor: R$ ").append(entrada.getValor())
              .append(" - Descrição: ").append(entrada.getDescricao())
              .append(" - Data: ").append(entrada.getDataHora()).append("\n");
        }

        sb.append("\nSaídas:\n");
        for (Caixa.Transacao saida : caixa.getSaidas()) {
            sb.append("Valor: R$ ").append(saida.getValor())
              .append(" - Descrição: ").append(saida.getDescricao())
              .append(" - Data: ").append(saida.getDataHora()).append("\n");
        }

        caixaTextArea.setText(sb.toString());
    }
}
