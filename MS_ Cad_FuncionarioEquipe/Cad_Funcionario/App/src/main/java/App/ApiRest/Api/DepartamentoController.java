package App.ApiRest.Api;

import App.ApiRest.Domain.Departamento;
import App.ApiRest.Infra.Persistence.Entity.DepartamentoEntity;
import App.ApiRest.Infra.UseCase.Departamento.UseCaseDepartamentoGet;
import App.ApiRest.Infra.UseCase.Departamento.UseCaseDepartamentoPost;
import App.ApiRest.Infra.UseCase.Departamento.UseCaseDepartamentoPut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("departamento")
@Tag(name = "Departamento", description = "Responsavel pela manipulação dos dados da entidade")
public class DepartamentoController {

    private final UseCaseDepartamentoGet departamentoGet;
    private final UseCaseDepartamentoPost departamentoPost;
    private final UseCaseDepartamentoPut departamentoPut;

    public DepartamentoController(UseCaseDepartamentoGet departamentoGet, UseCaseDepartamentoPost departamentoPost, UseCaseDepartamentoPut departamentoPut) {
        this.departamentoGet = departamentoGet;
        this.departamentoPost = departamentoPost;
        this.departamentoPut = departamentoPut;
    }

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/listarDepartamento")
    public ResponseEntity<List<DepartamentoEntity>> listarDepartamento()
    { return departamentoGet.listarDepartamento();}

    @Operation(summary = "Busca Registro por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarDepartamentoPorId")
    public ResponseEntity<Departamento> BuscarDepartamentoPorId(@RequestParam Long id)
    {return departamentoGet.BuscarDepartamentoPorId(id);}

    @Operation(summary = "Salva novo Registro", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoDepartamento")
    public ResponseEntity<Departamento> NovoDepartamento(@RequestParam String depNome, @RequestParam String depDescrisao)
    {return departamentoPost.NovoDepartamento(depNome, depDescrisao);}

    @Operation(summary = "Edita Registro", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/EditarDepartamento")
    public ResponseEntity<Departamento> EditarDepartamento(@RequestParam Long id,@RequestParam String nome, @RequestParam String descrisao)
    {return departamentoPut.EditarDepartamento(id, nome, descrisao);}

    @Operation(summary = "Desativa Departamento Registro", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/desativarDepartamento")
    public ResponseEntity<Departamento> desativarDepartamento(@RequestParam Long id, @RequestParam Boolean desativar)
    { return departamentoPut.desativarDepartamento(id, desativar);}
}
