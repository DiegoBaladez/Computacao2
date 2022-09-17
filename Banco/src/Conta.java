import java.util.ArrayList;

public  class Conta {
    private int numero;

    private Correntista correntista;

    protected float saldoEmReais = 0;

    private ArrayList<String> transacoes;

    private Gerente gerente;

    private boolean isativa;

    public static final float SALDO_INICIAL_DA_CONTA = 10; //constante

    private static int quantidadeDeTransacoesDeTodasAsContas = 0;

    private int senha;

    private int contSenhasInvalidasConsecutivas = 0;


    public Conta(int numeroDaConta, Correntista correntista) {
        this.correntista = correntista;
        this.numero = numeroDaConta;
        this.saldoEmReais = SALDO_INICIAL_DA_CONTA;
        this.transacoes = new ArrayList<>();
        this.transacoes.add(String.format("Conta criada com o saldo de R$%.2f", this.saldoEmReais));
        this.isativa = true;

        if(isContaCorrente()){
            correntista.adicionarConta(this);
        }


    }

    public int getSenha()
    {
        return this.senha;
    }

    public void setSenha(int senha){
        if(senha > 9999999)
        {
            return; //TodO Lançar exceção
        }

        if(senha == this.senha)
        {
            return; //ToDo não pode repetir a senha
        }
        this.senha = senha;

    }

    protected boolean isContaCorrente(){
        return true;
    }

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



        String registroTransacao = "Recebido deposito em dinheiro: " + valor;

        this.transacoes.add(registroTransacao);
        quantidadeDeTransacoesDeTodasAsContas++;
    }

    public long getCpfDoCorrentista(Correntista correntista) {

        return 0;
        // ToDo IMPLEMENTE ME!!!
    }

    public Correntista getCorrentista(){
        return this.correntista;
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

    protected void efetuarTransferencia(Conta contaRecebe, float valorTranserencia) {

        if (valorTranserencia <= 0) {
            return; //
        }
        if (this.saldoEmReais < valorTranserencia) {
            return; //
        }
        this.saldoEmReais -= valorTranserencia;
        contaRecebe.saldoEmReais += valorTranserencia;

        String mensagemDebito = "Foram debitados: " + valorTranserencia + " da sua conta de numero" +
                getNumeroDaConta() + "via transferência bancária";

        this.registrarTransacao(mensagemDebito);
    }

    /**
     *
     * O método precisa saber que ele pode lançar uma exceção. Logo, ao lado direito dos
     * parametros, ficam todas as exceções que ele sabe lançar.
     */
    public void sacar(float valorSacado, int senha) throws SenhaInvalidaException, SaldoInsuficienteException
            , ContaInativaException
    {
        if(senha != this.senha)
        {
            this.contSenhasInvalidasConsecutivas++;

            if(this.contSenhasInvalidasConsecutivas == 5)
            {
                this.isativa = false; //bloqueia a conta
            }
            throw new SenhaInvalidaException(this.contSenhasInvalidasConsecutivas);

        }
        /**
         * Cria uma exceção dentro do método.
         * Como todos os objetos, posso colocar atributos e métodos
         * Então eu coloco o valor faltante no atributo correspondente de exceção
         * e lanço a exceção que eu criei dentro do método
         *
         */
        if (valorSacado > this.saldoEmReais) {
            SaldoInsuficienteException excecao = new SaldoInsuficienteException();
            excecao.setSaldoFaltante(valorSacado - this.saldoEmReais);
            throw excecao;
        }
        if (valorSacado <= 0) {
            return;
        }

        if(!this.isativa)
        {
            throw new ContaInativaException();
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

    public void encerrar(Conta conta) {
        if (this.saldoEmReais < 0) {

        }

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
 *
 *
 * Criando uma excessão:
 * 1) Escolha se será uma runtime ou checked
 * 2) O método que irá chamar a exceção, precisara usar o Throws nomeDaExceçãoException
 * 3) O nomeDaExceçãoException será uma classe que herdará da classe Exception
 * 4) nomeDaExceçãoException é uma classe como qualquer outra. Posso ter atributos e métodos
 * e lá onde irei começar a tratar a exceção.
 * 5) Try e Catch - Quando o chamador usar o método com exceção, temos que usar o Try- Catch e
 * coloca no Catch todas as exceções que podem ocorrer com aquele método. Assim eu consigo
 * capturar a exceção e dar o seu tratamento devido.
 *
 *
 * Exceções - comunicar ao chamador do método que algo não funcionou como se esperava (diferente)
 * e qual é a razão disso. O chamador tem que ter ciência que tal exceção pode ser chamada
 * O chamador do método que recebe uma exceção precisa trata-la ou passar a
 * exceção para o chamador acima.
 *
 * Exceções são tratadas ou passadas para cima
 * Toda exceção tem que ser tratada individualmente
 *
 * Não necessariamente precisam declr
 *
 * Existem 2 tipos de exceções:
 * 1)Checked exceptions - são as exceções que herdam da classe Exception
 *  --- As checked exceptions precisam ser declaradas na assinatura do método
 * 2)Runtime exceptions - herdam da RuntimeException
 *  --- As Runtime, não precisam ser declaradas na assinatura do método
 *
 *  Pq criar uma ou outra:
 *
 *  As checked eexceptions são exceções que vão acontecer(são do bem). O chamador do método precisa
 *  estar preparado pra recebe-la!É uma exceção esperada! .Não é um bug do sistema. ex: login errado,
 *  ou mandar uma impressora imprimir e não ter papel suficiente. Falhas na redes.
 *  Essas checked dizem ao chamador que ele precisa se preprar para cada exceção que acontecer que sabemos
 *  que vai acontecer! As exceções são pré vistas e VÃO ACONTECER!!! O sistema foi pensado para que elas ocorram
 *
 *  Pq nao quero declarar as runtimesExceptions:
 *
 *  Runtime Exceptions (Ex: NullPointException)
 *  São exceções que ocorrem quando o código "quebra"
 *  São exceções que NÃO deveriam acontecer. Se ocorrem, é pq existe um BUG.
 *  O chamador do método PRECISA verificar as entradas antes mesmo do método ser chamado para
 *  que não ocorram. São relacionadas com BUGS no código.
 *
 *  Exemplo: um método que espera receber um objeto não nulo e recebe um objeto nulo. Isso cria uma
 *  NullPointerException!
 *
 *  Resumão:
 *  1) Quando for lançar uma exceção, ela deve ser esperada ou é uma situação ao qual não deve acontecer jamais
 *  2)
 */


/**
 * Melhorias: tentar colocar um setter para a conta no arraylist de contas
 */