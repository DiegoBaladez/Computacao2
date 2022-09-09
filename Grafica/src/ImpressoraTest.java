import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class ImpressoraTest {

    private ImpressoraJatoDeTinta impressoraJatoDeTinta;
    private ImpressoraLaser impressoraLaser;
    private ImpressoraMatricial impressoraMatricial;

    private Documento docPequenoPb;
    private Documento docGrandeColorido;


    @Before
    public void setUp() throws Exception {
        impressoraJatoDeTinta = new ImpressoraJatoDeTinta("xpto",123);
        impressoraLaser = new ImpressoraLaser("xpto2002",9874);
        impressoraMatricial = new ImpressoraMatricial(9884);

        docPequenoPb = TestUtils.criarDocumento(5,"Doc Pequeno",false);
        docGrandeColorido = TestUtils.criarDocumento(200,"Doc Grande",true);

       }


    //utilizar impressora em vez de especificar qual que é. JatoDeTinta, Matricial e Laser sao impressoras logo todas elas carregam papel e imprimem. Por isso nao precisa especificar. Deixa o código mais limpo e nao repetitivo

    private void executarTesteImpressoraComPapelSuficiente(Impressora impressora){

        impressora.carregarPapel(100);

        assertTrue(impressora.imprimirDocumento(docPequenoPb));
        assertTrue(impressora.imprimirDocumento(docPequenoPb));

        assertEquals(2,impressora.getQuantidadeDeDocumentosImpressos());
        assertEquals(90,impressora.getQuantidadeDeFolhasRestantes());
    }

    private void executarTesteImpressoraComPapelInsuficiente(Impressora impressora)
    {
        //a impressora não deve imprimir com o número de papel insuficiente
        impressora.carregarPapel(202);
        assertTrue(impressora.imprimirDocumento(docGrandeColorido));
        assertFalse("Não devemos" +
                "iniciar a impressao se na otiver" +
                "papel o suficiente",impressora.imprimirDocumento(docGrandeColorido));

        assertEquals(1,impressora.getQuantidadeDeDocumentosImpressos());
        assertEquals(2,impressora.getQuantidadeDeFolhasRestantes());

        impressora.carregarPapel(60);
        assertEquals(62,impressora.getQuantidadeDeFolhasRestantes());

        assertTrue(impressora.imprimirDocumento(docPequenoPb));

        assertEquals(2,impressoraLaser.getQuantidadeDeDocumentosImpressos());
        assertEquals(57,impressoraLaser.getQuantidadeDeFolhasRestantes());
    }

    @Test
    public void testarImpressaoLaserComPapelSuficiente()
    {
        executarTesteImpressoraComPapelSuficiente(impressoraLaser);
        executarTesteImpressoraComPapelSuficiente(impressoraJatoDeTinta);
        executarTesteImpressoraComPapelSuficiente(impressoraMatricial);
    }


    @Test
    public void testarImpressaoComPapelInsuficiente()
    {
        executarTesteImpressoraComPapelInsuficiente(impressoraLaser);
        executarTesteImpressoraComPapelInsuficiente(impressoraJatoDeTinta);
        executarTesteImpressoraComPapelInsuficiente(impressoraMatricial);

    }

}