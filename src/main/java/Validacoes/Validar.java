package Validacoes;

import operationperformed.Sinais;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validar {

    public static boolean isBigNumber(double number){
        String strNumber = String.valueOf(number);
        for (char chars:strNumber.toCharArray())
            if (chars == 'E')
                return true;
        return false;
    }

    //    verifica se há caracteres especiais no fim da String
    public static boolean isSinaisEspeciaisInEnd(String text){
        List<Character> sinaisEspeciaisArray = new ArrayList<>(Arrays.asList('÷', 'x', '-', '+'));
        return sinaisEspeciaisArray.contains(text.charAt(text.length() - 1));
    }

    public static boolean temSinalAqui(ArrayList<Character> chars, int i){
        return chars.get(i)== Sinais.ADICAO.getValue()||
                chars.get(i) ==Sinais.SUBTRACAO.getValue()||
                chars.get(i)==Sinais.MULTIPLICACAO.getValue()||
                chars.get(i)==Sinais.DIVISAO.getValue();
    }


}
