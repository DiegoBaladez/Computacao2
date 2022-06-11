import java.util.ArrayList;

/**
 * A classe é um modelo para um objeto. O objeto é a instância de uma classe.
 * Cada isntância terá as seus próprios dados
 * Classes tem atributos e métodos.
 * -- Encapsulamento - ténica que permite que apenas o objeto possa mudar seus atributos usando
 * métodos. Esse encapsulamento é garantido por modificadores de acesso.
 * Sem o encapsulamento, a responsabilidade da modificação dos atributos de
 * uma classe fica com o utilizador daquela classe.
 * -- Atributos são quase sempre como PRIVATE!
 * -- Métodos são geralmente Públicos a não ser que sejam métodos auxiliares.
 * -- Get é usado em métodos que retornam valores para visualização.
 * <p>
 * --NullPointerException -É uma runTimeException pois ocorre
 * em tempo de execução- pode acontecer quando eu crio uma variável(ou objeto)
 * que não possui memória alocada.
 * <p>
 * --Tipos primitivos não precisam ser incializados.
 * --Objetos PRECISAM que se aloque memória para eles.
 * --O valor default de um objeto é NULL !!
 * <p>
 * Método Construtor - É um método rodado apenas uma vez ao se instanciar o objeto.
 * Ele irá possuir o mesmo nome que a classe (literalmente) e receberá parâmetros
 * para inicializar o objeto.
 * <p>
 * o .this quebra a ambiguidade ao passar um parametro com o mesmo nome que o
 * atributo do objeto.
 * <p>
 * Não se retorna arrays! - PQ ELES RETORNAM UM ENDEREÇO DE MEMÓRIA QUE PODE SER
 * MUDADO!!!!!!!
 * <p>
 * Tipos primitivos retornam o seu valor dentro da gavetinha e nao o End de memoria
 * ---------------------------------------------------------------------------------------------------------------------
 * <p>
 * Atributos e métodos Static:
 * Atributo estático: todos os objetos daquela classe terão o mesmo atributo.
 * Eles ficaram em itálico na IDE. Atributos estáticos podem ser acessados via nome da Classe!
 * Normalmente atributos estáticos fazem o papel de constantes e usam o modificador Final.
 * O final é utilizado ou nos atributos da própria classe ou usando o construtor da classe
 * Atributos Final, são escritos com LETRAS MAIÚSCULAS separadas por UNDERLINE.
 * <p>
 * Métodos Estáticos - Se o método não for estático, é necessário uma instância da classe
 * para poder chama-lo. Ao usar um método estático, eu consigo retornar todos os atributos
 * de todos os objetos daquela classe com um método.
 * -- Regra geral: Sempre que dentro do meu método eu operar apenas com coisas estáticas da
 * classe, marque o método como estático. Qaundo nao usa nada que seja atributo não estático
 * se ele usa apenas atributos estáticos.
 * Método estático não usa nada não estático. Estático usa coisas estáticas!
 * Atributos estáticos são compartilhados pelas instancias e não pertencem a nenhuma delas.Eles
 * ficam na região de memória da classe e são visiveis pelas todas instancias da classe.Se
 * forem public, serão visiveis inclusive por coisas fora da classe.
 * Ex: Math.sqrt() - A classe Math usa um método estático sqrt().
 * <p>
 * String.format() - Método da classe String que possibilita formatar o texto com máscaras (%). Cada
 * valor que preencherá uma máscara deverá estar separado por vírgulas.
 */
public class Conta {
    private int numero;
    private Correntista correntista;
    private float saldoEmReais;
    private ArrayList<String> transacoes;
    private Gerente gerente;
    private boolean isativa;
    public static final float SALDO_INICIAL_DA_CONTA = 10; //constante
    private static int quantidadeDeTransacoesDeTodasAsContas = 0;


    public Conta(int numeroDaConta, Correntista correntista) {
        this.correntista = correntista;
        this.numero = numeroDaConta;
        this.saldoEmReais = SALDO_INICIAL_DA_CONTA;
        this.transacoes = new ArrayList<>();
        this.transacoes.add(String.format("Conta criada com o saldo de R$%.2f", this.saldoEmReais));
        this.isativa = true;
        correntista.adicionarConta(this);
    }

    protected Conta (){}


    public float getSaldoEmReais() {
        return this.saldoEmReais;
    }

    public void getSaldoEmReais(float novoSaldo) {
        this.saldoEmReais = novoSaldo;
    }

    //    public List<String> getTransacoes(){
//        return Collections.unmodifiableList(transacoes);
//    }
    public String getExtrato() {
        String resultado = "";
        for (int i = 0; i < this.transacoes.size(); i++) {
            resultado += this.transacoes.get(i) + "\n";
        }
        return resultado;
    }

    public void receberDepositoEmDinheiro(float valor) {
        if (valor <= 0) {

            return;
        }

        this.saldoEmReais += valor;

        //Date agora = new Date(); // data de agora formatada

        String registroTransacao = "Recebido deposito em dinheiro: " + valor;

        this.transacoes.add(registroTransacao);
        quantidadeDeTransacoesDeTodasAsContas++;
    }

    public long getCpfDoCorrentista(Correntista correntista) {

        return 0;
        // ToDo IMPLEMENTE ME!!!
    }

    /**
     * Retorna a quantidade total de transações do banco, ou seja, de todas as
     * contas correntes que já foram criadas.
     *
     * @return o total de transações
     */
    public static int getQuantidadeDeTransacoesDeTodasAsContas() {
        return quantidadeDeTransacoesDeTodasAsContas;
    }

    public long getCpfDoCorrentista() {
        return this.correntista.getCpf();
    }

    public int getNumeroDaConta() {
        return this.numero;
    }

    public void efetuarTransferencia(Conta contaRecebe, float valorTranserencia) {
        //Está recebendo dois parametros (ContaCorrente). Substituir pelo .this.
        if (valorTranserencia <= 0) {
            return; // ToDo Throw exception
        }
        if (this.saldoEmReais < valorTranserencia) {
            return; // ToDo Throw exception
        }
        this.saldoEmReais -= valorTranserencia;
        contaRecebe.saldoEmReais += valorTranserencia;

        String mensagemDebito = "Foram debitados: " + valorTranserencia + " da sua conta de numero" +
                getNumeroDaConta() + "via transferência bancária";

        this.registrarTransacao(mensagemDebito);
    }

    public void sacar(float valorSacado) {
        if (valorSacado > this.saldoEmReais) {
            return; // ToDo Throw exception
        }
        if (valorSacado <= 0) {
            return;
        }
        this.saldoEmReais -= valorSacado;

        String registroTransacao = "Efetuado saque em dinheiro de: R$" + valorSacado;

        this.registrarTransacao(registroTransacao);
    }

    private void registrarTransacao(String mensagem) {
//        String dataAtual = obterDataAtualAsString();
        this.transacoes.add(mensagem);
        quantidadeDeTransacoesDeTodasAsContas++;


    }
//    private String obterDataAtualAsString(){
//        return String.format("%s",new Date());
//    }

    public void encerrar() {
        if (this.saldoEmReais < 0) {
        } //ToDo lançar exceção - Não deixa encerrar

        this.isativa = false;
    }

    public boolean isIsativa() {
        return this.isativa;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        if (this.gerente != null) {
            this.gerente.deixarDeGerenciarConta(this);
        }
        this.gerente = gerente;
    }


}

/**
 * Melhorias:
 */