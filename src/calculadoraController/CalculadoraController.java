package calculadoraController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.lang.model.element.NestingKind;
import javax.swing.JTextField;

import calculadoraModel.Calculadora;
import calculadoraModel.OperacaoInvalidaException;
import calculadoraView.PainelDeDigitos;
import calculadoraView.TelaPrincipal;

public class CalculadoraController implements ActionListener {
	@SuppressWarnings("unused")
	private TelaPrincipal telaPrincipal;
	private PainelDeDigitos digitos;
	private JTextField areaDeTexto;
	private Calculadora calculadora;
	private ResultadosOperacoes resultadosOperacoes;
	private KeyHandler keyHandler;

	public CalculadoraController(TelaPrincipal telaPrincipal) {
		super();
		this.telaPrincipal = telaPrincipal;
		this.digitos = telaPrincipal.getPainelDigitos();
		this.areaDeTexto = telaPrincipal.getAreaDeExibicao();
		this.calculadora = new Calculadora();
		this.resultadosOperacoes = new ResultadosOperacoes();
		this.keyHandler = new KeyHandler();
		control();
		control2();
		digitos.getLigar().doClick();
	}

	private void control() {
//		BOTÃO ON
		digitos.getLigar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				calculadora.setLigada(true);
				areaDeTexto.setText("0");
			}

		});

//		BOTÃO OFF
		digitos.getApagarTudo().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				areaDeTexto.setText("");
				calculadora.setLigada(false);

			}
		});

//		BOTÃO DE ADIÇÃO

		digitos.getMais().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					if (!controlCharacEsp("+"))
						areaDeTexto.setText("+");
				}

			}
		});

//		BOTÃO SUBTRAÇÃO

		digitos.getMenos().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					if (!controlCharacEsp("-"))
						areaDeTexto.setText("-");
				}

			}
		});

//		BOTÃO MULTIPLICAÇÃO

		digitos.getVezes().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					if (!controlCharacEsp("x"))
						areaDeTexto.setText("x");

				}

			}
		});

//		BOTÃO DIVISÃO

		digitos.getDividir().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					if (!controlCharacEsp("÷"))
						areaDeTexto.setText("÷");
				}

			}
		});

//		NÚMEROS DE 0 AO 9

		digitos.getUm().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					controlFirstDig();
					if (!controlSegDigEmDiant("1"))
						areaDeTexto.setText("1");
				}

			}
		});
		digitos.getDois().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					controlFirstDig();
					if (!controlSegDigEmDiant("2"))
						areaDeTexto.setText("2");
				}

			}
		});
		digitos.getTres().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					controlFirstDig();
					if (!controlSegDigEmDiant("3"))
						areaDeTexto.setText("3");
				}

			}
		});
		digitos.getQuatro().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					controlFirstDig();
					if (!controlSegDigEmDiant("4"))
						areaDeTexto.setText("4");
				}

			}
		});
		digitos.getCinco().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					controlFirstDig();
					if (!controlSegDigEmDiant("5"))
						areaDeTexto.setText("5");
				}

			}
		});
		digitos.getSeis().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					controlFirstDig();
					if (!controlSegDigEmDiant("6"))
						areaDeTexto.setText("6");
				}

			}
		});
		digitos.getSete().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					controlFirstDig();
					if (!controlSegDigEmDiant("7"))
						areaDeTexto.setText("7");
				}

			}
		});
		digitos.getOito().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					controlFirstDig();
					if (!controlSegDigEmDiant("8"))
						areaDeTexto.setText("8");
				}

			}
		});
		digitos.getNove().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					controlFirstDig();
					if (!controlSegDigEmDiant("9"))
						areaDeTexto.setText("9");
				}

			}
		});
		digitos.getZero().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (calculadora.isLigada()) {
					controlFirstDig();
					if (!controlSegDigEmDiant("0"))
						areaDeTexto.setText("0");
				}

			}
		});
	}
	
