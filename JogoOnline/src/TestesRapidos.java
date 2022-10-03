import java.util.ArrayList;

public class TestesRapidos {
    public static void main(String[] args) throws JogadorCadastradoException, UsuarioNaoExistenteException,
            SenhaInvalidaException {

        final int senhaPadrao = 123;
        JogoOnline jogoOnline = new JogoOnline();

        Jogador jogador01 = new Jogador("A",senhaPadrao);
        Jogador jogador02 = new Jogador("B",senhaPadrao);
        Jogador jogador04 = new Jogador("C", senhaPadrao);

        jogoOnline.cadastrarJogador("A",senhaPadrao);
        jogoOnline.cadastrarJogador("C",senhaPadrao);
        jogoOnline.cadastrarJogador("B",senhaPadrao);

       jogoOnline.obterJogadoresEmOrdemAlfabetica().forEach(System.out::println);






    }
}
