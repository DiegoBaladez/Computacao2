import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JogoOnlineTest {

    Jogador jogador01;
    Jogador jogador02;
    JogoOnline jogoOnline;


    @Before
    public void setUp() {
        jogador01 = new Jogador("Diego", 1234);
        jogador02 = new Jogador("Thayná", 4321);
        jogoOnline = new JogoOnline();
        jogoOnline.cadastrarJogador(jogador01, "Diego", 1234);
    }

    @Test
    public void testarCadastrarJogadorComJogadorCadastrado() {

        assertFalse(jogoOnline.cadastrarJogador(jogador01, "Diego", 1234));
        assertTrue(jogoOnline.cadastrarJogador(jogador02, "Thayná", 4321));

    }

    @Test
    public void testarLogin() {
        jogoOnline.login("Diego", 1234);

        assertTrue(jogador01.isOnline());
    }

    @Test
    public void testarLogout() {
        jogoOnline.login("Diego", 1234);
        assertTrue(jogador01.isOnline());
        jogoOnline.logOut("Diego", 1234);
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
    public void testarAcharUmJogadorAdversario() {
        Jogador jogador04 = new Jogador("Jorge", 1234);
        Jogador jogador05 = new Jogador("Mateus", 1234);
        Jogador jogador06 = new Jogador("Carlos", 1234);
        Jogador jogador07 = new Jogador("Mariana", 1234);
        Jogador jogador08 = new Jogador("Stefany", 1234);

        jogoOnline.cadastrarJogador(jogador04, "Jorge", 1234);
        jogoOnline.cadastrarJogador(jogador05, "Mateus", 1234);
        jogoOnline.cadastrarJogador(jogador06, "Carlos", 1234);
        jogoOnline.cadastrarJogador(jogador07, "Mariana", 1234);
        jogoOnline.cadastrarJogador(jogador08, "Stefany", 1234);

        jogoOnline.login("Carlos", 1234);
        jogoOnline.login("Stefany", 1234);

        Jogador jogadorEsperado = jogoOnline.escolherAdversario();

        assertEquals("Deve retornar o primeiro jogador a ser encontrado Online e Não jogando",
                jogador06, jogadorEsperado);

//        Jogador novoJogadorEsperado = jogoOnline.escolherAdversario();
//
//        assertEquals("Deve retornar o primeiro jogador a ser encontrado Online e Não jogando",
//                jogador08, novoJogadorEsperado);
    }


    //ToDo está retornando o mesmo jogador!!
    @Test
    public void testarAcharUmJogadorAdversarioRetornandoOMesmoJogador() {
        Jogador jogador04 = new Jogador("Jorge", 1234);
        Jogador jogador05 = new Jogador("Mateus", 1234);
        Jogador jogador06 = new Jogador("Carlos", 1234);
        Jogador jogador07 = new Jogador("Mariana", 1234);
        Jogador jogador08 = new Jogador("Stefany", 1234);

        jogoOnline.cadastrarJogador(jogador04, "Jorge", 1234);
        jogoOnline.cadastrarJogador(jogador05, "Mateus", 1234);
        jogoOnline.cadastrarJogador(jogador06, "Carlos", 1234);
        jogoOnline.cadastrarJogador(jogador07, "Mariana", 1234);
        jogoOnline.cadastrarJogador(jogador08, "Stefany", 1234);

        jogoOnline.login("Carlos", 1234);


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
    public void testarAcharUmJogadorAdversarioRetornandoNenhumJogador() {
        Jogador jogador04 = new Jogador("Jorge", 1234);
        Jogador jogador05 = new Jogador("Mateus", 1234);
        Jogador jogador06 = new Jogador("Carlos", 1234);
        Jogador jogador07 = new Jogador("Mariana", 1234);
        Jogador jogador08 = new Jogador("Stefany", 1234);

        jogoOnline.cadastrarJogador(jogador04, "Jorge", 1234);
        jogoOnline.cadastrarJogador(jogador05, "Mateus", 1234);
        jogoOnline.cadastrarJogador(jogador06, "Carlos", 1234);
        jogoOnline.cadastrarJogador(jogador07, "Mariana", 1234);
        jogoOnline.cadastrarJogador(jogador08, "Stefany", 1234);

        jogoOnline.login("Carlos", 1234);


        Jogador jogadorEsperado = jogoOnline.escolherAdversario();

        assertEquals("Deve retornar o primeiro jogador a ser encontrado Online e Não jogando",
                jogador06, jogadorEsperado);

//        Jogador novoJogadorEsperado = jogoOnline.escolherAdversario();
//
//        assertEquals("Deve retornar o primeiro jogador a ser encontrado Online e Não jogando",
//                jogador08, novoJogadorEsperado);
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

    @Test
    public void testarTerminarPartidaComValorErrado() {
        JogoOnline jogoOnline = new JogoOnline();

        Partida partida = new Partida(jogador01, jogador02);

        jogoOnline.encerrarPartida(partida, 15);
    }
}
