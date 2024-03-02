package App.ApiRest.Config;

import App.ApiRest.Infra.Gateway.EquipeGateway;
import App.ApiRest.Infra.UseCase.Equipe.UseCaseEquipeGet;
import App.ApiRest.Infra.UseCase.Equipe.UseCaseEquipePost;
import App.ApiRest.Infra.UseCase.Equipe.UseCaseEquipePut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EquipeConfig {
    @Bean
    UseCaseEquipeGet useCaseEquipeGet(EquipeGateway equipeGateway)
    { return new UseCaseEquipeGet(equipeGateway);}
    @Bean
    UseCaseEquipePost useCaseEquipePost(EquipeGateway equipeGateway)
    { return new UseCaseEquipePost(equipeGateway);}
    @Bean
    UseCaseEquipePut useCaseEquipePut(EquipeGateway equipeGateway)
    { return new UseCaseEquipePut(equipeGateway);}
}
