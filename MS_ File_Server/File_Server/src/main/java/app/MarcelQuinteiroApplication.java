package app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title ="File Server", version = "1", description = "micro serviço de Upload de arquivo"))
public class MarcelQuinteiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarcelQuinteiroApplication.class, args);
	}

}
