package dominio;

public abstract class Mamifero extends Animal
{
    public void mamar()
    {

    }

    public void comerComida()
    {

    }

    @Override
    public void seAlimentar()
    {
        if(getIdadeAnimalEmAnos() < 2)
        {
            mamar();
        } else
        {
            comerComida();
        }
    }
}
