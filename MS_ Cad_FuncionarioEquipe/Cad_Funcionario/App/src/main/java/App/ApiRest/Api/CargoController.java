package App.ApiRest.Api;

import App.ApiRest.Domain.Cargo;
import App.ApiRest.Infra.Persistence.Entity.CargoEntity;
import App.ApiRest.Infra.UseCase.Cargo.UseCaseCargoGet;
import App.ApiRest.Infra.UseCase.Cargo.UseCaseCargoPost;
import App.ApiRest.Infra.UseCase.Cargo.UseCaseCargoPut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cargo")
@Tag(name = "cargo", description = "Responsavel pela manipulação dos dados da entidade")
public class CargoController {
    private final UseCaseCargoGet cargoGet;
    private final UseCaseCargoPut cargoPut;
    private final UseCaseCargoPost cargoPost;

    public CargoController(UseCaseCargoGet cargoGet, UseCaseCargoPut cargoPut, UseCaseCargoPost cargoPost) {
        this.cargoGet = cargoGet;
        this.cargoPut = cargoPut;
        this.cargoPost = cargoPost;
    }

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/listarCargos")
    public ResponseEntity<List<CargoEntity>> listarCargos()
    { return cargoGet.listarCargos();}


    @Operation(summary = "Busca Registro por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarPorId")
    public ResponseEntity<Cargo> BuscarPorId(@RequestParam Long id)
    { return cargoGet.BuscarPorId(id);}

    @Operation(summary = "Salva novo registro", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoCargo")
    public ResponseEntity<Cargo> NovoCargo(@RequestParam String nomeCargo, @RequestParam String descrisaoCargo,
                                           @RequestParam Long idDepartamento, @RequestParam Double salario,
                                           @RequestParam Double cargaHorariaSemanal)
    { return cargoPost.NovoCargo(nomeCargo, descrisaoCargo, idDepartamento, salario, cargaHorariaSemanal);}

    @Operation(summary = "Edita informações do registro", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/EditarCargo")
    public ResponseEntity<Cargo> EditarCargo(@RequestParam Long id, String nomeCargo, String descrisaoCargo,
                                             Double salario, Double cargaHorariaSemanal)
    { return cargoPut.EditarCargo(id, nomeCargo, descrisaoCargo, salario, cargaHorariaSemanal);}

    @Operation(summary = "Desativa Cargo", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/desativarCargo")
    public ResponseEntity<Cargo> desativarCargo(@RequestParam Long id, @RequestParam Boolean desativar)
    { return cargoPut.desativarCargo(id, desativar);}
}
