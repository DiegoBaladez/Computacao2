import java.util.Objects;

public class Partida {
    //ToDo criar variáveis para os resultados!
    public static final int PARTIDA_NAO_INICIADA = -2;
    public static final int PARTIDA_EM_ANDAMENTO = -1;


    private final Jogador jogador01;
    private final Jogador jogador02;
    private int resultado;
    /*

    -1 Partida em andamento
    0 empate
    1 jogador1 ganhou
    2 jogador 2 ganhou
    -2 Partida não iniciada
     */

    public Partida(Jogador jogador01, Jogador jogador02){
        this.jogador01 = jogador01;
        this.jogador02 = jogador02;
        this.resultado = PARTIDA_NAO_INICIADA;
    }

    public int getResultado(){
       return resultado;
    }

    public Jogador getJogador01(){
        return this.jogador01;
    }

    public Jogador getJogador02(){
        return this.jogador02;
    }

    public int setResultado(int x) {
       return this.resultado = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partida partida = (Partida) o;
        return Objects.equals(jogador01, partida.jogador01) && Objects.equals(jogador02, partida.jogador02);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jogador01, jogador02);
    }
}
