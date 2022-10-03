package produto;

import controle.Dimensoes;

public class Livro extends Produto {

    private int numeroDePaginas;

    private String titulo;

//    private Image imagem;

    private int anoDePublicacao;

    private String autor;

    private String editora;

    private final static float PRECO_DEFAULT_POR_PAGINA = 0.01f;

    public Livro (long codigoISBN, String titulo, String autor,
                  int anoDePublicacao, String editora){

        super(codigoISBN, String.format("Livro %s, de %s publicado na" +
                "editora %s" ,titulo, autor, editora));

        this.titulo = titulo;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
        this.editora = editora;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(int anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    @Override
    protected float getPrecoDefault() {
        return PRECO_DEFAULT_POR_PAGINA * this.getNumeroDePaginas();
    }

    @Override
    public Dimensoes getDimensoes() {
        return null;
    }
}
