package controle;

import exception.PesoMaxExcedidoException;

public class Pessoa implements Transportador, Transportavel {

    private long cpf;
    private String nome;

    public Pessoa(long cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void transportar(Transportavel transportavel, String endereco) throws PesoMaxExcedidoException
    {

    }

    @Override
    public Dimensoes getDimensoes() {
        return null;
    }

    @Override
    public int getPesoEmGramas() {
        return 0;
    }
}
