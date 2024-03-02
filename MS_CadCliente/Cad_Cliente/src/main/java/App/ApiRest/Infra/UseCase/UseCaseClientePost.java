package App.ApiRest.Infra.UseCase;

import App.ApiRest.Domain.Cliente;
import App.ApiRest.Infra.Gateway.ClienteGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public class UseCaseClientePost {

    private final ClienteGateway clienteGateway;

    public UseCaseClientePost(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public ResponseEntity<Cliente> NovoCliente(@RequestParam String nome, @RequestParam String sobrenome,
                                               @RequestParam Long cpf, @RequestParam Long rg,
                                               @RequestParam LocalDate dataNascimento, @RequestParam String numero,
                                               @RequestParam String cep, @RequestParam String email,
                                               @RequestParam Long telefone, @RequestParam Long celular)
    { return clienteGateway.NovoCliente(nome, sobrenome, cpf, rg, dataNascimento, numero, cep, email, telefone, celular);}

}
