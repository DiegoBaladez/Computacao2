import java.util.ArrayList;
import java.util.Objects;

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
    private ArrayList<Partida> historicoDePartidas = new ArrayList<Partida>();
    private boolean isJogando;
    private boolean isOnline;
    //ToDo add construtor que desvie para outro 'overload' que nao receba senha e use uma padrão

    public Jogador(String username, int senha){
        this.username = username;
        this.senha = senha;
        this.isJogando = false;
        this.isOnline = false;
        this.historicoDePartidas = new ArrayList<>();
    }

    public void setOnline()
    {
        this.isOnline = true;
    }

    public void setOffline(){
        this.isOnline = false;
    }

    public void setJogando(){
        this.isJogando = true;
    }

    public void setNaoJogando(){
        this.isJogando = false;
    }

    public boolean isJogando(){ return this.isJogando;}

    public boolean isOnline(){return this.isOnline;}

    public String getUsername() {return this.username;}

    public int getSenha() {return this.senha;}

    public void setSenha(int senha){this.senha = senha;}

    public int getPontuacaoAcumulada() {return this.pontuacaoAcumulada;}

    public void adicionarPartida(Partida partida){
        this.historicoDePartidas.add(partida);
    }

    public boolean temEssaPartida(Partida partida){
        return this.historicoDePartidas.contains(partida);
    }

    public void adicionaPontuação(int pontuacao){
        this.pontuacaoAcumulada += pontuacao;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return username.equals(jogador.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
