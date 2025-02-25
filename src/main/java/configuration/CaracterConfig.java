package configuration;

import java.util.ArrayList;

import javax.swing.JTextField;

import Validacoes.Validar;
import calculadoraModel.Calculadora;
import calculadoraservice.point.PointManager;
import operationperformed.Operation;

public class CaracterConfig {

    private JTextField areaDeTexto;

    private PointManager pointManager;

    private Calculadora calculadora;

    public CaracterConfig(JTextField areaDeTexto,  PointManager pointManager, Calculadora calculadora) {
        this.areaDeTexto = areaDeTexto;
        this.pointManager = pointManager;
        this.calculadora = calculadora;
    }

    /**
     * Realiza o controle da digitacão dos caracteres especiais
     * @return true or false
     */
    public boolean controlCharacEsp(String dig) {
        if(areaDeTexto.getText().length() >= 1) {
            ArrayList<Character> listChars = new ArrayList<>();

            for (char chars : areaDeTexto.getText().toCharArray())
                listChars.add(chars);

            if (Validar.isSinaisEspeciaisInEnd(areaDeTexto.getText()) ||
                    listChars.get(listChars.size() - 1) == '.') {

                if(listChars.get(listChars.size() - 2) == '0'&&
                        listChars.get(listChars.size()-1) == '.'&&
                        !dig.equals(".")){

                    /*Caso seja digitado um '.' a saída será '0.',
                     * mas quando houver a seguinte situação:
                     * A(+,-,* ou ÷)0.
                     * e algum dos quatro sinais forem digitados em seguida
                     * o 0. será substituido por esse sinal*/

                    for (int i=0;i<3;i++)
                        listChars.remove(listChars.size() - 1);

                    pointManager.setPonto(true);

                }else listChars.remove(listChars.size() - 1);

                char[] newchar = new char[listChars.size()];

                for (int i = 0; i < listChars.size(); i++)
                    newchar[i] = listChars.get(i);

                areaDeTexto.setText(new String(newchar)+dig);
                return true;
            }
            Operation.PERFORMED.setValue(false);
            areaDeTexto.setText(areaDeTexto.getText() + dig);
            return true;
        }
        return false;
    }

    //    CONTROLE DE CARACTERES NUMERICOS
    /**
     * <h3>Faz o gerenciamento do primeiro digito</h3>
     */
    public void controlFirstDig() {
        if (areaDeTexto.getText().isEmpty() || areaDeTexto.getText().equals("0"))
            areaDeTexto.setText("");
    }
    /**
     * Realiza o controle dos caracteres numericos
     */
    public boolean controlSegDigEmDiant(String dig) {
        if (Operation.PERFORMED.isValue()){
            pointManager.setPonto(true);
            Operation.PERFORMED.setValue(false);
            return false;
        }

        if(areaDeTexto.getText().length() >= 1) {
            areaDeTexto.setText(areaDeTexto.getText() + dig);
            return true;
        }
        return false;
    }
    //    CONTROLADOR DE CARACTERES NÃO ESPECIAIS
    public void controlCaracteresNomais(String caractere){
        if (calculadora.isLigada()) {
            controlFirstDig();
            if (!controlSegDigEmDiant(caractere))
                areaDeTexto.setText(caractere);
        }
    }
    //    CONTROLE DOS CARACTERES ESPECIAIS
    public void controlCaracteresEspeciais(String caractere){
        if (calculadora.isLigada()) {
            pointManager.setPonto(true);
            controlCharacEsp(caractere);
        }
    }

    public void controllerSinalNegativo(){
        if (calculadora.isLigada()) {
        pointManager.setPonto(true);
        if (!controlCharacEsp("-"))
            areaDeTexto.setText("-");
    }}
}
