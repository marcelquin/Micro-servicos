package App.ApiRest.Infra.Gateway;

import App.ApiRest.Domain.Cargo;
import App.ApiRest.Domain.Colaborador;
import App.ApiRest.Infra.Persistence.Entity.ColaboradorEntity;
import App.ApiRest.Infra.Persistence.Enum.EstadoCivil;
import App.ApiRest.Infra.Persistence.Enum.GrauEstudo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface ColaboradorGateway {

    public ResponseEntity<List<ColaboradorEntity>> listarColaborador();

    public ResponseEntity<Colaborador> BuscarPorId(@RequestParam Long id);

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
                                                    @RequestParam Long tituloEleitorZona,  @RequestParam Long tituloEleitorSessao,
                                                    @RequestParam String tituloEleitorEstado, @RequestParam Long carteiraReservistaNumero,
                                                    @RequestParam String carteiraReservistaSerie,  @RequestParam String carteiraReservistaCategoria,
                                                    @RequestParam Long pisNumero, @RequestParam LocalDate pisDataCadastro, @RequestParam String pisBanco,
                                                    @RequestParam Long pisAgencia, @RequestParam Long bancoConta, @RequestParam String banco,
                                                    @RequestParam Long bancoAgencia, @RequestParam GrauEstudo grauEstudo, @RequestParam EstadoCivil estadoCivil);

    public ResponseEntity<Colaborador> AlterarCargo(@RequestParam Long idColaborador, @RequestParam Long idCargo,@RequestParam LocalDate dataContratacao);

    public ResponseEntity<Colaborador> AlterarDocumento(@RequestParam Long idColaborador, String localNascimento, String estadoNascimento,
                                                        String nomeMae, String nomePai, Long cpf, Long rg, String rGOrgao, LocalDate rGDataEmissao,
                                                        Long carteiraTrabalhoNumero, String carteiraTrabalhoSerie, Long tituloEleitorNumero,
                                                        Long tituloEleitorZona,  Long tituloEleitorSessao, String tituloEleitorEstado, Long carteiraReservistaNumero,
                                                        String carteiraReservistaSerie,  String carteiraReservistaCategoria, Long pisNumero,
                                                        LocalDate pisDataCadastro, String pisBanco, Long pisAgencia, Long bancoConta, String banco,
                                                        Long bancoAgencia, GrauEstudo grauEstudo, EstadoCivil estadoCivil);


    public ResponseEntity<Colaborador> EditarEndereco(@RequestParam Long idColaborador, String logradouro, String numero,
                                                      String bairro, Long cep, String cidade, String estado);
    public ResponseEntity<Colaborador> EditarContato(@RequestParam Long idColaborador,String email, Long telefone, Long celular,
                                                     String instagram, String sie);

    public ResponseEntity<Colaborador> EnviarDocumentos(@RequestParam Long idColaborador, MultipartFile[] arquivos);


    public ResponseEntity<Colaborador> CadastrarFilhos(@RequestParam Long id, @RequestParam String nome, @RequestParam Long cpf, @RequestPart MultipartFile[] arquivo);

    public ResponseEntity<Colaborador> DesativarColaborador(@RequestParam Long id, @RequestParam Boolean desativar, @RequestParam String motivo);


}
