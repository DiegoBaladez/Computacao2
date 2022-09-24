package controle;

import exception.*;
import produto.Produto;

import java.util.HashMap;
import java.util.HashSet;

public class Loja {

    private HashMap<Long, Produto> produtoBycodigo;

    private HashMap<Produto, Integer> quantidadeByProduto;

    private HashSet<Usuario> usuariosBanidos;

    public Loja()
    {
          this.quantidadeByProduto = new HashMap<>();
          this.usuariosBanidos = new HashSet<>();
          this.produtoBycodigo = new HashMap<>();
    }

    /**
     * Queremos acrescentar um produto ao catálogo da loja
     * Se o produto já existir, acrescentamos a quantidade informada a
     * quantidade já existente daquele produto no estoque da loja
     * @param produto
     * @param quantidade A quantidade do produto a ser acrescentada ao estoque
     */
    //.getOrDefault -> Retorna um valor dado (no meu caso é zero) se a chave não for encontrada

    public void incluirProdutoNoEstoqueDaLoja(Produto produto, int quantidade)
    {
        this.produtoBycodigo.put(produto.getCodigo(), produto);
        this.quantidadeByProduto.put(produto,
                getQuantidadeEmEstoque(produto) + quantidade);
    }

    public Produto encontrarProduto(long codigo)
    {
        return this.produtoBycodigo.get(codigo);
    }

    /**
     *
     * @param produto
     * @return
     */
    public int getQuantidadeEmEstoque(Produto produto){
        return this.quantidadeByProduto.getOrDefault(produto,0);
        //retorna o valor zero por padrão se não achar o produto
    }

    public void banirUsuario(Usuario usuario){
        this.usuariosBanidos.add(usuario);

    }

    public Recibo efetuarVenda(Produto produto, int quantidade, Usuario comprador)
            throws EstoqueInsuficienteException, EnderecoInvalidoException, PagamentoException

    {
        int quantidadeEmEstoque = getQuantidadeEmEstoque(produto);

        if (quantidadeEmEstoque < quantidade){
            throw new EstoqueInsuficienteException(quantidadeEmEstoque);
        }

        if (comprador.getEndereço() == null)
        {
            throw new EnderecoInvalidoException();
        }

        final float valorTotal = produto.getPrecoSugerido() * quantidade;

        try{

            if (valorTotal > 0) {receberPagamento(comprador,  valorTotal);}

        } catch (UsuarioBanidoException | CartaoNaoAutorizadoException f){

            throw new PagamentoException();

        }

        //prepara o recebio
        Recibo recibo = new Recibo(produto,quantidade,comprador,
                valorTotal);

        entregar(produto, quantidade, comprador.getEndereço());

        //atualiza o estoque
        this.quantidadeByProduto.put(produto, quantidadeEmEstoque - quantidade);

        return recibo;
    }

    private void entregar(Produto produto, int quantidade, String endereço){
        // TODO IMPLEMENT ME
    }

    private void receberPagamento(Usuario comprador, float valor) throws CartaoNaoAutorizadoException,
            UsuarioBanidoException

    {
        if (this.usuariosBanidos.contains(comprador))
        {

            throw new UsuarioBanidoException();
        }

        if (!autorizarCartao(comprador.getNumeroCartaoDeCredito()))
        {
            throw new CartaoNaoAutorizadoException();
        }

        if (valor <= 0)
        {
            throw new IllegalArgumentException("Cobrança de valor não-positivo");
        }


    }

    private boolean autorizarCartao(long numeroDoCartao){
        //todo IMplement me!
        return true;
    }
}

