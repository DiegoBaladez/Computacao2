package exception;

public class VolumeMaxExcedido extends Exception{

    private int VolumeMaximoEmCm3;

    public VolumeMaxExcedido(int volumeMaximoEmCm3) {
        VolumeMaximoEmCm3 = volumeMaximoEmCm3;
    }


}
