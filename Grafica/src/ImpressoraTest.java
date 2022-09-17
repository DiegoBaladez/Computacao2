import Exceptions.PapelInsuficienteException;
import Exceptions.TintaEsgotadaException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class ImpressoraTest {

    private ImpressoraJatoDeTinta impressoraJatoDeTinta;
    private ImpressoraLaser impressoraLaser;
    private ImpressoraMatricial impressoraMatricial;

    private Grafica grafica;

    private Documento docPequenoPb;
    private Documento docGrandeColorido;


    @Before
    public void setUp() throws Exception {
        grafica = new Grafica();


        impressoraJatoDeTinta = new ImpressoraJatoDeTinta("xpto",123);
        impressoraLaser = new ImpressoraLaser("xpto2002",9874);
        impressoraMatricial = new ImpressoraMatricial(9884);

        docPequenoPb = TestUtils.criarDocumento(5,"Doc Pequeno",false);
        docGrandeColorido = TestUtils.criarDocumento(200,"Doc Grande",true);

       }


    //utilizar impressora em vez de especificar qual que é. JatoDeTinta, Matricial e Laser sao impressoras logo todas elas carregam papel e imprimem. Por isso nao precisa especificar. Deixa o código mais limpo e nao repetitivo


    private void executarTesteImpressoraComPapelSuficiente(Impressora impressora) throws TintaEsgotadaException, PapelInsuficienteException {

        impressora.carregarPapel(100);
        impressora.imprimirDocumento(docPequenoPb);
        impressora.imprimirDocumento(docPequenoPb);

        assertEquals(2,impressora.getQuantidadeDeDocumentosImpressos());
        assertEquals(90,impressora.getQuantidadeDeFolhasRestantes());
    }


    private void executarTesteImpressoraComPapelInsuficiente(Impressora impressora)
    {
        //a impressora não deve imprimir com o número de papel insuficiente
        impressora.carregarPapel(202);

        try {
            impressora.imprimirDocumento(docGrandeColorido);
        } catch (PapelInsuficienteException e) {
           fail("A impressão deveria ter acontecido sem problemas pois nao falta papel");
        } catch (TintaEsgotadaException e){
            //sem problemas
        }

        try {
            impressora.imprimirDocumento(docPequenoPb);
            fail("Não devemos começar a impressao se não houver papel suficiente");
        } catch (PapelInsuficienteException e) {
            //ok era esperado
        } catch (TintaEsgotadaException e){
            //sem problemas
        }

        assertEquals(1,impressora.getQuantidadeDeDocumentosImpressos());
        assertEquals(2,impressora.getQuantidadeDeFolhasRestantes());

        impressora.carregarPapel(60);
        assertEquals(62,impressora.getQuantidadeDeFolhasRestantes());

        try {
            impressora.imprimirDocumento(docPequenoPb);
        } catch (PapelInsuficienteException e) {
            fail("A impressão deveria ter acontecido sem problemas pois nao falta papel");
        } catch (TintaEsgotadaException e){
            //sem problemas
        }

        assertEquals(2,impressoraLaser.getQuantidadeDeDocumentosImpressos());
        assertEquals(57,impressoraLaser.getQuantidadeDeFolhasRestantes());
    }

    @Test
    public void testarImpressaoLaserComPapelSuficiente() throws TintaEsgotadaException, PapelInsuficienteException {
        executarTesteImpressoraComPapelSuficiente(impressoraLaser);
        executarTesteImpressoraComPapelSuficiente(impressoraJatoDeTinta);
        executarTesteImpressoraComPapelSuficiente(impressoraMatricial);
    }


    @Test
    public void testarImpressaoComPapelInsuficiente() throws TintaEsgotadaException {
        executarTesteImpressoraComPapelInsuficiente(impressoraLaser);
        executarTesteImpressoraComPapelInsuficiente(impressoraJatoDeTinta);
        executarTesteImpressoraComPapelInsuficiente(impressoraMatricial);

    }

}