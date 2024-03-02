package App.ApiRest.Infra.Gateway;

import App.ApiRest.Domain.Cliente;
import App.ApiRest.Infra.Persistence.Entity.ClienteEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface ClienteGateway {

    public ResponseEntity<List<ClienteEntity>> ListarCliente();
    public ResponseEntity<Cliente> BuscarClientePorId(@RequestParam Long id);
    public ResponseEntity<Cliente> BuscarClientePorNome(@RequestParam String nome);
    public ResponseEntity<Cliente> BuscarClientePorCpf(@RequestParam Long cpf);

    public ResponseEntity<Cliente> NovoCliente(@RequestParam String nome, @RequestParam String sobrenome,
                                               @RequestParam Long cpf, @RequestParam Long rg,
                                               @RequestParam LocalDate dataNascimento, @RequestParam String numero,
                                               @RequestParam String cep, @RequestParam String email,
                                               @RequestParam Long telefone,@RequestParam Long celular);

    public ResponseEntity<Cliente> AlterarDados(@RequestParam Long id, String nome, String sobrenome,
                                                Long cpf,  Long rg, LocalDate dataNascimento);
    public ResponseEntity<Cliente> AlterarEndereco(@RequestParam Long id, @RequestParam String numero,
                                                   @RequestParam Long cep);

    public ResponseEntity<Cliente> AlterarContato(@RequestParam Long id, String email, Long telefone, Long celular);
    public ResponseEntity<Cliente> DeletarCliente(@RequestParam Long id);




}
