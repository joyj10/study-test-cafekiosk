package sample.cafekiosk.unit.beverage;

import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * AmericanoTest
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */
class AmericanoTest {

    @Test
    void getName() {
        Americano americano = new Americano();
        String name = americano.getName();

        // JUnit API
        assertEquals("아메리카노", name);

        // assertJ API
        assertThat(name).isEqualTo("아메리카노");
    }

    @Test
    void getPrice() {
        Americano americano = new Americano();
        int price = americano.getPrice();

        // JUnit API
        assertEquals(4500, price);

        // assertJ API
        assertThat(price).isEqualTo(4500);
    }
}
