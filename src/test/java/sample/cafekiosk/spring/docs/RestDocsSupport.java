package sample.cafekiosk.spring.docs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

/**
 * Rest docs 설정 상위 클래스
 */
@ExtendWith(RestDocumentationExtension.class) // rest docs 확장 주입
public abstract class RestDocsSupport {

    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(RestDocumentationContextProvider provider) {
        // standaloneSetup 은 컨트롤러 클래스 넣어 주어야 함 > 하위 클래스에서 주입해서 넣어주도록 추상 메서드 화
        // webAppContextSetup() 으로도 mockMvc 생성 가능하나 @SpringBootTest 와 함께 사용해야 하며, 테스트 문서 생성 시 스프링을 띄워해 해서 낭비 발생 가능
        this.mockMvc = MockMvcBuilders.standaloneSetup(initController())
                .apply(documentationConfiguration(provider)) // MockMvc documentation 문서를 만들기 위한 설정으로 주입
                .build();
    }

    protected abstract Object initController();
}
