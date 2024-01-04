package calculadoraView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TelaPrincipal extends JFrame{

    private  JTextField areaDeExibicao;
    private final PainelDeDigitos painelDigitos;


    public TelaPrincipal() {
//        TITULO
        super("Calculadora");
//        TAMANHO
        setSize(280,275);
//        ICONE DO PROGRAMA
        setIconImage(new ImageIcon("src/main/img/icone_calculadora.png").getImage());
//        ENCERRA O PROGRAMA AO FECHAR
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        INICIA O PROGRAMA NO CENTRO
        setLocationRelativeTo(null);
//        NEGA A ALTERAÇÃO DA RESOLUÇÃO DA TELA
        setResizable(false);
//		VISOR DA CALCULADORA

        configVisor();

        add(areaDeExibicao, BorderLayout.NORTH);

//		DIGITOS

        painelDigitos = new PainelDeDigitos();
        add(painelDigitos, BorderLayout.CENTER);

//         SHOW
        setVisible(true);
    }

    /**
     * Configuração do visor da calculadora
     */
    private void configVisor(){
        areaDeExibicao = new JTextField(20);
//        FAZ O CURSOR COMEÇAR DA DIREITA
        areaDeExibicao.setHorizontalAlignment(JTextField.RIGHT);
//        NEGA A EDIÇÃO
        areaDeExibicao.setEditable(false);
//        CONFIGURAÇÃO DE FONTE
        areaDeExibicao.setFont(new Font("Arial Black", Font.BOLD, 25));
//        DIMENÇÕES DO VISOR
        areaDeExibicao.setPreferredSize(new Dimension(80, 50));
    }


    public JTextField getAreaDeExibicao() {
        return areaDeExibicao;
    }


    public PainelDeDigitos getPainelDigitos() {
        return painelDigitos;
    }
}
