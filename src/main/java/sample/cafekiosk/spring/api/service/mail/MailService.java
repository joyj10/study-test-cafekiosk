package sample.cafekiosk.spring.api.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.client.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

/**
 * 메일용 서비스에서는 Transaction 따로 걸지 않아도 됨
 * 트랙잭션을 가지고 DB 조회를 함 > DB 조회 시 커넥션을 맺어서 자원을 계속 소유하고 있음
 * 메일 전송 처럼 실제로 트랜잭션에 참여하지 않아도 되는데 긴 네트워크를 타는 서비스는 트랙잭션을 타지 않는 것이 좋음
 * repository 단에서 조회용 트랜잭션이 걸리기 때문에 서비스 단에서는 트랙잭션을 걸지 않아도 됨
 */
@RequiredArgsConstructor
@Service
public class MailService {
    private final MailSendClient mailSendClient;
    private final MailSendHistoryRepository mailSendHistoryRepository;

    public boolean sendMail(String fromEmail, String toEmail, String subject, String content) {
        boolean result = mailSendClient.sendEmail(fromEmail, toEmail, subject, content);
        if (result) {
            mailSendHistoryRepository.save(MailSendHistory.builder()
                    .fromEmail(fromEmail)
                    .toEmail(toEmail)
                    .subject(subject)
                    .content(content)
                    .build()
            );

            mailSendClient.a();
            mailSendClient.b();
            mailSendClient.c();
            return true;
        }
        return false;
    }
}
