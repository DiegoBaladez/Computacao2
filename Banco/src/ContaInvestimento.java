public class ContaInvestimento extends Conta {

    private String tipoDeInvestimento;
    private float taxaDeJuros;

    public ContaInvestimento(int numeroDaConta, Correntista correntista, String tipoDeInvestimento, float taxaDeJuros)
    {

        if(correntista.verificaSeTemConta())
        {
            throw new RuntimeException("Correntista sem conta corrente!");
        }

        this.tipoDeInvestimento = tipoDeInvestimento;
        this.taxaDeJuros = taxaDeJuros;
        correntista.adicionarContaInvestimento(this);
    }

    public float retornaJuros(){
        return this.taxaDeJuros;
    }

    public float aplicarJuros()
    {
       float novoValorComJuros = (getSaldoEmReais() * this.retornaJuros() - getSaldoEmReais());
       this.receberDepositoEmDinheiro(novoValorComJuros);
       return getSaldoEmReais();
    }


    public void resgatarTotal(Conta conta)
    {
        efetuarTransferencia(conta, getSaldoEmReais());
    }




}
