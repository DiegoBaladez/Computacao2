public class SaldoInsuficienteException extends Exception{

    private float saldoFaltante;

    public float getSaldoFaltante()
    {
        return this.saldoFaltante;
    }

    public void setSaldoFaltante(float novoSaldo)
    {
        this.saldoFaltante = novoSaldo;
    }
}
