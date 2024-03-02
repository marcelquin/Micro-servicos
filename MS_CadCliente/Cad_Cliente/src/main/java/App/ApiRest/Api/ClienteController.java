package App.ApiRest.Api;

import App.ApiRest.Domain.Cliente;
import App.ApiRest.Infra.Persistence.Entity.ClienteEntity;
import App.ApiRest.Infra.UseCase.UseCaseClienteDelete;
import App.ApiRest.Infra.UseCase.UseCaseClienteGet;
import App.ApiRest.Infra.UseCase.UseCaseClientePost;
import App.ApiRest.Infra.UseCase.UseCaseClientePut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("ms_Cliente")
@Tag(name = "Micro serviço cliente", description = "Manipula informações referente ao cliente")
public class ClienteController {

    private final UseCaseClienteGet clienteGet;
    private final UseCaseClientePost clientePost;
    private final UseCaseClientePut clientePut;
    private final UseCaseClienteDelete clienteDelete;

    public ClienteController(UseCaseClienteGet clienteGet, UseCaseClientePost clientePost, UseCaseClientePut clientePut, UseCaseClienteDelete clienteDelete) {
        this.clienteGet = clienteGet;
        this.clientePost = clientePost;
        this.clientePut = clientePut;
        this.clienteDelete = clienteDelete;
    }

    @Operation(summary = "Lista registros de clientes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/ListarCliente")
    public ResponseEntity<List<ClienteEntity>> ListarCliente()
    { return clienteGet.ListarCliente();}

    @Operation(summary = "Busca Registro de cliente por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/BuscarClientePorId")
    public ResponseEntity<Cliente> BuscarClientePorId(@RequestParam Long id)
    { return clienteGet.BuscarClientePorId(id);}

    @Operation(summary = "Busca Registro de cliente por Nome", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/BuscarClientePorNome")
    public ResponseEntity<Cliente> BuscarClientePorNome(@RequestParam String nome)
    { return clienteGet.BuscarClientePorNome(nome);}

    @Operation(summary = "Busca Registro de cliente por CPF", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/BuscarClientePorCpf")
    public ResponseEntity<Cliente> BuscarClientePorCpf(@RequestParam Long cpf)
    { return clienteGet.BuscarClientePorCpf(cpf);}

    @Operation(summary = "Cadastra novo cliente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PostMapping("/NovoCliente")
    public ResponseEntity<Cliente> NovoCliente(@RequestParam String nome, @RequestParam String sobrenome,
                                               @RequestParam Long cpf, @RequestParam Long rg,
                                               @RequestParam LocalDate dataNascimento, @RequestParam String numero,
                                               @RequestParam String cep, @RequestParam String email,
                                               @RequestParam Long telefone, @RequestParam Long celular)
    { return clientePost.NovoCliente(nome, sobrenome, cpf, rg, dataNascimento, numero, cep, email, telefone, celular);}

    @Operation(summary = "Edita dados do cliente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PutMapping("/AlterarDados")
    public ResponseEntity<Cliente> AlterarDados(@RequestParam Long id, String nome, String sobrenome,
                                                Long cpf,  Long rg, LocalDate dataNascimento)
    { return clientePut.AlterarDados(id, nome, sobrenome, cpf, rg, dataNascimento);}

    @Operation(summary = "Edita dados de Endereço do cliente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PutMapping("/AlterarEndereco")
    public ResponseEntity<Cliente> AlterarEndereco(@RequestParam Long id, @RequestParam String numero,
                                                   @RequestParam Long cep)
    { return clientePut.AlterarEndereco(id, numero, cep);}

    @Operation(summary = "Edita dados de contato do cliente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PutMapping("/AlterarContato")
    public ResponseEntity<Cliente> AlterarContato(@RequestParam Long id, String email, Long telefone, Long celular)
    { return clientePut.AlterarContato(id, email, telefone, celular);}

    @Operation(summary = "Deleta Registro do cliente", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @DeleteMapping("/DeletarCliente")
    public ResponseEntity<Cliente> DeletarCliente(@RequestParam Long id)
    { return clienteDelete.DeletarCliente(id);}
}
