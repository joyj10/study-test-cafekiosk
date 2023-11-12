package sample.cafekiosk.unit.beverage;

/**
 * Americano
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */
public class Americano implements Beverage {
    @Override
    public String getName() {
        return "아메리카노";
    }

    @Override
    public int getPrice() {
        return 4000;
    }
}
