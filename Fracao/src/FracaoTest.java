import org.junit.Test;

import static org.junit.Assert.*;

public class FracaoTest {

    private final float FLOAT_DELTA = 0.0000F;

    @Test
    public void testarEqualsParaNumeradoreEDenominadorIdenticos() {
        Fracao fracao01 = new Fracao(1, 2);
        Fracao fracao02 = new Fracao(1, 2);
        assertFalse(fracao01 == fracao02);
        assertTrue(fracao01.equals(fracao02));
    }

    @Test
    public void testarEqualsParaNumeradoreEDenominadorEquivalentes() {
        Fracao fracao01 = new Fracao(1, 2);
        Fracao fracao02 = new Fracao(2, 4);
        assertTrue(fracao01.equals(fracao02));
        assertFalse(fracao01 == fracao02);
    }

    @Test
    public void testarEqualsParaFracoesOpostas() {
        Fracao fracao01 = new Fracao(1, 2, true);
        Fracao fracao02 = new Fracao(1, 2, false);
        assertFalse(fracao01.equals(fracao02));
        assertFalse(fracao01 == fracao02);
    }

    @Test
    public void TestarGetNumerador() {
        Fracao fracao;

        fracao = new Fracao(2, 7);
        assertEquals(2, fracao.getNumerador());

        fracao = new Fracao(-2, 7);
        assertEquals(2, fracao.getNumerador());

        fracao = new Fracao(2, -7);
        assertEquals(2, fracao.getNumerador());

        fracao = new Fracao(-2, -7);
        assertEquals(2, fracao.getNumerador());

        fracao = new Fracao(-2, -7, true);
        assertEquals(2, fracao.getNumerador());

        fracao = new Fracao(-2, -7, false);
        assertEquals(2, fracao.getNumerador());
    }

    @Test
    public void TestarGetDenominador() {
        Fracao fracao;

        fracao = new Fracao(2, 7);
        assertEquals(7, fracao.getDenominador());

        fracao = new Fracao(-2, 7);
        assertEquals(7, fracao.getDenominador());

        fracao = new Fracao(2, -7);
        assertEquals(7, fracao.getDenominador());

        fracao = new Fracao(-2, -7);
        assertEquals(7, fracao.getDenominador());

        fracao = new Fracao(-2, -7, true);
        assertEquals(7, fracao.getDenominador());

        fracao = new Fracao(-2, -7, false);
        assertEquals(7, fracao.getDenominador());
    }

    @Test
    public void testarFracaoIrredutivel() {
        Fracao fracao = new Fracao(5, 20, false);
        Fracao fracaoIrredutivel = fracao.getFracaoIrredutivel();

        assertEquals(1, fracaoIrredutivel.getNumerador());
        assertEquals(4, fracaoIrredutivel.getDenominador());
        assertFalse(fracaoIrredutivel.getSinal());


    }

    @Test
    public void testarFracaoIrredutivelQuandoAPropriaJaEhIrredutivel() {
        Fracao fracao = new Fracao(5, 20, false);
        Fracao fracaoIrredutivel = fracao.getFracaoIrredutivel();

        assertTrue(fracao == fracaoIrredutivel);


    }

    @Test
    public void testarSimplificar() {
        Fracao fracao = new Fracao(10, -36);

        assertEquals(10, fracao.getNumerador());
        assertEquals(36, fracao.getDenominador());
        assertFalse(fracao.getSinal());

        fracao.simplificar();

        assertEquals(5, fracao.getNumerador());
        assertEquals(18, fracao.getDenominador());
        assertFalse(fracao.getSinal());
    }

    @Test
    public void testarSomaDenominadorDiferente(){
        Fracao fracao01 = new Fracao(1, 3);
        Fracao fracao02 = new Fracao(3, 5);

        Fracao resultadoEsperado = new Fracao(14,15);
        Fracao resultadoObtido = fracao01.somarFracao(fracao02);

        assertEquals(resultadoEsperado.getNumerador(),resultadoObtido.getNumerador());
        assertEquals(resultadoEsperado.getDenominador(),resultadoObtido.getDenominador());

        assertTrue(resultadoObtido.getSinal());
    }

    @Test
    public void testarSomaComNumerosNegativos(){
        Fracao fracao01 = new Fracao(-1, 3);
        Fracao fracao02 = new Fracao(3, 5);

        Fracao resultadoEsperado = new Fracao(14,15,false);
        Fracao resultadoObtido = fracao01.somarFracao(fracao02);

        assertEquals(resultadoEsperado.getNumerador(),resultadoObtido.getNumerador());
        assertEquals(resultadoEsperado.getDenominador(),resultadoObtido.getDenominador());

        assertFalse(resultadoObtido.getSinal());
    }

    @Test
    public void testarSomaDenominadorIgual(){
        Fracao fracao01 = new Fracao(1, 5);
        Fracao fracao02 = new Fracao(3, 5);

        Fracao resultadoEsperado = new Fracao(4,5);
        Fracao resultadoObtido = fracao01.somarFracao(fracao02);

        assertEquals(resultadoEsperado.getNumerador(),resultadoObtido.getNumerador());
        assertEquals(resultadoEsperado.getDenominador(),resultadoObtido.getDenominador());

        assertTrue(resultadoObtido.getSinal());


    }

