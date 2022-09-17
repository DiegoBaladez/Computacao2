package Exceptions;

public class TintaEsgotadaException extends Exception{
    public TintaEsgotadaException(String cor){
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    private String cor;


}
