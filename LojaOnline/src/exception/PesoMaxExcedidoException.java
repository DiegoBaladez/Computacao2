package exception;

public class PesoMaxExcedidoException extends Exception{

    private int pesoMaximoSuportadoEmGramas;

    public PesoMaxExcedidoException(int pesoMaximoSuportadoEmGramas) {

        this.pesoMaximoSuportadoEmGramas = pesoMaximoSuportadoEmGramas;

    }

    public int getPesoMaximoSuportadoEmGramas(){
        return pesoMaximoSuportadoEmGramas;
    }
}
