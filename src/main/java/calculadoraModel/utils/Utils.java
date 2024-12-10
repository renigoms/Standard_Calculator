package calculadoraModel.utils;

import calculadoraModel.Calculadora;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static calculadoraModel.Calculadora.ONE;

public class Utils {

    /**
     * Adiciona Resultados na lista princiapal
     * @param result
     * @param chars
     */
    public static void addResultPrincipal(double result, Character chars, ArrayList<String> temporario,
                                    ArrayList<String> principal, StringBuilder unirChars){
        temporario.clear();
        principal.add(String.valueOf(result));
        unirChars.append(chars);
    }

    /**
     * Adicona resultados na lista temporaria
     * @param result
     */
    public static void addResultTemporario(double result, ArrayList<String> temporario){
        temporario.clear();
        temporario.add(String.valueOf(result));
    }

    /**
     * Adiciona chars à lista temporaria
     */
    public static void addCharsTemporario(ArrayList<String> temporario,
                                          Calculadora calculadora){
        temporario.add(calculadora.getUnirChars().toString());
        calculadora.setUnirChars(new StringBuilder());
    }

    /**
     * Método que realiza todas as multiplicações
     * prioritarias
     * @param temporario
     * @return
     */

    public static double multiplicacaoPrioritaria(ArrayList<String> temporario, Calculadora calculadora){
        temporario.remove(ONE);
        return calculadora.multiplicacao(
                temporario.stream()
                        .map(Double::parseDouble)
                        .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    /**
     * Método que realiza todas as divisões prioritarias
     * @param temporario
     * @return
     */
    public static double divisaoPrioritaria(ArrayList<String> temporario, Calculadora calculadora){
        temporario.remove(ONE);
        return calculadora.divisao(
                temporario
                        .stream()
                        .map(Double::parseDouble)
                        .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    public static void addListaPrincipal(ArrayList<String> principal, Calculadora calculadora, Character chars){
        principal.add(calculadora.getUnirChars().toString());
        calculadora.setUnirChars(new StringBuilder());
        calculadora.getUnirChars().append(chars);
    }
}
