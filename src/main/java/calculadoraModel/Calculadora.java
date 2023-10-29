package calculadoraModel;

import java.util.ArrayList;

public class Calculadora implements OperacoesI{

    private boolean isLigada, isSizeUm;

    public Calculadora() {
        isLigada = false;
    }

    /**
     * @param num
     * @return A soma de valores no da lista de doubles fornecida
     */
    @Override
    public double soma(ArrayList<Double> num) {
        if(num.size() == 1)
            return num.get(0);
        double soma = 0;
        for(Double nums:num)
            soma+=nums;
        return soma;
    }

    /**
     *
     * @param num
     * @return A multiplicação de valores no da lista de doubles fornecida
     */
    @Override
    public double multiplicacao(ArrayList<Double> num) {
        if(num.size() == 1)
            return num.get(0);
        double mult = 1;
        for(double nums:num)
            mult*=nums;
        return mult;
    }

    /**
     *
     * @param num
     * @return A divisão de valores no da lista de doubles fornecida
     */
    @Override
    public double divisao(ArrayList<Double> num) {
        if(num.size() == 1)
            return num.get(0);
        double div = num.get(0);
        num.remove(0);
        for(double nums:num)
            div/=nums;
        return div;
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

    private double multiplicacaoPrioritaria(ArrayList<String> temporario){
        temporario.remove(1);
        return multiplicacao(convArrayListString(temporario));
    }
    private double divisaoPrioritaria(ArrayList<String> temporario){
        temporario.remove(1);
        return divisao(convArrayListString(temporario));
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
        String unirChars = "+";
        // DEFINIÇÃO DE TODAS AS BOOLEANAS NECESSÁRIAS AO MÉTODO
        boolean inicioNegativo = caracteres.get(0) == '-',
                isSomSub = true, isMultDiv = false,
                isSomSubAnterior = false, isMultDivAnterior = false;

        // DEFINIÇÃO DA VARIÁVEL DE RESULTADO FINAL DA CONTA
        double result = 0;

        /*
        O ALGORITMO UTILIZA DE DOIS ARRAYLISTS: UM PRINCIPAL E UM TEMPORARIO QUE SERÁ USADO COMO
        FORMA DE GARANTIR A PRIORIDADE DA MULTIPLICAÇÃO E DA DIVISÃO NA EQUAÇÃO CALCULADA.
         */
        ArrayList<String> principal = new ArrayList<>(),temporario = new ArrayList<>();

        for (Character chars : caracteres) {
            // AQUI É FEITA A VERIFICAÇÃO DE SINAL NEGATIVO NO PRIMEIRO NÚMERO.
            if (inicioNegativo) {
                // CASO A VERIFICAÇÃO SEJA POSITIVA UNICHAR É LIMPO.
                unirChars = "";
                // E ESSE SINAL NEGATIVO ('-') É INCORPORADO A UNICHAR
                // SUBSTITUINDO ('+') ATRIBUIDO NO MOMENTO DE SUA INICIALIZAÇÃO.
                unirChars += String.valueOf(chars);
                // ATRIBUIMOS TRUE A ISSOMSUB JA QUE '-' REPRESENTA UMA SUBTRAÇÃO
                isSomSub = true;
                // FALSE EM INICIONEGATIVO JÁ QUE ESSA VERIFICAÇÃO SÓ DEVE
                // OCORRER NO PRIMEIRO CHAR DA LISTA UMA VEZ.
                inicioNegativo = false;
                // PARA PASSAR PARA O PRÓXIMO CHAR.
                continue;
            }

            if (chars == '+' || chars == '-') {
                /*
                OS BOOLEANOS DE VERIFICAÇÃO DE SINAIS SERVEM PARA DIZER QUAL FOI O ULTIMO SINAL QUE APARECU
                E QUAL O SINAL ATUAL.
                 */
                isMultDivAnterior = isMultDiv;
                isMultDiv = false;
                isSomSubAnterior = isSomSub;
                isSomSub = true;

                // CASO +/-12345+/-
                if (isSomSubAnterior && isSomSub) {
//                    UNICHAR ADICIONADO A LISTA PRINCIPAL
                    principal.add(unirChars);
//                    LIMPA UNICHAR E ADICIONA O SINAL DE + OU - DE FORMA QUE UNICHAR TENHA SEMPRE UM DESSES
//                    DOIS NO COMEÇO.
                    unirChars = "";
                    unirChars += String.valueOf(chars);
                    continue;
                }
//               CASO X/÷12345+/-
                if (isMultDivAnterior && isSomSub) {
                    temporario.add(unirChars);
                    unirChars = "";
                    /*
                    NESSE CASO QUANDO TEPORARIO ATINGIR TAMANHO 3, ELE SERÁ COMPOSTO DE UM SINAL X/÷
                    NO MEIO E DOIS NÚMEROOS ISSO SERVE PARA SEMPRE REALIZAR AS CONTAS DE DIVISÃO E
                    MULTIPLICAÇÃO COM PRIORIDADE DE ACORDO COM O SINAL.
                     */
                    if (temporario.size() == 3) {
                        switch(temporario.get(1)){
                            case "x":
                                result = multiplicacaoPrioritaria(temporario);
                                temporario.clear();
                                principal.add(String.valueOf(result));
                                unirChars += String.valueOf(chars);
                                continue;
                            case "÷":
                                result = divisaoPrioritaria(temporario);
                                temporario.clear();
                                principal.add(String.valueOf(result));
                                unirChars += String.valueOf(chars);
                                continue;
                        }
                    }
                }

            }

            if(chars == 'x' || chars == '÷'){
                isMultDivAnterior = isMultDiv;
                isMultDiv = true;
                isSomSubAnterior = isSomSub;
                isSomSub = false;

                if(isSomSubAnterior && isMultDiv){
                    temporario.add(unirChars);
                    unirChars="";
                    unirChars += String.valueOf(chars);
                    temporario.add(unirChars);
                    unirChars="";
                    continue;

                }

                if(isMultDivAnterior && isMultDiv){
                    temporario.add(unirChars);
                    unirChars="";
                    if(temporario.size() == 3){
                        switch (temporario.get(1)){
                            case "x":
                                result = multiplicacaoPrioritaria(temporario);
                                temporario.clear();
                                temporario.add(String.valueOf(result));
                                if(chars == 'x' || chars == '÷'){
                                    unirChars += String.valueOf(chars);
                                    temporario.add(unirChars);
                                    unirChars="";
                                }
                                continue;
                            case "÷":
                                result = divisaoPrioritaria(temporario);
                                temporario.clear();
                                temporario.add(String.valueOf(result));
                                if(chars == 'x' || chars == '÷'){
                                    unirChars += String.valueOf(chars);
                                    temporario.add(unirChars);
                                    unirChars="";
                                }
                                continue;
                        }
                    }
                }
            }
            unirChars += String.valueOf(chars);
        }

        if(isSomSub)  principal.add(unirChars);

        if(isMultDiv) temporario.add(unirChars);

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
        return soma(convArrayListString(principal));
    }

    public boolean isLigada() {
        return isLigada;
    }
    public void setLigada(boolean isLigada) {
        this.isLigada = isLigada;
    }
}
