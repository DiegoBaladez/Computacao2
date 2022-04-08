import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testes são sempre públicos e void!
 * <p>
 * São métodos da biblioteca Junit para fazermos comparações.
 * -- AssertEquals("Regra de Negocio",NumeroEsperado,
 * NumeroObtido,VARIAÇÃO FLOAT){}
 * <p>
 * - OBSERVAÇÃO PARA QUANDO SE
 * COMPARAR DOIS FLOATS. USAR UM FLOAT_DELTA
 * <p>
 * -- Quando uso mais de um assert dentro do mesmo teste, se um quebrar antes,
 * o outro não irá rodar. Por isso, é melhor quebrar em vários testes!
 * <p>
 * Método Setup @Before - antes de cada teste, o programa roda o método setup!
 */

/**
 * Lab02
 *
 * 1)Crie um unit test para o método getQuantidadeDeTransacoesDeTodasAsContas()
 *   Dica: crie mais de uma ContaCorrente no seu teste de depois faça um Assert cha
 *   mando o método static ContaCorrente.getQuantidadeDeTransaçõesDeTodasAsContas()
 *   diretamente via nome da classe, uma vez que o método é static.
 *   -----------------------------------------------------------------------------------
 *
 *   2)Crie um construtor para a classe Correntista que receba CPF e nome.
 *-----------------------------------------------------------------------------------
 *   3)Crie um Getter e um Setter para o nome do correntista.
 *-----------------------------------------------------------------------------------
 *  4) faça com que o atributo CPF seja Final;
 *-----------------------------------------------------------------------------------
 *  5)Crie um teste para o método getCpfDoCorrentista. /////////////DUVIDAS////////////////
 *
 *  6)TDD - TestSaqueComFundos() e TestSaqueComSemFundos()
 *---------------------------------------------------------------------------------------
 *  7)TDD - implementar  efetuarTransferencia()
 *
 *  8)Faça com que maria e contaDaMaria sejam atributos da classe de teste, e
 *      inicializa-las no metodo setUp
 *-------------------------------------------------------------------------------------------
 *   9)TDD- implementar o método Transferencia() e fazer seus testes passarem.
 *-----------------------------------------------------------------------------------------------
 * Extra: Testar se o extrato foi alterado no metodo TestReceberDepositoEmDinheiroParaValorZero()
 *          --Regra: o extrato não deve se alterar com depósitos zerados!
 */
public class ContaCorrenteTest {

    private float FLOAT_DELTA = 0.00001f;

    private Correntista joao ;
    private long cpfDoJoao = 1256325;
    private ContaCorrente contaDoJoao;

    private Correntista maria;
    private long cpfDaMaria = 65498712;
    private ContaCorrente contaDaMaria;

    private Correntista diego;
    private ContaCorrente contaDoDiego;
    private float saldoInicial;

    @Before
    public void setUp() {
        joao = new Correntista(cpfDoJoao, "João");
        maria = new Correntista(cpfDaMaria,"Maria");
        diego = new Correntista(98321546,"Diego");

        contaDoJoao = new ContaCorrente(1, joao);
        contaDaMaria = new ContaCorrente(2, maria);
        contaDoDiego = new ContaCorrente(3, diego);

        saldoInicial = contaDoJoao.getSaldoEmReais();
        saldoInicial = contaDaMaria.getSaldoEmReais();
        saldoInicial = contaDoDiego.getSaldoEmReais();
    }

    @Test
    public void testSaldoInicialDaConta() {
        assertEquals("Toda conta deve ser criada com R$10,00",
                ContaCorrente.SALDO_INICIAL_DA_CONTA,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);


    }

    @Test
    public void testSaldoEmReais() {
    }

    @Test
    public void testGetSaldoEmReais() {
    }

    @Test
    public void testGetExtrato() {
    }

