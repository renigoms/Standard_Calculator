package calculadoraApp;

public class teste {
    public static void teste1(String teste){
        teste = "mudou";
    }
    public static void main(String[] args) {
        String teste = "mudar";

        teste1(teste);

        System.out.println(teste);

    }
}
