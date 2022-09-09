package interface_grafica;

import dominio.bicho.Cachorro;
import dominio.bicho.Gato;
import controle.Zoologico;

public class Principal {

    public static void main(String[] args) {
        Zoologico zoo = new Zoologico();

        Cachorro caozinho = new Cachorro("Samoieda");
        Gato gatinho = new Gato();

        zoo.receberBicho(caozinho);
        zoo.receberBicho(gatinho);
    }
}
