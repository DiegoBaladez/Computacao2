import java.util.ArrayList;
import java.util.Objects;

public class JogoOnline {

    private ArrayList<Jogador> jogadoresCadastrados = new ArrayList<Jogador>();


    public JogoOnline() {this.jogadoresCadastrados = new ArrayList<>();}
    //Melhora o Cadastro do Jogador. Aqui eu passo um objeto Jogador.
    //O método deve CRIAR o objeto e não receber um
    //ToDo cadastrarJogador deve Criar um objeto e não receber um.
    public boolean cadastrarJogador(Jogador jogador, String username, int senha) {

        for (int i = 0; i < jogadoresCadastrados.size(); i++) {
            if (jogadoresCadastrados.get(i).equals(jogador)) {
                System.out.println("Nome já utilizado");
                return false;
            }
        }
        jogadoresCadastrados.add(jogador);
        return true;
    }

    public Jogador consultaCadastro(int posicao) {
        return this.jogadoresCadastrados.get(posicao);
    }
    //ToDo!!!!
    public void econtrarJogador(String username){}
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
    public Jogador escolherAdversario() {
        for (int i = 0; i < jogadoresCadastrados.size(); i++) {
            if (jogadoresCadastrados.get(i).isOnline() == true
                    && jogadoresCadastrados.get(i).isJogando() == false) {
                return jogadoresCadastrados.get(i);
            }
        }
        return null;
    }


}



