public class AritimeticaBasica {

    /**
     *
     * @param x um inteiro positivo
     * @param y um inteiro positivo
     * @return o mds, se for possivel calcular
     */
    public static int calcularMaximoDivisorComum(int x, int y) {
        if(x == 0 || y == 0)
        {
            throw new RuntimeException("Os parâmetros precisam ser inteiros positivos !0");
        }

        int resto = x % y;
        while (resto != 0) {
            x = y;
            y = resto;
            resto = x % y;
        }
        return y;
    }

    public static boolean extrairSinal(int numero){
        return numero >= 0;
    }
}
