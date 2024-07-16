package calculadoraservice.tecladoservice;

import Validacoes.Validar;
import calculadoraservice.CalculadoraService;
import calculadoraservice.point.PointManager;

import javax.swing.*;
import java.util.ArrayList;

public class ApagarService {

    public void apagarActionConfig(JTextField areaDeTexto, PointManager pointManager, CalculadoraService calculadoraService){
        ArrayList<Character> chars = new ArrayList<>(), chars2 = new ArrayList<>();
        boolean isMenosSinal = false, sinalfinal, sinalInicial = false;

        if (!areaDeTexto.getText().isEmpty()) {
            for (char itemChar:areaDeTexto.getText().toCharArray())
                chars.add(itemChar);
            if (chars.get(0) == '-') isMenosSinal = true;

            else chars.add(0,'+');

            if(chars.get(chars.size() - 1) == '.')
                pointManager.setPonto(true);

            if(Validar.isSinaisEspeciaisInEnd(areaDeTexto.getText())) {
                for(int i = chars.size() - 1;i>=0;i--) {
                    chars2.add(chars.get(i));
                    if(chars.get(i)=='+'|| chars.get(i)=='-'||
                            chars.get(i)=='x'|| chars.get(i)=='÷') {
                        if(!sinalInicial) {
                            sinalInicial = true;
                            continue;
                        }
                    }

                    if(Validar.isSinaisEspeciaisInEnd(String.valueOf(chars.get(i))) && sinalInicial) {
                        sinalfinal = true;
                        if(chars2.contains('.')) {
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
