import Exceptions.PapelInsuficienteException;
import Exceptions.TintaEsgotadaException;

public abstract class Impressora {

    private final long numeroDeSerie;
    private final String modelo;
    private int quantPapel;
    private int quantDocumentos;

    public Impressora(String modelo, long numeroDeSerie)
    {
        this.modelo = modelo;
        this.numeroDeSerie = numeroDeSerie;
    }

    /**
     * Imprime um documento se possível
     * @param documento o documento a ser impresso
     * @throws PapelInsuficienteException se não houver papel suficente para a impressão do doc
     * @throws TintaEsgotadaException se acabar a tinta!
     */
    public void imprimirDocumento(Documento documento) throws PapelInsuficienteException, TintaEsgotadaException {

        if(this.quantPapel < documento.getQuantidadeDePaginas() )
        {
            throw new PapelInsuficienteException();
        }

        for (int i = 0; i < documento.getQuantidadeDePaginas(); i++)
        {

            executarImpressaoDaPagina(documento.getPagina(i));
            this.quantPapel --;

        }

        this.quantDocumentos += 1;

    }

    public void carregarPapel(int numeroDeFolhas)
    {
        this.quantPapel += numeroDeFolhas;
    }

    public abstract void executarRotinaDeLimpeza(); //pq é abstract. pq cada impressora terá esse método mas o fará de modo diferente
                                                    // é obrigatório a subclasse implementar esse método!
    public int getQuantidadeDeFolhasRestantes()
    {
        return this.quantPapel;
    }

    public int getQuantidadeDeDocumentosImpressos() {
        return quantDocumentos;
    }

    public abstract void executarImpressaoDaPagina (String pagina) throws TintaEsgotadaException; //cada impressora irá imprimir mas imprimir do seu jeito

    public long getNumeroDeSerie(){return this.numeroDeSerie;}
}

/**
 * 1º) A impressora só pode imprimir o documento se ela possuir papel
 * o suficiente para imprimir o documento inteiro.
 * <p>
 * 2º)
 *
 *
 *
 *  OBSERVAÇÃO: Métodos com @Override que lançam exceção, precisam sinalizar também
 *  ao método de cima que ele pode lançar uma exceção!
 */