package produto;

import controle.Dimensoes;
import controle.Vendavel;

import java.awt.*;

public abstract class Vendavel implements controle.Vendavel {

    private final long codigo; //ID único
    private String descricao;
    private Image imagem;
    private float precoSugerido;
    private int pesoEmGramas;
    private Dimensoes dimensoes;

    public Vendavel(long codigo, String descricao){

        this.codigo = codigo;
        this.descricao = descricao;
        this.precoSugerido = getPrecoDefault();

    }

    public long getCodigo() {
        return codigo;
    } //da classe implementada!

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
    } //da classe implementada!

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
