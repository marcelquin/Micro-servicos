package App.Infra.Gateway;

import App.Domain.Email;
import org.springframework.http.ResponseEntity;

public interface EmailGateway {

    public ResponseEntity<Email> EnviarEmail(String para, String assunto, String mensagem);
}
