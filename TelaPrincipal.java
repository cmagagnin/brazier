import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {
    private DataManager dataManager;

    public TelaPrincipal(DataManager dataManager) {
        this.dataManager = dataManager;
        setTitle("Hamburgueria Brazier");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initMenu();
    }

    private void initMenu() {
        // Criando a barra de menu
        JMenuBar menuBar = new JMenuBar();

        // Menu Clientes
        JMenu menuClientes = new JMenu("Clientes");
        JMenuItem cadastrarCliente = new JMenuItem("Cadastrar Cliente");
        JMenuItem visualizarClientes = new JMenuItem("Visualizar Clientes");

        cadastrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaClientes telaClientes = new TelaClientes(dataManager);
                telaClientes.setVisible(true);
            }
        });

        visualizarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaClientes telaClientes = new TelaClientes(dataManager);
                telaClientes.setVisible(true);  // Pode ser refatorado para abrir em modo visualização
            }
        });

        menuClientes.add(cadastrarCliente);
        menuClientes.add(visualizarClientes);
        menuBar.add(menuClientes);

        // Menu Ingredientes
        JMenu menuIngredientes = new JMenu("Ingredientes");
        JMenuItem cadastrarIngrediente = new JMenuItem("Cadastrar Ingrediente");
        JMenuItem visualizarIngredientes = new JMenuItem("Visualizar Ingredientes");

        cadastrarIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaIngredientes telaIngredientes = new TelaIngredientes(dataManager);
                telaIngredientes.setVisible(true);
            }
        });

        visualizarIngredientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaIngredientes telaIngredientes = new TelaIngredientes(dataManager);
                telaIngredientes.setVisible(true);  // Pode ser refatorado para abrir em modo visualização
            }
        });

        menuIngredientes.add(cadastrarIngrediente);
        menuIngredientes.add(visualizarIngredientes);
        menuBar.add(menuIngredientes);

        // Menu Lanches
        JMenu menuLanches = new JMenu("Lanches");
        JMenuItem cadastrarLanche = new JMenuItem("Cadastrar Lanche");
        JMenuItem visualizarLanches = new JMenuItem("Visualizar Lanches");

        cadastrarLanche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaLanches telaLanches = new TelaLanches(dataManager);
                telaLanches.setVisible(true);
            }
        });

        visualizarLanches.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaLanches telaLanches = new TelaLanches(dataManager);
                telaLanches.setVisible(true);  // Pode ser refatorado para abrir em modo visualização
            }
        });

        menuLanches.add(cadastrarLanche);
        menuLanches.add(visualizarLanches);
        menuBar.add(menuLanches);

        // Menu Pedidos
        JMenu menuPedidos = new JMenu("Pedidos");
        JMenuItem criarPedido = new JMenuItem("Criar Pedido");
        JMenuItem visualizarPedidos = new JMenuItem("Visualizar Pedidos");

        criarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPedidos telaPedidos = new TelaPedidos(dataManager);
                telaPedidos.setVisible(true);
            }
        });

        visualizarPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPedidos telaPedidos = new TelaPedidos(dataManager);
                telaPedidos.setVisible(true);  // Pode ser refatorado para abrir em modo visualização
            }
        });

        menuPedidos.add(criarPedido);
        menuPedidos.add(visualizarPedidos);
        menuBar.add(menuPedidos);

        // Menu Caixa
        JMenu menuCaixa = new JMenu("Caixa");
        JMenuItem relatorioCaixa = new JMenuItem("Gerar Relatório");

        relatorioCaixa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCaixa telaCaixa = new TelaCaixa(dataManager);
                telaCaixa.setVisible(true);
            }
        });

        menuCaixa.add(relatorioCaixa);
        menuBar.add(menuCaixa);

        // Definindo o menu bar
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        TelaPrincipal telaPrincipal = new TelaPrincipal(dataManager);
        telaPrincipal.setVisible(true);
    }
}
