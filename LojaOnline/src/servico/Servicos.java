package servico;

import controle.Pessoa;
import controle.Vendavel;

public class Servicos implements Vendavel {

    private Pessoa profissionalResponsavel;
    private int duracaoEstimadaEmHoras;
    private float precoSugerido;
    private String descricao;
    private long codigo;

    public Servicos(long codigo, String descricao){

        this.codigo = codigo;
        this.descricao = descricao;

    }

    public int getDuracaoEstimadaEmHoras() {
        return duracaoEstimadaEmHoras;
    }

    public void setDuracaoEstimadaEmHoras(int duracaoEstimadaEmHoras) {
        this.duracaoEstimadaEmHoras = duracaoEstimadaEmHoras;
    }

    @Override
    public long getCodigo() {
        return this.codigo;
    }

    public float getPrecoSugerido() {
        return precoSugerido;
    }

    public void setPrecoSugerido(float precoSugerido) {
        this.precoSugerido = precoSugerido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
