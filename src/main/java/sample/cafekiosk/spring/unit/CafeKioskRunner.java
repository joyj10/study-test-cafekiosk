package sample.cafekiosk.spring.unit;

import lombok.extern.slf4j.Slf4j;
import sample.cafekiosk.spring.unit.beverage.Americano;
import sample.cafekiosk.spring.unit.beverage.Latte;

/**
 * CafeKioskRunner
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */
@Slf4j
public class CafeKioskRunner {

    public static void main(String[] args) {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        log.info(">>> add americano");

        cafeKiosk.add(new Latte());
        log.info(">>> add Latte");

        int totalPrice = cafeKiosk.calculateTotalPrice();
        log.info("total price : {}", totalPrice);
    }
}
