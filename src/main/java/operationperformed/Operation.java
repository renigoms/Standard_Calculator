package operationperformed;

public enum Operation {

    PERFORMED(true);

    private boolean value;

    Operation(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
