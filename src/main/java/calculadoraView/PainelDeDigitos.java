package calculadoraView;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PainelDeDigitos extends JPanel{
    private final JButton UM, DOIS, TRES, QUATRO, CINCO, SEIS, SETE, OITO, NOVE, ZERO;
    private final JButton MAIS, MENOS, VEZES, DIVIDIR, APAGAR, LIGAR,
            DELIGAR, RAIZ_QUADRADA, PONTO, IGUAL;

    public PainelDeDigitos() {
        Color color = new Color(51, 104, 255);
        setLayout(new GridLayout(4,6));

//		PRIMEIRA FILEIRA
        SETE = new JButton("7");
        add(SETE);

        OITO = new JButton("8");
        add(OITO);

        NOVE = new JButton("9");
        add(NOVE);

        LIGAR = new JButton("ON");
        LIGAR.setBackground(color);
        add(LIGAR);

        DELIGAR = new JButton("OFF");
        DELIGAR.setBackground(color);
        add(DELIGAR);

//		SEGUNDA FILEIRA

        QUATRO = new JButton("4");
        add(QUATRO);

        CINCO = new JButton("5");
        add(CINCO);

        SEIS = new JButton("6");
        add(SEIS);

        VEZES = new JButton("X");
        VEZES.setBackground(color);
        add(VEZES);

        DIVIDIR = new JButton("÷");
        DIVIDIR.setBackground(color);
        add(DIVIDIR);

//		TECEIRA FILEIRA

        UM = new JButton("1");
        add(UM);

        DOIS = new JButton("2");
        add(DOIS);

        TRES = new JButton("3");
        add(TRES);

        MAIS = new JButton("+");
        MAIS.setBackground(color);
        add(MAIS);

        MENOS = new JButton("-");
        MENOS.setBackground(color);
        add(MENOS);

//		QUARTA FILEIRA

        ZERO = new JButton("0");
        add(ZERO);

        APAGAR = new JButton(new ImageIcon("src/main/img/apagarCalculadora.png"));
        APAGAR.setBackground(color);
        add(APAGAR);

        PONTO = new JButton(".");
        PONTO.setFont(new Font("Arial", Font.BOLD, 16));
        PONTO.setBackground(color);
        add(PONTO);

        RAIZ_QUADRADA = new JButton("√");
        RAIZ_QUADRADA.setBackground(color);
        add(RAIZ_QUADRADA);


        IGUAL = new JButton("=");
        IGUAL.setBackground(color);
        add(IGUAL);
    }

    public JButton getUm() {
        return UM;
    }

    public JButton getDOIS() {
        return DOIS;
    }

    public JButton getTRES() {
        return TRES;
    }

    public JButton getQuatro() {
        return QUATRO;
    }

    public JButton getCinco() {
        return CINCO;
    }

    public JButton getSeis() {
        return SEIS;
    }

    public JButton getSete() {
        return SETE;
    }

    public JButton getOito() {
        return OITO;
    }

    public JButton getNove() {
        return NOVE;
    }

    public JButton getZero() {
        return ZERO;
    }

    public JButton getMais() {
        return MAIS;
    }

    public JButton getMenos() {
        return MENOS;
    }

    public JButton getVezes() {
        return VEZES;
    }

    public JButton getDividir() {
        return DIVIDIR;
    }

    public JButton getApagar() {
        return APAGAR;
    }

    public JButton getLigar() {
        return LIGAR;
    }

    public JButton getButtonOff() {
        return DELIGAR;
    }

    public JButton getRaiz() {
        return RAIZ_QUADRADA;
    }


    public JButton getPonto() {
        return PONTO;
    }

    public JButton getIgual() {
        return IGUAL;
    }
}
