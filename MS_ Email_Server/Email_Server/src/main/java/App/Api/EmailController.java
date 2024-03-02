package App.Api;

import App.Domain.Email;
import App.Infra.UseCase.UseCaseEmail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("MS_email")
@Tag(name = "email", description = "Micro serviço de envio de email de teste")
public class EmailController {

    private final UseCaseEmail caseEmail;

    public EmailController(UseCaseEmail caseEmail) {
        this.caseEmail = caseEmail;
    }

    @Operation(summary = "Salva Novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/EnviarEmail")
    public ResponseEntity<Email> EnviarEmail(String para, String assunto, String mensagem)
    { return caseEmail.EnviarEmail(para, assunto, mensagem);}
}
