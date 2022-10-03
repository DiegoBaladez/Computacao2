package Veiculo;

import controle.Pessoa;
import exception.PesoMaxExcedidoException;
import org.junit.Test;
import produto.Livro;

import static org.junit.Assert.*;

public class VeiculoTest {

    @Test
    public void testarTransporte() throws PesoMaxExcedidoException {
        Caminhao caminhao = new Caminhao();

        Livro livro = new Livro(132456,"titulo","Autor",2010,"editora");
        Pessoa pessoa = new Pessoa(13265,"Diego");

        caminhao.transportar(livro,"minha rua");
        caminhao.transportar(pessoa ,"minha rua");

    }

}