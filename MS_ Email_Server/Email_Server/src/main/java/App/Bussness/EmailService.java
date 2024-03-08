package App.Bussness;

import App.Domain.Email;
import App.Infra.Exceotions.NullargumentsException;
import App.Infra.Gateway.EmailGateway;
import App.Infra.Percistence.Entity.EmailEntity;
import App.Infra.Percistence.Repository.EmailRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService implements EmailGateway {

    private final MailSender mailSender;
    private final EmailRepository emailRepository;

    @Value("#{environment['App.email']}")
    private String emailto;

    public EmailService(MailSender mailSender, EmailRepository emailRepository) {
        this.mailSender = mailSender;
        this.emailRepository = emailRepository;
    }

    @Override
    public ResponseEntity<Email> EnviarEmail(String para, String assunto, String mensagem)
    {
        try{
            if(para != null && assunto != null && mensagem != null )
            {
                var mail = new SimpleMailMessage();
                mail.setFrom(emailto);
                mail.setTo(para);
                mail.setSubject(assunto);
                mail.setText(mensagem);
                mailSender.send(mail);
                EmailEntity entity = new EmailEntity();
                entity.setEmissor(emailto);
                entity.setReceptor(para);
                entity.setAssunto(assunto);
                entity.setMensagem(mensagem);
                entity.setTimeStamp(LocalDateTime.now());
                emailRepository.save(entity);
                Email response = new Email(entity.getEmissor(),entity.getReceptor(),entity.getAssunto(),
                                           entity.getMensagem(), entity.getTimeStamp());
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
