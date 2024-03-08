package App.ApiRest.Api;

import App.ApiRest.Domain.Colaborador;
import App.ApiRest.Infra.Persistence.Entity.ColaboradorEntity;
import App.ApiRest.Infra.Persistence.Enum.EstadoCivil;
import App.ApiRest.Infra.Persistence.Enum.GrauEstudo;
import App.ApiRest.Infra.UseCase.Colaboador.UseCaseColaboradorGet;
import App.ApiRest.Infra.UseCase.Colaboador.UseCaseColaboradorPost;
import App.ApiRest.Infra.UseCase.Colaboador.UseCaseColaboradorPut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("colaorador")
@Tag(name = "colaborador", description = "Responsavel pela manipulação dos dados da entidade")
public class ColaboradorController {

    private final UseCaseColaboradorGet colaboradorGet;
    private final UseCaseColaboradorPost colaboradorPost;
    private final UseCaseColaboradorPut colaboradorPut;

    public ColaboradorController(UseCaseColaboradorGet colaboradorGet, UseCaseColaboradorPost colaboradorPost, UseCaseColaboradorPut colaboradorPut) {
        this.colaboradorGet = colaboradorGet;
        this.colaboradorPost = colaboradorPost;
        this.colaboradorPut = colaboradorPut;
    }

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/listarColaborador")
    public ResponseEntity<List<ColaboradorEntity>> listarColaborador()
    { return colaboradorGet.listarColaborador();}

    @Operation(summary = "Busca Registro por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarPorId")
    public ResponseEntity<Colaborador> BuscarPorId(@RequestParam Long id)
    { return colaboradorGet.BuscarPorId(id);}

