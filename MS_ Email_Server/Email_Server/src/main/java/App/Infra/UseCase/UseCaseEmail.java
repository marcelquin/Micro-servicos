package App.Infra.UseCase;

import App.Domain.Email;
import App.Infra.Gateway.EmailGateway;
import org.springframework.http.ResponseEntity;

public class UseCaseEmail {

    private final EmailGateway emailGateway;

    public UseCaseEmail(EmailGateway emailGateway) {
        this.emailGateway = emailGateway;
    }

    public ResponseEntity<Email> EnviarEmail(String para, String assunto, String mensagem)
    { return emailGateway.EnviarEmail(para, assunto, mensagem);}
}
