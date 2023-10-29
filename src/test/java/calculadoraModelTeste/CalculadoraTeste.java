package calculadoraModelTeste;

import calculadoraModel.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Locale;

public class CalculadoraTeste {
    private String equacao = "1+2-3x4÷5+1x2-3÷4+5+8+7+6+5.5x4.4x3.3x2.2x1.1÷10.65÷7.9÷0.5";
    private ArrayList<Character> listChars = new ArrayList<>();

   private Calculadora calculadora = new Calculadora();

    @BeforeEach
    public void inicializar(){
        Locale.setDefault(Locale.US);
        for (int i = 0; i < equacao.length(); i++)
            listChars.add(equacao.charAt(i));
    }

    @Test
    @DisplayName("Verificando se os calculos estão sendo feitos corretamente")
    public void executarOperacoes(){
        Assertions.assertEquals("32.4", "%.1f".formatted(calculadora.executarOperacoes(listChars)));
    }
}