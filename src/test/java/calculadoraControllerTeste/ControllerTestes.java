package calculadoraControllerTeste;

import calculadoraController.CalculadoraController;
import calculadoraModel.Calculadora;
import calculadoraView.TelaPrincipal;
import configuration.FormatterConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ControllerTestes {
    private Calculadora calculadora;

    private final ArrayList<Character> LISTCHRAS2 = new ArrayList<>();
    private final ArrayList<Character> LISTCHRAS3 = new ArrayList<>();
    @BeforeEach
    public void inicio(){
         new CalculadoraController(new TelaPrincipal());
         calculadora = new Calculadora();
        String EQUACAO = "0.14÷6500000";
        for(char chars:EQUACAO.toCharArray())
            LISTCHRAS2.add(chars);

        String EQUACAO2 = "123456789x123456789";
        for(char chars2:EQUACAO2.toCharArray())
            LISTCHRAS3.add(chars2);
    }


    @Test
    @DisplayName("Teste de Formatação")
    public void testeFormater(){
        Assertions.assertEquals("2.153846153846154E-8",
                FormatterConfig.formatarResultado(calculadora.executarOperacoes(
                       LISTCHRAS2), "#.####"));

    }
    @Test
    @DisplayName("Teste de Formatação 2")
    public void testeFormater2(){
        Assertions.assertEquals("1.524157875019052E16",
                FormatterConfig.formatarResultado(calculadora.executarOperacoes(
                        LISTCHRAS3), "#.####"));

    }
}
