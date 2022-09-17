import Exceptions.TintaEsgotadaException;

public class ImpressoraJatoDeTinta extends Impressora {

    public ImpressoraJatoDeTinta(String modelo, long numeroDeSerie)
    {
        super(modelo, numeroDeSerie);
    }

    @Override
    public void executarRotinaDeLimpeza()
    {
        //ToDo
    }

    @Override
    public void executarImpressaoDaPagina(String pagina) throws TintaEsgotadaException {
        if(getNivelTintaPreto() == 0)
        {
            throw new TintaEsgotadaException("preta");
        }
    }

    public int getNivelTintaPreto()
    {
        //ToDo
        return 10;
    }

    public int getNivelTintaAmarela()
    {
        //ToDo
        return 10;
    }

    public int getNivelTintaMagenta()
    {
        //ToDo
        return 10;
    }

    public int getNivelTintaCiano(){
        //ToDo
        return 10;
    }
}
