package calculadoraservice;

import Validacoes.Validar;
import calculadoraController.CalculadoraController;
import calculadoraController.ResultadosActionsController;
import calculadoraModel.Calculadora;
import calculadoraView.PainelDeDigitos;
import calculadoraView.TelaPrincipal;
import calculadoraservice.point.PointManager;
import calculadoraservice.tecladoservice.ApagarService;
import calculadoraservice.tecladoservice.RaizQuadradaService;
import configuration.CaracterConfig;
import configuration.FormatterConfig;
import operationperformed.Sinais;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CalculadoraService {

    private final JTextField areaDeTexto;

    private Calculadora calculadora;

    private TelaPrincipal telaPrincipal;


    private final PainelDeDigitos digitos;

    private PointManager pointManager;

    private CalculadoraController controller;

    private ResultadosActionsController resultadosOperacoes;

    private CaracterConfig caracterConfig;

    private RaizQuadradaService raizQuadradaService;

    private ApagarService apagarService;

    public CalculadoraService(TelaPrincipal telaPrincipal, PointManager pointManager,CalculadoraController controller) {
        this.telaPrincipal = telaPrincipal;
        areaDeTexto = telaPrincipal.getAreaDeExibicao();
        this.digitos = telaPrincipal.getPainelDigitos();
        this.pointManager = pointManager;
        this.calculadora = new Calculadora();
        this.controller = controller;
        this.raizQuadradaService = new RaizQuadradaService();
        this.caracterConfig = new CaracterConfig(areaDeTexto, pointManager, calculadora);
        this.apagarService = new ApagarService();
    }

    public double executarOperacoesService(char[] caractes){
        ArrayList<Character> listCharacteres = FormatterConfig.formartarArrayCharParaListCharacter(caractes);
        return calculadora.executarOperacoes(listCharacteres);
    }

    public double getRaizQuadrada(String numText){
        return calculadora.raizQuadrada(numText);
    }

    public boolean isCalculateOn(){
        return calculadora.isLigada();
    }

    /*
        REALIZA A FUNÇÃO DE LIGAR E DESLIGAR A CALCULADORA TENDO A
        OPÇÃO DE INICIAR COM UM DIGITO AO LIGAR
         */
    public void ligarDesligarCalculadora(boolean isLigado, String digitoInicial){
        calculadora.setLigada(isLigado);
        areaDeTexto.setText(digitoInicial);
        pointManager.setPonto(true);
    }


    public void numberConfig(){
        digitos.getUm().addActionListener(e -> caracterConfig.controlCaracteresNomais("1"));
        digitos.getDOIS().addActionListener(e -> caracterConfig.controlCaracteresNomais("2"));
        digitos.getTRES().addActionListener(e -> caracterConfig.controlCaracteresNomais("3"));
        digitos.getQuatro().addActionListener(e -> caracterConfig.controlCaracteresNomais("4"));
        digitos.getCinco().addActionListener(e -> caracterConfig.controlCaracteresNomais("5"));
        digitos.getSeis().addActionListener(e -> caracterConfig.controlCaracteresNomais("6"));
        digitos.getSete().addActionListener(e -> caracterConfig.controlCaracteresNomais("7"));
        digitos.getOito().addActionListener(e -> caracterConfig.controlCaracteresNomais("8"));
        digitos.getNove().addActionListener(e -> caracterConfig.controlCaracteresNomais("9"));
        digitos.getZero().addActionListener(e -> caracterConfig.controlCaracteresNomais("0"));
    }

    public void fuctionsConfig(){
        digitos.getLigar().addActionListener(e -> ligarDesligarCalculadora(true, "0"));

//		BOTÃO OFF
        digitos.getButtonOff().addActionListener(e -> ligarDesligarCalculadora(false, ""));

//		BOTÃO DE ADIÇÃO

        digitos.getMais().addActionListener(e -> caracterConfig.controlCaracteresEspeciais(Sinais.ADICAO.getValue()));

//		BOTÃO SUBTRAÇÃO

        digitos.getMenos().addActionListener(e -> caracterConfig.controllerSinalNegativo());

//		BOTÃO MULTIPLICAÇÃO

        digitos.getVezes().addActionListener(e -> caracterConfig.controlCaracteresEspeciais(Sinais.MULTIPLICACAO.getValue()));
//		BOTÃO DIVISÃO
        digitos.getDividir().addActionListener(e -> caracterConfig.controlCaracteresEspeciais(Sinais.DIVISAO.getValue()));

//        Raiz Quadrada
        digitos.getRaiz().addActionListener(e -> raizQuadradaService.raizQuadConfig(
                this, areaDeTexto, pointManager
        ));

//        Ponto
        digitos.getPonto().addActionListener(e -> pointManager.pointConfig(
                areaDeTexto, this,caracterConfig
        ));

//        Apagar caracter
        digitos.getApagar().addActionListener(e -> apagarService.apagarActionConfig(
                areaDeTexto, pointManager,this
        ));
    }
}
