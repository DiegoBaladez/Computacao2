package Veiculo;

import controle.Dimensoes;
import controle.Transportador;
import controle.Transportavel;
import exception.PesoMaxExcedidoException;
import exception.VolumeMaxExcedido;
import produto.Produto;

public class Veiculo implements Transportavel, Transportador {

    private int volumeMaxaximoEmCm3;
    private int pesoMaxSuportadoEmGramas;

    public void andar(int velocidade){

    }


    public long getCodigo() {
        return 0;
    }


    public float getPrecoSugerido() {
        return 0;
    }


    public String getDescricao() {
        return null;
    }

    @Override
    public void transportar(Transportavel transportavel, String endereco)
            throws PesoMaxExcedidoException, VolumeMaxExcedido
    {
        if(transportavel.getPesoEmGramas() > this.pesoMaxSuportadoEmGramas)
        {
            throw new PesoMaxExcedidoException(100000);
        }

        if(transportavel.getDimensoes().getVolumeEmCentimetrosCubicos() > this.volumeMaxaximoEmCm3)
        {
            throw new VolumeMaxExcedido(VolumeMaxExcedido);
        }
    }


    @Override
    public Dimensoes getDimensoes() {
        return null;
    }

    @Override
    public int getPesoEmGramas() {
        return 0;
    }
}
