package calculadoraControllerTeste;

import calculadoraController.CalculadoraController;
import calculadoraModel.Calculadora;
import calculadoraView.TelaPrincipal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ControllerTestes {
    private  CalculadoraController calculadoraController;
    private Calculadora calculadora;
    @BeforeEach
    public void inicio(){
         calculadoraController = new CalculadoraController(new TelaPrincipal());
         calculadora = new Calculadora();
    }
    @Test
    @DisplayName("Teste de Formatação")
    public void testeFormater(){
        Assertions.assertEquals("2.1538",
                calculadoraController.formatarResultado(calculadora.executarOperacoes(
                        new ArrayList<Character>(Arrays.asList('0','.','1','4','÷',
                                '6','5','0','0','0','0','0'))), "#.####"));
    }
}
