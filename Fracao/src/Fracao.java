/**
 * Aula sobre TDD:
 * 1)Primeiro criamos os métodos que queremos.
 * 2)Documentamos os métodos
 * 3)Depois criamos a classe de testes.
 * <p>
 * Crtl + F12 mostra todos os métodos de uma classe.
 * <p>
 * OVERLOAD - SOBRECARGA ----> Podemos ter métodos com o mesmo nome porém com assinaturas
 * diferentes. Em outras palavras, são métodos com o mesmo nome mas seus parâmetros são
 * diferentes. No momento em que o método é chamado, o próprio Java vai decidir qual
 * dos dois métodos usar baseado no porâmetros que está recebendo.
 * A SOBRECARGA pode ocorrer no método construtor também!
 * <p>
 * <p>
 * plus - Método Construtor ----> O construtor deve conter todos os atributos os quais
 * serão fundamentais para a existência do objeto. Serão os atributos mínimos para que
 * o objeto possua algum sentido de existir.
 * <p>
 * == enter objetivos ---> Verifica se estamos lidando com o mesmo objeto. A comparação
 * irá verificar se os objetos apontam para o mesmo endereço de memória.
 * <p>
 * Métodos Equals ---> As vezes precisamos implementar o método equals. O padrão é comparar o endereço de memória.
 * <p>
 * Ctrl + Shift ------> Para subir a linha
 * <p>
 * Overload de construtor -->> Posso fazer com que o
 * construtor em overload possa chamar o outro para,
 * assim, evitar de repetir código.
 * Na aula, o construtor é desviado em: 1:44:10
 * <p>
 * OBS: INT DIVIDIDO POR INT GERA UM INT NO JAVA!
 * PARA RESOLVER ISSO, UM DOS OPERANDOS PRECISA SER
 * FLOAT/DOUBLE
 */


import java.util.Objects;

/**
 * Retorna o numerador da fração
 *
 * @return um inteiro não-negativo
 */
public class Fracao {

    private int numerador;
    private int denominador;
    private boolean sinal;
    private Fracao minhaFracaoIrredutivel;


    /**
     * cria uma fração. O sinal da fração é informado explicitamente no parâmetro
     *
     * @param numerador   um inteiro qualquer não-negativo
     * @param denominador um inteiro positivo.
     * @param sinal       true positiva ou zero, false se negativa
     *                    <p>
     *                    OBS: CASO A FRAÇÃO SEJA IGUAL A ZERO, O DENOMINADOR SERÁ
     *                    SEMPRE ARMAZENADO COMO 1
     */
    public Fracao(int numerador, int denominador, boolean sinal) {
        if (denominador == 0) {
            throw new RuntimeException("O denominador não pode ser zero!");
        }

        if (denominador < 0) {
            this.denominador = Math.abs(numerador);
        }

        if (numerador < 0) {
            this.numerador = Math.abs(numerador);
        }

        if (numerador == 0) {
            this.numerador = numerador;
            this.denominador = 1;
            this.sinal = sinal;

        }

        this.numerador = numerador;
        this.denominador = denominador;
        this.sinal = sinal;
    }

    /**
     * Casos:
     * + e +
     * + e -
     * - e +
     * - e -
     */
    public Fracao(int numerador, int denominador) {
        if (denominador == 0) {
            throw new RuntimeException("O denominador não pode ser zero!");
        }

        if (denominador < 0) {
            this.denominador = Math.abs(denominador);
        } else {
            this.denominador = denominador;
        }

        if (numerador == 0) {
            this.numerador = numerador;
            this.denominador = 1;
        }

        if (numerador < 0) {
            this.numerador = Math.abs(numerador);
        } else {
            this.numerador = numerador;
        }


        if (numerador * denominador < 0) {
            this.sinal = false;
        } else {
            this.sinal = true;
        }
        this.minhaFracaoIrredutivel = null;
    }

    /**
     * Retorna o valor ABSOLUTO do numerador da fração
     *
     * @return um inteiro não-negativo
     */
    public int getNumerador() {
        return Math.abs(this.numerador);
    }

    /**
     * Retorna o denominador da fração
     *
     * @return um inteiro não-negativo
     */
    public int getDenominador() { //adequar para numerador ZERO!
        return Math.abs(this.denominador);
    }

//    public int getNumeradorNaoAbsoluto(){
//        return this.numerador;
//    }

    /**
     * Retorna um boolean indicando o sinal da fração
     *
     * @return true positivo - false negativo
     */
    public boolean getSinal() {
        return this.sinal;
    }

    /**
     * Recebe uma fração
     *
     * @return retorna o valor da divisão dela.
     */
    public double getValorNumerico() {
        if(this.getSinal()){
            return numerador / (double) denominador;
        }
        return numerador * -1 / (double) denominador;
    }

