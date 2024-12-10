package configuration;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FormatterConfig {

    /**
     *  @return Retorna uma String formatada, conforme a máscara especificada,
     * do resultado passado.
     */

    private static boolean isBigNumber(double number){
        String strNumber = String.valueOf(number);
        for (char chars:strNumber.toCharArray())
            if (chars == 'E')
                return true;
        return false;
    }

    public static String formatarResultado(double resultado, String mask) {
        Locale.setDefault(Locale.US);// padroniza o ponto
        DecimalFormat df = new DecimalFormat(mask);
        if (isBigNumber(resultado)){
            return String.valueOf(resultado);
        }
        return df.format(resultado);
    }

    public static ArrayList<Character> formartarArrayCharParaListCharacter(char [] caracteres){
        //lista de chars na area de texto
        ArrayList<Character> newCaracteres = new ArrayList<>();
        for (char chars:caracteres)
            newCaracteres.add(chars);
        return newCaracteres;
    }
}
