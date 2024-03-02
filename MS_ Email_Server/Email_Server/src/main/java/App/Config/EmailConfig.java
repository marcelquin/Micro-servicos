package App.Config;

import App.Infra.Gateway.EmailGateway;
import App.Infra.UseCase.UseCaseEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Bean
    UseCaseEmail useCaseEmail(EmailGateway emailGateway)
    { return new UseCaseEmail(emailGateway);}
}
