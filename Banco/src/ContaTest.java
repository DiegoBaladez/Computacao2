import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContaTest {

    private float FLOAT_DELTA = 0.00001f;

    private Correntista joao;
    private long cpfDoJoao = 1256325;
    private Conta contaDoJoao;
    private int senhaDoJoao = 123;

    private Correntista maria;
    private long cpfDaMaria = 65498712;
    private Conta contaDaMaria;

    private Correntista diego;
    private Conta contaDoDiego;
    private float saldoInicial;

    @Before
    public void setUp() {
        joao = new Correntista(cpfDoJoao, "João");
        maria = new Correntista(cpfDaMaria, "Maria");
        diego = new Correntista(98321546, "Diego");

        contaDoJoao = new Conta(1, joao);
        contaDaMaria = new Conta(2, maria);
        contaDoDiego = new Conta(3, diego);

        saldoInicial = contaDoJoao.getSaldoEmReais();
        saldoInicial = contaDaMaria.getSaldoEmReais();
        saldoInicial = contaDoDiego.getSaldoEmReais();

        contaDoJoao.setSenha(senhaDoJoao);
    }

    @Test
    public void testSaldoInicialDaConta() {
        assertEquals("Toda conta deve ser criada com R$10,00",
                Conta.SALDO_INICIAL_DA_CONTA,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
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
        String extratoAntes = contaDoJoao.getExtrato();

        contaDoJoao.receberDepositoEmDinheiro(-50);
        assertEquals("Depositos de valor negativo devem ser" +
                        "ignorados",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);

        String extratoDepois = contaDoJoao.getExtrato();

        assertEquals("Depósitos ignorados não devem contar no extrato"
                , extratoAntes, extratoDepois);
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


        contaDoDiego.receberDepositoEmDinheiro(12);
        contaDoJoao.receberDepositoEmDinheiro(12);
        contaDaMaria.receberDepositoEmDinheiro(12);

        int quantidadeDeTransacoes = Conta.getQuantidadeDeTransacoesDeTodasAsContas();

        assertEquals("Devem ser contabilizadas todas as transações feitas",
                4,
                quantidadeDeTransacoes);

    }

    @Test
    public void TestSaqueComFundos() throws SaldoInsuficienteException, ContaInativaException, SenhaInvalidaException {
        contaDoJoao.sacar(4, senhaDoJoao);
        assertEquals(
                "O valor sacado deve ser descontado do saldo da conta",
                saldoInicial - 4,
                contaDoJoao.getSaldoEmReais()
                , FLOAT_DELTA);
    }

//    @Test
//    public void TestSaqueComFundosComSenhaErrada() throws SaldoInsuficienteException, ContaInativaException {
//        try {
//            contaDoJoao.sacar(4, 5874885);
//            fail("senhas invalidas devem disparar exceções"); //oldschool
//        } catch (SenhaInvalidaException e)
//        {
//
//        }
//    }


    @Test
    public void TestSaqueSemFundos() throws ContaInativaException, SenhaInvalidaException {
        try
        {
            contaDoJoao.sacar(100000000, senhaDoJoao);
        } catch (SaldoInsuficienteException e)

        {
            System.out.println("exceção tratada");
        }
//        assertEquals(
//                "Saques de valores maiores que o saldo, não devem ser permitidos",
//                saldoInicial,
//                contaDoJoao.getSaldoEmReais()
//                , FLOAT_DELTA
//        );
    }

    @Test
    public void testarTransferenciaComFundos() {


        contaDoJoao.efetuarTransferencia(contaDaMaria, 3);

        assertEquals("Maria deve receber 3 reais",
                saldoInicial + 3,
                contaDaMaria.getSaldoEmReais(),
                FLOAT_DELTA);

        assertEquals("Joao deve ter 3 reais descontados",
                saldoInicial - 3,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void testarTransferenciaSemFundos() {

        contaDoJoao.efetuarTransferencia(contaDaMaria, 1000000);

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
    public void testarGetCpfDoCorrentista() {
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
    public void testarGetCpfViaContaCorrente() {
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

    @Test
    public void testarExtrato() throws SenhaInvalidaException, SaldoInsuficienteException, ContaInativaException {
        String extratoEsperado = "Conta criada com o saldo de R$10,00\n";
        contaDoJoao.getExtrato();
        assertEquals(extratoEsperado, contaDoJoao.getExtrato());

        contaDoJoao.sacar(3,senhaDoJoao);
        extratoEsperado = "Conta criada com o saldo de R$10,00\n" +
                "Efetuado saque em dinheiro de: R$3.0\n";
        assertEquals(extratoEsperado, contaDoJoao.getExtrato());

    }

    @Test
    public void testarEncerramentoDeContaPeloGerenteDaContaErrado() throws
            SenhaInvalidaException, SaldoInsuficienteException {
        Gerente gerenteDeContaCarlos = new Gerente("Carlos",999,20);

        try {
            gerenteDeContaCarlos.encerrarConta(contaDoJoao);
        }catch(ContaNaoGerenciadaException e)
        {
            System.out.printf("A exceção foi tratada");
        }
//        assertTrue("contas nao pode ser encerradas por gerentes" +
//                "de contas que nao gerenciam aquela conta.",contaDoJoao.isIsativa());
    }

    @Test
    public void testarEncerramentoDeContaPeloGerenteDaContaCorreto() throws ContaNaoGerenciadaException,
            SenhaInvalidaException, SaldoInsuficienteException {
        Gerente gerenteDeContaCarlos = new Gerente("Carlos",999,20);
        gerenteDeContaCarlos.gerenciarConta(contaDoJoao);
        assertTrue(gerenteDeContaCarlos.ehGerenteDaConta(contaDoJoao));

        gerenteDeContaCarlos.encerrarConta(contaDoJoao);
        assertFalse(contaDoJoao.isIsativa());
        assertFalse(gerenteDeContaCarlos.ehGerenteDaConta(contaDoJoao));

        assertFalse("deve ficar inativa após ser encerrada",contaDoJoao.isIsativa());


    }

    @Test
    public void testarEncerramentoDeContaPeloGerenteGeral() throws ContaNaoGerenciadaException,
            SenhaInvalidaException, SaldoInsuficienteException {
        Gerente gerenteDeContaCarlos = new Gerente("Carlos",999,20);
        GerenteGeral gerenteGeralMariza = new GerenteGeral("Mariza",123456,2);
        gerenteDeContaCarlos.gerenciarConta(contaDoJoao);
        assertTrue(gerenteDeContaCarlos.ehGerenteDaConta(contaDoJoao));

        gerenteGeralMariza.encerrarConta(contaDoJoao);
        assertFalse(contaDoJoao.isIsativa());
        assertFalse(gerenteGeralMariza.ehGerenteDaConta(contaDoJoao));
        assertFalse(gerenteDeContaCarlos.ehGerenteDaConta(contaDoJoao));

        assertFalse(contaDoJoao.isIsativa());


    }

}

