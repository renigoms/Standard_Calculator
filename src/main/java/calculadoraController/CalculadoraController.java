package calculadoraController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JTextField;

import calculadoraModel.Calculadora;
import calculadoraModel.OperacaoInvalidaException;
import calculadoraView.PainelDeDigitos;
import calculadoraView.TelaPrincipal;

public class CalculadoraController implements ActionListener {
    @SuppressWarnings("unused")
    private TelaPrincipal telaPrincipal;
    private PainelDeDigitos digitos;
    private JTextField areaDeTexto;
    private Calculadora calculadora;
    private ResultadosOperacoes resultadosOperacoes;
    private KeyHandler keyHandler;
    private boolean isPonto;
    private String text;
    private String text1;

    public CalculadoraController(TelaPrincipal telaPrincipal) {
        super();
        this.telaPrincipal = telaPrincipal;
        this.digitos = telaPrincipal.getPainelDigitos();
        this.areaDeTexto = telaPrincipal.getAreaDeExibicao();
        this.calculadora = new Calculadora();
        this.resultadosOperacoes = new ResultadosOperacoes();
        this.keyHandler = new KeyHandler();
        this.isPonto = true;
        control();
        control2();
        digitos.getLigar().doClick();
    }


    private void ligarDesligarCalculadora(boolean isLigado, String digitoInicial){
        calculadora.setLigada(isLigado);
        areaDeTexto.setText(digitoInicial);
        isPonto = true;
    }
//    CONTROLE DE CARACTERES NUMERICOS
    private void controlCaracteresNomais(String caractere){
        if (calculadora.isLigada()) {
            controlFirstDig();
            if (!controlSegDigEmDiant(caractere))
                areaDeTexto.setText(caractere);
        }
    }
    //    CONTROLE DOS CARACTERES ESPECIAIS
    private void controlCaracteresEspeciais(String caractere){
        if (calculadora.isLigada()) {
            isPonto = true;
            controlCharacEsp(caractere);
        }
    }

    private void control() {
//		BOTÃO ON
        digitos.getLigar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ligarDesligarCalculadora(true, "0");
            }
        });

//		BOTÃO OFF
        digitos.getButtonOff().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ligarDesligarCalculadora(false, "");
            }
        });

//		BOTÃO DE ADIÇÃO

        digitos.getMais().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlCaracteresEspeciais("+");
            }
        });

//		BOTÃO SUBTRAÇÃO

        digitos.getMenos().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (calculadora.isLigada()) {
                    isPonto = true;
                    if (!controlCharacEsp("-"))
                        areaDeTexto.setText("-");
                }

            }
        });

//		BOTÃO MULTIPLICAÇÃO

        digitos.getVezes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlCaracteresEspeciais("x");
            }
        });
//		BOTÃO DIVISÃO
        digitos.getDividir().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               controlCaracteresEspeciais("÷");
            }
        });

//		NÚMEROS DE 0 AO 9

        digitos.getUm().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlCaracteresNomais("1");
            }
        });
        digitos.getDois().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlCaracteresNomais("2");
            }
        });
        digitos.getTres().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlCaracteresNomais("3");

            }
        });
        digitos.getQuatro().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               controlCaracteresNomais("4");
            }
        });
        digitos.getCinco().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               controlCaracteresNomais("5");
            }
        });
        digitos.getSeis().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               controlCaracteresNomais("6");
            }
        });
        digitos.getSete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               controlCaracteresNomais("7");
            }
        });
        digitos.getOito().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               controlCaracteresNomais("8");
            }
        });
        digitos.getNove().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlCaracteresNomais("9");
            }
        });
        digitos.getZero().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlCaracteresNomais("0");
            }
        });
    }

