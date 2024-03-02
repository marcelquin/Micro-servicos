package app.Config;

import app.ApiRest.Infra.Gateway.FileServergateway;
import app.ApiRest.Infra.UseCases.UseCaseArquivo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoConfig {

    @Bean
    UseCaseArquivo useCaseArquivo(FileServergateway fileServergateway)
    { return  new UseCaseArquivo(fileServergateway);}
}