    /**
     * Retorna uma fração que seja equivalente a original e irredutivel
     *
     * @return um novo objeto fracao caso esta fracao não seja irredutivel ou esta
     * propria fracao(this) caso ela propria já seja irredutivel
     */
    public Fracao getFracaoIrredutivel() {
        garantirInicializarFracaoIrredutivel();
        return this.minhaFracaoIrredutivel;
    }


    private Fracao garantirInicializarFracaoIrredutivel() {
        if (this.minhaFracaoIrredutivel == null) {
            int mdc = AritimeticaBasica.calcularMaximoDivisorComum(this.numerador, this.denominador);
            this.minhaFracaoIrredutivel = new Fracao(
                    this.numerador / mdc, this.denominador / mdc,
                    this.sinal);
            if (mdc == 1) {
                this.minhaFracaoIrredutivel = this;
            } else {
                this.minhaFracaoIrredutivel = new Fracao(
                        this.numerador / mdc, this.denominador / mdc,
                        this.sinal);
            }
        }

        return this.minhaFracaoIrredutivel;
    }

    public Fracao somarFracao(Fracao outraFracao) {
        int novoDenomidador;
        int novoNumerador;
        boolean novoSinal;
        Fracao fracaoSomada;

        if (this.getDenominador() == outraFracao.getDenominador()) {
            novoDenomidador = this.getDenominador();
            novoNumerador = this.getNumerador() + outraFracao.getNumerador();
        } else {
            novoDenomidador = this.getDenominador() * outraFracao.getDenominador();
            novoNumerador = (this.getNumerador() * outraFracao.getDenominador())
                    + (outraFracao.getNumerador() * this.getDenominador());
        }
        if (this.getSinal() != outraFracao.getSinal()) {
            novoSinal = false;
        } else {
            novoSinal = true;
        }

        fracaoSomada = new Fracao(novoNumerador, novoDenomidador, novoSinal);


        return fracaoSomada;
    }

    public Fracao somarFracao(int numeroInteiro) {
        Fracao somaDasFracoes;
        int novoNumerador;
        int novoDenominador;
        int getNumeradorComSinal;


        if (this.getSinal() == false) {
            getNumeradorComSinal = this.getNumerador() * (-1);
            novoNumerador = getNumeradorComSinal + (numeroInteiro * this.getDenominador());
        } else {
            novoNumerador = this.getNumerador() + (numeroInteiro * this.getDenominador());
        }

        novoDenominador = this.getDenominador();

        somaDasFracoes = new Fracao(novoNumerador, novoDenominador);
        return somaDasFracoes;
    }

    public Fracao multiplicar(Fracao outraFracao) { //criar teste
        Fracao fracaoMultiplicada;
        int novoNumerador;
        int novoDenominador;
        boolean novoSinal;

        novoNumerador = this.getNumerador() * outraFracao.getNumerador();
        novoDenominador = this.getDenominador() * outraFracao.getDenominador();

        if (this.getSinal() != outraFracao.getSinal()) {
            novoSinal = false;
        } else {
            novoSinal = true;
        }

        fracaoMultiplicada = new Fracao(novoNumerador, novoDenominador, novoSinal);
        return fracaoMultiplicada;
    }

    public Fracao multiplicacaoMelhorada(Fracao outra){
        Fracao fracaoProduto = new Fracao(this.numerador * outra.getNumerador(),
                this.denominador * outra.getDenominador(),
                this.sinal == outra.getSinal());
        fracaoProduto.simplificar();
        return fracaoProduto;
    }

    public Fracao multiplicar(int numeroInteiro) {
        Fracao fracaoProduto = new Fracao(this.numerador * numeroInteiro,
                this.denominador,
                this.sinal == AritimeticaBasica.extrairSinal(numeroInteiro));
        fracaoProduto.simplificar();
        return fracaoProduto;
    }



    public void simplificar() {
        garantirInicializarFracaoIrredutivel();
        this.numerador = this.minhaFracaoIrredutivel.getNumerador();
        this.denominador = this.minhaFracaoIrredutivel.getDenominador();

    }

    @Override
    public String toString() {
        String booleanToString;

        if (sinal) {
            booleanToString = "";
        } else {
            booleanToString = "-";
        }

        if (denominador == 1) {
            return booleanToString + numerador;
        }

        if (numerador == 0) {
            return "0";
        }

        return booleanToString + numerador + "/" + denominador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; // se as classes forem dif
        Fracao fracao = (Fracao) o;

        Fracao minhaFracaoIrredutivel = fracao.getFracaoIrredutivel();
        Fracao outraFracaoIrredutivel = fracao.getFracaoIrredutivel();

        return minhaFracaoIrredutivel.numerador == outraFracaoIrredutivel.numerador &&
                minhaFracaoIrredutivel.denominador == outraFracaoIrredutivel.denominador &&
                this.sinal == fracao.sinal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerador, denominador, sinal);
    }
}

/*
 * fazer um overload de construtor depois
 * */