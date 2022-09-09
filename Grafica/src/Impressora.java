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

    public boolean imprimirDocumento(Documento documento) {

        if(this.quantPapel < documento.getQuantidadeDePaginas() )
        {
            return false;
        }

        for (int i = 0; i < documento.getQuantidadeDePaginas(); i++)
        {

            executarImpressaoDaPagina(documento.getPagina(i));
            this.quantPapel --;

        }

        this.quantDocumentos += 1;
        return true;
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

    public abstract void executarImpressaoDaPagina(String pagina); //cada impressora irá imprimir mas imprimir do seu jeito

    public long getNumeroDeSerie(){return this.numeroDeSerie;}
}

/**
 * 1º) A impressora só pode imprimir o documento se ela possuir papel
 * o suficiente para imprimir o documento inteiro.
 * <p>
 * 2º)
 */