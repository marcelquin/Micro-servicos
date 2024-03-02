package App.ApiRest.Api;

import App.ApiRest.Domain.Equipe;
import App.ApiRest.Infra.Persistence.Entity.EquipeEntity;
import App.ApiRest.Infra.UseCase.Equipe.UseCaseEquipeGet;
import App.ApiRest.Infra.UseCase.Equipe.UseCaseEquipePost;
import App.ApiRest.Infra.UseCase.Equipe.UseCaseEquipePut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("equipe")
@Tag(name = "equipe", description = "Manipula dados relacionados a entidade")
public class EquipeController {

    private final UseCaseEquipeGet equipeGet;
    private final UseCaseEquipePost equipePost;
    private final UseCaseEquipePut equipePut;

    public EquipeController(UseCaseEquipeGet equipeGet, UseCaseEquipePost equipePost, UseCaseEquipePut equipePut) {
        this.equipeGet = equipeGet;
        this.equipePost = equipePost;
        this.equipePut = equipePut;
    }
    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarEquipes")
    public ResponseEntity<List<EquipeEntity>> ListarEquipes()
    { return equipeGet.ListarEquipes();}

    @Operation(summary = "Busca Registros Por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarEquipePorId")
    public ResponseEntity<Equipe> BuscarEquipePorId(@RequestParam Long id)
    { return equipeGet.BuscarEquipePorId(id);}

    @Operation(summary = "Salva novo registro", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/NovaEquipe")
    public ResponseEntity<Equipe> NovaEquipe(@RequestParam Long idDepartamento, @RequestParam Long[] idCargos, @RequestParam Long[] idColaboradores, @RequestParam String nome, @RequestParam String descrisao)
    { return equipePost.NovaEquipe(idDepartamento, idCargos, idColaboradores, nome, descrisao);}

    @Operation(summary = "Altera Nome e descrisão da equipe", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ALterarDadosEquipe")
    public ResponseEntity<Equipe> ALterarDadosEquipe(@RequestParam Long idEquipe, String nome, String descrisao)
    { return equipePut.ALterarDadosEquipe(idEquipe, nome, descrisao);}

    @Operation(summary = "Adiciona colaborador a equipe", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/AdicionarColaborador")
    public ResponseEntity<Equipe> AdicionarColaborador(@RequestParam Long idEquipe,@RequestParam Long[]idColaboradores)
    { return equipePut.AdicionarColaborador(idEquipe, idColaboradores);}
}
