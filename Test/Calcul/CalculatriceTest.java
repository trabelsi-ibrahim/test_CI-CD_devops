package Calcul;
import Clacul.Calculatrice;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatriceTest {

    @Test
    public void testAdditionner() {
        Calculatrice calc = new Calculatrice();
        assertEquals(5, calc.additionner(2, 3));
    }

    @Test
    public void testSoustraire() {
        Calculatrice calc = new Calculatrice();
        assertEquals(1, calc.soustraire(4, 3));
    }

    @Test
    public void testMultiplier() {
        Calculatrice calc = new Calculatrice();
        assertEquals(12, calc.multiplier(3, 4));
    }

    @Test
    public void testDiviser() {
        Calculatrice calc = new Calculatrice();
        assertEquals(2, calc.diviser(8, 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivisionParZero() {
        Calculatrice calc = new Calculatrice();
        calc.diviser(5, 0);
    }
}
