import java.util.ArrayList;
import java.util.Date;

/**
 * --Para que uma classe herde de outra, utiliza o extends e do nome da classe que será a "mãe"
 * -- Uma classe não pode herdar de mais de uma classe
 * -- Por padrão, toda classe herda da classe Object
 * -- classes que herdam, herdam todos os atributos e métodos
 * --Visibilidade dos atributos herdados ----> se forem private, nao podem ser mexidos na classe que herda
 *  para muda-los, preciso usar métodos que estejam na classe 'mãe'
 *
 *  Dispostos na ordem de mais acessivel até o menos acessivel.
 *  private - acessivel apenas de dentro da propria classe
 *  sem modificador de acesso - (default) disponível de dentro do mesmo package
 *  protected - disponível para as subclasses e todos que estejam no mesmo package
 *  public - acessivel de qualquer lugar do seu código
 *
 *  Quando não declaramos um construtor na classe, o compilador utiliza um construtor default que nao
 *  recebe parâmetro nenhum e somente chama o construtor da super classe. O construtor da classe object
 *  faz apenas a alocação de memória para aquela classe.
 *
 *  No exemplo abaixo, a superclasse é a PessoaFisica
 *
 *  super() -------> Construtor da superclasse!
 *
 *  Atenção na hora de fazer o construtor de uma classe que extends uma superclasse.
 *  Se a superclasse possuir parametros a serem recebidos por seu construtor, devo informar
 *  esses na classe filha!
 *  Devo também colocar na primeira linha o super(parametro 01, parametroX);
 *  Se eu nao escrever o super(), o compilador colocará a chamada ao construtor da superclasse
 *
 *  Classes filhas de superclasses terão seus mesmos métodos. Porém, uma casse filha pode querer
 *  fazer um método porem de um jeito diferente. para isso, é necessário fazer um override do método
 */

public class Gerente extends PessoaFisica {

    private final long numeroDaMatricula;
    private Date dataDeAdmissao;
    private ArrayList<Conta> contasGerenciadas;
    private Date dataDeNascimento;

    public Gerente(String nome, long cpf, long numeroDaMatricula){
        super(cpf,nome);
        this.numeroDaMatricula = numeroDaMatricula;
        this.contasGerenciadas = new ArrayList<>();
        this.dataDeAdmissao = new Date();
    }

    public long getNumeroDaMatricula() {
        return numeroDaMatricula;
    }

    public Date getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void setDataDeAdmissao(Date dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }

    public ArrayList<Conta> getContasGerenciadas() {
        return contasGerenciadas;
    }

    public void setContasGerenciadas(ArrayList<Conta> contasGerenciadas) {
        this.contasGerenciadas = contasGerenciadas;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    @Override
    public String toString() {
        return String.format("Gerente %s (matrícula %d)",
                getNome(), this.numeroDaMatricula);
    }

    public void gerenciarConta(Conta conta){
        this.contasGerenciadas.add(conta);
        conta.setGerente(this);
    }

    public void deixarDeGerenciarConta(Conta conta){
        this.contasGerenciadas.remove(conta);
    }

    public boolean ehGerenteDaConta(Conta conta){
        return this.contasGerenciadas.contains(conta);
    }

    public void encerrarConta(Conta conta) throws ContaNaoGerenciadaException,
            SenhaInvalidaException, SaldoInsuficienteException
    {
        if(!this.contasGerenciadas.contains(conta)){
            throw new ContaNaoGerenciadaException();
        }
        try {
            conta.sacar(conta.getSaldoEmReais(), conta.getSenha());
            conta.encerrar(conta);
        } catch (ContaInativaException e){
            return; //conta inativa
        } catch (Exception e){
            //nao faz nada
        }

        deixarDeGerenciarConta(conta);
    }
}
