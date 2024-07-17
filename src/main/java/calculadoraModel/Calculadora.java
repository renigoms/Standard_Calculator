package calculadoraModel;

import calculadoraModel.utils.Utils;
import operationperformed.Sinais;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Calculadora implements OperacoesI{
    public static final int ONE = 1;
    public static final int ZERO = 0;
    private boolean isLigada;
    private ArrayList<String> principal ,temporario;
    private StringBuilder unirChars;
    private boolean inicioNegativo, isSomSub, isMultDiv, isSomSubAnterior, isMultDivAnterior;
    public Calculadora() {

        isLigada = false;
    }
    /**
     * @param num
     * @return A soma de valores no da lista de doubles fornecida
     */
    @Override
    public double soma(ArrayList<Double> num) {
        double soma = num.stream().mapToDouble(Double::doubleValue).sum();
        return num.size() == ONE ? num.get(ZERO) : soma;
    }
    /**
     *
     * @param num
     * @return A multiplicação de valores no da lista de doubles fornecida
     */
    @Override
    public double multiplicacao(ArrayList<Double> num) {
        double mult = ONE;
        mult = num.stream().reduce(mult, (a, b) -> a*b);
        return num.size() == ONE ? num.get(ZERO) : mult;
    }

    /**
     *
     * @param nums
     * @return A divisão de valores no da lista de doubles fornecida
     */
    @Override
    public double divisao(ArrayList<Double> nums) {
        double div = nums.get(ZERO), numSizeInit = nums.size();
        nums.remove(ZERO);
        div = nums.stream().reduce(div, (x,y) -> x/y);
        return numSizeInit == ONE ? nums.get(ZERO) : div;
    }


    /**
     *
     * @param caracteres — > ArrayList com a equação a ser calculada.
     *
     * <p>Realiza todas as operações necessárias de +,-,* e / </p>
     */
    @Override
    public double executarOperacoes(ArrayList<Character> caracteres) {
        // DEFINIÇÃO DA VARIÁVEL UNIRCHAR (CHAR ÚNICO) TENDO COMO CHAR INICIAL '+'
        unirChars = new StringBuilder(Sinais.ADICAO.getValue());
        // DEFINIÇÃO DE TODAS AS BOOLEANAS NECESSÁRIAS AO MÉTODO
         inicioNegativo = caracteres.get(ZERO).charValue() == Sinais.SUBTRACAO.toChar();
         isSomSub = true; isMultDiv = false;
         isSomSubAnterior = false; isMultDivAnterior = false;

        // DEFINIÇÃO DA VARIÁVEL DE RESULTADO FINAL DA CONTA
        double result;

         /*
            O ALGORITMO UTILIZA DE DOIS ARRAYLISTS: UM PRINCIPAL E UM TEMPORARIO QUE SERÁ USADO COMO
            FORMA DE GARANTIR A PRIORIDADE DA MULTIPLICAÇÃO E DA DIVISÃO NA EQUAÇÃO CALCULADA.
          */
        principal = new ArrayList<>();
        temporario = new ArrayList<>();


        for (Character chars : caracteres) {
            // AQUI É FEITA A VERIFICAÇÃO DE SINAL NEGATIVO NO PRIMEIRO NÚMERO.
            if (inicioNegativo) {
                Utils.addSinalNegativo(chars, this);
                continue;
            }
//            VERIFICA SE O CARACTERE chars É UM + OU -
            if (chars.charValue() == Sinais.ADICAO.toChar() || chars.charValue() == Sinais.SUBTRACAO.toChar()) {
                /*
                OS BOOLEANOS DE DEFINIÇAO DE SINAIS SERVEM PARA DEFINIR QUAL FOI O
                ULTIMO SINAL QUE APARECU E QUAL O SINAL ATUAL.
                 */
                Utils.definirPosicaoSeMaisOuMenos(this);

                /*
                cASO NÃO HAJA NENHUM SINAL DE MULTIPLICAÇÃO A DIREITA OU
                A ESQUERDA DO NÚMERO ELE É ADICIONADO NA LISTA PRINCIPAL
                 */
                // CASO +/-12345+/-
                if (isSomSubAnterior && isSomSub) {
                   Utils.addListaPrincipal(principal, this, chars);
                    continue;
                }
                /*
                CASO UM CHARS X/÷ SEJA ENCONTRATO A ESQUER OU A DIREITA DO NÚMERO
                ELE VAI PARA A LISTA TEMPORARIA
                 */
//               CASO X/÷12345+/-
                if (isMultDivAnterior && isSomSub) {
                    Utils.addCharsTemporario(temporario, this);
                    /*
                    NESSE CASO QUANDO TEPORARIO ATINGIR TAMANHO 3, ELE SERÁ COMPOSTO DE UM SINAL X/÷
                    NO MEIO E DOIS NÚMEROS ISSO SERVE PARA SEMPRE REALIZAR AS CONTAS DE DIVISÃO E
                    MULTIPLICAÇÃO COM PRIORIDADE DE ACORDO COM O SINAL.
                     */
                    if (temporario.size() == 3) {
                        switch(temporario.get(ONE)){
                            case "x":
                                result = Utils.multiplicacaoPrioritaria(temporario, this);
                                Utils.addResultPrincipal(result, chars, temporario, principal, unirChars);
                                continue;
                            case "÷":
                                result = Utils.divisaoPrioritaria(temporario, this);
                                Utils.addResultPrincipal(result, chars, temporario, principal, unirChars);
                                continue;
                        }
                    }
                }

            }

            if(chars.charValue() == Sinais.MULTIPLICACAO.toChar() || chars.charValue() == Sinais.DIVISAO.toChar()){
                Utils.definirPosicaoSeVezesOuDiv(this);

//                CASE +/-12345X/÷
                if(isSomSubAnterior && isMultDiv){
                    Utils.addCharsTemporario(temporario, this);
                    unirChars.append(chars);
                    Utils.addCharsTemporario(temporario, this);
                    continue;

                }
//                CASE X/÷123456X/÷
                if(isMultDivAnterior && isMultDiv){
                    Utils.addCharsTemporario(temporario, this);
                    if(temporario.size() == 3){
                        switch (temporario.get(ONE)){
                            case "x":
                                result = Utils.multiplicacaoPrioritaria(temporario, this);
                               Utils.addResultTemporario(result, temporario);
                                if(chars.charValue() == Sinais.MULTIPLICACAO.toChar() || chars.charValue() == Sinais.DIVISAO.toChar()){
                                    unirChars.append(chars);
                                    Utils.addCharsTemporario(temporario, this);
                                }
                                continue;
                            case "÷":
                                result = Utils.divisaoPrioritaria(temporario, this);
                                Utils.addResultTemporario(result, temporario);
                                if(chars.charValue() == Sinais.MULTIPLICACAO.toChar() || chars.charValue() == Sinais.DIVISAO.toChar()){
                                    unirChars.append(chars);
                                    Utils.addCharsTemporario(temporario, this);
                                }
                                continue;
                        }
                    }
                }
            }
//            CASO O CHARS SEJA UM NUMERO ELE É ADICIONADO A STRING DE CHARS
            unirChars.append(chars);
        }
//      ADICIONANDO O ULTIMO CHAR DA LISTA DE CARACTERES À LISTA PRINCIPAL
        if(isSomSub)  principal.add(unirChars.toString());

        if(isMultDiv) temporario.add(unirChars.toString());
//        EXECUTANDO MULTIPLICAÇÕES OU DIVISÕES DA PONTA FINAL DA LISTA DE CARACTERES
        if(temporario.size() == 3){
            switch (temporario.get(ONE)){
                case "x":
                    result = Utils.multiplicacaoPrioritaria(temporario, this);
                    principal.add(String.valueOf(result));
                    break;
                case "÷":
                    result = Utils.divisaoPrioritaria(temporario, this);
                    principal.add(String.valueOf(result));
                    break;
            }
        }
        /*
        RETORNA UMA SOMA FINAL COM NUMEROS POSITIVOS E NEGATIVOS E OS RESULTADOS
        DAS MULTIPLICAÇÕES E DIVISÕES
         */
        return soma(
                principal
                        .stream()
                        .map(Double::parseDouble)
                        .collect(Collectors.toCollection(ArrayList::new))
        );
    }

//    CALCULO DE RAIZ QUADRADA
    public double raizQuadrada(String numText){
        return Math.sqrt(Double.parseDouble(numText));
    }
    public boolean isLigada() {
        return isLigada;
    }
    public void setLigada(boolean isLigada) {
        this.isLigada = isLigada;
    }

    public boolean isInicioNegativo() {
        return inicioNegativo;
    }

    public void setInicioNegativo(boolean inicioNegativo) {
        this.inicioNegativo = inicioNegativo;
    }

    public boolean isSomSub() {
        return isSomSub;
    }

    public void setSomSub(boolean somSub) {
        isSomSub = somSub;
    }

    public boolean isMultDiv() {
        return isMultDiv;
    }

    public void setMultDiv(boolean multDiv) {
        isMultDiv = multDiv;
    }

    public boolean isSomSubAnterior() {
        return isSomSubAnterior;
    }

    public void setSomSubAnterior(boolean somSubAnterior) {
        isSomSubAnterior = somSubAnterior;
    }

    public boolean isMultDivAnterior() {
        return isMultDivAnterior;
    }

    public void setMultDivAnterior(boolean multDivAnterior) {
        isMultDivAnterior = multDivAnterior;
    }

    public StringBuilder getUnirChars() {
        return unirChars;
    }

    public void setUnirChars(StringBuilder unirChars) {
        this.unirChars = unirChars;
    }
}
