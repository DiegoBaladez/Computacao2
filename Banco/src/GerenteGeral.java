public class GerenteGeral extends Gerente {

    public static int totalDeGerentesCriados = 0;

    public GerenteGeral(String nome, long cpf, int matricula) {
        super(nome, cpf, matricula);
    }

    public void oferecerEmprestimo(Correntista correntista) {

    }


    @Override
    public void encerrarConta(Conta conta) throws ContaNaoGerenciadaException, SaldoInsuficienteException,
            SenhaInvalidaException {
        gerenciarConta(conta);
        super.encerrarConta(conta);

    }
}





















/**
 *
 * Customização do método encerrar conta desviando para o método da superclasse.
 */