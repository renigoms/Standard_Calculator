package configuration;

import javax.swing.JTextField;

import Validacoes.Validar;
import calculadoraModel.OperacaoInvalidaException;
import calculadoraservice.CalculadoraService;
import calculadoraservice.point.PointManager;
import operationperformed.Operation;
import operationperformed.Sinais;

public class RaizQuadradaConfiguration {

    private static final String MASK = "#.####";

    public void raizQuad(CalculadoraService calculadoraService, JTextField areaDeTexto, PointManager pointManager){
        try {
            if(!areaDeTexto.getText().isEmpty() && calculadoraService.isCalculateOn()) {
                if (Validar.isSinaisEspeciaisInEnd(areaDeTexto.getText()) ||
                        areaDeTexto.getText().charAt(0)== Sinais.SUBTRACAO.getValue())
                    throw new OperacaoInvalidaException("Operação inválida");
                String resultadoString = FormatterConfig.formatarResultado(calculadoraService.getRaizQuadrada(areaDeTexto.getText()),
                        MASK);
                for(char chars:resultadoString.toCharArray()) {
                    if (chars == Sinais.PONTO.getValue()){
                        pointManager.setPonto(false);
                        break;
                    }else pointManager.setPonto(true);
                }
                Operation.PERFORMED.setValue(true);
                areaDeTexto.setText(resultadoString);
            }
        } catch (OperacaoInvalidaException ignored) {}
    }

}
