package App.ApiRest.Config;

import App.ApiRest.Infra.Gateway.DepartamentoGateway;
import App.ApiRest.Infra.UseCase.Departamento.UseCaseDepartamentoGet;
import App.ApiRest.Infra.UseCase.Departamento.UseCaseDepartamentoPost;
import App.ApiRest.Infra.UseCase.Departamento.UseCaseDepartamentoPut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartamentoConfig {

    @Bean
    UseCaseDepartamentoGet useCaseDepartamentoGet(DepartamentoGateway departamentoGateway)
    { return new UseCaseDepartamentoGet(departamentoGateway);}
    @Bean
    UseCaseDepartamentoPost useCaseDepartamentoPost(DepartamentoGateway departamentoGateway)
    { return new UseCaseDepartamentoPost(departamentoGateway);}
    @Bean
    UseCaseDepartamentoPut useCaseDepartamentoPut(DepartamentoGateway departamentoGateway)
    {return new UseCaseDepartamentoPut(departamentoGateway);}
}
