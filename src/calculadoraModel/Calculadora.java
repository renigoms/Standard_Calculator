package calculadoraModel;

import java.util.ArrayList;

public class Calculadora implements OperacoesI{
	
	private boolean isLigada;
	
	public Calculadora() {
		isLigada = false;
	}

	@Override
	public double soma(ArrayList<Double> num) {
		if(num.size() == 1)
			return num.get(0);
		double soma = 0;
		for(Double nums:num)
			soma+=nums;
		return soma;
	}
	
	@Override
	public double multiplicacao(ArrayList<Double> num) {
		if(num.size() == 1)
			return num.get(0);
		double soma = 1;
		for(double nums:num)
			soma*=nums;
		return soma;
	}

	@Override
	public double divisao(ArrayList<Double> num) {
		if(num.size() == 1)
			return num.get(0);
		double soma = num.get(0);
		num.remove(0);
		for(double nums:num)
			soma/=nums;
		return soma;	
	}
	
	public boolean isLigada() {
		return isLigada;
	}

	public void setLigada(boolean isLigada) {
		this.isLigada = isLigada;
	}
	
	private ArrayList<Double> convArrayListString(ArrayList<String> stringInput) {
		ArrayList<Double> numOperacao = new ArrayList<>();
		for(String str:stringInput) {
			numOperacao.add(Double.parseDouble(str));
		}
		return numOperacao;
	}

	@Override
	public double executarOperacoes(ArrayList<Character> caracteres) {
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
							double result = multiplicacao(convArrayListString(temporario));
							temporario.clear();
							principal.add(String.valueOf(result));
							unirChars += String.valueOf(chars);
							continue;
						}

						if (temporario.get(1).equals("÷")) {
							temporario.remove(1);
							double result = divisao(convArrayListString(temporario));
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
							double result = multiplicacao(convArrayListString(temporario));
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
							double result = divisao(convArrayListString(temporario));
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
				double result = multiplicacao(convArrayListString(temporario));
				principal.add(String.valueOf(result));
			}
			if (temporario.get(1).equals("÷")) {
				temporario.remove(1);
				double result = divisao(convArrayListString(temporario));
				principal.add(String.valueOf(result));
			}
		}
		return soma(convArrayListString(principal));
	}
	
	
	
	
}
