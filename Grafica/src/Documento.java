import java.util.ArrayList;

public class Documento {

    private ArrayList<String> pagina;
    private boolean isColorido;

    public Documento(ArrayList paginas, boolean cores)
    {

        //this.pagina = paginas; Duas referencias para o mesmo objeto! - Copia a referência do objeto
        this.pagina = new ArrayList<>(paginas); // cria um novo objeto - tira uma cópia do conteúdo do objeto
        this.isColorido = cores;

    }

    public int getQuantidadeDePaginas()
    {
       return pagina.size();
    }

    public String getPagina (int numeroDaPagina)
    {
        return pagina.get(numeroDaPagina);
    }

    public boolean isTemCores()
    {
        return this.isColorido;
    }
}
