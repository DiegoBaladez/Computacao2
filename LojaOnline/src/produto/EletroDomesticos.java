package produto;

import java.awt.*;

public class EletroDomesticos extends Produto{

    private static final float PRECO_DEFAULT = 1.99f;

    private int garantiaEmMeses;

    private int consumoEmWatss;

    private String marca;

    private String tipo;

    private String cor;

    public EletroDomesticos(long numeroDeSerie,String tipo, String marca) {
        super(numeroDeSerie, String.format("%s %s", tipo, marca));
        }

    @Override
    protected float getPrecoDefault() {
        return PRECO_DEFAULT;
    }
}
