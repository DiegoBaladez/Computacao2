import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class JogoOnline {

    //private ArrayList<Jogador> jogadoresCadastrados = new ArrayList<Jogador>();
    private HashMap<String, Jogador> jogadorByUsername;

    public JogoOnline()
    {
        //this.jogadoresCadastrados = new ArrayList<>();
        this.jogadorByUsername = new HashMap<>();
    }
    //Melhora o Cadastro do Jogador. Aqui eu passo um objeto Jogador.
    //O método deve CRIAR o objeto e não receber um
    //ToDo cadastrarJogador deve Criar um objeto e não receber um.

    public Jogador cadastrarJogador(String username, int senha) {
        if(encontrarJogador(username) != null )
        {
            return null;
        }

        Jogador novoJogador = new Jogador(username, senha);
        //novoJogador.setSenha(senha);
        this.jogadorByUsername.put(username, novoJogador);
    }

    public Jogador encontrarJogador(String username) {
        return this.jogadorByUsername.get(username);
    }
    //ToDo!!!!
//    public Jogador econtrarJogador(String username)
//    {
//        return this.jogadorByUsername.get(username);
//    }


    //ToDo Login e logout repetindo muito código. Substituir por um método que faça isso
    public void login(String username, int senha) {
        for (int i = 0; i < jogadoresCadastrados.size(); i++) {
            if (jogadoresCadastrados.get(i).getUsername() == username &&
                    jogadoresCadastrados.get(i).getSenha() == senha) {

                if (jogadoresCadastrados.get(i).isOnline() == false) {

                    jogadoresCadastrados.get(i).setOnline();
                } else {
                    System.out.println("Jogador já está online");
                }


            }
        }

    }

    public void logOut(String username, int senha) {
        for (int i = 0; i < jogadoresCadastrados.size(); i++) {
            if (jogadoresCadastrados.get(i).getUsername() == username &&
                    jogadoresCadastrados.get(i).getSenha() == senha) {

                if (jogadoresCadastrados.get(i).isOnline() == true) {

                    jogadoresCadastrados.get(i).setOffline();
                } else {
                    System.out.println("Jogador já está offline");
                }


            }
        }

    }
    //ToDo criar um overload para criar a partida com os usernames
    //ToDo Verificar se os jogadores são nulos!
    public Partida iniciarPartida(Jogador jogador01, Jogador jogador02) {
        if (jogador01.isOnline() && jogador02.isOnline() &&
                !jogador01.isJogando() && !jogador02.isJogando()) {

            Partida novaPartida = new Partida(jogador01, jogador02);

            jogador01.adicionarPartida(novaPartida);
            jogador02.adicionarPartida(novaPartida);

            novaPartida.setResultado(Partida.PARTIDA_EM_ANDAMENTO);

            return novaPartida;

        }

        return null;
    }

    //ToDo retirar os inteiros 0 e 2 e substitui-los por Variaveis!
    //ToDo verificar primeiro se a partida está em andamento
    public void encerrarPartida(Partida partida, int resultado) {
        if (resultado < 0 || resultado > 2) {
            return; // lançar excessão
        }

        Jogador jogador01 = partida.getJogador01();
        Jogador jogador02 = partida.getJogador02();

        partida.setResultado(resultado);

        jogador01.adicionarPartida(partida);
        jogador02.adicionarPartida(partida);

        jogador01.setOffline();
        jogador02.setOffline();

        switch (resultado) {
            case 0:
                break;
            case 1:
                jogador01.adicionaPontuação(1);
                break;
            case 2:
                jogador02.adicionaPontuação(1);
                break;
            default:
                break;
        }
    }

    //ToDo criar um método adicionarPartidaJogaada para setar a partida no historico e a pontuação

    //ToDo esse método deve ter como parâmetro um jogador solicitante que não deve ser retornado
    public Jogador escolherAdversario(Jogador solicitante) {
        int posicaoInicial = this.random.nextInt(this.jogadorByUsername.size());

        int posicaoCorrente = posicaoInicial;

        do{
            Jogador adversario = this.jogadores.get(posicaoCorrente);
            if(adversario.isOnline() &&
            !adversario.isJogando() &&
            !adversario.equals(solicitante)){
                return adversario;
            }
            posicaoCorrente++;
            if(posicaoCorrente == this.jogadores.size)
        }
    }
}

/**
 * HashMap: Mapeamento entre dois objetos. Sempre que acrecentamos algumas coisa no
 * mapa, informamos duas coisas: a chave(pode ser qualquer coisa) e o valor(qualquer
 * coisa que eu queira)
 * Ex: Loja virtual. Preços e produtos. chave -> Objeto produto e valor o preço do produto
 * Na hora de ler os dados, eu apresento a chave e ela me retorna o valor associado a essa chave
 * Tempo o(1).
 *Sintaxe HashMap<TipoDaChave,TipoDoValor> valorByChave;
 *
 */

