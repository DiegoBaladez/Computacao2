import java.util.Date;

public class PessoaFisica {

    private final long cpf;
    private String nome;
    private String endereco;
    private Date dataDeNascimento;

    public PessoaFisica(long cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = "Não informado";
    }

    public long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome.length() > 30){
            return; //ToDo throw exception
        }
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
}
