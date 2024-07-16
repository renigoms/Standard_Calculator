package calculadoraservice.tecladoservice;

import Validacoes.Validar;
import calculadoraModel.OperacaoInvalidaException;
import calculadoraservice.CalculadoraService;
import calculadoraservice.point.PointManager;
import configuration.FormatterConfig;
import operationperformed.Operation;

import javax.swing.*;

public class RaizQuadradaService {

    public static final String MASK = "#.####";

    public void raizQuadConfig(CalculadoraService calculadoraService, JTextField areaDeTexto, PointManager pointManager){
        try {
            if(!areaDeTexto.getText().isEmpty() && calculadoraService.isCalculateOn()) {
                if (Validar.isSinaisEspeciaisInEnd(areaDeTexto.getText()) || areaDeTexto.getText().charAt(0)=='-')
                    throw new OperacaoInvalidaException("Operação inválida");
                String resultadoString = FormatterConfig.formatarResultado(calculadoraService.getRaizQuadrada(areaDeTexto.getText()),
                        MASK);
                for(char chars:resultadoString.toCharArray()) {
                    if (chars == '.'){
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