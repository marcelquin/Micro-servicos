package App.ApiRest.Infra.UseCase.Colaboador;

import App.ApiRest.Domain.Colaborador;
import App.ApiRest.Infra.Gateway.ColaboradorGateway;
import App.ApiRest.Infra.Persistence.Enum.EstadoCivil;
import App.ApiRest.Infra.Persistence.Enum.GrauEstudo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class UseCaseColaboradorPost {

    private final ColaboradorGateway colaboradorGateway;

    public UseCaseColaboradorPost(ColaboradorGateway colaboradorGateway) {
        this.colaboradorGateway = colaboradorGateway;
    }

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
    { return colaboradorGateway.NovoCadastro(nome, sobrenome, dataNascimento, logradouro, numero, bairro, cep, cidade, estado, idCargo, email, telefone, celular, instagram, sie, localNascimento, estadoNascimento, nomeMae, nomePai, numeroFilhos,cpf, rg, rGOrgao, rGDataEmissao, carteiraTrabalhoNumero, carteiraTrabalhoSerie, tituloEleitorNumero, tituloEleitorZona, tituloEleitorSessao, tituloEleitorEstado, carteiraReservistaNumero, carteiraReservistaSerie, carteiraReservistaCategoria, pisNumero, pisDataCadastro, pisBanco, pisAgencia, bancoConta, banco, bancoAgencia, grauEstudo, estadoCivil);}

    public ResponseEntity<Colaborador> EnviarDocumentos(@RequestParam Long idColaborador, @RequestPart MultipartFile[] arquivos)
    { return colaboradorGateway.EnviarDocumentos(idColaborador, arquivos);}
}
