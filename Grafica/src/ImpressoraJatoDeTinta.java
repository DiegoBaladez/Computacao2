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
    public void executarImpressaoDaPagina(String pagina) {

    }

    public int getNivelTintaPreto()
    {
        //ToDo
        return 0;
    }

    public int getNivelTintaAmarela()
    {
        //ToDo
        return 0;
    }

    public int getNivelTintaMagenta()
    {
        //ToDo
        return 0;
    }

    public int getNivelTintaCiano(){
        //ToDo
        return 0;
    }
}
