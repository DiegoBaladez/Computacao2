package produto;

import controle.Dimensoes;

import java.awt.*;

public abstract class Produto {



    private final long codigo; //ID único

    private String descricao;

    private Image imagem;

    private float precoSugerido;

    private int pesoEmGramas;

    private Dimensoes dimensoes;

    public Produto(long codigo, String descricao, Dimensoes dimensoes){

        this.dimensoes = dimensoes;
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoSugerido = getPrecoDefault();
    }

    public long getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public float getPrecoSugerido() {
        return precoSugerido;
    }

    public void setPrecoSugerido(float precoSugerido) {
        this.precoSugerido = precoSugerido;
    }

    protected abstract float getPrecoDefault();

    public int getPesoEmGramas() {
        return pesoEmGramas;
    }

    public void setPesoEmGramas(int pesoEmGramas) {
        this.pesoEmGramas = pesoEmGramas;
    }
}
