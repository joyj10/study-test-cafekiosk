package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import sample.cafekiosk.spring.domain.product.ProductRepository;

@RequiredArgsConstructor
@Component  // repository 를 주입 받아서 사용하기 위해서 component 추가
public class ProductNumberFactory {

    private final ProductRepository productRepository;

    public String createNextProductNumber() {
        String latesProductNumber = productRepository.findLatesProduct();
        if (Strings.isEmpty(latesProductNumber)) {
            return "001";
        }

        int latesProductNumberInt = Integer.parseInt(latesProductNumber);
        int nextProductNumberInt = latesProductNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }
}
