import java.util.ArrayList;

/**
 * Singleton - Técnica usada para fazer um construtor PRIVATE que cria uma única instância de uma classe
 * Ex: public static Correntista singleton = new Correntista();
 *
 * private Correntista(){
 *     construtor privado que impedirá que seja instanciado
 * }
 * Para dar acesso a essa instancia, seria através de um método publico
 *
 * public static Correntista getInstancia(){
 *     return singleton;
 * }
 * ----------------------------------------------------------------------------------------------
 *
 */

public class Correntista extends PessoaFisica {
    private static final int LIMITE_DEFAULT = 100;
    private Gerente gerente;
    private float limiteDoChequeEspecial;
    private ArrayList<Conta> contasCorrentes;
    private ArrayList<ContaInvestimento> investimento;

    public Correntista(long cpf, String nome) {
        super(cpf,nome);
        contasCorrentes = new ArrayList<>();
        investimento = new ArrayList<>();
        this.limiteDoChequeEspecial = LIMITE_DEFAULT;

    }

    public float getLimiteChegueEspecial(){
        return this.limiteDoChequeEspecial;
    }

    public void setLimiteDoChequeEspecial(float limiteDoChequeEspecial){
        this.limiteDoChequeEspecial = limiteDoChequeEspecial;
    }

    public float getTotalInvestido(){
        return 0; // ToDo IMPLEMENT ME!!
    }

    public boolean verificaSeTemConta()
    {
        return this.contasCorrentes.isEmpty();
    }

    public void adicionarConta(Conta conta)
    {
        contasCorrentes.add(conta);
    }

    public void adicionarContaInvestimento(ContaInvestimento contaInvestimento)
    {
        investimento.add(contaInvestimento);
    }

}

