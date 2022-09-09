package dominio.bicho;

import dominio.Mamifero;

public class Cachorro extends Mamifero
{
    private String racaDoCao;

    public Cachorro(String raca)
    {
    this.racaDoCao = raca;
    }


    public void emitirSom()
    {
        System.out.printf("UAUAUAUAUAUAUUAU");
    }


}

