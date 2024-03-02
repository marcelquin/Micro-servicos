package App.ApiRest.Config;

import App.ApiRest.Infra.Gateway.CargoGateway;
import App.ApiRest.Infra.UseCase.Cargo.UseCaseCargoGet;
import App.ApiRest.Infra.UseCase.Cargo.UseCaseCargoPost;
import App.ApiRest.Infra.UseCase.Cargo.UseCaseCargoPut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CargoConfig {

    @Bean
    UseCaseCargoGet useCaseCargoGet(CargoGateway cargoGateway)
    { return new UseCaseCargoGet(cargoGateway);}
    @Bean
    UseCaseCargoPost useCaseCargoPost(CargoGateway cargoGateway)
    { return new UseCaseCargoPost(cargoGateway);}
    @Bean
    UseCaseCargoPut useCaseCargoPut(CargoGateway cargoGateway)
    { return  new UseCaseCargoPut(cargoGateway);}
}
