public class TestesRapidos {
    public static void main(String[] args) throws JogadorCadastradoException, UsuarioNaoExistenteException,
            SenhaInvalidaException {

        Jogador diego = new Jogador("Gagdau",123);
        JogoOnline partida1 = new JogoOnline();

        partida1.cadastrarJogador("Gagdau",123);

        partida1.login("Gagdau",123);

        System.out.println(diego.isOnline());

    }
}
