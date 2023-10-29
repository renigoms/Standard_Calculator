package calculadoraView;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PainelDeDigitos extends JPanel{
    private JButton um,dois,tres, quatro, cinco, seis, sete, oito, nove, zero;
    private JButton mais, menos, vezes, dividir, apagar, ligar,
            apagarTudo, raiz, ponto,igual;
    private Color color;

    public PainelDeDigitos() {
        color = new Color(51,104,255);
        setLayout(new GridLayout(4,6));

//		PRIMEIRA FILEIRA
        sete = new JButton("7");
        add(sete);

        oito = new JButton("8");
        add(oito);

        nove = new JButton("9");
        add(nove);

        ligar = new JButton("ON");
        ligar.setBackground(color);
        add(ligar);

        apagarTudo = new JButton("OFF");
        apagarTudo.setBackground(color);
        add(apagarTudo);

//		SEGUNDA FILEIRA

        quatro = new JButton("4");
        add(quatro);

        cinco = new JButton("5");
        add(cinco);

        seis = new JButton("6");
        add(seis);

        vezes = new JButton("X");
        vezes.setBackground(color);
        add(vezes);

        dividir = new JButton("÷");
        dividir.setBackground(color);
        add(dividir);

//		TECEIRA FILEIRA

        um = new JButton("1");
        add(um);

        dois = new JButton("2");
        add(dois);

        tres = new JButton("3");
        add(tres);

        mais = new JButton("+");
        mais.setBackground(color);
        add(mais);

        menos = new JButton("-");
        menos.setBackground(color);
        add(menos);

//		QUARTA FILEIRA

        zero = new JButton("0");
        add(zero);

        apagar = new JButton(new ImageIcon("src/main/img/apagarCalculadora.png"));
        apagar.setBackground(color);
        add(apagar);

        ponto = new JButton(".");
        ponto.setFont(new Font("Arial", Font.BOLD, 16));
        ponto.setBackground(color);
        add(ponto);

        raiz = new JButton("√");
        raiz.setBackground(color);
        add(raiz);


        igual = new JButton("=");
        igual.setBackground(color);
        add(igual);
    }

    public JButton getUm() {
        return um;
    }

    public JButton getDois() {
        return dois;
    }

    public JButton getTres() {
        return tres;
    }

    public JButton getQuatro() {
        return quatro;
    }

    public JButton getCinco() {
        return cinco;
    }

    public JButton getSeis() {
        return seis;
    }

    public JButton getSete() {
        return sete;
    }

    public JButton getOito() {
        return oito;
    }

    public JButton getNove() {
        return nove;
    }

    public JButton getZero() {
        return zero;
    }

    public JButton getMais() {
        return mais;
    }

    public JButton getMenos() {
        return menos;
    }

    public JButton getVezes() {
        return vezes;
    }

    public JButton getDividir() {
        return dividir;
    }

    public JButton getApagar() {
        return apagar;
    }

    public JButton getLigar() {
        return ligar;
    }

    public JButton getButtonOff() {
        return apagarTudo;
    }

    public JButton getRaiz() {
        return raiz;
    }


    public JButton getPonto() {
        return ponto;
    }

    public JButton getIgual() {
        return igual;
    }

    public Color getColor() {
        return color;
    }
}
