package controle;
import dominio.Animal;

import java.util.ArrayList;

public class Zoologico {

    public ArrayList<Animal> bichos;

    public Zoologico() {
        this.bichos = new ArrayList<>();

    }

    /**
     * recebe qq animal, sem distinção. assim poderá receber qualquer animal.
     * Cada objeto vai executar um método de acordo com o seu ponto de vista.
     */
    public void receberBicho(Animal novoBicho) {
        System.out.println("\n" +  "Recebendo um novo dominio.bicho do tipo "
                + novoBicho.getClass().getName() + ".");

        this.bichos.add(novoBicho);

        novoBicho.seAlimentar();

        novoBicho.emitirSom();

    }
}
