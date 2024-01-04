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
        SETE = createButton("7",new Font("Arial", Font.BOLD, 14));
        add(SETE);

        OITO = createButton("8",new Font("Arial", Font.BOLD, 14));
        add(OITO);

        NOVE = createButton("9",new Font("Arial", Font.BOLD, 14));
        add(NOVE);

        LIGAR = createButton("ON",new Font("Arial", Font.BOLD, 14), color);
        add(LIGAR);

        DELIGAR = createButton("OFF",new Font("Arial Black", Font.BOLD, 11), color);
        add(DELIGAR);

//		SEGUNDA FILEIRA

        QUATRO = createButton("4",new Font("arial ", Font.BOLD, 14));
        add(QUATRO);

        CINCO = createButton("5",new Font("arial ", Font.BOLD, 14));
        add(CINCO);

        SEIS = createButton("6",new Font("arial ", Font.BOLD, 14));
        add(SEIS);

        VEZES = createButton("X",new Font("arial ", Font.BOLD, 14), color);
        add(VEZES);

        DIVIDIR = createButton("÷", new Font("arial ", Font.BOLD, 14), color);
        add(DIVIDIR);

//		TECEIRA FILEIRA

        UM = createButton("1",new Font("arial ", Font.BOLD, 14));
        add(UM);

        DOIS = createButton("2",new Font("arial ", Font.BOLD, 14));
        add(DOIS);

        TRES = createButton("3",new Font("arial ", Font.BOLD, 14));;
        add(TRES);

        MAIS = createButton("+", new Font("arial ", Font.BOLD, 14), color);
        add(MAIS);

        MENOS = createButton("-", new Font("arial ", Font.BOLD, 14), color);
        add(MENOS);

//		QUARTA FILEIRA

        ZERO = createButton("0", new Font("arial ", Font.BOLD, 14));
        add(ZERO);

        APAGAR = createButton(new ImageIcon("src/main/img/apagarCalculadora.png"),
                new Font("arial ", Font.BOLD, 14), color);
        add(APAGAR);

        PONTO = createButton(".", new Font("Arial", Font.BOLD, 16), color);
        add(PONTO);

        RAIZ_QUADRADA = createButton("√",new Font("arial ", Font.BOLD, 14),color);
        add(RAIZ_QUADRADA);

        IGUAL = createButton("=",new Font("arial ", Font.BOLD, 14),color);
        add(IGUAL);
    }

    /**
     * Configurações do button
     * @param text
     * @param fonte
     * @param corDeFundo
     * @return
     */
    private JButton createButton(String text, Font fonte, Color corDeFundo){
        JButton button = new JButton(text);
        button.setFont(fonte);
        button.setBackground(corDeFundo);
        return button;
    }

    /**
     * Configurações do button
     * @param imageIcon
     * @param fonte
     * @param corDeFundo
     * @return
     */
    private JButton createButton(ImageIcon imageIcon, Font fonte, Color corDeFundo){
        JButton button = new JButton(imageIcon);
        button.setFont(fonte);
        button.setBackground(corDeFundo);
        return button;
    }

    /**
     * Configurações do button
     * @param text
     * @param fonte
     * @return
     */
    private JButton createButton(String text, Font fonte){
        JButton button = new JButton(text);
        button.setFont(fonte);
        return button;
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