    @Test
    public void testarSomaComInteirosPositivos(){
        Fracao fracao01 = new Fracao(1,2);
        int inteiroPositivo = 3;

        Fracao resultadoEsperado = new Fracao(7,2);
        Fracao resultadoObtido = fracao01.somarFracao(3);

        assertEquals(resultadoEsperado.getNumerador(),resultadoObtido.getNumerador());
        assertEquals(resultadoEsperado.getDenominador(),resultadoObtido.getDenominador());
        assertTrue(resultadoObtido.getSinal());
    }

    @Test
    public void testarSomaComInteirosNegativos(){
        Fracao fracao01;
        Fracao resultadoEsperado;
        Fracao resultadoObtido;
        int inteiroNegativo = -3;

        fracao01 = new Fracao(1,2);
        resultadoEsperado = new Fracao(5,2);
        resultadoObtido = fracao01.somarFracao(inteiroNegativo);

        assertEquals(resultadoEsperado.getNumerador(),resultadoObtido.getNumerador());
        assertEquals(resultadoEsperado.getDenominador(),resultadoObtido.getDenominador());
        assertFalse(resultadoObtido.getSinal());

        fracao01 = new Fracao(4,5);
        resultadoEsperado = new Fracao(11,5);
        resultadoObtido = fracao01.somarFracao(inteiroNegativo);

        assertEquals(resultadoEsperado.getNumerador(),resultadoObtido.getNumerador());
        assertEquals(resultadoEsperado.getDenominador(),resultadoObtido.getDenominador());
        assertFalse(resultadoObtido.getSinal());

    }

    @Test
    public void testarSomaComInteirosFracaoNegativaEInteiroNegativo(){
        Fracao fracao01;
        int inteiroNegativo = -3;
        Fracao resultadoEsperado;
        Fracao resultadoObtido;

        fracao01 = new Fracao(-1,2);
        resultadoEsperado = new Fracao(-7,2);
        resultadoObtido = fracao01.somarFracao(inteiroNegativo);

        assertEquals(resultadoEsperado.getNumerador(),resultadoObtido.getNumerador());
        assertEquals(resultadoEsperado.getDenominador(),resultadoObtido.getDenominador());
        assertFalse(resultadoObtido.getSinal());

        fracao01 = new Fracao(-8,3);
        resultadoEsperado = new Fracao(17,3);
        resultadoObtido = fracao01.somarFracao(inteiroNegativo);

        assertEquals(resultadoEsperado.getNumerador(),resultadoObtido.getNumerador());
        assertEquals(resultadoEsperado.getDenominador(),resultadoObtido.getDenominador());
        assertFalse(resultadoObtido.getSinal());
    }

    @Test
    public void testarFracaoIgualAZero(){
        Fracao fracao = new Fracao(0,670);
        assertEquals(0,fracao.getNumerador());
        assertEquals(1,fracao.getDenominador());
    }

    @Test
    public void testarToString(){
        Fracao fracao;

        fracao = new Fracao(1,3,false);
        assertEquals("-1/3", fracao.toString());

        fracao = new Fracao(1,-3);
        assertEquals("-1/3", fracao.toString());

        fracao = new Fracao(2,5);
        assertEquals("2/5", fracao.toString());

        fracao = new Fracao(8,4);
        assertEquals("8/4", fracao.toString());

        fracao = new Fracao(9,1);
        assertEquals("9", fracao.toString());

        fracao = new Fracao(0,7);
        assertEquals("0", fracao.toString());


    }

    @Test
    public void testarmultiplicarPositivo(){
        Fracao fracao01 = new Fracao(3,2);
        Fracao fracao02 = new Fracao(4,5);

        Fracao fracaoMultiplicada = fracao01.multiplicar(fracao02);

        assertEquals(12,fracaoMultiplicada.getNumerador());
        assertEquals(10,fracaoMultiplicada.getDenominador());

        assertTrue(fracaoMultiplicada.getSinal());

    }

    @Test
    public void testarmultiplicarPositivoNegativo(){
        Fracao fracao01 = new Fracao(-3,2);
        Fracao fracao02 = new Fracao(4,5);

        Fracao fracaoMultiplicada = fracao01.multiplicar(fracao02);

        assertEquals(12,fracaoMultiplicada.getNumerador());
        assertEquals(10,fracaoMultiplicada.getDenominador());

        assertFalse(fracaoMultiplicada.getSinal());

    }

    @Test
    public void testarmultiplicarPassandoParametro(){
        Fracao fracao01 = new Fracao(3,2,false);
        Fracao fracao02 = new Fracao(4,5,false);

        Fracao fracaoMultiplicada = fracao01.multiplicar(fracao02);

        assertEquals(12,fracaoMultiplicada.getNumerador());
        assertEquals(10,fracaoMultiplicada.getDenominador());

        assertFalse(fracaoMultiplicada.getSinal());
    }

    @Test
    public void testarValorNumericoParaFracaoPositiva(){
        Fracao fracao = new Fracao(2,5);
        assertEquals(0.4,
                fracao.getValorNumerico(),
                FLOAT_DELTA);
    }

    @Test
    public void testarValorNumericoParaFracaoNegativa(){
        Fracao fracao = new Fracao(2,5,false);
        assertEquals(-0.4,
                fracao.getValorNumerico(),
                FLOAT_DELTA);

    }

    @Test
    public void testarValorNumericoParaFracaoNula(){
        Fracao fracao = new Fracao(0,5);
        assertEquals(0,
                fracao.getValorNumerico(),
                FLOAT_DELTA);

    }
}

/**
 * Consertar o teste getFracaoIrredutivelQuandoJaEh irredutivel()
 *
 *
 */