package calculadoraModel;

import java.util.ArrayList;

public class Calculadora implements OperacoesI{
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
        double soma = 0;
        for(Double nums:num)
            soma+=nums;
        return num.size() == 1 ? num.get(0) : soma;
    }

    /**
     *
     * @param num
     * @return A multiplicação de valores no da lista de doubles fornecida
     */
    @Override
    public double multiplicacao(ArrayList<Double> num) {
        double mult = 1;
        for(double nums:num)
            mult*=nums;
        return num.size() == 1 ? num.get(0) : mult;
    }

    /**
     *
     * @param num
     * @return A divisão de valores no da lista de doubles fornecida
     */
    @Override
    public double divisao(ArrayList<Double> num) {
        double div = num.get(0), numSizeInit = num.size();
        num.remove(0);
        for(double nums:num)
            div/=nums;
        return numSizeInit == 1 ? num.get(0) : div;
    }

    /**
     *
     * @param stringInput
     *
     * <p>Converte um <strong>'ArrayList(String)'</strong> como entrada em um
     * <strong>ArrayList(Double)</strong> na saída.</p>
     */

    private ArrayList<Double> convArrayListString(ArrayList<String> stringInput) {
        ArrayList<Double> numOperacao = new ArrayList<>();
        for(String str:stringInput) {
            numOperacao.add(Double.parseDouble(str));
        }
        return numOperacao;
    }

    /**
     * Método que realiza todas as multiplicações
     * prioritarias
     * @param temporario
     * @return
     */

    private double multiplicacaoPrioritaria(ArrayList<String> temporario){
        temporario.remove(1);
        return multiplicacao(convArrayListString(temporario));
    }

    /**
     * Método que realiza todas as divisões prioritarias
     * @param temporario
     * @return
     */
    private double divisaoPrioritaria(ArrayList<String> temporario){
        temporario.remove(1);
        return divisao(convArrayListString(temporario));
    }

    /**
     * Adiciona Resultados na lista princiapal
     * @param result
     * @param chars
     */
    private void addResultPrincipal(double result, Character chars){
        temporario.clear();
        principal.add(String.valueOf(result));
        unirChars.append(String.valueOf(chars));
    }

    /**
     * Adicona resultados na lista temporaria
     * @param result
     */
    private void addResultTemporario(double result){
        temporario.clear();
        temporario.add(String.valueOf(result));
    }

    /**
     * Adiciona chars à lista temporaria
     */
    public void addCharsTemporario(){
        temporario.add(unirChars.toString());
        unirChars = new StringBuilder();
    }

    /**
     * Adicona o sinal negativo no inicio da String de chars
     * @param caractere
     */
    private void addSinalNegativo(char caractere){
        unirChars = new StringBuilder();
        unirChars.append(String.valueOf(caractere));
        isSomSub = true;
        inicioNegativo = false;
    }

    /**
     * Define a localização de sinais
     */
    private void definirPosicaoSeMaisOuMenos(){
        isMultDivAnterior = isMultDiv;
        isMultDiv = false;
        isSomSubAnterior = isSomSub;
        isSomSub = true;
    }

    /**
     * Define a localização de sinais
     */

    private void definirPosicaoSeVezesOuDiv(){
        isMultDivAnterior = isMultDiv;
        isMultDiv = true;
        isSomSubAnterior = isSomSub;
        isSomSub = false;
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
        unirChars = new StringBuilder("+");
        // DEFINIÇÃO DE TODAS AS BOOLEANAS NECESSÁRIAS AO MÉTODO
         inicioNegativo = caracteres.get(0) == '-';
         isSomSub = true; isMultDiv = false;
         isSomSubAnterior = false; isMultDivAnterior = false;

        // DEFINIÇÃO DA VARIÁVEL DE RESULTADO FINAL DA CONTA
        double result = 0;

         /*
            O ALGORITMO UTILIZA DE DOIS ARRAYLISTS: UM PRINCIPAL E UM TEMPORARIO QUE SERÁ USADO COMO
            FORMA DE GARANTIR A PRIORIDADE DA MULTIPLICAÇÃO E DA DIVISÃO NA EQUAÇÃO CALCULADA.
          */
        principal = new ArrayList<>();
        temporario = new ArrayList<>();


        for (Character chars : caracteres) {
            // AQUI É FEITA A VERIFICAÇÃO DE SINAL NEGATIVO NO PRIMEIRO NÚMERO.
            if (inicioNegativo) {
                addSinalNegativo(chars);
                continue;
            }
//            VERIFICA SE O CARACTERE chars É UM + OU -
            if (chars == '+' || chars == '-') {
                /*
                OS BOOLEANOS DE DEFINIÇAO DE SINAIS SERVEM PARA DEFINIR QUAL FOI O
                ULTIMO SINAL QUE APARECU E QUAL O SINAL ATUAL.
                 */
                definirPosicaoSeMaisOuMenos();

                /*
                cASO NÃO HAJA NENHUM SINAL DE MULTIPLICAÇÃO A DIREITA OU
                A ESQUERDA DO NÚMERO ELE É ADICIONADO NA LISTA PRINCIPAL
                 */
                // CASO +/-12345+/-
                if (isSomSubAnterior && isSomSub) {
                    principal.add(unirChars.toString());
                    unirChars = new StringBuilder();
                    unirChars.append(String.valueOf(chars));
                    continue;
                }
                /*
                CASO UM CHARS X/÷ SEJA ENCONTRATO A ESQUER OU A DIREITA DO NÚMERO
                ELE VAI PARA A LISTA TEMPORARIA
                 */
//               CASO X/÷12345+/-
                if (isMultDivAnterior && isSomSub) {
                    addCharsTemporario();
                    /*
                    NESSE CASO QUANDO TEPORARIO ATINGIR TAMANHO 3, ELE SERÁ COMPOSTO DE UM SINAL X/÷
                    NO MEIO E DOIS NÚMEROS ISSO SERVE PARA SEMPRE REALIZAR AS CONTAS DE DIVISÃO E
                    MULTIPLICAÇÃO COM PRIORIDADE DE ACORDO COM O SINAL.
                     */
                    if (temporario.size() == 3) {
                        switch(temporario.get(1)){
                            case "x":
                                result = multiplicacaoPrioritaria(temporario);
                                addResultPrincipal(result, chars);
                                continue;
                            case "÷":
                                result = divisaoPrioritaria(temporario);
                                addResultPrincipal(result, chars);
                                continue;
                        }
                    }
                }

            }

            if(chars == 'x' || chars == '÷'){
                definirPosicaoSeVezesOuDiv();

//                CASE +/-12345X/÷
                if(isSomSubAnterior && isMultDiv){
                    addCharsTemporario();
                    unirChars.append(String.valueOf(chars));
                    addCharsTemporario();
                    continue;

                }
//                CASE X/÷123456X/÷
                if(isMultDivAnterior && isMultDiv){
                    addCharsTemporario();
                    if(temporario.size() == 3){
                        switch (temporario.get(1)){
                            case "x":
                                result = multiplicacaoPrioritaria(temporario);
                               addResultTemporario(result);
                                if(chars == 'x' || chars == '÷'){
                                    unirChars.append(String.valueOf(chars));
                                    addCharsTemporario();
                                }
                                continue;
                            case "÷":
                                result = divisaoPrioritaria(temporario);
                                addResultTemporario(result);
                                if(chars == 'x' || chars == '÷'){
                                    unirChars.append(String.valueOf(chars));
                                    addCharsTemporario();
                                }
                                continue;
                        }
                    }
                }
            }
//            CASO O CHARS SEJA UM NUMERO ELE É ADICIONADO A STRING DE CHARS
            unirChars.append(String.valueOf(chars));
        }
//      ADICIONANDO O ULTIMO CHAR DA LISTA DE CARACTERES À LISTA PRINCIPAL
        if(isSomSub)  principal.add(unirChars.toString());

        if(isMultDiv) temporario.add(unirChars.toString());
//        EXECUTANDO MULTIPLICAÇÕES OU DIVISÕES DA PONTA FINAL DA LISTA DE CARACTERES
        if(temporario.size() == 3){
            switch (temporario.get(1)){
                case "x":
                    result = multiplicacaoPrioritaria(temporario);
                    principal.add(String.valueOf(result));
                    break;
                case "÷":
                    result = divisaoPrioritaria(temporario);
                    principal.add(String.valueOf(result));
                    break;
            }
        }
        /*
        RETORNA UMA SOMA FINAL COM NUMEROS POSITIVOS E NEGATIVOS E OS RESULTADOS
        DAS MULTIPLICAÇÕES E DIVISÕES
         */
        return soma(convArrayListString(principal));
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
}
