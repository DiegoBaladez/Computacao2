package lojaTeste;

import controle.Loja;
import org.junit.Before;
import org.junit.Test;
import produto.Livro;
import produto.Produto;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LojaTest {

    private Loja loja;
    private Produto produto;

    @Before
    public void setUp()
    {
        loja = new Loja();
    }

    @Test
    public void testarCasdastrarDeProduto()
    {
        produto = new Livro(1234,"Pequeno Principe",
                "Saint",2010,"Editora de Livros");

        loja.incluirProdutoNoEstoqueDaLoja(produto,5);

        assertEquals(5, loja.getQuantidadeEmEstoque(produto));

        loja.incluirProdutoNoEstoqueDaLoja(produto,3);
        assertEquals(8, loja.getQuantidadeEmEstoque(produto));
    }


}