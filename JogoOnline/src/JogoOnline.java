import java.util.*;

public class JogoOnline {

    private Random random;

    private HashMap<String, Jogador> jogadorByUsername;

    public JogoOnline()
    {
        this.random = new Random();
        this.jogadorByUsername = new HashMap<>();
    }
    //Melhora o Cadastro do Jogador. Aqui eu passo um objeto Jogador.
    //O método deve CRIAR o objeto e não receber um


    public Jogador cadastrarJogador(String username, int senha) throws JogadorCadastradoException {

        if(encontrarJogador(username) != null )
        {
            throw new JogadorCadastradoException();
        }

        Jogador novoJogador = new Jogador(username, senha);
        this.jogadorByUsername.put(username, novoJogador);

        return novoJogador;
    }

    //se der ruim, trocar por public mas acho que é isso mesmo
    public Jogador encontrarJogador(String username)
    {
        return this.jogadorByUsername.get(username);
    }

    public void login(String username, int senha) throws UsuarioNaoExistenteException, SenhaInvalidaException
    {
        if(!verificaSeJogadorEstaCadastrado(username))
        {
            throw new UsuarioNaoExistenteException();
        }

        if(encontrarJogador(username).getSenha() != senha)
        {
            throw new SenhaInvalidaException();
        }

        if(encontrarJogador(username) != null)
        {
            encontrarJogador(username).setOnline();
        }



    }

    private boolean verificaSeJogadorEstaCadastrado(String username)
    {
        return this.jogadorByUsername.containsKey(username);
    }

    public void logOut(Jogador jogador)
    {
        if (!jogador.isOnline()){
            throw new RuntimeException("Jogador já se encontra offline");
        }

        if(jogador != null)
        {
            jogador.setOffline();
        }
    }

    public Partida iniciarPartida(Jogador jogador01, Jogador jogador02) {
        if(jogador01  != null && jogador02 != null)
        {
            if (jogador01.isOnline() && jogador02.isOnline() &&
                    !jogador01.isJogando() && !jogador02.isJogando()) {

                Partida novaPartida = new Partida(jogador01, jogador02);

                jogador01.adicionarPartida(novaPartida);
                jogador02.adicionarPartida(novaPartida);

                novaPartida.setResultado(Partida.PARTIDA_EM_ANDAMENTO);

                return novaPartida;
            }
        }

        return null; //TODO lançar uma exceção
    }

    public Partida iniciarPartida(String username1, String username2) {
        if(jogadorByUsername.containsKey(username1) && jogadorByUsername.containsKey(username2))
        {
            if (jogadorByUsername.get(username1).isOnline() && jogadorByUsername.get(username2).isOnline() &&
                    !jogadorByUsername.get(username1).isJogando() && !jogadorByUsername.get(username2).isJogando()) {

                Partida novaPartida = new Partida(jogadorByUsername.get(username1), jogadorByUsername.get(username2));

                jogadorByUsername.get(username1).adicionarPartida(novaPartida);
                jogadorByUsername.get(username2).adicionarPartida(novaPartida);

                novaPartida.setResultado(Partida.PARTIDA_EM_ANDAMENTO);

                return novaPartida;
            }
        }

        return null; //TODO lançar uma exceção
    }


    //ToDo Não estou verificando se a partida está em andamento. Verificar!! E lançar exceção
    public void encerrarPartida(Partida partida, int resultado) {

        if (resultado < 0 || resultado > 2) {
            throw new IllegalArgumentException("Resultado Inválido"); // lançar exceção
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
//    public void escolherAdversario() {
//        Random numeroAleatorio = new Random();
//        int posicaoInicial = numeroAleatorio.nextInt(this.jogadorByUsername.size());
//
//        int posicaoCorrente = posicaoInicial;
//
//        do{
//            Jogador adversario = this.jogadores.get(posicaoCorrente);
//            if(adversario.isOnline() &&
//            !adversario.isJogando() &&
//            !adversario.equals(this)){
//                return adversario;
//            }
//            posicaoCorrente++;
//            if(posicaoCorrente == this.jogadores.size)
//        }
//    }

    public Jogador escolherAdversario() {

        Collection<Jogador> valuesSet = jogadorByUsername.values();

        ArrayList<Jogador> arrayDosJogadores = new ArrayList<>(valuesSet);

        Collections.shuffle(arrayDosJogadores);

        for (Jogador jogador : arrayDosJogadores)
        {
            if(!jogador.isJogando() && jogador.isOnline())
            {
                return jogador;
            }

        }
        return null;
    }
}

/**
 * HashMap: Mapeamento entre dois objetos. Sempre que acrecentarmos alguma coisa no
 * mapa, informamos duas coisas: a chave(pode ser qualquer coisa) e o valor(qualquer
 * coisa que eu queira)
 * Ex: Loja virtual. Preços e produtos. chave -> Objeto produto e valor o preço do produto
 * Na hora de ler os dados, eu apresento a chave e ela me retorna o valor associado a essa chave
 * Tempo o(1).
 *Sintaxe HashMap<TipoDaChave,TipoDoValor> valorByChave;
 *
 */

