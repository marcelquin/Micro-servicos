package App.ApiRest.Infra.UseCase;

import App.ApiRest.Domain.Cliente;
import App.ApiRest.Infra.Gateway.ClienteGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public class UseCaseClientePut {

    private final ClienteGateway clienteGateway;

    public UseCaseClientePut(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public ResponseEntity<Cliente> AlterarDados(@RequestParam Long id, String nome, String sobrenome,
                                                Long cpf, Long rg, LocalDate dataNascimento)
    {return clienteGateway.AlterarDados(id, nome, sobrenome, cpf, rg, dataNascimento);}
    public ResponseEntity<Cliente> AlterarEndereco(@RequestParam Long id, @RequestParam String numero,
                                                   @RequestParam Long cep)
    { return clienteGateway.AlterarEndereco(id, numero, cep);}

    public ResponseEntity<Cliente> AlterarContato(@RequestParam Long id, String email, Long telefone, Long celular)
    { return clienteGateway.AlterarContato(id, email, telefone, celular);}
}
