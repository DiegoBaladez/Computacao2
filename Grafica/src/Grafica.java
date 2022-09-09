import java.util.ArrayList;
import java.util.HashMap;

public class Grafica {

    private float precoPorPaginaPB;

    private float precoPorPaginaColorida;

    private ArrayList<Impressora> impressoras;

    private HashMap<Long, Impressora> impressoraByNumeroDeSerie;

    private int indiceProximaImpressoraNoRevezamento;


    public Grafica() {
        this.impressoras = new ArrayList<>();
    }

    public void imprimirDocumento(Documento documento) {


        if (this.indiceProximaImpressoraNoRevezamento >= impressoras.size()) {
            this.indiceProximaImpressoraNoRevezamento = this.indiceProximaImpressoraNoRevezamento
                    % impressoras.size();
        }
        Impressora impressoraDaVez = this.impressoras.get(this.indiceProximaImpressoraNoRevezamento);

        impressoraDaVez.imprimirDocumento(documento);
        this.indiceProximaImpressoraNoRevezamento++;


    }

    public float orcarImpressao(Documento documento) {
        float valorTotal;
        //escrever com o operador ternário
        if (documento.isTemCores() == false) {
            valorTotal = documento.getQuantidadeDePaginas() * 0.1f;
        } else {
            valorTotal = documento.getQuantidadeDePaginas() * 0.3f;
        }
        return valorTotal;
    }

    //armazena a impressora em algum lugar
    public void adicionarImpressora(Impressora impressora) {
        this.impressoras.add(impressora);
        this.impressoraByNumeroDeSerie.put(impressora.getNumeroDeSerie(), impressora);
    }

    public void removerImpressora(Impressora impressora) {
        this.impressoras.remove(impressora);
        this.impressoraByNumeroDeSerie.remove(impressora.getNumeroDeSerie());
    }

    public void removerImpressora(int indice) {

        this.impressoras.remove(indice);

    }

    public float setPrecoPorPagina(float precoPorPagina, boolean cor) {
        if (cor) {
            return this.precoPorPaginaColorida = precoPorPagina;
        } else {
            return this.precoPorPaginaPB = precoPorPagina;
        }
    }


}
/*
 * a grafica pega a proxima impressora livre para iniciar uma impressão
 * usando sempre a proxima impressora.
 * */