package servico;

public class Servicos {

    private int duracaoEstimadaEmHoras;

    private float precoSugerido;

    private String descricao;

    public int getDuracaoEstimadaEmHoras() {
        return duracaoEstimadaEmHoras;
    }

    public void setDuracaoEstimadaEmHoras(int duracaoEstimadaEmHoras) {
        this.duracaoEstimadaEmHoras = duracaoEstimadaEmHoras;
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
