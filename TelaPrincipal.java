import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {
    private DataManager dataManager;

    public TelaPrincipal(DataManager dataManager) {
        this.dataManager = dataManager;
        setTitle("Hamburgueria Brazier");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JButton btnClientes = new JButton("Gerenciar Clientes");
        JButton btnIngredientes = new JButton("Gerenciar Ingredientes");
        JButton btnLanches = new JButton("Gerenciar Lanches");
        JButton btnPedidos = new JButton("Gerenciar Pedidos");
        JButton btnCaixa = new JButton("Caixa");

        btnClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaClientes telaClientes = new TelaClientes(dataManager);
                telaClientes.setVisible(true);
            }
        });

        // Adicionar listeners para os outros botões conforme necessário

        JPanel panel = new JPanel();
        panel.add(btnClientes);
        panel.add(btnIngredientes);
        panel.add(btnLanches);
        panel.add(btnPedidos);
        panel.add(btnCaixa);

        add(panel);
    }
}
