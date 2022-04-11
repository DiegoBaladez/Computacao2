import org.junit.Test;

import static org.junit.Assert.*;

public class AritimeticaBasicaTest {

    @Test
    public void testarCalcularMaximoDivisorComum() {

        assertEquals(3, AritimeticaBasica.calcularMaximoDivisorComum(3, 9));
        assertEquals(5, AritimeticaBasica.calcularMaximoDivisorComum(40, 55));
        assertEquals(1, AritimeticaBasica.calcularMaximoDivisorComum(50, 21));
        assertEquals(1, AritimeticaBasica.calcularMaximoDivisorComum(5, 7));
        assertEquals(1, AritimeticaBasica.calcularMaximoDivisorComum(5, 1));

    }
}