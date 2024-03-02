package App.ApiRest.Infra.UseCase;

import App.ApiRest.Domain.Cliente;
import App.ApiRest.Infra.Gateway.ClienteGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseClienteDelete {

    private final ClienteGateway clienteGateway;

    public UseCaseClienteDelete(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public ResponseEntity<Cliente> DeletarCliente(@RequestParam Long id)
    { return clienteGateway.DeletarCliente(id);}

}
