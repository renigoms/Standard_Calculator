package calculadoraservice.tecladoservice;

import calculadoraController.CalculadoraController;
import calculadoraView.PainelDeDigitos;
import calculadoraView.TelaPrincipal;
import calculadoraservice.CalculadoraService;
import calculadoraController.ResultadosActionsController;
import calculadoraservice.point.PointManager;

import javax.swing.*;

public class TecladoActions {

    private PainelDeDigitos digitos;

    private KeyHandlerService keyHandler;

    private CalculadoraController controller;

    private JTextField areaDeTexto;

    private ResultadosActionsController resultadosOperacoes;

    private PointManager pointManager;

    public TecladoActions(TelaPrincipal telaPrincipal, CalculadoraController controller, CalculadoraService calculadoraService, PointManager pointManager){
        this.digitos = telaPrincipal.getPainelDigitos();
        this.controller = controller;
        this.areaDeTexto = telaPrincipal.getAreaDeExibicao();
        this.keyHandler = new KeyHandlerService(this.digitos);
        this.pointManager = pointManager;
        this.resultadosOperacoes = new ResultadosActionsController(telaPrincipal,
                calculadoraService, pointManager);
    }

    //	ALGUNS CARACTERES ESPECIAIS

    private void sinaisEspeciais(){
        digitos.getMais().addKeyListener(keyHandler);
        digitos.getMenos().addKeyListener(keyHandler);
        digitos.getVezes().addKeyListener(keyHandler);
        digitos.getDividir().addKeyListener(keyHandler);
        digitos.getIgual().addKeyListener(keyHandler);
        digitos.getApagar().addKeyListener(keyHandler);
        digitos.getPonto().addKeyListener(keyHandler);
        digitos.getLigar().addKeyListener(keyHandler);
        digitos.getButtonOff().addKeyListener(keyHandler);
        digitos.getRaiz().addKeyListener(keyHandler);
    }
    private void teclasDeFuncionamento(){
        digitos.getIgual().addActionListener(resultadosOperacoes);
        areaDeTexto.addKeyListener(keyHandler);
    }
    private void digitosNumericos(){
        digitos.getZero().addKeyListener(keyHandler);
        digitos.getUm().addKeyListener(keyHandler);
        digitos.getDOIS().addKeyListener(keyHandler);
        digitos.getTRES().addKeyListener(keyHandler);
        digitos.getQuatro().addKeyListener(keyHandler);
        digitos.getCinco().addKeyListener(keyHandler);
        digitos.getSeis().addKeyListener(keyHandler);
        digitos.getSete().addKeyListener(keyHandler);
        digitos.getOito().addKeyListener(keyHandler);
        digitos.getNove().addKeyListener(keyHandler);
    }

    public void control() {
        this.teclasDeFuncionamento();
//		COMANDOS DO TECLADO
//		SINAIS ESPECIAIS
        this.sinaisEspeciais();
//		NÚMEROS DE 0 A 9
        this.digitosNumericos();
    }
}

