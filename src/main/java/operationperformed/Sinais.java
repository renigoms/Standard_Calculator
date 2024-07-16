package operationperformed;

public enum Sinais {

    ADICAO("+"),
    MULTIPLICACAO("x"),
    SUBTRACAO("-"),
    DIVISAO("÷"),
    PONTO(".");

    private final String value;

    Sinais(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Character toChar(){
        return value.charAt(0);
    }

    public static void main(String[] args) {
        System.out.println(Sinais.DIVISAO.toChar()==Character.valueOf('÷').charValue());
    }
}
