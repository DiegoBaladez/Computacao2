import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

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
    private Gerente gerente;
    private static final int LIMITE_DEFAULT = 100;
    private float limiteDoChequeEspecial;
    private ArrayList<ContaCorrente> contasCorrentes;
    private ArrayList<AplicacaoFinanceira> investimento;

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

}

