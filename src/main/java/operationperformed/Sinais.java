package operationperformed;

public enum Sinais {

    ADICAO('+'),
    MULTIPLICACAO('x'),
    SUBTRACAO('-'),
    DIVISAO('÷'),
    PONTO('.');

    private final char value;

    Sinais(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
