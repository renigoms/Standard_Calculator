package calculadoraservice;

import calculadoraController.CalculadoraController;
import calculadoraController.ResultadosActionsController;
import calculadoraModel.Calculadora;
import calculadoraView.PainelDeDigitos;
import calculadoraView.TelaPrincipal;
import calculadoraservice.point.PointManager;
import configuration.ApagarConfiguration;
import configuration.RaizQuadradaConfiguration;
import configuration.CaracterConfig;
import configuration.FormatterConfig;
import operationperformed.Sinais;

import javax.swing.*;
import java.util.ArrayList;

public class CalculadoraService {

    private final JTextField areaDeTexto;

    private Calculadora calculadora;

    private TelaPrincipal telaPrincipal;


    private final PainelDeDigitos digitos;

    private PointManager pointManager;

    private CalculadoraController controller;

    private ResultadosActionsController resultadosOperacoes;

    private CaracterConfig caracterConfig;

    private RaizQuadradaConfiguration raizQuadradaService;

    private ApagarConfiguration apagarConfiguration;

    public CalculadoraService(TelaPrincipal telaPrincipal, PointManager pointManager,CalculadoraController controller) {
        this.telaPrincipal = telaPrincipal;
        areaDeTexto = telaPrincipal.getAreaDeExibicao();
        this.digitos = telaPrincipal.getPainelDigitos();
        this.pointManager = pointManager;
        this.calculadora = new Calculadora();
        this.controller = controller;
        this.raizQuadradaService = new RaizQuadradaConfiguration();
        this.caracterConfig = new CaracterConfig(areaDeTexto, pointManager, calculadora);
        this.apagarConfiguration = new ApagarConfiguration();
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


    private void numberConfig(){
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

    private void fuctionsConfig(){
        digitos.getLigar().addActionListener(e -> ligarDesligarCalculadora(true, "0"));

//		BOTÃO OFF
        digitos.getButtonOff().addActionListener(e -> ligarDesligarCalculadora(false, ""));

//		BOTÃO DE ADIÇÃO

        digitos.getMais().addActionListener(e -> caracterConfig.controlCaracteresEspeciais(Sinais.ADICAO.toString()));

//		BOTÃO SUBTRAÇÃO

        digitos.getMenos().addActionListener(e -> caracterConfig.controllerSinalNegativo());

//		BOTÃO MULTIPLICAÇÃO

        digitos.getVezes().addActionListener(e -> caracterConfig.controlCaracteresEspeciais(Sinais.MULTIPLICACAO.toString()));
//		BOTÃO DIVISÃO
        digitos.getDividir().addActionListener(e -> caracterConfig.controlCaracteresEspeciais(Sinais.DIVISAO.toString()));

//        Raiz Quadrada
        digitos.getRaiz().addActionListener(e -> raizQuadradaService.raizQuad(
                this, areaDeTexto, pointManager
        ));

//        Ponto
        digitos.getPonto().addActionListener(e -> pointManager.pointConfig(
                areaDeTexto, this,caracterConfig
        ));

//        Apagar caracter
        digitos.getApagar().addActionListener(e -> apagarConfiguration.apagarActionConfig(
                areaDeTexto, pointManager,this
        ));
    }

    public void control() {
//		FUNÇÕES
        fuctionsConfig();
//		NÚMEROS DE 0 AO 9
        numberConfig();
    }
}
