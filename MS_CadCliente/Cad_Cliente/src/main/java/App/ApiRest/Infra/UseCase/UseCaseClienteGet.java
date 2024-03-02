package App.ApiRest.Infra.UseCase;

import App.ApiRest.Domain.Cliente;
import App.ApiRest.Infra.Gateway.ClienteGateway;
import App.ApiRest.Infra.Persistence.Entity.ClienteEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseClienteGet {

    private final ClienteGateway clienteGateway;

    public UseCaseClienteGet(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public ResponseEntity<List<ClienteEntity>> ListarCliente()
    { return clienteGateway.ListarCliente();}

    public ResponseEntity<Cliente> BuscarClientePorId(@RequestParam Long id)
    { return  clienteGateway.BuscarClientePorId(id);}
    public ResponseEntity<Cliente> BuscarClientePorNome(@RequestParam String nome)
    { return clienteGateway.BuscarClientePorNome(nome);}
    public ResponseEntity<Cliente> BuscarClientePorCpf(@RequestParam Long cpf)
    { return clienteGateway.BuscarClientePorCpf(cpf);}

}
