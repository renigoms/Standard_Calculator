package configuration;

import Validacoes.Validar;
import calculadoraservice.CalculadoraService;
import calculadoraservice.point.PointManager;
import operationperformed.Sinais;

import javax.swing.*;
import java.util.ArrayList;

public class ApagarConfiguration {

    private boolean temSinalAqui(ArrayList<Character> chars, int i){
        return chars.get(i)== Sinais.ADICAO.getValue()||
                chars.get(i) ==Sinais.SUBTRACAO.getValue()||
                chars.get(i)==Sinais.MULTIPLICACAO.getValue()||
                chars.get(i)==Sinais.DIVISAO.getValue();
    }

    public void apagarActionConfig(JTextField areaDeTexto, PointManager pointManager, CalculadoraService calculadoraService){
        ArrayList<Character> chars, chars2 = new ArrayList<>();
        boolean isMenosSinal = false, sinalfinal, sinalInicial = false;

        if (!areaDeTexto.getText().isEmpty()) {
            chars = FormatterConfig.formartarArrayCharParaListCharacter(areaDeTexto.getText().toCharArray());
            if (chars.get(0) == Sinais.SUBTRACAO.getValue())
                isMenosSinal = true;

            else chars.add(0,Sinais.ADICAO.getValue());

            if(chars.get(chars.size() - 1).charValue() == Sinais.PONTO.getValue())
                pointManager.setPonto(true);

            if(Validar.isSinaisEspeciaisInEnd(areaDeTexto.getText())) {
                for(int i = chars.size() - 1;i>=0;i--) {
                    chars2.add(chars.get(i));
                    if(this.temSinalAqui(chars, i)) {
                        if(!sinalInicial) {
                            sinalInicial = true;
                            continue;
                        }
                    }

                    if(Validar.isSinaisEspeciaisInEnd(String.valueOf(chars.get(i))) && sinalInicial) {
                        sinalfinal = true;
                        if(chars2.contains(Sinais.PONTO.getValue())) {
                            pointManager.setPonto(false);
                            break;
                        }
                        if (sinalfinal) break;
                    }
                }
            }
            chars.remove(chars.size() - 1);
            if (!isMenosSinal)
                chars.remove(0);

            char[] newchar = new char[chars.size()];

            for (int i = 0; i < chars.size(); i++)
                newchar[i] = chars.get(i);

            areaDeTexto.setText(new String(newchar));
        }
    }
}
