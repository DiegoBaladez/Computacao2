public class GerenteGeral extends Gerente {

    public static int totalDeGerentesCriados = 0;

    public GerenteGeral(String nome, long cpf, int matricula) {
        super(nome, cpf, matricula);
    }

    public void oferecerEmprestimo(Correntista correntista) {

    }

    /**
     *
     * Customização do método encerrar conta desviando para o método da superclasse.
     */
    @Override
    public void encerrarConta(Conta conta){
        gerenciarConta(conta);
        super.encerrarConta(conta);

    }
}
