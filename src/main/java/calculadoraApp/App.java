package calculadoraApp;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import calculadoraController.CalculadoraController;
import calculadoraView.TelaPrincipal;

public class App {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            CalculadoraController calculadoraController = new CalculadoraController(telaPrincipal);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                 | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