//	ALGUNS CARACTERES ESPECIAIS

    private void control2() {
        digitos.getRaiz().addActionListener(this);
        digitos.getPonto().addActionListener(this);
        digitos.getApagar().addActionListener(this);
        digitos.getIgual().addActionListener(resultadosOperacoes);
        areaDeTexto.addKeyListener(keyHandler);
//		COMANDOS DO TECLADO
//		SINAIS ESPECIAIS
        digitos.getMais().addKeyListener(keyHandler);
        digitos.getMenos().addKeyListener(keyHandler);
        digitos.getVezes().addKeyListener(keyHandler);
        digitos.getDividir().addKeyListener(keyHandler);
        digitos.getIgual().addKeyListener(keyHandler);
        digitos.getApagar().addKeyListener(keyHandler);
        digitos.getPonto().addKeyListener(keyHandler);
        digitos.getLigar().addKeyListener(keyHandler);
        digitos.getButtonOff().addKeyListener(keyHandler);
        digitos.getRaiz().addKeyListener(keyHandler);
//		NÚMEROS DE 0 A 9
        digitos.getZero().addKeyListener(keyHandler);
        digitos.getUm().addKeyListener(keyHandler);
        digitos.getDois().addKeyListener(keyHandler);
        digitos.getTres().addKeyListener(keyHandler);
        digitos.getQuatro().addKeyListener(keyHandler);
        digitos.getCinco().addKeyListener(keyHandler);
        digitos.getSeis().addKeyListener(keyHandler);
        digitos.getSete().addKeyListener(keyHandler);
        digitos.getOito().addKeyListener(keyHandler);
        digitos.getNove().addKeyListener(keyHandler);

    }

    /**
     * <p>Faz o gerenciamento do primeiro digito</p>
     */
    private void controlFirstDig() {
        if (areaDeTexto.getText().isEmpty() || areaDeTexto.getText().equals("0"))
            areaDeTexto.setText("");
    }
    /**
     * Realiza o controle dos caracteres numericos
     * @param dig
     * @return
     */
    private boolean controlSegDigEmDiant(String dig) {
        if(areaDeTexto.getText().length() >= 1) {
            areaDeTexto.setText(areaDeTexto.getText() + dig);
            return true;
        }
        return false;
    }
    private boolean isSinaisEspeciaisInEnd(String text){
        if(text.charAt(text.length()-1)=='÷'|| text.charAt(text.length()-1)=='+'||
                text.charAt(text.length()-1)=='-'|| text.charAt(text.length()-1)=='x')
            return true;
        return false;
    }
    /**
     * Realiza o controle da digitacão dos caracteres especiais
     * @param dig
     * @return
     */
    private boolean controlCharacEsp(String dig) {
        if(areaDeTexto.getText().length() >= 1) {

            ArrayList<Character> listChars = new ArrayList<>();


            for (char chars : areaDeTexto.getText().toCharArray())
                listChars.add(chars);

            if (isSinaisEspeciaisInEnd(areaDeTexto.getText()) ||
                    listChars.get(listChars.size() - 1) == '.') {

                listChars.remove(listChars.size() - 1);

                char[] newchar = new char[listChars.size()];

                for (int i = 0; i < listChars.size(); i++)
                    newchar[i] = listChars.get(i);

                areaDeTexto.setText(new String(newchar)+dig);
                return true;

            }
            areaDeTexto.setText(areaDeTexto.getText() + dig);
            return true;
        }
        return false;
    }

    private boolean isBigNumber(double number){
        String strNumber = String.valueOf(number);
        for (char chars:strNumber.toCharArray())
            if (chars == 'E')
                return true;
        return false;
    }

    /**
     *
     * @param resultado
     * @param mask
     * @return Retorna uma String formatada, de acordo com a máscara especificada,
     * do resultado passado.
     */

    public String formatarResultado(double resultado, String mask) {
        Locale.setDefault(Locale.US);// padroniza o ponto
        DecimalFormat df = new DecimalFormat(mask);
        if (isBigNumber(resultado)){
            StringBuilder unionCharResult = new StringBuilder();

            String strResult = String.valueOf(resultado);

            for (char chars:strResult.toCharArray()) {
                if (chars == '.') break;
                unionCharResult.append(chars);
            }

            StringBuilder charResultPosPonto = new StringBuilder(unionCharResult+".");
            int cont = charResultPosPonto.length();
            for (int i = 0; i<4;i++) {
                charResultPosPonto.append(strResult.charAt(cont));
                cont++;
            }
            return String.valueOf(charResultPosPonto);
        }
       return df.format(resultado);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//		BOTÃO DE RAIZ QUADRADA
        if (e.getSource() == digitos.getRaiz()) {
            try {
                if(!areaDeTexto.getText().isEmpty() && calculadora.isLigada()) {
                    if (isSinaisEspeciaisInEnd(areaDeTexto.getText()) || areaDeTexto.getText().charAt(0)=='-')
                        throw new OperacaoInvalidaException("Operação inválida");
//					CALCULO DE RAIZ QUADRADA
                    double resultado = Math.sqrt(Double.parseDouble(areaDeTexto.getText()));
                    String resultadoString = formatarResultado(resultado, "#.####");
                    for(int i = 0; i<resultadoString.length();i++) {
                        this.isPonto = resultadoString.charAt(i)=='.' ? false:true;
                    }
                    areaDeTexto.setText(resultadoString);
                }
            } catch (OperacaoInvalidaException ignored) {}
        }

//		BOTÃO PONTO

        if (e.getSource() == digitos.getPonto()) {
            if (calculadora.isLigada()) {
                if(this.isPonto) {
                    if(areaDeTexto.getText().isEmpty()) {
                        areaDeTexto.setText("0.");
                        this.isPonto = false;
                        return;
                    }
                    if(isSinaisEspeciaisInEnd(areaDeTexto.getText())) {
                        areaDeTexto.setText(areaDeTexto.getText()+"0.");
                    }
                    if (!controlCharacEsp("."))
                        areaDeTexto.setText("0.");
                    this.isPonto = false;
                }
            }
        }

//		BOTÃO DE APAGAR

        if (e.getSource() == digitos.getApagar()) {
            ArrayList<Character> chars = new ArrayList<>(), chars2 = new ArrayList<>();
            boolean isMenosSinal = false, sinalfinal = false, sinalInicial = false;

            if (!areaDeTexto.getText().isEmpty()) {
                for (char itemChar:areaDeTexto.getText().toCharArray())
                    chars.add(itemChar);

                if (chars.get(0) == '-') isMenosSinal = true;

                else chars.add(0,'+');

                if(chars.get(chars.size() - 1) == '.')
                    this.isPonto = true;

                if(isSinaisEspeciaisInEnd(areaDeTexto.getText())) {
                    for(int i = chars.size() - 1;i>=0;i--) {
                        chars2.add(chars.get(i));
                        if(chars.get(i)=='+'|| chars.get(i)=='-'||
                                chars.get(i)=='x'|| chars.get(i)=='÷') {
                            if(!sinalInicial) {
                                sinalInicial = true;
                                continue;
                            }
                        }

                        if(isSinaisEspeciaisInEnd(String.valueOf(chars.get(i))) && sinalInicial) {
                            sinalfinal = true;
                            if(chars2.contains('.')) {
                                this.isPonto = false;
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

    private class ResultadosOperacoes implements ActionListener {

        private ArrayList<Character> caracteres;

        public ResultadosOperacoes() {}

        private void listChars() {
            this.caracteres = new ArrayList<>();
            for (char chars:areaDeTexto.getText().toCharArray())
                caracteres.add(chars);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!areaDeTexto.getText().isEmpty() && calculadora.isLigada()) {

                try {
                    isPonto = true;
                    if (areaDeTexto.getText().length() == 1 || areaDeTexto.getText().isEmpty()) {
                        return;
                    }

                    if (isSinaisEspeciaisInEnd(areaDeTexto.getText()))
                        throw new OperacaoInvalidaException("Operação inválida");

//					OPERAÇÕES:
                    listChars(); //lista de chars na area de texto
                    String resultadoString = formatarResultado(calculadora.executarOperacoes(caracteres), "#.####");
                    for(char chars:resultadoString.toCharArray()) {
                        if (chars == '.') {
                            isPonto = false;
                            break;
                        }
                    }
                    areaDeTexto.setText(resultadoString);

                } catch (OperacaoInvalidaException e2) {
                    e2.printStackTrace();
                }catch(Exception e3) {
                    e3.printStackTrace();
                }
            }

        }
    }
    private class KeyHandler extends KeyAdapter{
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
                    digitos.getDois().doClick();
                    break;
                case '3':
                    digitos.getTres().doClick();
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
}
