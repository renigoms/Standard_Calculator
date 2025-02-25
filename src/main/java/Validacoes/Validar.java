package Validacoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validar {

    //    verifica se há caracteres especiais no fim da String
    public static boolean isSinaisEspeciaisInEnd(String text){
        List<Character> sinaisEspeciaisArray = new ArrayList<>(Arrays.asList('÷', 'x', '-', '+'));
        return sinaisEspeciaisArray.contains(text.charAt(text.length() - 1));
    }




}
