package calculadoraView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame{

    private JTextField areaDeExibicao;
    private PainelDeDigitos painelDigitos;


    public TelaPrincipal() {
        super("Calculadora");
        setSize(280,275);
        setIconImage(new ImageIcon("img/icone_calculadora.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
//		VISOR DA CALCULADORA
        areaDeExibicao = new JTextField(20);
        areaDeExibicao.setHorizontalAlignment(JTextField.RIGHT);
        areaDeExibicao.setEditable(false);
        areaDeExibicao.setFont(new Font("Arial Black", Font.BOLD, 25));
        areaDeExibicao.setPreferredSize(new Dimension(80, 50));
        add(areaDeExibicao, BorderLayout.NORTH);

//		DIGITOS

        painelDigitos = new PainelDeDigitos();
        add(painelDigitos, BorderLayout.CENTER);


        setVisible(true);
    }


    public JTextField getAreaDeExibicao() {
        return areaDeExibicao;
    }


    public PainelDeDigitos getPainelDigitos() {
        return painelDigitos;
    }
}
