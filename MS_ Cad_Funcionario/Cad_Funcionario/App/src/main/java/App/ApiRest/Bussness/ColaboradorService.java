package App.ApiRest.Bussness;

import App.ApiRest.Bussness.File.FileServerService;
import App.ApiRest.Domain.Colaborador;
import App.ApiRest.Infra.Exceptions.EntityNotFoundException;
import App.ApiRest.Infra.Exceptions.NullargumentsException;
import App.ApiRest.Infra.Gateway.ColaboradorGateway;
import App.ApiRest.Infra.Persistence.Entity.*;
import App.ApiRest.Infra.Persistence.Enum.EstadoCivil;
import App.ApiRest.Infra.Persistence.Enum.GrauEstudo;
import App.ApiRest.Infra.Persistence.Repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_DISPOSITION;

@Service
public class ColaboradorService implements ColaboradorGateway {


    private final ColaboradorRepository colaboradorRepository;
    private final EnderecoRepository enderecoRepository;
    private final ContatoRepository contatoRepository;
    private final CargoRepository cargoRepository;
    private final DocumentosRepository documentosRepository;
    private final FilhosRepository filhosRepository;
    private final ArquivoRepository arquivoRepository;
    private final FileServerService fileServerService;

    @Value("#{environment['App.caminhozip']}")
    private String caminhozip;

    public ColaboradorService(ColaboradorRepository colaboradorRepository, EnderecoRepository enderecoRepository, ContatoRepository contatoRepository, CargoRepository cargoRepository, DocumentosRepository documentosRepository, FilhosRepository filhosRepository, ArquivoRepository arquivoRepository, FileServerService fileServerService) {
        this.colaboradorRepository = colaboradorRepository;
        this.enderecoRepository = enderecoRepository;
        this.contatoRepository = contatoRepository;
        this.cargoRepository = cargoRepository;
        this.documentosRepository = documentosRepository;
        this.filhosRepository = filhosRepository;
        this.arquivoRepository = arquivoRepository;
        this.fileServerService = fileServerService;
    }

