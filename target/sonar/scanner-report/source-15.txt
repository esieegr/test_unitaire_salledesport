package fr.example;
/**
 * Test simple exécutable pour vérifier le prix d'une Prestation.
 * Ce test n'utilise pas JUnit afin de rester indépendant des dépendances.
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCoutPrestation {

    @Test
    public void testCoutPrestation() {
        Prestation prestation = new Prestation("P1", "TestPrestation", 100.0);
        double expected = 100.0;
        double actual = prestation.getPrix();
        assertEquals(expected, actual, 1e-6, "Le prix de la prestation doit être 100.0");
    }
}
