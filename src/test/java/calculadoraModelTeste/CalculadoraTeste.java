package calculadoraModelTeste;

import java.util.ArrayList;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import calculadoraModel.Calculadora;

public class CalculadoraTeste {
    private final ArrayList<Character> LISTCHARS = new ArrayList<>(), LISTCHRAS2 = new ArrayList<>(),
    LISTCHAR3 = new ArrayList<>();

   private final Calculadora CALCULADORA = new Calculadora();

    @BeforeEach
    public void inicializar(){
        Locale.setDefault(Locale.US);
        String equacao = "1+2-3x4÷5+1x2-3÷4+5+8+7+6+5.5x4.4x3.3x2.2x1.1÷10.65÷7.9÷0.5";
        for (char chras:equacao.toCharArray())
            LISTCHARS.add(chras);
        String EQUACAO2 = "123456789x123456789";
        for(char chars:EQUACAO2.toCharArray())
            LISTCHRAS2.add(chars);

        String EQUACAO3 = "10000x100000";
        for(char chars:EQUACAO3.toCharArray())
            LISTCHAR3.add(chars);

    }

    @Test
    @DisplayName("Verificando se os calculos estão sendo feitos corretamente")
    public void executarOperacoes(){
        Assertions.assertEquals("32.4", "%.1f".formatted(CALCULADORA.executarOperacoes(LISTCHARS)));
    }

    @Test
    @DisplayName("Multiplicacao de números grandes")
    public void operMultNumBig(){
        Assertions.assertEquals(1.524157875019052E16, CALCULADORA.executarOperacoes(LISTCHRAS2));

    }
    @Test
    @DisplayName("Multiplicacao de números grandes")
    public void operMultNumBig2(){
        Assertions.assertEquals(1.0E9, CALCULADORA.executarOperacoes(LISTCHAR3));

    }




}