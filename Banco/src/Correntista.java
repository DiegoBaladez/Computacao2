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

public class Correntista {

    private final long cpf;
    private String nome;

    public Correntista(long cpfDoCorrentista, String nomeDoCorrentista) {
        this.cpf = cpfDoCorrentista;
        this.nome = nomeDoCorrentista;

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public long getCpf() {
        return this.cpf;
    }

}

