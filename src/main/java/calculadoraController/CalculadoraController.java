package calculadoraController;

import calculadoraView.PainelDeDigitos;
import calculadoraView.TelaPrincipal;
import calculadoraservice.tecladoservice.ApagarService;
import calculadoraservice.CalculadoraService;
import calculadoraservice.tecladoservice.RaizQuadradaService;
import calculadoraservice.point.PointManager;
import calculadoraservice.tecladoservice.TecladoActions;
import configuration.CaracterConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraController implements ActionListener {

    private final TelaPrincipal telaPrincipal;
    private final PainelDeDigitos digitos;
    private final JTextField areaDeTexto;

    private PointManager pointManager;

    private CalculadoraService calculadoraService;



    private TecladoActions tecladoActions;

    public CalculadoraController(TelaPrincipal telaPrincipal) {
        super();
        this.telaPrincipal = telaPrincipal;
        this.digitos = telaPrincipal.getPainelDigitos();
        this.areaDeTexto = telaPrincipal.getAreaDeExibicao();
        this.pointManager = new PointManager();
        calculadoraService = new CalculadoraService(telaPrincipal, pointManager, this);


        this.tecladoActions = new TecladoActions(telaPrincipal, this, calculadoraService, pointManager);
        control();
        control2();
        digitos.getLigar().doClick();
    }

    public void control() {
//		FUNÇÕES
        calculadoraService.fuctionsConfig();
//		NÚMEROS DE 0 AO 9
        calculadoraService.numberConfig();
    }

    public void control2() {
        tecladoActions.teclasDeFuncionamento();
//		COMANDOS DO TECLADO
//		SINAIS ESPECIAIS
        tecladoActions.sinaisEspeciais();
//		NÚMEROS DE 0 A 9
        tecladoActions.digitosNumericos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
////		BOTÃO DE RAIZ QUADRADA
//        if (e.getSource() == digitos.getRaiz()) {
//            raizQuadradaService.raizQuadConfig(
//                    calculadoraService,
//                    areaDeTexto,
//                    pointManager
//            );
//        }
//
////		BOTÃO PONTO
//
//        if (e.getSource() == digitos.getPonto()) {
//            pointManager.pointConfig(areaDeTexto, calculadoraService, caracterConfig);
//        }
//
////		BOTÃO DE APAGAR
//
//        if (e.getSource() == digitos.getApagar()) {
//            apagarService.apagarActionConfig(
//                    areaDeTexto,
//                    pointManager,
//                    calculadoraService
//            );
//        }

    }
}
