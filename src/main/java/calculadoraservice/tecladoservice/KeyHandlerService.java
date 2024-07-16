package calculadoraservice.tecladoservice;

import calculadoraView.PainelDeDigitos;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandlerService extends KeyAdapter {
    private final PainelDeDigitos digitos;

    public KeyHandlerService(PainelDeDigitos digitos) {
        this.digitos = digitos;
    }

    @Override
    public void keyPressed(KeyEvent e) {
//			NÚMEROS DE 0 A 9
        switch (e.getKeyChar()){
            case '0':
                digitos.getZero().doClick();
                break;
            case '1':
                digitos.getUm().doClick();
                break;
            case '2':
                digitos.getDOIS().doClick();
                break;
            case '3':
                digitos.getTRES().doClick();
                break;
            case '4':
                digitos.getQuatro().doClick();
                break;
            case '5':
                digitos.getCinco().doClick();
                break;
            case '6':
                digitos.getSeis().doClick();
                break;
            case '7':
                digitos.getSete().doClick();
                break;
            case '8':
                digitos.getOito().doClick();
                break;
            case '9':
                digitos.getNove().doClick();
                break;
            //			SINAIS DE ON ATE IGUAL
            case '+':
                digitos.getMais().doClick();
                break;
            case '-':
                digitos.getMenos().doClick();
                break;
            case '*':
                digitos.getVezes().doClick();
                break;
            case '/':
                digitos.getDividir().doClick();
                break;
            case KeyEvent.VK_ENTER:
                digitos.getIgual().doClick();
                break;
            case '.':
            case ',':
                digitos.getPonto().doClick();
                break;
            case KeyEvent.VK_BACK_SPACE:
                digitos.getApagar().doClick();
                break;
            case 'r':
            case 'R':
                digitos.getRaiz().doClick();
                break;
        }
    }
}