//	ALGUNS CARACTERES ESPECIAIS
	
	private void control2() {
		digitos.getRaiz().addActionListener(this);
		digitos.getPonto().addActionListener(this);
		digitos.getApagar().addActionListener(this);
		digitos.getIgual().addActionListener(resultadosOperacoes);
		areaDeTexto.addKeyListener(keyHandler);
//		COMANDOS DO TECLADO
//		SINAIS ESPECIAIS
		digitos.getMais().addKeyListener(keyHandler);
		digitos.getMenos().addKeyListener(keyHandler);
		digitos.getVezes().addKeyListener(keyHandler);
		digitos.getDividir().addKeyListener(keyHandler);
		digitos.getIgual().addKeyListener(keyHandler);
		digitos.getApagar().addKeyListener(keyHandler);
		digitos.getPonto().addKeyListener(keyHandler);
		digitos.getLigar().addKeyListener(keyHandler);
		digitos.getApagarTudo().addKeyListener(keyHandler);
//		NÚMEROS DE 0 A 9
		digitos.getZero().addKeyListener(keyHandler);
		digitos.getUm().addKeyListener(keyHandler);
		digitos.getDois().addKeyListener(keyHandler);
		digitos.getTres().addKeyListener(keyHandler);
		digitos.getQuatro().addKeyListener(keyHandler);
		digitos.getCinco().addKeyListener(keyHandler);
		digitos.getSeis().addKeyListener(keyHandler);
		digitos.getSete().addKeyListener(keyHandler);
		digitos.getOito().addKeyListener(keyHandler);
		digitos.getNove().addKeyListener(keyHandler);
		
	}
	
	/**
	 * <h1>Faz o gerenciamento do primeiro digito</h1>
	 */
	private void controlFirstDig() {
		if (areaDeTexto.getText().isEmpty() || areaDeTexto.getText().equals("0"))
			areaDeTexto.setText("");
	}
	/**
	 * Realiza o controle dos caracteres numericos
	 * @param dig
	 * @return
	 */
	private boolean controlSegDigEmDiant(String dig) {
		if(areaDeTexto.getText().length() >= 1) {
			areaDeTexto.setText(areaDeTexto.getText() + dig);
			return true;
		}
		return false;
	}
	/**
	 * Realiza o controle da digitacão dos caracteres especiais
	 * @param dig
	 * @return
	 */
	private boolean controlCharacEsp(String dig) {
		if(areaDeTexto.getText().length() >= 1) {
			
			ArrayList<Character> listChars = new ArrayList<>();
			
			for (int i = 0; i < areaDeTexto.getText().length(); i++)
				listChars.add(areaDeTexto.getText().charAt(i));
			
			if (listChars.get(listChars.size() - 1) == dig.charAt(0)) {
				listChars.remove(listChars.size() - 1);

				char[] newchar = new char[listChars.size()];

				for (int i = 0; i < listChars.size(); i++)
					newchar[i] = listChars.get(i);

				areaDeTexto.setText(new String(newchar)+dig);
				return true;

			}
			areaDeTexto.setText(areaDeTexto.getText() + dig);
			return true;
		}
		return false;
	}
	
	
	private boolean pontoUnico() {
		for (int i = 0; i<areaDeTexto.getText().length();i++)
			if(areaDeTexto.getText().charAt(i) == '.')
				return true;
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		BOTÃO DE RAIZ QUADRADA
		if (e.getSource() == digitos.getRaiz()) {
			try {
				if(!areaDeTexto.getText().isEmpty() && calculadora.isLigada()) {
					if (areaDeTexto.getText().charAt(areaDeTexto.getText().length()-1)=='÷'||
							areaDeTexto.getText().charAt(areaDeTexto.getText().length()-1)=='+'||
							areaDeTexto.getText().charAt(areaDeTexto.getText().length()-1)=='-'||
							areaDeTexto.getText().charAt(areaDeTexto.getText().length()-1)=='x')
						throw new OperacaoInvalidaException("Operação inválida");
//					CALCULO DE RAIZ QUADRADA
					double resultado = Math.sqrt(Double.parseDouble(areaDeTexto.getText()));
					areaDeTexto.setText(String.valueOf(resultado));
				}
			} catch (OperacaoInvalidaException e2) {
				// TODO: handle exception
			}
		}
		
//		BOTÃO PONTO

		if (e.getSource() == digitos.getPonto()) {
			if (calculadora.isLigada()) {
				if(!pontoUnico())
					if (!controlCharacEsp("."))
						areaDeTexto.setText("0.");
			}
		}
		
//		BOTÃO DE APAGAR

		if (e.getSource() == digitos.getApagar()) {
			ArrayList<Character> chars = new ArrayList<>();

			if (!areaDeTexto.getText().isEmpty()) {
				for (int i = 0; i < areaDeTexto.getText().length(); i++)
					chars.add(areaDeTexto.getText().charAt(i));

				chars.remove(chars.size() - 1);

				char[] newchar = new char[chars.size()];

				for (int i = 0; i < chars.size(); i++)
					newchar[i] = chars.get(i);

				areaDeTexto.setText(new String(newchar));
			}
		}

	}

	private class ResultadosOperacoes implements ActionListener {

		private ArrayList<Character> caracteres;

		public ResultadosOperacoes() {}
		
		private void listChars() {
			this.caracteres = new ArrayList<>();
			for (int i = 0; i < areaDeTexto.getText().length(); i++)
				caracteres.add(areaDeTexto.getText().charAt(i));
		}
		
		
		private ArrayList<Double> convArrayListString(ArrayList<String> stringInput) {
			ArrayList<Double> numOperacao = new ArrayList<>();
			for(String str:stringInput) {
				numOperacao.add(Double.parseDouble(str));
			}
			return numOperacao;
		}
		
		private double operacoes() {

			String unirChars = "+";

			boolean inicioNegativo = caracteres.get(0) == '-';

			ArrayList<String> principal = new ArrayList<>();
			ArrayList<String> temporario = new ArrayList<>();

			boolean isSomSub = true, isMultDiv = false;
			boolean isSomSubAnterior = false, isMultDivAnterior = false;


			for (Character chars : caracteres) {
				if (inicioNegativo) {
					unirChars = "";
					unirChars += String.valueOf(chars);
					isSomSub = true;
					inicioNegativo = false;
					continue;
				}

				if (chars == '+' || chars == '-') {
					isMultDivAnterior = isMultDiv;
					isMultDiv = false;
					isSomSubAnterior = isSomSub;
					isSomSub = true;

					if (isSomSubAnterior && isSomSub) {
						principal.add(unirChars);
						unirChars = "";
						unirChars += String.valueOf(chars);
						continue;
					}
					if (isMultDivAnterior && isSomSub) {
						temporario.add(unirChars);
						unirChars = "";
						if (temporario.size() == 3) {
							if (temporario.get(1).equals("x")) {
								temporario.remove(1);
								double result = calculadora.multiplicacao(convArrayListString(temporario));
								temporario.clear();
								principal.add(String.valueOf(result));
								unirChars += String.valueOf(chars);
								continue;
							}

							if (temporario.get(1).equals("÷")) {
								temporario.remove(1);
								double result = calculadora.divisao(convArrayListString(temporario));
								temporario.clear();
								principal.add(String.valueOf(result));
								unirChars += String.valueOf(chars);
								continue;
							}

						}
					}

				}

				if(chars == 'x' || chars == '÷'){
					isMultDivAnterior = isMultDiv;
					isMultDiv = true;
					isSomSubAnterior = isSomSub;
					isSomSub = false;

					if(isSomSubAnterior && isMultDiv){
						temporario.add(unirChars);
						unirChars="";
						unirChars += String.valueOf(chars);
						temporario.add(unirChars);
						unirChars="";
						continue;

					}

					if(isMultDivAnterior && isMultDiv){
						temporario.add(unirChars);
						unirChars="";
						if(temporario.size() == 3){
							if(temporario.get(1).equals("x")){
								temporario.remove(1);
								double result = calculadora.multiplicacao(convArrayListString(temporario));
								temporario.clear();
								temporario.add(String.valueOf(result));
								if(chars == 'x' || chars == '÷'){
									unirChars += String.valueOf(chars);
									temporario.add(unirChars);
									unirChars="";
								}
								continue;
							}if(temporario.get(1).equals("÷")){
								temporario.remove(1);
								double result = calculadora.divisao(convArrayListString(temporario));
								temporario.clear();
								temporario.add(String.valueOf(result));
								if(chars == 'x' || chars == '÷'){
									unirChars += String.valueOf(chars);
									temporario.add(unirChars);
									unirChars="";
								}
								continue;
							}
						}
					}
				}
				unirChars += String.valueOf(chars);
			}

			if(isSomSub)
				principal.add(unirChars);
			if(isMultDiv)
				temporario.add(unirChars);

			if(temporario.size() == 3){
				if (temporario.get(1).equals("x")) {
					temporario.remove(1);
					double result = calculadora.multiplicacao(convArrayListString(temporario));
					principal.add(String.valueOf(result));
				}
				if (temporario.get(1).equals("÷")) {
					temporario.remove(1);
					double result = calculadora.divisao(convArrayListString(temporario));
					principal.add(String.valueOf(result));
				}
			}
			return calculadora.soma(convArrayListString(principal));
		}
		
		private String formatarResultado(double resultado, String mask) {
			Locale.setDefault(Locale.US);// padroniza o ponto
			DecimalFormat df = new DecimalFormat(mask);
			String resultadoFormatado = df.format(resultado);
			return resultadoFormatado;
		} 


		@Override
		public void actionPerformed(ActionEvent e) {
			if (!areaDeTexto.getText().isEmpty() && calculadora.isLigada()) {

				try {
					if (areaDeTexto.getText().length() == 1 || areaDeTexto.getText().isEmpty()) {
						return;
					}

					if (areaDeTexto.getText().charAt(areaDeTexto.getText().length()-1)=='÷'||
							areaDeTexto.getText().charAt(areaDeTexto.getText().length()-1)=='+'||
							areaDeTexto.getText().charAt(areaDeTexto.getText().length()-1)=='-'||
							areaDeTexto.getText().charAt(areaDeTexto.getText().length()-1)=='x')
						throw new OperacaoInvalidaException("Operação inválida");
			
//					OPERAÇÕES:
					listChars(); //lista de chars na area de texto
					areaDeTexto.setText(formatarResultado(operacoes(), "#.####"));
					
				} catch (OperacaoInvalidaException e2) {
					e2.printStackTrace();
				}catch(Exception e3) {
					e3.printStackTrace();
				}
			}

		}

	}
	
	private class KeyHandler extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
