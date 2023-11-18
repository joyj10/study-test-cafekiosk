package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // 테스트 시작 시 mockito 사용해서 mock을 만드는 것을 인식 함
class MailServiceTest {

    @Mock
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    // MailService의 생성자를 보고 Mock으로 선언된 객체를 Inject 해줌(DI와 똑같은 역할)
    @InjectMocks
    private MailService mailService;


    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        // given
        // mock의 경우 RETURS_DEFAULTS 가 있어서, int는 0 객체는 null을 자동으로 반환
        Mockito.when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);

        // 위와 같은 방식, 단 given 절에 있기 때문에 given 메서드가 더 적합하게 느껴짐
        // BDDMockito 는 Mockito 를 하나 감싼 것(기능은 모두 동일하나, 이름만 변경 된 것 이라고 생각하면 됨)
        BDDMockito.given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .willReturn(true);

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
