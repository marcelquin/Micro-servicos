package App.Bussness;

import App.Domain.Email;
import App.Infra.Exceotions.NullargumentsException;
import App.Infra.Gateway.EmailGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService implements EmailGateway {

    private final MailSender mailSender;

    public EmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public ResponseEntity<Email> EnviarEmail(String para, String assunto, String mensagem)
    {
        try{
            if(para != null && assunto != null && mensagem != null )
            {
                var mail = new SimpleMailMessage();
                mail.setFrom("noreply@email.com");
                mail.setTo(para);
                mail.setSubject(assunto);
                mail.setText(mensagem);
                mailSender.send(mail);
                Email response = new Email("noreply@email.com",para,assunto,mensagem, LocalDateTime.now());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else
            {
                throw new NullargumentsException();
            }
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }
}