    @Override
    public ResponseEntity<List<ColaboradorEntity>> listarColaborador()
    {
        try
        {
            return new ResponseEntity<>(colaboradorRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Colaborador> BuscarPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                if(colaboradorRepository.existsById(id))
                {
                    ColaboradorEntity entity = colaboradorRepository.findById(id).get();
                    Colaborador response = new Colaborador(entity.getNome(),entity.getSobrenome(), entity.getDataNascimento(),
                                                            entity.getMatricula(),entity.getCargo().getDepartamento().getNome(),
                                                            entity.getCargo().getNome(),entity.getCargo().getSalario(), entity.getCargo().getCargaHorariaSemanal(),
                                                            entity.getEndereco().getLogradouro(),entity.getEndereco().getNumero(),entity.getEndereco().getBairro(),
                                                            entity.getEndereco().getCep(),entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),
                                                            entity.getContato().getEmail(),entity.getContato().getTelefone(),entity.getContato().getCelular(),
                                                            entity.getContato().getInstagram(),entity.getContato().getSite());
                    return new ResponseEntity<>(response,HttpStatus.OK);
                }
                else
                { throw new EntityNotFoundException(); }
            }
            else
            { throw new NullargumentsException(); }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Resource> downloadFiles(Long id) throws IOException
    {
        try
        {

            if(id != null)
            {
                if(colaboradorRepository.existsById(id))
                {
                    ColaboradorEntity entity = colaboradorRepository.findById(id).get();
                    String filename = entity.getMatricula()+".zip";
                    Path filePath  = Path.of(caminhozip+filename);
                    if (!Files.exists(filePath)) {
                        throw new FileNotFoundException(filename + " was not found on the server");
                    }
                    Resource resource = new UrlResource(filePath.toUri());
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.add("File-Name", filename);
                    httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
                    return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                            .headers(httpHeaders).body(resource);
                }
                else
                { throw new EntityNotFoundException(); }
            }
            else
            { throw new NullargumentsException(); }

        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Colaborador> NovoCadastro(String nome, String sobrenome,LocalDate dataNascimento, String logradouro, String numero, String bairro,
                                                    Long cep, String cidade, String estado, Long idCargo, String email, Long telefone,
                                                    Long celular, String instagram, String sie, String localNascimento,
                                                    String estadoNascimento, String nomeMae, String nomePai, int numeroFilhos, Long cpf, Long rg,
                                                    String rGOrgao, LocalDate rGDataEmissao, Long carteiraTrabalhoNumero,
                                                    String carteiraTrabalhoSerie, Long tituloEleitorNumero, Long tituloEleitorZona,
                                                    Long tituloEleitorSessao, String tituloEleitorEstado, Long carteiraReservistaNumero,
                                                    String carteiraReservistaSerie, String carteiraReservistaCategoria, Long pisNumero,
                                                    LocalDate pisDataCadastro, String pisBanco, Long pisAgencia, Long bancoConta, String banco,
                                                    Long bancoAgencia, GrauEstudo grauEstudo, EstadoCivil estadoCivil)
    {
        try
        {
            if(nome != null && sobrenome != null && dataNascimento != null && logradouro != null && numero != null && bairro != null && cep != null &&
               cidade != null && estado != null && idCargo != null && email != null && telefone != null && rg != null &&
                rGDataEmissao != null && rGOrgao != null && cpf != null && carteiraTrabalhoNumero != null && carteiraTrabalhoSerie != null &&
                tituloEleitorNumero != null && tituloEleitorZona != null && tituloEleitorSessao != null && tituloEleitorEstado != null &&
                grauEstudo != null && estadoCivil != null)
            {
                ColaboradorEntity entity = new ColaboradorEntity();
                entity.setNome(nome);
                entity.setSobrenome(sobrenome);
                entity.setDataNascimento(dataNascimento);
                int matriculanumero = (int) (10000001 + Math.random() * 89999999);
                String matricula = "matr_"+matriculanumero;
                entity.setMatricula(matricula);
               if(cargoRepository.existsById(idCargo))
               {
                   CargoEntity cargo = cargoRepository.findById(idCargo).get();
                   entity.setCargo(cargo);
                   Colaborador response = new Colaborador(nome,sobrenome,dataNascimento,matricula,cargo.getDepartamento().getNome(),
                                                            cargo.getNome(),cargo.getSalario(),cargo.getCargaHorariaSemanal(),
                                                            logradouro,numero,bairro,cep,cidade,estado,email,telefone,celular,
                                                            instagram,sie);
                   EnderecoEntity endereco = new EnderecoEntity(response);
                   endereco.setTimeStamp(LocalDateTime.now());
                   enderecoRepository.save(endereco);
                   entity.setEndereco(endereco);
                   ContatoEntity contato = new ContatoEntity(response);
                   contato.setTimeStamp(LocalDateTime.now());
                   contatoRepository.save(contato);
                   entity.setContato(contato);
                   DocumentosEntity documentos = new DocumentosEntity();
                   documentos.setLocalNascimento(localNascimento);
                   documentos.setEstadoNascimento(estadoNascimento);
                   documentos.setNomeMae(nomeMae);
                   documentos.setNomePai(nomePai);
                   documentos.setCPF(cpf);
                   documentos.setRG(rg);
                   documentos.setRGOrgao(rGOrgao);
                   documentos.setRGDataEmissao(rGDataEmissao);
                   documentos.setCarteiraTrabalhoNumero(carteiraTrabalhoNumero);
                   documentos.setCarteiraReservistaSerie(carteiraReservistaSerie);
                   documentos.setTituloEleitorNumero(tituloEleitorNumero);
                   documentos.setTituloEleitorZona(tituloEleitorZona);
                   documentos.setTituloEleitorSessao(tituloEleitorSessao);
                   documentos.setTituloEleitorEstado(tituloEleitorEstado);
                   documentos.setCarteiraReservistaNumero(carteiraReservistaNumero);
                   documentos.setCarteiraReservistaCategoria(carteiraReservistaCategoria);
                   documentos.setCarteiraReservistaSerie(carteiraReservistaSerie);
                   documentos.setPisNumero(pisNumero);
                   documentos.setPisBanco(pisBanco);
                   documentos.setPisAgencia(pisAgencia);
                   documentos.setPisDataCadastro(pisDataCadastro);
                   documentos.setBancoConta(bancoConta);
                   documentos.setBanco(banco);
                   documentos.setBancoAgencia(bancoAgencia);
                   documentos.setGrauEstudo(grauEstudo);
                   documentos.setEstadoCivil(estadoCivil);
                   documentos.setNumeroFilhos(numeroFilhos);
                   documentos.setTimeStamp(LocalDateTime.now());
                   documentosRepository.save(documentos);
                   entity.setDocumentos(documentos);
                   entity.setTimeStamp(LocalDateTime.now());
                   entity.setAtivo(Boolean.TRUE);
                   colaboradorRepository.save(entity);
                   return  new ResponseEntity<>(response,HttpStatus.CREATED);
               }
               else
               { throw new EntityNotFoundException(); }
            }
            else
            { throw new NullargumentsException(); }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Colaborador> AlterarCargo(Long idColaborador, Long idCargo, LocalDate dataContratacao)
    {
        try
        {
            if(idColaborador != null && idCargo != null)
            {
                if(colaboradorRepository.existsById(idColaborador))
                {
                    ColaboradorEntity entity = colaboradorRepository.findById(idColaborador).get();
                    if(cargoRepository.existsById(idCargo))
                    {
                        CargoEntity cargo = cargoRepository.findById(idCargo).get();
                        entity.setCargo(cargo);
                        colaboradorRepository.save(entity);
                        Colaborador response = new Colaborador(entity.getNome(),entity.getSobrenome(), entity.getDataNascimento(),
                                entity.getMatricula(),entity.getCargo().getDepartamento().getNome(),
                                entity.getCargo().getNome(),entity.getCargo().getSalario(), entity.getCargo().getCargaHorariaSemanal(),
                                entity.getEndereco().getLogradouro(),entity.getEndereco().getNumero(),entity.getEndereco().getBairro(),
                                entity.getEndereco().getCep(),entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),
                                entity.getContato().getEmail(),entity.getContato().getTelefone(),entity.getContato().getCelular(),
                                entity.getContato().getInstagram(),entity.getContato().getSite());
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                    else
                    { throw new EntityNotFoundException(); }
                }
                else
                { throw new EntityNotFoundException(); }
            }
            else
            { throw new NullargumentsException(); }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Colaborador> AlterarDocumento(Long idColaborador, String localNascimento, String estadoNascimento,
                                                        String nomeMae, String nomePai, Long cpf, Long rg,
                                                        String rGOrgao, LocalDate rGDataEmissao, Long carteiraTrabalhoNumero,
                                                        String carteiraTrabalhoSerie, Long tituloEleitorNumero,
                                                        Long tituloEleitorZona, Long tituloEleitorSessao,
                                                        String tituloEleitorEstado, Long carteiraReservistaNumero,
                                                        String carteiraReservistaSerie, String carteiraReservistaCategoria,
                                                        Long pisNumero, LocalDate pisDataCadastro, String pisBanco,
                                                        Long pisAgencia, Long bancoConta, String banco, Long bancoAgencia,
                                                        GrauEstudo grauEstudo, EstadoCivil estadoCivil)
    {
        try
        {
            if(idColaborador != null)
            {
                if(colaboradorRepository.existsById(idColaborador))
                {
                    ColaboradorEntity entity = colaboradorRepository.findById(idColaborador).get();
                    if(documentosRepository.existsById(entity.getDocumentos().getId()))
                    {
                        DocumentosEntity documentos = documentosRepository.findById(entity.getDocumentos().getId()).get();
                        if(localNascimento != null) { documentos.setLocalNascimento(localNascimento);}
                        if(estadoNascimento != null) { documentos.setEstadoNascimento(estadoNascimento);}
                        if(nomeMae != null) {documentos.setNomeMae(nomeMae);}
                        if(nomePai != null) { documentos.setNomePai(nomePai);}
                        if(cpf != null) { documentos.setCPF(cpf);}
                        if(rg != null) { documentos.setRG(rg);}
                        if(rGDataEmissao != null) { documentos.setRGDataEmissao(rGDataEmissao);}
                        if(rGOrgao != null) { documentos.setRGOrgao(rGOrgao);}
                        if(carteiraTrabalhoNumero != null) { documentos.setCarteiraTrabalhoNumero(carteiraTrabalhoNumero);}
                        if(carteiraTrabalhoSerie != null) { documentos.setCarteiraTrabalhoSerie(carteiraTrabalhoSerie);}
                        if(carteiraReservistaNumero != null) { documentos.setCarteiraReservistaNumero(carteiraReservistaNumero);}
                        if(carteiraReservistaCategoria != null) { documentos.setCarteiraReservistaCategoria(carteiraReservistaCategoria);}
                        if(pisBanco != null) { documentos.setPisBanco(pisBanco);}
                        if(pisAgencia != null) { documentos.setPisAgencia(pisAgencia);}
                        if(pisNumero != null) { documentos.setPisNumero(pisNumero);}
                        if(pisDataCadastro != null) { documentos.setPisDataCadastro(pisDataCadastro);}
                        if(banco != null) { documentos.setBanco(banco);}
                        if(bancoAgencia != null) {documentos.setBancoAgencia(bancoAgencia);}
                        if(bancoConta != null) { documentos.setBancoConta(bancoConta);}
                        if(grauEstudo != null) { documentos.setGrauEstudo(grauEstudo);}
                        if(estadoCivil != null) { documentos.setEstadoCivil(estadoCivil);}
                        documentos.setTimeStamp(LocalDateTime.now());
                        documentosRepository.save(documentos);
                        Colaborador response = new Colaborador(entity.getNome(),entity.getSobrenome(), entity.getDataNascimento(),
                                entity.getMatricula(),entity.getCargo().getDepartamento().getNome(),
                                entity.getCargo().getNome(),entity.getCargo().getSalario(), entity.getCargo().getCargaHorariaSemanal(),
                                entity.getEndereco().getLogradouro(),entity.getEndereco().getNumero(),entity.getEndereco().getBairro(),
                                entity.getEndereco().getCep(),entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),
                                entity.getContato().getEmail(),entity.getContato().getTelefone(),entity.getContato().getCelular(),
                                entity.getContato().getInstagram(),entity.getContato().getSite());
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                    else
                    { throw new EntityNotFoundException(); }
                }
                else
                { throw new EntityNotFoundException(); }
            }
            else
            { throw new NullargumentsException(); }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Colaborador> EditarEndereco(Long idColaborador, String logradouro, String numero, String bairro, Long cep, String cidade, String estado)
    {
        try
        {
            if(idColaborador != null)
            {
               if(colaboradorRepository.existsById(idColaborador))
               {
                   ColaboradorEntity entity = colaboradorRepository.findById(idColaborador).get();
                   if(enderecoRepository.existsById(entity.getEndereco().getId()))
                   {
                       EnderecoEntity endereco = enderecoRepository.findById(entity.getEndereco().getId()).get();
                       if(logradouro != null){ endereco.setLogradouro(logradouro);}
                       if(numero != null){endereco.setNumero(numero);}
                       if(bairro != null){endereco.setBairro(bairro);}
                       if(cep != null){endereco.setCep(cep);}
                       if(cidade != null){endereco.setCidade(cidade);}
                       if(estado != null){endereco.setEstado(estado);}
                       endereco.setTimeStamp(LocalDateTime.now());
                       enderecoRepository.save(endereco);
                       Colaborador response = new Colaborador(entity.getNome(),entity.getSobrenome(), entity.getDataNascimento(),
                               entity.getMatricula(),entity.getCargo().getDepartamento().getNome(),
                               entity.getCargo().getNome(),entity.getCargo().getSalario(), entity.getCargo().getCargaHorariaSemanal(),
                               entity.getEndereco().getLogradouro(),entity.getEndereco().getNumero(),entity.getEndereco().getBairro(),
                               entity.getEndereco().getCep(),entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),
                               entity.getContato().getEmail(),entity.getContato().getTelefone(),entity.getContato().getCelular(),
                               entity.getContato().getInstagram(),entity.getContato().getSite());
                       return new ResponseEntity<>(response, HttpStatus.OK);
                   }
                   else
                   { throw new EntityNotFoundException();}
               }
               else
               { throw new EntityNotFoundException();}
            }
            else
            { throw  new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Colaborador> EditarContato(Long idColaborador, String email, Long telefone, Long celular, String instagram, String sie)
    {
        try
        {
            if(idColaborador != null)
            {
                if(colaboradorRepository.existsById(idColaborador))
                {
                    ColaboradorEntity entity = colaboradorRepository.findById(idColaborador).get();
                    if(contatoRepository.existsById(entity.getContato().getId()))
                    {
                        ContatoEntity contato = contatoRepository.findById(entity.getContato().getId()).get();
                        if(email != null){ contato.setEmail(email);}
                        if(telefone != null){ contato.setTelefone(telefone);}
                        if(celular != null){ contato.setCelular(celular);}
                        if(instagram != null){ contato.setInstagram(instagram);}
                        if(sie != null){ contato.setSite(sie);}
                        contato.setTimeStamp(LocalDateTime.now());
                        contatoRepository.save(contato);
                        Colaborador response = new Colaborador(entity.getNome(),entity.getSobrenome(), entity.getDataNascimento(),
                                entity.getMatricula(),entity.getCargo().getDepartamento().getNome(),
                                entity.getCargo().getNome(),entity.getCargo().getSalario(), entity.getCargo().getCargaHorariaSemanal(),
                                entity.getEndereco().getLogradouro(),entity.getEndereco().getNumero(),entity.getEndereco().getBairro(),
                                entity.getEndereco().getCep(),entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),
                                entity.getContato().getEmail(),entity.getContato().getTelefone(),entity.getContato().getCelular(),
                                entity.getContato().getInstagram(),entity.getContato().getSite());
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                    else
                    { throw new EntityNotFoundException();}
                }
                else
                { throw new EntityNotFoundException();}
            }
            else
            { throw  new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Colaborador> EnviarDocumentos(Long idColaborador, MultipartFile[] arquivos)
    {
        try
        {
            if(idColaborador != null && arquivos != null)
            {
              if(colaboradorRepository.existsById(idColaborador))
              {
                  ColaboradorEntity entity = colaboradorRepository.findById(idColaborador).get();
                  if(documentosRepository.existsById(entity.getDocumentos().getId()))
                  {
                      DocumentosEntity documentos = documentosRepository.findById(entity.getDocumentos().getId()).get();
                      List<ArquivosEntity> arquivosLista = new ArrayList<>();
                      for(MultipartFile arquivo : arquivos)
                      {
                          ArquivosEntity arquivosEntity = new ArquivosEntity();
                          arquivosEntity.setNomeArquivo(arquivo.getOriginalFilename());
                          arquivosEntity.setTimeStamp(LocalDateTime.now());
                          arquivoRepository.save(arquivosEntity);
                          arquivosLista.add(arquivosEntity);
                      }
                      documentos.setArquivos(arquivosLista);
                      documentos.setTimeStamp(LocalDateTime.now());
                      documentosRepository.save(documentos);
                      fileServerService.Upload(entity.getMatricula(), arquivos);
                      Colaborador response = new Colaborador(entity.getNome(),entity.getSobrenome(), entity.getDataNascimento(),
                              entity.getMatricula(),entity.getCargo().getDepartamento().getNome(),
                              entity.getCargo().getNome(),entity.getCargo().getSalario(), entity.getCargo().getCargaHorariaSemanal(),
                              entity.getEndereco().getLogradouro(),entity.getEndereco().getNumero(),entity.getEndereco().getBairro(),
                              entity.getEndereco().getCep(),entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),
                              entity.getContato().getEmail(),entity.getContato().getTelefone(),entity.getContato().getCelular(),
                              entity.getContato().getInstagram(),entity.getContato().getSite());
                      return new ResponseEntity<>(response, HttpStatus.OK);
                  }
                  else
                  { throw new EntityNotFoundException();}
              }
              else
              { throw new EntityNotFoundException();}
            }
            else
            { throw  new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Colaborador> AdicionarArquivos(Long idColaborador, MultipartFile[] arquivos)
    {
        try
        {
            if(idColaborador != null && arquivos != null)
            {
                if(colaboradorRepository.existsById(idColaborador))
                {
                    ColaboradorEntity entity = colaboradorRepository.findById(idColaborador).get();
                    if(documentosRepository.existsById(entity.getDocumentos().getId()))
                    {
                        DocumentosEntity documentos = documentosRepository.findById(entity.getDocumentos().getId()).get();
                        List<ArquivosEntity> arquivosLista = new ArrayList<>();
                        for(MultipartFile arquivo : arquivos)
                        {
                            ArquivosEntity arquivosEntity = new ArquivosEntity();
                            arquivosEntity.setNomeArquivo(arquivo.getOriginalFilename());
                            arquivosEntity.setTimeStamp(LocalDateTime.now());
                            arquivoRepository.save(arquivosEntity);
                            arquivosLista.add(arquivosEntity);

                        }
                        documentos.setArquivos(arquivosLista);
                        documentos.setTimeStamp(LocalDateTime.now());
                        documentosRepository.save(documentos);
                        fileServerService.AddFile(entity.getMatricula(), arquivos);
                        Colaborador response = new Colaborador(entity.getNome(),entity.getSobrenome(), entity.getDataNascimento(),
                                entity.getMatricula(),entity.getCargo().getDepartamento().getNome(),
                                entity.getCargo().getNome(),entity.getCargo().getSalario(), entity.getCargo().getCargaHorariaSemanal(),
                                entity.getEndereco().getLogradouro(),entity.getEndereco().getNumero(),entity.getEndereco().getBairro(),
                                entity.getEndereco().getCep(),entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),
                                entity.getContato().getEmail(),entity.getContato().getTelefone(),entity.getContato().getCelular(),
                                entity.getContato().getInstagram(),entity.getContato().getSite());
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                    else
                    { throw new EntityNotFoundException();}
                }
                else
                { throw new EntityNotFoundException();}
            }
            else
            { throw  new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Colaborador> CadastrarFilhos(Long id, String nome, Long cpf, MultipartFile[] arquivos)
    {
        try
        {
            if(id != null && nome != null && cpf != null && arquivos != null)
            {
                if(colaboradorRepository.existsById(id))
                {
                   ColaboradorEntity entity = colaboradorRepository.findById(id).get();
                    if(documentosRepository.existsById(entity.getDocumentos().getId()))
                    {
                        DocumentosEntity documentos = documentosRepository.findById(entity.getDocumentos().getId()).get();
                        FilhosEntity filhos = new FilhosEntity();
                        filhos.setNomeCompleto(nome);
                        filhos.setCpf(cpf);
                        List<ArquivosEntity>ArquivosLista = new ArrayList<>();
                        for(MultipartFile arquivo : arquivos)
                        {
                            ArquivosEntity arquivosEntity = new ArquivosEntity();
                            arquivosEntity.setNomeArquivo(arquivo.getOriginalFilename());
                            arquivosEntity.setTimeStamp(LocalDateTime.now());
                            arquivoRepository.save(arquivosEntity);
                            ArquivosLista.add(arquivosEntity);
                        }
                        filhos.setArquivos(ArquivosLista);
                        filhos.setTimeStamp(LocalDateTime.now());
                        filhosRepository.save(filhos);
                        documentos.getFilhos().add(filhos);
                        documentos.setTimeStamp(LocalDateTime.now());
                        documentosRepository.save(documentos);
                        Colaborador response = new Colaborador(entity.getNome(),entity.getSobrenome(), entity.getDataNascimento(),
                                entity.getMatricula(),entity.getCargo().getDepartamento().getNome(),
                                entity.getCargo().getNome(),entity.getCargo().getSalario(), entity.getCargo().getCargaHorariaSemanal(),
                                entity.getEndereco().getLogradouro(),entity.getEndereco().getNumero(),entity.getEndereco().getBairro(),
                                entity.getEndereco().getCep(),entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),
                                entity.getContato().getEmail(),entity.getContato().getTelefone(),entity.getContato().getCelular(),
                                entity.getContato().getInstagram(),entity.getContato().getSite());
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                    else
                    {throw new EntityNotFoundException();}
                }
                else
                {throw new EntityNotFoundException();}
            }
            else
            {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Colaborador> DesativarColaborador(Long id, Boolean desativar, String motivo)
    {
        try
        {
            if(id != null && desativar != null && motivo != null)
            {
                if(colaboradorRepository.existsById(id))
                {
                    ColaboradorEntity entity = colaboradorRepository.findById(id).get();
                    if(desativar != entity.getAtivo())
                    {
                        entity.setAtivo(desativar);
                        entity.setMotivoDemissao(motivo);
                        entity.setTimeStamp(LocalDateTime.now());
                        colaboradorRepository.save(entity);
                        Colaborador response = new Colaborador(entity.getNome(),entity.getSobrenome(), entity.getDataNascimento(),
                                entity.getMatricula(),entity.getCargo().getDepartamento().getNome(),
                                entity.getCargo().getNome(),entity.getCargo().getSalario(), entity.getCargo().getCargaHorariaSemanal(),
                                entity.getEndereco().getLogradouro(),entity.getEndereco().getNumero(),entity.getEndereco().getBairro(),
                                entity.getEndereco().getCep(),entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),
                                entity.getContato().getEmail(),entity.getContato().getTelefone(),entity.getContato().getCelular(),
                                entity.getContato().getInstagram(),entity.getContato().getSite());
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                }
                else
                { throw new EntityNotFoundException();}
            }
            else
            { throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

}
