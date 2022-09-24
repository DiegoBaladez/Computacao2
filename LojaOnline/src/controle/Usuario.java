package controle;

public class Usuario {

    private final long cpf;

    private String nome;

    private String endereço;

    private long numeroCartaoDeCredito;

    public Usuario(long cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public long getNumeroCartaoDeCredito() {
        return numeroCartaoDeCredito;
    }

    public void setNumeroCartaoDeCredito(long numeroCartaoDeCredito) {
        this.numeroCartaoDeCredito = numeroCartaoDeCredito;
    }
}
