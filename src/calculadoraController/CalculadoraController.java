package calculadoraController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTextField;
import javax.swing.Spring;

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

	public CalculadoraController(TelaPrincipal telaPrincipal) {
		super();
		this.telaPrincipal = telaPrincipal;
		this.digitos = telaPrincipal.getPainelDigitos();
		this.areaDeTexto = telaPrincipal.getAreaDeExibicao();
		this.calculadora = new Calculadora();
		this.resultadosOperacoes = new ResultadosOperacoes();
		control();
		control2();
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
			
			if (listChars.get(listChars.size() - 1) == '÷' || listChars.get(listChars.size() - 1) == 'x'
					|| listChars.get(listChars.size() - 1) == '+' || listChars.get(listChars.size() - 1) == '-') {
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

//	ALGUNS CARACTERES ESPECIAIS

	private void control2() {
		digitos.getRaiz().addActionListener(this);
		digitos.getPonto().addActionListener(this);
		digitos.getApagar().addActionListener(this);
		digitos.getIgual().addActionListener(resultadosOperacoes);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == digitos.getRaiz()) {
//			LEMBRAR DE FAZER ISSO DEPOIS
			System.out.println("sou uma raiz");
		}

		if (e.getSource() == digitos.getPonto()) {
			if (calculadora.isLigada()) {
				if (!controlSegDigEmDiant("."))
					areaDeTexto.setText(".");
			}
		}

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

		public ResultadosOperacoes() {
			super();
			

		}
		
		private void listChars() {
			this.caracteres = new ArrayList<>();
			for (int i = 0; i < areaDeTexto.getText().length(); i++)
				caracteres.add(areaDeTexto.getText().charAt(i));
		}
		
		private boolean verificarSinal(char sinal) {
			switch (sinal) {
				case '+':
					if(!caracteres.contains('-')&&
							!caracteres.contains('÷')&&
							!caracteres.contains('x')) {
						return true;
					}
					break;
					
				case '-':
					if(!caracteres.contains('+')&&
							!caracteres.contains('÷')&&
							!caracteres.contains('x')) {
						return true;
					}
					break;
				case 'x':
					if(!caracteres.contains('-')&&
							!caracteres.contains('÷')&&
							!caracteres.contains('+')) {
						return true;
					}break;
				case '÷':
					if(!caracteres.contains('-')&&
							!caracteres.contains('x')&&
							!caracteres.contains('+')) {
						return true;
					}break;

				default:
					break;
			}
			return false;
		}
		
		private ArrayList<Double> OperacoesEmSequencia(char sinal) {
			ArrayList<Double> numOperacao = new ArrayList<>();
			String unir = "";
			boolean acessou = false;
			for(Character chars:caracteres) {
				if(String.valueOf(sinal).equals(String.valueOf(caracteres.get(0)))&&
						caracteres.indexOf(chars) == 0 && !acessou) {
					unir += String.valueOf(chars);
					acessou = true;
				}else if(chars == sinal) {
					numOperacao.add(Double.parseDouble(unir));
					unir = "";
				}else {
					unir += String.valueOf(chars);
				}
			}
			numOperacao.add(Double.parseDouble(unir));
			return numOperacao;
		}
		private ArrayList<Double> convArrayListString(ArrayList<String> stringInput) {
			ArrayList<Double> numOperacao = new ArrayList<>();
			for(String str:stringInput) {
				numOperacao.add(Double.parseDouble(str));
			}
			return numOperacao;
		}
		
		private void OperacSomaSubtr() {
			if(!caracteres.contains('x') && !caracteres.contains('÷')) {
//				ARRAYLIST DE OPERAÇÕES DE SOMAS
				ArrayList<String> operSoma = new ArrayList<>(); 
//				ARRAYLIST DE OPERAÇÕES DE SUBTRAÇÃO
				ArrayList<String> operSub = new ArrayList<>();
//				VARIÁVEIS BOLEANAS
				boolean isSoma = true, inicioNegativo = caracteres.get(0)=='-'?true:false;
//				STRING DE UNIÃO
				String unir = "";
				
//				FOR QUE PERCORRE OS CARACTERES DA ÁREA DE EXIBIÇÃO
		
				for(int i=0; i<=caracteres.size()-1;i++) {
//					GETCARACTER
					Character chars = caracteres.get(i);
					
//					SEPARANDO OS NÚMEROS ENTRE POSITIVO E NEGATIVO
					
					switch (caracteres.get(i)) {
					case '+':
						if(isSoma) {
							operSoma.add(unir);
							unir="";
						}else {
							operSub.add(unir);
							unir = "";
						}
						unir+=String.valueOf(chars);
						isSoma = true;
						break;
					case '-':
						if(inicioNegativo) {
							unir+=String.valueOf(chars);
							isSoma = false;
							inicioNegativo = false;
							break;
						}
						if(!inicioNegativo) {
							if(isSoma ) {
								operSoma.add(unir);
								unir="";
							}else {
								operSub.add(unir);
								unir = "";
							}
							unir+=String.valueOf(chars);
							isSoma = false;
						}
						break;
					default:
						unir += String.valueOf(chars);
						break;
					}
				}
				if(isSoma) {
					operSoma.add(unir);
				}else {
					operSub.add(unir);
				}
				
				double resultSoma = calculadora.soma(convArrayListString(operSoma)),
						resultSub = calculadora.subtracao(convArrayListString(operSub));
				double resultTotal = resultSoma + resultSub;

				areaDeTexto.setText(String.valueOf(resultTotal));
			}
		}
		
//		private void operacoesMultDiv() {
//			if(!caracteres.contains('+') && !caracteres.contains('-')) {
////				ARRAYLIST DE OPERAÇÕES DE MULTIPLICAÇÃO
//				ArrayList<String> operMult = new ArrayList<>(); 
////				ARRAYLIST DE OPERAÇÕES DE DIVISÃO
//				ArrayList<String> operDiv = new ArrayList<>();
////				VARIÁVEIS BOLEANAS
//				boolean isMult = true, inicioNegativo = caracteres.get(0)=='-'?true:false;
////				STRING DE UNIÃO
//				String unir = "";
//				
////				FOR QUE PERCORRE OS CARACTERES DA ÁREA DE EXIBIÇÃO
//		
//				for(int i=0; i<=caracteres.size()-1;i++) {
////					GETCARACTER
//					Character chars = caracteres.get(i);
//					
//					switch (chars) {
//					case 'x':
//						if(isMult) {
//							operMult.add(unir);
//							unir="";
//						}else {
//							operDiv.add(unir);
//							unir = "";
//						}
//						isMult = true;
//						break;
//					case '÷':
//						if(inicioNegativo) {
//							unir+=String.valueOf(chars);
//							isMult = false;
//							inicioNegativo = false;
//							break;
//						}
//						if(!inicioNegativo) {
//							if(isMult ) {
//								operMult.add(unir);
//								unir="";
//							}else {
//								operDiv.add(unir);
//								unir = "";
//							}
//							isMult = false;
//						}
//						break;
//					default:
//						unir += String.valueOf(chars);
//						break;
//					}
//				}
//				if(isMult) {
//					operMult.add(unir);
//				}else {
//					operDiv.add(unir);
//				}
//				
//				double resultMult = calculadora.multiplicacao(convArrayListString(operMult));
//				
//				ArrayList<Double> listResultMult = new ArrayList<>(Arrays.asList(resultMult));
//				ArrayList<Double>listNumsDiv = convArrayListString(operDiv);
//				listResultMult.addAll(listNumsDiv);
//				double resultTotal = calculadora.divisao(listResultMult);
//
//				areaDeTexto.setText(String.valueOf(resultTotal));
//				
//			}
//		}
		private void operacoesMultDiv(char foraExpre1, char foraExpre2,char operInicio, char operIncluida) {
			if(!caracteres.contains(foraExpre1) && !caracteres.contains(foraExpre2)) {
//				ARRAYLIST DE OPERAÇÕES DE MULTIPLICAÇÃO
				ArrayList<String> listOpenInicio = new ArrayList<>(); 
//				ARRAYLIST DE OPERAÇÕES DE DIVISÃO
				ArrayList<String> listOperIncluida = new ArrayList<>();
//				VARIÁVEIS BOLEANAS
				boolean isOpeInicio = true, inicioNegativo = caracteres.get(0)=='-'?true:false;
//				STRING DE UNIÃO
				String unir = "";
				
//				FOR QUE PERCORRE OS CARACTERES DA ÁREA DE EXIBIÇÃO
				
				for(int i=0; i<=caracteres.size()-1;i++) {
//					GETCARACTER
					Character chars = caracteres.get(i);
					
					if(inicioNegativo) {
						unir+=String.valueOf(chars);
						isOpeInicio = false;
						inicioNegativo = false;
						continue;
					}
					
					if(chars == operInicio) {
						if(isOpeInicio) {
							listOpenInicio.add(unir);
							unir="";
						}else {
							listOperIncluida.add(unir);
							unir = "";
						}
						isOpeInicio = true;
						continue;
					}else if (chars == operIncluida) {
						if(!inicioNegativo) {
							if(isOpeInicio ) {
								listOpenInicio.add(unir);
								unir="";
							}else {
								listOperIncluida.add(unir);
								unir = "";
							}
							isOpeInicio = false;
							continue;
						}
						
					}else unir += String.valueOf(chars);
				}
				if(isOpeInicio) {
					listOpenInicio.add(unir);
				}else {
					listOperIncluida.add(unir);
				}
				
				double resultInicio = calculadora.multiplicacao(convArrayListString(listOpenInicio));
				
				ArrayList<Double> listResultInicio = new ArrayList<>(Arrays.asList(resultInicio));
				ArrayList<Double>listResultIncluidos = convArrayListString(listOperIncluida);
				listResultInicio.addAll(listResultIncluidos);
				double resultTotal;
				
				switch (operIncluida) {
				case '+':
					resultTotal = calculadora.soma(listResultInicio);
					break;
					
				case '-':
					resultTotal = calculadora.subtracao(listResultInicio);
					break;

				default:
					resultTotal = calculadora.divisao(listResultInicio);
					break;
				}
				
				
				
				areaDeTexto.setText(String.valueOf(resultTotal));
				
			}
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
					
					
					
//					SOMATÓRIO
					
					if(verificarSinal('+')) {
						String resultado = String.valueOf(calculadora.
								soma(OperacoesEmSequencia('+')));
						areaDeTexto.setText(resultado);
						return;
					}
					
//					SUBTRAÇÃO
					
					if(verificarSinal('-')) {
						String resultado = String.valueOf(calculadora.
								subtracao(OperacoesEmSequencia('-')));
						areaDeTexto.setText(resultado);
						return;
					}
					
//					EXPRESÃO DE SOMA E SUBTRAÇÃO
					
					OperacSomaSubtr();
					
					
//					MULTIPLICAÇÃO
					
					if(verificarSinal('x')) {
						String resultado = String.valueOf(calculadora.
								multiplicacao(OperacoesEmSequencia('x')));
						areaDeTexto.setText(resultado);
						return;
					}
					
//					DIVISÃO
					
					if(verificarSinal('÷')) {
						String resultado = String.valueOf(calculadora.
								divisao(OperacoesEmSequencia('÷')));
						areaDeTexto.setText(resultado);
						return;
					}
					
//					EXPRESSÕES DE MULTIPLICAÇÃO E DIVISÃO
					operacoesMultDiv('+','-','x', '÷');
					
//					EXPRESSÕES DE MULTIPLICAÇÃO E ADIÇÃO E MULTIPLICAÇÃO E SUBTRAÇÃO
					operacoesMultDiv('÷', '-', 'x', '+');
					operacoesMultDiv('÷', '+', 'x', '-');
					
//					EXPRESSÕES DE MULTIPLICAÇÃO E ADIÇÃO E MULTIPLICAÇÃO E SUBTRAÇÃO
					operacoesMultDiv('x', '-', '÷', '+');
					operacoesMultDiv('x', '+', '÷', '-');
					
//					EXPLESSÕES DE COM TRÊS OU QUATRO OPERAÇÕES
					

					
					
				} catch (OperacaoInvalidaException e2) {
					// TODO: handle exception
				}
			}

		}

	}
}
