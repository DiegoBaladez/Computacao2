import java.util.ArrayList;

/**
 * 2) A classe Jogador terá como atributos, entre outros que você queira,
 *    username (identificador único do usuário), senha, pontuação (score acumulado),
 *    e histórico de partidas jogadas (dica: ArrayList<Partida>). Um jogador ao ser
 *    criado começa com 1000 pontos.
 *    (Dica: no construtor da classe Jogador você vai querer exatamente
 *    que sejam informados o username e a senha.)
 */
public class Jogador {
    private String username;
    private int senha;
    private  int pontuacaoAcumulada = 1000;
    private ArrayList<Partida> historico = new ArrayList<Partida>();
    private boolean isJogando;
    private boolean isOnline;

    public Jogador(String username, int senha){
        this.username = username;
        this.senha = senha;
    }

    public void setOnline(){
        this.isJogando = true;
    }

    public void setOffline(){
        this.isJogando = false;
    }

    public void setJogando(){
        this.isJogando = true;
    }

    public void setNaoJogando(){
        this.isJogando = false;
    }
}
