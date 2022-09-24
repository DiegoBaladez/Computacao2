package controle;

import produto.Produto;

public class Recibo {

    private final Produto produtoVendido;

    private final int quantidadeVendida;

    private final Usuario comprador;

    private final float valorTotal;


    public Recibo(Produto produtoVendido, int quantidadeVendida, Usuario comprador, float valorTotal) {
        this.produtoVendido = produtoVendido;
        this.quantidadeVendida = quantidadeVendida;
        this.comprador = comprador;
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString(){
        return String.format("Recibo no valor de R$%.2f referente à compra de" +
                " %d unidades de %s pelo comprador %s",valorTotal,quantidadeVendida,
                produtoVendido.getDescricao(), comprador.getNome() );
    }
}
