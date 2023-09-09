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
	
	

	

}
