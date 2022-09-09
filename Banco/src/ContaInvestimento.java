public class ContaInvestimento extends Conta {

    private String tipoDeInvestimento;
    private float taxaDeJuros;

    public ContaInvestimento(int numeroDaConta, Correntista correntista, String tipoDeInvestimento, float taxaDeJuros)
    {
        super(numeroDaConta, correntista);

        if(correntista.verificaSeTemConta())
        {
            throw new RuntimeException("Correntista sem conta corrente!");
        }

        this.tipoDeInvestimento = tipoDeInvestimento;
        this.taxaDeJuros = taxaDeJuros;

        correntista.adicionarContaInvestimento(this);
    }

    @Override
    protected boolean isContaCorrente(){
        return false;
    }

    public float retornaJuros(){
        return this.taxaDeJuros;
    }

    public float aplicarJuros()
    {
       float novoValorComJuros = (getSaldoEmReais() * this.retornaJuros() - getSaldoEmReais());
       this.receberDepositoEmDinheiro(novoValorComJuros);
       return getSaldoEmReais();
       //this.saldoEmReais = *= (1 + taxaDeJuros);

    }

    public void resgatarTotal(Conta conta)
    {
        efetuarTransferencia(conta, getSaldoEmReais());
    }

}













/**
 * E se eu quisesse que a contaInvestimento nao herdasse o valor de 10 reais da conta
 * ----- Fazer com que Conta seja uma classe abstrata
 * ----- criar uma nova classe ContaCorrente que herde de Conta
 * ----- saldoEmReais = getSaldoInicialDefault()
 * ----  public abstract getSaldoInicialDefault(){}
 * ---- implementar o método em cada classe filha.
 *
 *
 * OBS: A RUNTIMEEXCEPTION TRAVA A APLICAÇÃO "dá uma tela azul"
 */