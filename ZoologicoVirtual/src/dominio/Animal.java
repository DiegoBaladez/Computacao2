package dominio;

import java.time.LocalDate;


public abstract class Animal {

    private String nome;
    private LocalDate dataDeNascimento;
    private String especie;

    public void respirar()
    {
        System.out.println("Respirando");
    }

    public void seAlimentar()
    {
        System.out.println("Se alimentando");
    }
    //força as subclasses a implementar esse método!
    public abstract void emitirSom();


    public void morrer() {
        System.out.println("morri");
    }

    public void dormir() {
        System.out.println("dormindo....");
    }

    public int getIdadeAnimalEmAnos() {
        return 0;

    }

    public float getTemperatura() {
        return 0;
    }

}


/**
 * A classe animal permite que eu evite a repetição de atributos, métodos e lógicas nas
 * classes filhas. Nesse exemplo, não faz sentido instancia-la..
 * <p>
 * O método emitirsom() não consegue abraanger todos todos sons que os animais podem fazer.
 * Um som genérico para todos os animais não faz sentido. Então ele se torna um método
 * impossível de se implementar. E o que fazer? Não iremos implementa-lo. Faremos com que seja
 * um método abstract (nem as chaves se coloca).
 * <p>
 * MÉTODOS ABSTRATOS ---> No nosso exemplo, o animal precisa emitir um som, porém não
 * sabemos qual som irá ser emitido. As subclasses serão obrigadas a implementar esses
 * métodos. Os utilizadores da classe dominio.Animal, podem confiar que a instancia animal que
 * terão em mãos saberá emitir um som pq esse aniaml será uma subclasse de animal que
 * saberá emitir um som.
 * Quando marcamos 1 ou mais métodos como abstratos (dá um erro de compilação) e a propria
 * classe precisa ser marcada como abstrata! Assim eu garanto que ninguém irá instanciar uma
 * classe dominio.Animal.
 * Toda subclasse concreta (logo, as não abstratas) deverão implementar os métodos abstratos
 * das superclasses. Toda classe nao abstrata, deve enxegar a implementação de todos os métodos.
 *
 *
 */