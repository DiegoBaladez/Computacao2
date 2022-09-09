public class SenhaInvalidaException extends Exception {

    private int numeroDeTentativasInvalidas = 0;

    public SenhaInvalidaException(int numeroDeTentativas)
    {
        this.numeroDeTentativasInvalidas = numeroDeTentativas;
    }

    public int getNumeroDeTentativasInvalidas()
    {
        return this.numeroDeTentativasInvalidas;
    }
}
