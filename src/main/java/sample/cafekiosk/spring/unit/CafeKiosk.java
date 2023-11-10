package sample.cafekiosk.spring.unit;

import lombok.Getter;
import sample.cafekiosk.spring.unit.beverage.Beverage;
import sample.cafekiosk.spring.unit.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * CafeKiosk
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */
@Getter
public class CafeKiosk {

    private final List<Beverage> beverages = new ArrayList<>();

    public void add(Beverage beverage) {
        beverages.add(beverage);
    }

    public void remove(Beverage beverage) {
        beverages.remove(beverage);
    }

    public void clear() {
        beverages.clear();
    }

    public int calculateTotalPrice() {
        return beverages.stream()
                .mapToInt(Beverage::getPrice)
                .sum();
    }

    public Order createOrder() {
        return new Order(LocalDateTime.now(), beverages);
    }
}
