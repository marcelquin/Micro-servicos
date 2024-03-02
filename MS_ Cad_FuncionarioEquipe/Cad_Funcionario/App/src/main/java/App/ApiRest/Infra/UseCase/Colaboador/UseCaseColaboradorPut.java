package App.ApiRest.Infra.UseCase.Colaboador;

import App.ApiRest.Domain.Cargo;
import App.ApiRest.Domain.Colaborador;
import App.ApiRest.Infra.Gateway.ColaboradorGateway;
import App.ApiRest.Infra.Persistence.Enum.EstadoCivil;
import App.ApiRest.Infra.Persistence.Enum.GrauEstudo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class UseCaseColaboradorPut {

    private final ColaboradorGateway colaboradorGateway;

    public UseCaseColaboradorPut(ColaboradorGateway colaboradorGateway) {
        this.colaboradorGateway = colaboradorGateway;
    }
    public ResponseEntity<Colaborador> EditarDepartamento(@RequestParam Long idColaborador, @RequestParam Long idCargo, @RequestParam LocalDate dataContratacao)
    { return colaboradorGateway.AlterarCargo(idColaborador, idCargo,dataContratacao);}

    public ResponseEntity<Colaborador> AlterarDocumento(@RequestParam Long idColaborador, String localNascimento, String estadoNascimento,
                                                        String nomeMae, String nomePai, Long cpf, Long rg, String rGOrgao, LocalDate rGDataEmissao,
                                                        Long carteiraTrabalhoNumero, String carteiraTrabalhoSerie, Long tituloEleitorNumero,
                                                        Long tituloEleitorZona,  Long tituloEleitorSessao, String tituloEleitorEstado, Long carteiraReservistaNumero,
                                                        String carteiraReservistaSerie,  String carteiraReservistaCategoria, Long pisNumero,
                                                        LocalDate pisDataCadastro, String pisBanco, Long pisAgencia, Long bancoConta, String banco,
                                                        Long bancoAgencia, GrauEstudo grauEstudo, EstadoCivil estadoCivil)
    {return  colaboradorGateway.AlterarDocumento(idColaborador, localNascimento, estadoNascimento, nomeMae, nomePai, cpf, rg, rGOrgao, rGDataEmissao, carteiraTrabalhoNumero, carteiraTrabalhoSerie, tituloEleitorNumero, tituloEleitorZona, tituloEleitorSessao, tituloEleitorEstado, carteiraReservistaNumero, carteiraReservistaSerie, carteiraReservistaCategoria, pisNumero, pisDataCadastro, pisBanco, pisAgencia, bancoConta, banco, bancoAgencia, grauEstudo, estadoCivil);}

    public ResponseEntity<Colaborador> AlterarCargo(@RequestParam Long idColaborador, @RequestParam Long idCargo,@RequestParam LocalDate dataContratacao)
    {return colaboradorGateway.AlterarCargo(idColaborador, idCargo, dataContratacao);}


    public ResponseEntity<Colaborador> EditarEndereco(@RequestParam Long idColaborador, String logradouro, String numero,
                                                      String bairro, Long cep, String cidade, String estado)
    {return colaboradorGateway.EditarEndereco(idColaborador, logradouro, numero, bairro, cep, cidade, estado);}
    public ResponseEntity<Colaborador> EditarContato(@RequestParam Long idColaborador,String email, Long telefone, Long celular,
                                                     String instagram, String sie)
    { return colaboradorGateway.EditarContato(idColaborador, email, telefone, celular, instagram, sie);}


    public ResponseEntity<Colaborador> CadastrarFilhos(@RequestParam Long id, @RequestParam String nome, @RequestParam Long cpf, @RequestPart MultipartFile[] arquivo)
    { return  colaboradorGateway.CadastrarFilhos(id, nome, cpf, arquivo);}


    public ResponseEntity<Colaborador> DesativarColaborador(@RequestParam Long id, @RequestParam Boolean desativar, @RequestParam String motivo)
    { return colaboradorGateway.DesativarColaborador(id, desativar, motivo);}
}
