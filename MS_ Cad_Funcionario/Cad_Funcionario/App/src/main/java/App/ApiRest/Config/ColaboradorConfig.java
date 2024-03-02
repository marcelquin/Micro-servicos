package App.ApiRest.Config;

import App.ApiRest.Infra.Gateway.ColaboradorGateway;
import App.ApiRest.Infra.UseCase.Colaboador.UseCaseColaboradorGet;
import App.ApiRest.Infra.UseCase.Colaboador.UseCaseColaboradorPost;
import App.ApiRest.Infra.UseCase.Colaboador.UseCaseColaboradorPut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ColaboradorConfig {

    @Bean
    UseCaseColaboradorGet useCaseColaboradorGet(ColaboradorGateway colaboradorGateway)
    { return new UseCaseColaboradorGet(colaboradorGateway);}
    @Bean
    UseCaseColaboradorPost useCaseColaboradorPost(ColaboradorGateway colaboradorGateway)
    { return new UseCaseColaboradorPost(colaboradorGateway);}
    @Bean
    UseCaseColaboradorPut useCaseColaboradorPut(ColaboradorGateway colaboradorGateway)
    { return new UseCaseColaboradorPut(colaboradorGateway);}
}
