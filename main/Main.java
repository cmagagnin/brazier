package main;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaPrincipal telaPrincipal = new TelaPrincipal(dataManager);
                telaPrincipal.setVisible(true);
            }
        });

        // Ao fechar o programa, salvar os dados
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            dataManager.salvarDados();
        }));
    }
}
