package App.Config;

import App.ApiRest.Infra.Gateway.ClienteGateway;
import App.ApiRest.Infra.UseCase.UseCaseClienteDelete;
import App.ApiRest.Infra.UseCase.UseCaseClienteGet;
import App.ApiRest.Infra.UseCase.UseCaseClientePost;
import App.ApiRest.Infra.UseCase.UseCaseClientePut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteConfig {
    @Bean
    UseCaseClienteGet useCaseClienteGet(ClienteGateway clienteGateway)
    { return new UseCaseClienteGet(clienteGateway);}
    @Bean
    UseCaseClientePost useCaseClientePost(ClienteGateway clienteGateway)
    { return new UseCaseClientePost(clienteGateway);}
    @Bean
    UseCaseClientePut useCaseClientePut(ClienteGateway clienteGateway)
    { return  new UseCaseClientePut(clienteGateway);}
    @Bean
    UseCaseClienteDelete useCaseClienteDelete(ClienteGateway clienteGateway)
    { return  new UseCaseClienteDelete(clienteGateway);}
}
