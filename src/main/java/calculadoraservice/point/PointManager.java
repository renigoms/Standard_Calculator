package calculadoraservice.point;

import Validacoes.Validar;
import calculadoraservice.CalculadoraService;
import configuration.CaracterConfig;

import javax.swing.*;

public class PointManager {
    private boolean isPonto;

    public PointManager() {
        this.isPonto = true;
    }

    public void pointConfig(JTextField areaDeTexto, CalculadoraService calculadoraService, CaracterConfig caracterConfig){
        if (calculadoraService.isCalculateOn()) {
            if (this.isPonto) {
                if (areaDeTexto.getText().isEmpty()) {
                    areaDeTexto.setText("0.");
                    this.isPonto = false;
                    return;
                }
                if (Validar.isSinaisEspeciaisInEnd(areaDeTexto.getText())) {
                    areaDeTexto.setText(areaDeTexto.getText() + "0.");
                }
                if (!caracterConfig.controlCharacEsp("."))
                    areaDeTexto.setText("0.");
                this.isPonto = false;
            }
        }
    }

    public boolean isPonto() {
        return isPonto;
    }

    public void setPonto(boolean ponto) {
        isPonto = ponto;
    }
}
