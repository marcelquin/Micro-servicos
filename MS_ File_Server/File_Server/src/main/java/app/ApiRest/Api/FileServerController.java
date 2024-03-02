package app.ApiRest.Api;

import app.ApiRest.Domain.Arquivo;
import app.ApiRest.Infra.UseCases.UseCaseArquivo;
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
import java.util.List;

@RestController
@RequestMapping("ms_FileServer")
@Tag(name = "File server", description = "Micro serviço de upload de arquivo")
public class FileServerController {

    private final UseCaseArquivo caseArquivo;


    public FileServerController(UseCaseArquivo caseArquivo) {
        this.caseArquivo = caseArquivo;
    }

    @Operation(summary = "Busca e libera o download do arquivo zipado", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/downloadFiles")
    public ResponseEntity<Resource> downloadFiles(@RequestParam String nome,@RequestParam String codigo) throws IOException
    {return caseArquivo.downloadFiles(nome,codigo);}

    @Operation(summary = "Salva Novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping(value = "/SalvarArquivo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Arquivo> SalvarArquivo(@RequestParam String nome, @RequestPart MultipartFile[] files, @RequestParam String codigo) throws IOException
    { return caseArquivo.SalvarArquivo(nome, files, codigo);}


    @Operation(summary = "Altera arquivos do pacote", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping(value = "/AlterarArquivo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Arquivo> AlterarArquivo(@RequestParam String nome, @RequestParam List<String> arquivos, @RequestPart MultipartFile[] files, @RequestParam String codigo) throws IOException
    { return caseArquivo.AlterarArquivo(nome, arquivos, files, codigo);}


    @Operation(summary = "Adiciona arquivo ao pacote", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping(value = "/AdicionarArquivo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Arquivo> AdicionarArquivo(@RequestParam String nome, @RequestPart MultipartFile[] files, @RequestParam String codigo) throws IOException
    { return caseArquivo.AdicionarArquivo(nome, files, codigo);}

}
