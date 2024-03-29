import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JogoOnlineTest {

    Jogador jogador01;
    Jogador jogador02;
    JogoOnline jogoOnline;
    int senhaPadrao = 123;


    @Before
    public void setUp() throws JogadorCadastradoException {
        jogador01 = new Jogador("Diego", senhaPadrao);
        jogador02 = new Jogador("Thayná", senhaPadrao);

        jogoOnline = new JogoOnline();
        jogoOnline.cadastrarJogador("Diego",senhaPadrao);

    }
    //atenção aqui, tem alago de errado.
    @Test
    public void testarRankingComComparator() throws JogadorCadastradoException
    {
        Jogador jogador08 = new Jogador("Stefany5", senhaPadrao);
        Jogador jogador10 = new Jogador("baladez50",senhaPadrao);
        Jogador jogador11 = new Jogador("baladez60",senhaPadrao);

        jogoOnline.cadastrarJogador("Stefany5", senhaPadrao);
        jogoOnline.cadastrarJogador("baladez50", senhaPadrao);
        jogoOnline.cadastrarJogador("baladez60", senhaPadrao);

        jogoOnline.encontrarJogador("Diego").adicionaPontuação(1);
        jogoOnline.encontrarJogador("Stefany5").adicionaPontuação(5);
        jogoOnline.encontrarJogador("baladez50").adicionaPontuação(50);
        jogoOnline.encontrarJogador("baladez60").adicionaPontuação(60);

        ArrayList<Jogador> listaRankingEsperado = new ArrayList<>();
        listaRankingEsperado.add(jogador11);
        listaRankingEsperado.add(jogador10);
        listaRankingEsperado.add(jogador08);
        listaRankingEsperado.add(jogador01);

        assertEquals(listaRankingEsperado, jogoOnline.obterRanking());
    }

    @Test
    public void testarOrdenamentoLexicoGraficoComComparator() throws JogadorCadastradoException {

        Jogador jogador05 = new Jogador("Alberto", senhaPadrao);


        jogoOnline.cadastrarJogador("Alberto", senhaPadrao);
        jogoOnline.cadastrarJogador("Thayná", senhaPadrao);


        ArrayList<Jogador> listaEmOrdemAlfabetica = new ArrayList<>();
        listaEmOrdemAlfabetica.add(jogador05);
        listaEmOrdemAlfabetica.add(jogador01);
        listaEmOrdemAlfabetica.add(jogador02);

        assertEquals(listaEmOrdemAlfabetica, jogoOnline.obterJogadoresEmOrdemAlfabetica());

    }

    @Test (expected = JogadorCadastradoException.class)
    public void testarCadastrarJogadorComJogadorCadastrado() throws JogadorCadastradoException {

        jogoOnline.cadastrarJogador("Diego",senhaPadrao);
        jogoOnline.cadastrarJogador("Thayná", senhaPadrao);

    }

    @Test
    public void testarLoginComSenhaCorreta() throws UsuarioNaoExistenteException, SenhaInvalidaException {

        jogoOnline.login("Diego", senhaPadrao);
        assertTrue(jogoOnline.encontrarJogador("Diego").isOnline());

    }

    @Test (expected = SenhaInvalidaException.class)
    public void testarLoginComSenhaErrada() throws UsuarioNaoExistenteException, SenhaInvalidaException {

        jogoOnline.login("Diego", 777);
        assertTrue(jogoOnline.encontrarJogador("Diego").isOnline());

    }

    @Test (expected = UsuarioNaoExistenteException.class)
    public void testarLoginComJogadorNaoCadastrado() throws UsuarioNaoExistenteException, SenhaInvalidaException {
        jogoOnline.login("Thayná",senhaPadrao);

    }

    //Outra maneira de fazer esse teste
    @Test
    public void testarLoginComJogadorNaoCadastradoOutroModo() throws UsuarioNaoExistenteException, SenhaInvalidaException {
        try {
            jogoOnline.login("Thayná", senhaPadrao);
            fail("Uma UsarioNaoExistenteException deve ser lançada se o usuario nao estiver cadastrado");
        }catch (UsuarioNaoExistenteException e){
            //tratando localmente
        }
    }

    @Test
    public void testarLogoutComUsuarioOnline() throws UsuarioNaoExistenteException, SenhaInvalidaException {
        jogoOnline.login("Diego", senhaPadrao);
        assertTrue(jogoOnline.encontrarJogador("Diego").isOnline());

        Jogador jogadorTeste = jogoOnline.encontrarJogador("Diego");

        jogoOnline.logOut(jogadorTeste);
        assertFalse(jogador01.isOnline());

    }

    @Test (expected = RuntimeException.class)
    public void testarLogoutComUsuarioOffline() throws UsuarioNaoExistenteException
    {

        assertFalse(jogoOnline.encontrarJogador("Diego").isOnline());

        Jogador jogadorTeste = jogoOnline.encontrarJogador("Diego");

        jogoOnline.logOut(jogadorTeste);
        assertFalse(jogador01.isOnline());

    }

    @Test
    public void testarIniciarPartida() {
        jogador01.setOnline();
        jogador02.setOnline();
        jogador01.setNaoJogando();
        jogador02.setNaoJogando();

        Partida partidaCriada = jogoOnline.iniciarPartida(jogador01, jogador02);
        Partida partidaEsperada = new Partida(jogador01, jogador02);
        Partida nula = null;

        assertTrue(partidaCriada.equals(partidaEsperada));
        assertFalse(partidaCriada.equals(nula));
    }

    @Test
    public void testarAcharUmJogadorAdversario() throws JogadorCadastradoException, UsuarioNaoExistenteException, SenhaInvalidaException {
        Jogador jogador04 = new Jogador("Jorge", senhaPadrao);
        Jogador jogador05 = new Jogador("Mateus", senhaPadrao);
        Jogador jogador06 = new Jogador("Carlos", senhaPadrao);
        Jogador jogador07 = new Jogador("Mariana", senhaPadrao);
        Jogador jogador08 = new Jogador("Stefany", senhaPadrao);

        jogoOnline.cadastrarJogador("Jorge", senhaPadrao);
        jogoOnline.cadastrarJogador("Mateus", senhaPadrao);
        jogoOnline.cadastrarJogador("Carlos", senhaPadrao);
        jogoOnline.cadastrarJogador("Mariana", senhaPadrao);
        jogoOnline.cadastrarJogador("Stefany", senhaPadrao);

        jogoOnline.login("Carlos", senhaPadrao);
        jogoOnline.login("Stefany", senhaPadrao);

        Jogador jogadorAtual = jogoOnline.escolherAdversario();

        assertTrue(jogadorAtual.isOnline());
        assertFalse(jogadorAtual.isJogando());


    }


    //ToDo está retornando o mesmo jogador!!
    @Test
    public void testarAcharUmJogadorAdversarioRetornandoOMesmoJogador() throws JogadorCadastradoException,
            UsuarioNaoExistenteException, SenhaInvalidaException {
        Jogador jogador04 = new Jogador("Jorge", senhaPadrao);
        Jogador jogador05 = new Jogador("Mateus", senhaPadrao);
        Jogador jogador06 = new Jogador("Carlos", senhaPadrao);
        Jogador jogador07 = new Jogador("Mariana", senhaPadrao);
        Jogador jogador08 = new Jogador("Stefany", senhaPadrao);

        jogoOnline.cadastrarJogador("Jorge", senhaPadrao);
        jogoOnline.cadastrarJogador("Mateus", senhaPadrao);
        jogoOnline.cadastrarJogador("Carlos", senhaPadrao);
        jogoOnline.cadastrarJogador("Mariana", senhaPadrao);
        jogoOnline.cadastrarJogador("Stefany", senhaPadrao);

        jogoOnline.login("Carlos", senhaPadrao);


        Jogador jogadorEsperado = jogoOnline.escolherAdversario();

        assertEquals("Deve retornar o primeiro jogador a ser encontrado Online e Não jogando",
                jogador06, jogadorEsperado);

//        Jogador novoJogadorEsperado = jogoOnline.escolherAdversario();
//
//        assertEquals("Deve retornar o primeiro jogador a ser encontrado Online e Não jogando",
//                jogador08, novoJogadorEsperado);
    }

    //ToDo  testar quando não acha um jogador
    @Test
    public void testarAcharUmJogadorAdversarioRetornandoNenhumJogador() throws JogadorCadastradoException,
            UsuarioNaoExistenteException, SenhaInvalidaException {
        Jogador jogador04 = new Jogador("Jorge", senhaPadrao);
        Jogador jogador05 = new Jogador("Mateus", senhaPadrao);
        Jogador jogador06 = new Jogador("Carlos", senhaPadrao);
        Jogador jogador07 = new Jogador("Mariana", senhaPadrao);
        Jogador jogador08 = new Jogador("Stefany", senhaPadrao);

        jogoOnline.cadastrarJogador("Jorge", senhaPadrao);
        jogoOnline.cadastrarJogador("Mateus", senhaPadrao);
        jogoOnline.cadastrarJogador("Carlos", senhaPadrao);
        jogoOnline.cadastrarJogador("Mariana", senhaPadrao);
        jogoOnline.cadastrarJogador("Stefany", senhaPadrao);

        jogoOnline.login("Carlos", senhaPadrao);


        Jogador jogadorEsperado = jogoOnline.escolherAdversario();

        assertEquals("Deve retornar o primeiro jogador a ser encontrado Online e Não jogando",
                jogador06, jogadorEsperado);

    }

    @Test
    public void testarTerminarPartida() {
        JogoOnline jogoOnline = new JogoOnline();

        Partida partida = new Partida(jogador01, jogador02);

        jogoOnline.encerrarPartida(partida, 1);

        assertEquals(1, partida.getResultado());

        assertTrue("a partida deve estar aqui", jogador01.temEssaPartida(partida));
        assertTrue("a partida deve estar aqui",jogador02.temEssaPartida(partida));

        assertFalse(jogador01.isOnline());
        assertFalse(jogador02.isOnline());

        assertEquals(1001, jogador01.getPontuacaoAcumulada());
        assertEquals(1000, jogador02.getPontuacaoAcumulada());
    }

    @Test
    public void testarTerminarPartidaComEmpate() {
        JogoOnline jogoOnline = new JogoOnline();

        Partida partida = new Partida(jogador01, jogador02);

        jogoOnline.encerrarPartida(partida, 0);

        assertEquals(0, partida.getResultado());

        assertTrue("a partida deve estar aqui", jogador01.temEssaPartida(partida));
        assertTrue("a partida deve estar aqui",jogador02.temEssaPartida(partida));

        assertFalse(jogador01.isOnline());
        assertFalse(jogador02.isOnline());

        assertEquals(1000, jogador01.getPontuacaoAcumulada());
        assertEquals(1000, jogador02.getPontuacaoAcumulada());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testarTerminarPartidaComValorErrado() {
        JogoOnline jogoOnline = new JogoOnline();

        Partida partida = new Partida(jogador01, jogador02);

        jogoOnline.encerrarPartida(partida, 15);
    }
}
