import java.util.ArrayList;
import java.util.Locale;

public class TestUtils {

    static Documento criarDocumento(int numeroDePaginas, String nomeDoDocumento,boolean emCores)
    {
        ArrayList<String> paginas = new ArrayList<>();
        for(int i = 1; i <= numeroDePaginas; i++)
        {
            String pagina = String.format("Página %d do documento %s", i ,nomeDoDocumento);

            paginas.add(pagina);
        }
        Documento documento = new Documento(paginas, emCores);
        return documento;
    }

    static Impressora criarImpressora(String tipo)
    {
        switch (tipo.toLowerCase())
        {
            case "jato":
            case "jato de tinta":
            case "jatodetinta":
                return new ImpressoraJatoDeTinta("JJ222",1144);
            case "matricial":
                return new ImpressoraMatricial(7756);
            case "laser":
            default:
                return new ImpressoraLaser("EMCB",456);
        }
    }

}