    @Operation(summary = "Busca Registro por id e disponibiliza download", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/downloadFiles")
    public ResponseEntity<Resource> downloadFiles(@RequestParam Long id) throws IOException
    { return colaboradorGet.downloadFiles(id);}

    @Operation(summary = "Salva novo colaborador", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoCadastro")
    public ResponseEntity<Colaborador> NovoCadastro(@RequestParam String nome, @RequestParam String sobrenome, @RequestParam LocalDate dataNascimento,
                                                    @RequestParam String logradouro, @RequestParam String numero,
                                                    @RequestParam String bairro, @RequestParam Long cep,
                                                    @RequestParam String cidade, @RequestParam String estado,
                                                    @RequestParam Long idCargo, @RequestParam String email,
                                                    @RequestParam Long telefone, @RequestParam Long celular,
                                                    @RequestParam String instagram, @RequestParam String sie,
                                                    @RequestParam String localNascimento, @RequestParam String estadoNascimento,
                                                    @RequestParam String nomeMae, @RequestParam String nomePai, @RequestParam int numeroFilhos,
                                                    @RequestParam Long cpf, @RequestParam Long rg, @RequestParam String rGOrgao,
                                                    @RequestParam LocalDate rGDataEmissao, @RequestParam Long carteiraTrabalhoNumero,
                                                    @RequestParam String carteiraTrabalhoSerie, @RequestParam Long tituloEleitorNumero,
                                                    @RequestParam Long tituloEleitorZona, @RequestParam Long tituloEleitorSessao,
                                                    @RequestParam String tituloEleitorEstado, @RequestParam Long carteiraReservistaNumero,
                                                    @RequestParam String carteiraReservistaSerie, @RequestParam String carteiraReservistaCategoria,
                                                    @RequestParam Long pisNumero, @RequestParam LocalDate pisDataCadastro, @RequestParam String pisBanco,
                                                    @RequestParam Long pisAgencia, @RequestParam Long bancoConta, @RequestParam String banco,
                                                    @RequestParam Long bancoAgencia, @RequestParam GrauEstudo grauEstudo, @RequestParam EstadoCivil estadoCivil)
    { return colaboradorPost.NovoCadastro(nome, sobrenome, dataNascimento, logradouro, numero, bairro, cep, cidade, estado, idCargo, email, telefone, celular, instagram, sie, localNascimento, estadoNascimento, nomeMae, nomePai, numeroFilhos, cpf, rg, rGOrgao, rGDataEmissao, carteiraTrabalhoNumero, carteiraTrabalhoSerie, tituloEleitorNumero, tituloEleitorZona, tituloEleitorSessao, tituloEleitorEstado, carteiraReservistaNumero, carteiraReservistaSerie, carteiraReservistaCategoria, pisNumero, pisDataCadastro, pisBanco, pisAgencia, bancoConta, banco, bancoAgencia, grauEstudo, estadoCivil);}

    @Operation(summary = "Altera dados de Cargo do colaborador", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarCargo")
    public ResponseEntity<Colaborador> AlterarCargo(@RequestParam Long idColaborador, @RequestParam Long idCargo,@RequestParam LocalDate dataContratacao)
    { return colaboradorPut.AlterarCargo(idColaborador, idCargo, dataContratacao);}

    @Operation(summary = "Altera dados de Documentos do colaborador", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarDocumento")
    public ResponseEntity<Colaborador> AlterarDocumento(@RequestParam Long idColaborador, String localNascimento, String estadoNascimento,
                                                        String nomeMae, String nomePai, Long cpf, Long rg, String rGOrgao, LocalDate rGDataEmissao,
                                                        Long carteiraTrabalhoNumero, String carteiraTrabalhoSerie, Long tituloEleitorNumero,
                                                        Long tituloEleitorZona,  Long tituloEleitorSessao, String tituloEleitorEstado, Long carteiraReservistaNumero,
                                                        String carteiraReservistaSerie,  String carteiraReservistaCategoria, Long pisNumero,
                                                        LocalDate pisDataCadastro, String pisBanco, Long pisAgencia, Long bancoConta, String banco,
                                                        Long bancoAgencia, GrauEstudo grauEstudo, EstadoCivil estadoCivil)
    {return colaboradorPut.AlterarDocumento(idColaborador, localNascimento, estadoNascimento, nomeMae, nomePai, cpf, rg, rGOrgao, rGDataEmissao, carteiraTrabalhoNumero, carteiraTrabalhoSerie, tituloEleitorNumero, tituloEleitorZona, tituloEleitorSessao, tituloEleitorEstado, carteiraReservistaNumero, carteiraReservistaSerie, carteiraReservistaCategoria, pisNumero, pisDataCadastro, pisBanco, pisAgencia, bancoConta, banco, bancoAgencia, grauEstudo, estadoCivil);}

    @Operation(summary = "Altera dados de Endereço do colaborador", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/EditarEndereco")
    public ResponseEntity<Colaborador> EditarEndereco(@RequestParam Long idColaborador, String logradouro, String numero,
                                                      String bairro, Long cep, String cidade, String estado)
    { return colaboradorPut.EditarEndereco(idColaborador, logradouro, numero, bairro, cep, cidade, estado);}

    @Operation(summary = "Altera dados de contato do colaborador", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/EditarContato")
    public ResponseEntity<Colaborador> EditarContato(@RequestParam Long idColaborador,String email, Long telefone, Long celular,
                                                     String instagram, String site)
    { return colaboradorPut.EditarContato(idColaborador, email, telefone, celular, instagram, site);}

    @Operation(summary = "Envia documentos ao registro", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping(value = "/EnviarDocumentos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Colaborador> EnviarDocumentos(@RequestParam Long idColaborador, MultipartFile[] arquivos)
    { return colaboradorPost.EnviarDocumentos(idColaborador, arquivos);}

    @Operation(summary = "Adiciona documentos ao registro", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping(value = "/AdicionarArquivos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Colaborador> AdicionarArquivos(@RequestParam Long idColaborador,@RequestPart MultipartFile[] arquivos)
    { return colaboradorPut.AdicionarArquivos(idColaborador, arquivos);}


    @Operation(summary = "Cadastra dados de filhos existentes", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping(value = "/CadastrarFilhos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Colaborador> CadastrarFilhos(@RequestParam Long id, @RequestParam String nome, @RequestParam Long cpf, @RequestPart MultipartFile[] arquivo)
    { return colaboradorPut.CadastrarFilhos(id, nome, cpf, arquivo);}

    @Operation(summary = "desativa Colaborador da função", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/DesativarColaborador")
    public ResponseEntity<Colaborador> DesativarColaborador(@RequestParam Long id, @RequestParam Boolean desativar, @RequestParam String motivo)
    { return colaboradorPut.DesativarColaborador(id, desativar, motivo);}
}
