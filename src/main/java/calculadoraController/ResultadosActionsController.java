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
    private ArrayList<Character> caracteres;

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

    //        DIVIDE A STRING ORIGINARIA DO VISOR EM UMA LISTA DE CHARS
    private void listChars() {
        this.caracteres = new ArrayList<>();
        for (char chars:areaDeTexto.getText().toCharArray())
            caracteres.add(chars);
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

//					OPERAÇÕES:
                listChars(); //lista de chars na area de texto
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
