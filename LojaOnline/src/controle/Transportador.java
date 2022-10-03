package controle;

import exception.PesoMaxExcedidoException;
import exception.VolumeMaxExcedido;

public interface Transportador {

    void transportar(Transportavel transportavel, String endereço) throws PesoMaxExcedidoException,
            VolumeMaxExcedido;

}
