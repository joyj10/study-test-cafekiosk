package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // 테스트 시작 시 mockito 사용해서 mock을 만드는 것을 인식 함
class MailServiceSpyTest {
    @Spy
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    // MailService의 생성자를 보고 Mock으로 선언된 객체를 Inject 해줌(DI와 똑같은 역할)
    @InjectMocks
    private MailService mailService;


    @DisplayName("메일 전송 테스트 : SPY")
    @Test
    void sendMail() {
        // given

        // spy를 사용하면 아래와 같이 stub 한 메서드만 직접 정의한 대로 리턴됨
        // 나머지 메서드는 실제 객체의 메서드를 사용함
        doReturn(true)
                .when(mailSendClient)
                .sendEmail(anyString(), anyString(), anyString(), anyString());
        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        assertThat(result).isTrue();

        // mock 객체를 안에 넣어주고, 몇번 불렸는지 횟수를 검증할 수 있음
        // save에 지정된 객체가 들어가고 한번 불리는게 맞는지 검증하는 코드
        verify(mailSendHistoryRepository, times(1))
                .save(any(MailSendHistory.class));
    }

}
