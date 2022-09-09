import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
//organize imports

public class ContaInvestimentoTest {

    private float FLOAT_DELTA = 0.00001f;
    private int CPF_PADRAO = 123456789;

    private Correntista diego;
    private long cpfDoDiego = 1234567;
    private Conta contaDoDiego;
    private ContaInvestimento contaInvestimentoDoDiego;

    private Correntista maria;
    private long cpfDaMaria = 65498712;
    private Conta contaDaMaria;
    private ContaInvestimento contaInvestimentoDaMaria;

    private Correntista joao;
    private Correntista maicon;

    @Before
    public void setup() {
        maria = new Correntista(cpfDaMaria, "MARIA");
        contaDaMaria = new Conta(1234, maria);
        contaInvestimentoDaMaria = new ContaInvestimento(4321, maria, "CBW", 1.20f);

        diego = new Correntista(cpfDoDiego, "DIEGO");
        contaDoDiego = new Conta(987, diego);
        contaInvestimentoDoDiego = new ContaInvestimento(654, diego, "CBW", 1.20f);


    }

    @Test(expected=RuntimeException.class)
    public void testarConstrutorComExceçaoOcorrendo()
    {
        maicon = new Correntista(123456,"JOAO");
        ContaInvestimento contaInvestimentoDoJoao = new ContaInvestimento(21, maicon, "cdf",
                1.07f);
    }

    @Test
    public void testarConstrutorCorreto()
    {
        joao = new Correntista(123456,"JOAO");
        Conta contaDoJoao = new Conta(123,joao);
        ContaInvestimento contaInvestimentoDoJoao = new ContaInvestimento(789,joao,
                "cdf,", 1.02f);
    }

    @Test
    public void testarAplicarJuros() {

        float saldoInvestimentoAtual = contaInvestimentoDaMaria.getSaldoEmReais();
        float saldoInvestimentoAposAplicaoDoJuros = contaInvestimentoDaMaria.aplicarJuros();
        float saldoEsperado = 12;

        assertEquals(saldoEsperado, saldoInvestimentoAposAplicaoDoJuros,FLOAT_DELTA);
    }

    @Test
    public void testarResgatarTotal()
    {
        contaInvestimentoDoDiego.resgatarTotal(contaDoDiego);
        assertEquals("Não transferiu p conta",0, contaInvestimentoDoDiego.getSaldoEmReais(),FLOAT_DELTA);

        assertEquals("valor na conta ",20,contaDoDiego.getSaldoEmReais(), FLOAT_DELTA);

    }
}