//			NÚMEROS DE 0 A 9
			if(e.getKeyChar() == '0')
				digitos.getZero().doClick();
			if(e.getKeyChar() == '1')
				digitos.getUm().doClick();
			if(e.getKeyChar() == '2')
				digitos.getDois().doClick();
			if(e.getKeyChar() == '3')
				digitos.getTres().doClick();
			if(e.getKeyChar() == '4')
				digitos.getQuatro().doClick();
			if(e.getKeyChar() == '5')
				digitos.getCinco().doClick();
			if(e.getKeyChar() == '6')
				digitos.getSeis().doClick();
			if(e.getKeyChar() == '7')
				digitos.getSete().doClick();
			if(e.getKeyChar() == '8')
				digitos.getOito().doClick();
			if(e.getKeyChar() == '9')
				digitos.getNove().doClick();
			
//			SINAIS DE ON ATE IGUAL
			if(e.getKeyChar() == '+')
				digitos.getMais().doClick();
			if(e.getKeyChar() == '-')
				digitos.getMenos().doClick();
			if(e.getKeyChar() == '*')
				digitos.getVezes().doClick();
			if(e.getKeyChar() == '/')
				digitos.getDividir().doClick();
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
				digitos.getIgual().doClick();
			if(e.getKeyChar() == '.')
				digitos.getPonto().doClick();
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				digitos.getApagar().doClick();
		}
		
	}
}
