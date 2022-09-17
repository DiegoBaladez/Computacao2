import Exceptions.PapelInsuficienteException;
import Exceptions.TintaEsgotadaException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraficaTest {
    private static final float FLOAT_DELTA = 0.0000001f;
    Grafica grafica;
    Impressora impressora1;
    Impressora impressora2;
    Impressora impressora3;
    Documento docColorido;
    Documento docPB;

    @Before
    public void setUp(){

        grafica  = new Grafica();
        grafica.setPrecoPorPagina(0.1f,false);
        grafica.setPrecoPorPagina(0.3f,true);

        impressora1 = TestUtils.criarImpressora("jatodetinta");
        impressora2 = TestUtils.criarImpressora("laser");
        impressora3 = TestUtils.criarImpressora("matricial");

        grafica.adicionarImpressora(impressora1);
        grafica.adicionarImpressora(impressora2);
        grafica.adicionarImpressora(impressora3);

        docColorido = TestUtils.criarDocumento(10,
                "Doc Test",true);
        docPB = TestUtils.criarDocumento(50,
                "Doc Test",false);

    }

    @Test
    public void testarOrcamentoImpressao()
    {

        assertEquals(3,grafica.orcarImpressao(docColorido),FLOAT_DELTA);
        assertEquals(5,grafica.orcarImpressao(docPB),FLOAT_DELTA);

    }


    @Test
    public void testarRevesamentoEntreAsImpressoras() throws TintaEsgotadaException, PapelInsuficienteException {
        impressora1.carregarPapel(10000000);
        impressora2.carregarPapel(10000000);
        impressora3.carregarPapel(10000000);

        grafica.imprimirDocumento(docColorido);
        grafica.imprimirDocumento(docPB);
        grafica.imprimirDocumento(docColorido);

        assertEquals(1,impressora1.getQuantidadeDeDocumentosImpressos());
        assertEquals(1,impressora2.getQuantidadeDeDocumentosImpressos());
        assertEquals(1,impressora3.getQuantidadeDeDocumentosImpressos());

        for (int i = 0; i < 3000; i++)
        {
            grafica.imprimirDocumento(docColorido);
        }

        String regraDeNegocio = "As impressoras precisam trabalhar em revisamento";
        assertEquals(regraDeNegocio,1001,impressora1.getQuantidadeDeDocumentosImpressos());
        assertEquals(regraDeNegocio,1001,impressora2.getQuantidadeDeDocumentosImpressos());
        assertEquals(regraDeNegocio,1001,impressora3.getQuantidadeDeDocumentosImpressos());
    }

    @Test

    public void testarRemocaoImpressora() throws TintaEsgotadaException, PapelInsuficienteException {
        grafica.removerImpressora(impressora2);

        impressora1.carregarPapel(20);
        impressora3.carregarPapel(20);

        for(int i = 0; i < 4; i++  )
        {
            grafica.imprimirDocumento(docColorido);
        }


        assertEquals(2,impressora1.getQuantidadeDeDocumentosImpressos());
        assertEquals(2,impressora3.getQuantidadeDeDocumentosImpressos());

    }
    @Test
    public void testarRevezamentoAposRemocaoImpressora() throws TintaEsgotadaException, PapelInsuficienteException {
        grafica.removerImpressora(impressora2);

        impressora1.carregarPapel(200);
        impressora3.carregarPapel(200);

        for(int i = 0; i < 5; i++  )
        {
            grafica.imprimirDocumento(docColorido);
        }


        assertEquals(3,impressora1.getQuantidadeDeDocumentosImpressos());
        assertEquals(2,impressora3.getQuantidadeDeDocumentosImpressos());
        //nesse ponto, a prox impressora será a de indice 1
        grafica.removerImpressora(impressora1);

        grafica.imprimirDocumento(docColorido); //exceção

    }

}