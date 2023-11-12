package sample.cafekiosk.spring.api.service.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
@SpringBootTest
class OrderServiceTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderService orderService;

    @BeforeEach
    void init() {
        Product product1 = createProduct("001", HANDMADE, "아메리카노", 3000);
        Product product2 = createProduct("002", HANDMADE, "카페라떼", 4000);
        Product product3 = createProduct("003", HANDMADE, "모카라떼", 5000);
        productRepository.saveAll(List.of(product1, product2, product3));
    }

    private Product createProduct(String productNumber, ProductType productType, String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(productType)
                .name(name)
                .price(price)
                .sellingStatus(SELLING)
                .build();
    }

    @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrder() {
        // given
        OrderCreateRequest request = OrderCreateRequest.builder()
                .productNumbers(List.of("001", "002"))
                .build();

        // when
        LocalDateTime registeredDateTime = LocalDateTime.now();
        OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(registeredDateTime, 7000);
        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 3000),
                        tuple("002", 4000)
                );
    }

}
