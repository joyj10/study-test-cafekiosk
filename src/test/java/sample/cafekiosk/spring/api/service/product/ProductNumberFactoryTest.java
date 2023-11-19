package sample.cafekiosk.spring.api.service.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sample.cafekiosk.spring.IntegrationTestSupport;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

class ProductNumberFactoryTest extends IntegrationTestSupport {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductNumberFactory productNumberFactory;

    @DisplayName("첫 상품 등록 시 상품 번호는 001로 생성한다.")
    @Test
    void createNextProductNumber001() {
        // given // when
        String nextProductNumber = productNumberFactory.createNextProductNumber();

        // then
        assertThat(nextProductNumber).isEqualTo("001");
    }

    @DisplayName("현재 등록 된 상품의 다음 상품 번호를 생성한다.")
    @Test
    void createNextProductNumber() {
        // given
        productRepository.save(Product.builder()
                .productNumber("001")
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("아메리카노")
                .price(4000)
                .build());

        // when
        String nextProductNumber = productNumberFactory.createNextProductNumber();

        // then
        assertThat(nextProductNumber).isEqualTo("002");
    }

}
