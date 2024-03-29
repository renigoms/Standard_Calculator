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
    private final TelaPrincipal telaPrincipal;
    private final PainelDeDigitos digitos;
    private final JTextField areaDeTexto;
    private final Calculadora calculadora;
    private final ResultadosOperacoes resultadosOperacoes;
    private final KeyHandler keyHandler;
    private boolean isPonto;
    private boolean isOperationPerformed;

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

    /*
    REALIZA A FUNÇÃO DE LIGAR E DESLIGAR A CALCULADORA TENDO A
    OPÇÃO DE INICIAR COM UM DIGITO AO LIGAR
     */
    private void ligarDesligarCalculadora(boolean isLigado, String digitoInicial){
        calculadora.setLigada(isLigado);
        areaDeTexto.setText(digitoInicial);
        isPonto = true;
    }
//    CONTROLE DE CARACTERES NUMERICOS
    /**
     * <h3>Faz o gerenciamento do primeiro digito</h3>
     */
    private void controlFirstDig() {
        if (areaDeTexto.getText().isEmpty() || areaDeTexto.getText().equals("0"))
            areaDeTexto.setText("");
    }
    /**
     * Realiza o controle dos caracteres numericos
     */
    private boolean controlSegDigEmDiant(String dig) {
        if (isOperationPerformed){
            isPonto = true;
            isOperationPerformed = false;
            return false;
        }

        if(areaDeTexto.getText().length() >= 1) {
            areaDeTexto.setText(areaDeTexto.getText() + dig);
            return true;
        }
        return false;
    }
//    CONTROLADOR DE CARACTERES NÃO ESPECIAIS
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
        digitos.getLigar().addActionListener(e -> ligarDesligarCalculadora(true, "0"));

//		BOTÃO OFF
        digitos.getButtonOff().addActionListener(e -> ligarDesligarCalculadora(false, ""));

//		BOTÃO DE ADIÇÃO

        digitos.getMais().addActionListener(e -> controlCaracteresEspeciais("+"));

//		BOTÃO SUBTRAÇÃO

        digitos.getMenos().addActionListener(e -> {
            if (calculadora.isLigada()) {
                isPonto = true;
                if (!controlCharacEsp("-"))
                    areaDeTexto.setText("-");
            }

        });

//		BOTÃO MULTIPLICAÇÃO

        digitos.getVezes().addActionListener(e -> controlCaracteresEspeciais("x"));
//		BOTÃO DIVISÃO
        digitos.getDividir().addActionListener(e -> controlCaracteresEspeciais("÷"));

//		NÚMEROS DE 0 AO 9

        digitos.getUm().addActionListener(e -> controlCaracteresNomais("1"));
        digitos.getDOIS().addActionListener(e -> controlCaracteresNomais("2"));
        digitos.getTRES().addActionListener(e -> controlCaracteresNomais("3"));
        digitos.getQuatro().addActionListener(e -> controlCaracteresNomais("4"));
        digitos.getCinco().addActionListener(e -> controlCaracteresNomais("5"));
        digitos.getSeis().addActionListener(e -> controlCaracteresNomais("6"));
        digitos.getSete().addActionListener(e -> controlCaracteresNomais("7"));
        digitos.getOito().addActionListener(e -> controlCaracteresNomais("8"));
        digitos.getNove().addActionListener(e -> controlCaracteresNomais("9"));
        digitos.getZero().addActionListener(e -> controlCaracteresNomais("0"));
    }

//	ALGUNS CARACTERES ESPECIAIS

    private void sinaisEspeciais(){
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
    }
    private void teclasDeFuncionamento(){
        digitos.getRaiz().addActionListener(this);
        digitos.getPonto().addActionListener(this);
        digitos.getApagar().addActionListener(this);
        digitos.getIgual().addActionListener(resultadosOperacoes);
        areaDeTexto.addKeyListener(keyHandler);
    }
    private void digitosNumericos(){
        digitos.getZero().addKeyListener(keyHandler);
        digitos.getUm().addKeyListener(keyHandler);
        digitos.getDOIS().addKeyListener(keyHandler);
        digitos.getTRES().addKeyListener(keyHandler);
        digitos.getQuatro().addKeyListener(keyHandler);
        digitos.getCinco().addKeyListener(keyHandler);
        digitos.getSeis().addKeyListener(keyHandler);
        digitos.getSete().addKeyListener(keyHandler);
        digitos.getOito().addKeyListener(keyHandler);
        digitos.getNove().addKeyListener(keyHandler);
    }



    private void control2() {
        teclasDeFuncionamento();
//		COMANDOS DO TECLADO
//		SINAIS ESPECIAIS
        sinaisEspeciais();
//		NÚMEROS DE 0 A 9
        digitosNumericos();
    }
//    verifica se há caracteres especiais no fim da String
    private boolean isSinaisEspeciaisInEnd(String text){
        return text.charAt(text.length() - 1) == '÷' || text.charAt(text.length() - 1) == '+' ||
                text.charAt(text.length() - 1) == '-' || text.charAt(text.length() - 1) == 'x';
    }
    /**
     * Realiza o controle da digitacão dos caracteres especiais
     * @return true or false
     */
    private boolean controlCharacEsp(String dig) {
        if(areaDeTexto.getText().length() >= 1) {
            ArrayList<Character> listChars = new ArrayList<>();

            for (char chars : areaDeTexto.getText().toCharArray())
                listChars.add(chars);

            if (isSinaisEspeciaisInEnd(areaDeTexto.getText()) ||
                    listChars.get(listChars.size() - 1) == '.') {

                if(listChars.get(listChars.size() - 2) == '0'&&
                        listChars.get(listChars.size()-1) == '.'&&
                        !dig.equals(".")){

                    for (int i=0;i<3;i++)
                        listChars.remove(listChars.size() - 1);
                    isPonto = true;
                }else listChars.remove(listChars.size() - 1);

                char[] newchar = new char[listChars.size()];

                for (int i = 0; i < listChars.size(); i++)
                    newchar[i] = listChars.get(i);

                areaDeTexto.setText(new String(newchar)+dig);
                return true;
            }
            isOperationPerformed = false;
            areaDeTexto.setText(areaDeTexto.getText() + dig);
            return true;
        }
        return false;
    }

//    VERIFICA SE O NUMERO É MUITO GRANDE
    private boolean isBigNumber(double number){
        String strNumber = String.valueOf(number);
        for (char chars:strNumber.toCharArray())
            if (chars == 'E')
                return true;
        return false;
    }

    /**
     *  @return Retorna uma String formatada, conforme a máscara especificada,
     * do resultado passado.
     */

    public String formatarResultado(double resultado, String mask) {
        Locale.setDefault(Locale.US);// padroniza o ponto
        DecimalFormat df = new DecimalFormat(mask);
        if (isBigNumber(resultado)){
            return String.valueOf(resultado);
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
                    String resultadoString = formatarResultado(calculadora.raizQuadrada(areaDeTexto.getText()),
                            "#.####");
                    for(char chars:resultadoString.toCharArray()) {
                        if (chars == '.'){
                            isPonto = false;
                            break;
                        }else isPonto = true;
                    }
                    isOperationPerformed = true;
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

//        DIVIDE A STRING ORIGINARIA DO VISOR EM UMA LISTA DE CHARS
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
                    isOperationPerformed = true;
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
}
