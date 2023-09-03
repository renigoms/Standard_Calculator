package calculadoraModel;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class OperacaoInvalidaException extends Exception{
	
	public OperacaoInvalidaException() {}
	public OperacaoInvalidaException(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	public OperacaoInvalidaException(String msg, String titulo, int tipo) {
		JOptionPane.showMessageDialog(null, msg, titulo, tipo);
	}

}
