package calculadoraModel;

import javax.swing.JOptionPane;

public class OperacaoInvalidaException extends Exception{
    public OperacaoInvalidaException(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }


}
