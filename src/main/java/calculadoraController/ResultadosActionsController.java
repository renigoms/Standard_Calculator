package calculadoraController;

import Validacoes.Validar;
import calculadoraModel.OperacaoInvalidaException;
import calculadoraView.TelaPrincipal;
import calculadoraservice.CalculadoraService;
import calculadoraservice.point.PointManager;
import configuration.FormatterConfig;
import operationperformed.Operation;
import operationperformed.Sinais;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResultadosActionsController implements ActionListener {

    private JTextField areaDeTexto;

    private CalculadoraService calculadoraService;

    private TelaPrincipal telaPrincipal;

    private PointManager pointManager;

    public ResultadosActionsController(TelaPrincipal telaPrincipal, CalculadoraService calculadoraService, PointManager pointManager) {
        this.calculadoraService = calculadoraService;
        this.telaPrincipal = telaPrincipal;
        this.pointManager = pointManager;
        areaDeTexto = telaPrincipal.getAreaDeExibicao();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!areaDeTexto.getText().isEmpty() && calculadoraService.isCalculateOn()) {

            try {
                pointManager.setPonto(true);
                if (areaDeTexto.getText().length() == 1 || areaDeTexto.getText().isEmpty()) {
                    return;
                }

                if (Validar.isSinaisEspeciaisInEnd(areaDeTexto.getText()))
                    throw new OperacaoInvalidaException("Operação inválida");

//				OPERAÇÕES:
                char [] caracteres = areaDeTexto.getText().toCharArray();


                String resultadoString = FormatterConfig.formatarResultado(
                        calculadoraService.executarOperacoesService(caracteres), "#.####");
                for(char chars:resultadoString.toCharArray()) {
                    if (chars == Sinais.PONTO.toChar()) {
                        pointManager.setPonto(false);
                        break;
                    }
                }
                Operation.PERFORMED.setValue(true);
                areaDeTexto.setText(resultadoString);

            } catch (OperacaoInvalidaException e2) {
                e2.printStackTrace();

            }catch(Exception e3) {
                e3.printStackTrace();
            }
        }
    }
}
