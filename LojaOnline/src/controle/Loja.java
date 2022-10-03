package controle;

import Veiculo.Caminhao;
import exception.*;
import produto.Produto;

import java.util.HashMap;
import java.util.HashSet;

public class Loja {

    private Pessoa donoDaLoja;

    private HashMap<Long, Vendavel> vendavelBycodigo;
    private HashMap<Vendavel, Integer> quantidadeByVendavel;
    private HashSet<Usuario> usuariosBanidos;

    private Caminhao caminhao;

    public Loja()
    {
          this.quantidadeByVendavel = new HashMap<>();
          this.usuariosBanidos = new HashSet<>();
          this.vendavelBycodigo = new HashMap<>();
    }

    /**
     * Queremos acrescentar um produto ao catálogo da loja
     * Se o produto já existir, acrescentamos a quantidade informada a
     * quantidade já existente daquele produto no estoque da loja
     * @param vendavel
     * @param quantidade A quantidade do produto a ser acrescentada ao estoque
     */
    //.getOrDefault -> Retorna um valor dado (no meu caso é zero) se a chave não for encontrada

    public void incluirProdutoNoEstoqueDaLoja(Vendavel vendavel, int quantidade)
    {
        this.vendavelBycodigo.put(vendavel.getCodigo(), vendavel);
        this.quantidadeByVendavel.put(vendavel,
                getQuantidadeEmEstoque(vendavel) + quantidade);
    }

    public Vendavel encontrarProduto(long codigo)
    {
        return this.vendavelBycodigo.get(codigo);
    }

    /**
     *
     * @param vendavel
     * @return
     */
    public int getQuantidadeEmEstoque(Vendavel vendavel){
        return this.quantidadeByVendavel.getOrDefault(vendavel,0);
        //retorna o valor zero por padrão se não achar o produto
    }

    public void banirUsuario(Usuario usuario){
        this.usuariosBanidos.add(usuario);

    }

    public Recibo efetuarVenda(Vendavel vendavel, int quantidade, Usuario comprador)
            throws EstoqueInsuficienteException, EnderecoInvalidoException, PagamentoException, VolumeMaxExcedido {
        int quantidadeEmEstoque = getQuantidadeEmEstoque(vendavel);

        if (quantidadeEmEstoque < quantidade){
            throw new EstoqueInsuficienteException(quantidadeEmEstoque);
        }

        if (comprador.getEndereço() == null)
        {
            throw new EnderecoInvalidoException();
        }

        final float valorTotal = vendavel.getPrecoSugerido() * quantidade;

        try{

            if (valorTotal > 0) {receberPagamento(comprador,  valorTotal);}

        } catch (UsuarioBanidoException | CartaoNaoAutorizadoException f){

            throw new PagamentoException();

        }

        this.quantidadeByVendavel.put(vendavel, quantidadeEmEstoque - quantidade);
        //prepara o recebio


        if(vendavel instanceof Transportavel)
        {
            try{

                entregar(this.caminhao,(Transportavel) vendavel, quantidade, comprador.getEndereço());
            }catch (PesoMaxExcedidoException | VolumeMaxExcedido e1)
            {

            } finally {
                //executa o código aconteça o que acontecer
                //executa após o catch ou se não ocorroe exceção alguma.

            }

        }

        Recibo recibo = new Recibo(vendavel, quantidade, comprador, valorTotal);
        return recibo;

    }

    private void entregar(Transportador transportador,
            Transportavel transportavel,
                          int quantidade,
                          String endereço) throws PesoMaxExcedidoException, VolumeMaxExcedido {
        transportador.transportar(transportavel, endereço);
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

/**
 * Interfaces -
 * Tenho a situação onde possuo uma classe (loja) que utiliza em seus métodos
 * e estruturas de dados outras classes (produto e servico). Os metodos da classe
 * loja permitem a livre troca entre produtos e servicos sem quebrar o código
 * No momento atual, temos um código que só vende serviços ou produtos.
 * Como vender os dois com o mesmo método:
 *"fazer algo do tipo venderproduto(Produto produto | Servico servico)"
 *
 * Que soluções podemos adotar:
 * 1ª solução: fazer com que serviços e produtos herdem de uma classe acima deles
 * e assim utilizar os métodos da superclasse. Porém, nem sempre podemos fazer
 * com que elas herdem de uma outra classe. Por questões de não existir herança
 * multipla ou pq simplesmente não faz sentido a herança.
 *
 * Para isso, existe a implementação de uma interface para essa classes
 * As interfaces possuem o nome adjetivado e possuem apenas métodos
 * Se colocar atributos, eles serão estáticos.
 * As classes que implementarão a interface, devem receber o 'implements' ao
 * lado do seu nome. E cada classe implementando uma interface deve declarar os
 * métodos que estão na interface!
 * Posso implementar quantas classes eu quiser!
 *
 * Resumo: interface agrupa classes MUITO distintas que nao fazem sentido elas herdarem
 * da mesma classe. Porém, essas classes tem algumas coisas em comum, por exemplo, um método
 * Então a interface entra como uma forma genérica, para agrupar essas classes
 *
 *
 * */