    @Test
    public void TestReceberDepositoEmDinheiroParaValoresValidos() {
        contaDoJoao.receberDepositoEmDinheiro(50);
        assertEquals("O saldo da conta deve ser atualizado após" +
                        "um depósito.",
                saldoInicial + 50,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void TestReceberDepositoEmDinheiroParaValoresNegativos() {
        contaDoJoao.receberDepositoEmDinheiro(-50);
        assertEquals("Depositos de valor negativo devem ser" +
                        "ignorados",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void TestReceberDepositoEmDinheiroParaValorZero() {
        contaDoJoao.receberDepositoEmDinheiro(0);
        assertEquals("Depositos de valor zero devem ser" +
                        "ignorados",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void testarTotalDeTransacoesEmTodasAsContas() {
        //assertEquals(0 ,ContaCorrente.getQuantidadeDeTransacoesDeTodasAsContas());

        contaDoDiego.receberDepositoEmDinheiro(12);
        contaDoJoao.receberDepositoEmDinheiro(12);
        contaDaMaria.receberDepositoEmDinheiro(12);
        //esse int pode sair!
        int quantidadeDeTransacoes = ContaCorrente.getQuantidadeDeTransacoesDeTodasAsContas();

        assertEquals("Devem ser contabilizadas todas as transações feitas",
                5,
                quantidadeDeTransacoes);

    }

    @Test
    public void TestSaqueComFundos(){
        contaDoJoao.sacar(contaDoJoao, 4);
        assertEquals(
                "O valor sacado deve ser descontado do saldo da conta",
                saldoInicial - 4,
                contaDoJoao.getSaldoEmReais()
                ,FLOAT_DELTA
        );
    }

    @Test
    public void TestSaqueComSemFundos() {
        contaDoJoao.sacar(contaDoJoao,100000000);
        assertEquals(
                "Saques de valores maiores que o saldo, não devem ser permitidos",
                saldoInicial,
                contaDoJoao.getSaldoEmReais()
                ,FLOAT_DELTA
        );
    }

    @Test
    public void testarTransferenciaComFundos(){


        contaDoJoao.efetuarTransferencia(contaDoJoao,contaDaMaria,3);

        assertEquals("Maria deve receber 3 reais",
                saldoInicial+3,
                contaDaMaria.getSaldoEmReais(),
                FLOAT_DELTA);

        assertEquals("Joao deve ter 3 reais descontados",
                saldoInicial-3,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void testarTransferenciaSemFundos(){

        contaDoJoao.efetuarTransferencia(contaDoJoao, contaDaMaria, 1000000);

        assertEquals("Transferencias sem fundo não devem alterar o saldo",
                saldoInicial,
                contaDaMaria.getSaldoEmReais(),
                FLOAT_DELTA);

        assertEquals("Transferencias sem fundo não devem alterar o saldo",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void testarGetCpfDoCorrentista(){
        assertEquals("O cpf do João deve ser o mesmo que o valor retornado",
                1256325, joao.getCpf());
        assertEquals("O cpf da Maria deve ser o mesmo que o valor retornado",
                65498712, maria.getCpf());
        assertEquals("O cpf do Diego deve ser o mesmo que o valor retornado",
                98321546, diego.getCpf());

        assertEquals("O cpf do João deve ser o mesmo que o valor retornado",
                1256325, contaDoJoao.getCpfDoCorrentista());
        assertEquals("O cpf da Maria deve ser o mesmo que o valor retornado",
                65498712, contaDaMaria.getCpfDoCorrentista());
        assertEquals("O cpf do Diego deve ser o mesmo que o valor retornado",
                98321546, contaDoDiego.getCpfDoCorrentista());
    }

    @Test
    public void testarGetCpfViaContaCorrente(){
        assertEquals("O cpf do João deve ser o mesmo que o valor retornado",
                1256325,
                contaDoJoao.getCpfDoCorrentista()
        );
        assertEquals("O cpf da Maria deve ser o mesmo que o valor retornado",
                65498712,
                contaDaMaria.getCpfDoCorrentista()
        );
        assertEquals("O cpf do Diego deve ser o mesmo que o valor retornado",
                98321546,
                contaDoDiego.getCpfDoCorrentista()
        );
    }


}

/**
 * Correções / Melhorias
 * 1) Setar os CPFS como uma variável long para facilitar a mudança
 */