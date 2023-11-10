package sample.cafekiosk.spring.unit.beverage;

/**
 * Latte
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */
public class Latte implements Beverage {
    @Override
    public String getName() {
        return "라떼";
    }

    @Override
    public int getPrice() {
        return 4500;
    }
}
