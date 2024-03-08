package App.ApiRest.Bussness;

import App.ApiRest.Domain.Cliente;
import App.ApiRest.Domain.Endereco;
import App.ApiRest.Infra.Exceptions.EntityNotFoundException;
import App.ApiRest.Infra.Exceptions.NullargumentsException;
import App.ApiRest.Infra.Gateway.ClienteGateway;
import App.ApiRest.Infra.Persistence.Entity.ClienteEntity;
import App.ApiRest.Infra.Persistence.Entity.ContatoEntity;
import App.ApiRest.Infra.Persistence.Entity.EnderecoEntity;
import App.ApiRest.Infra.Persistence.Repository.ClienteRepository;
import App.ApiRest.Infra.Persistence.Repository.ContatoRepository;
import App.ApiRest.Infra.Persistence.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_DISPOSITION;

@Service
public class ClienteService implements ClienteGateway {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final ContatoRepository contatoRepository;

    private RestTemplate restTemplate = new RestTemplate();

    public ClienteService(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, ContatoRepository contatoRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.contatoRepository = contatoRepository;
    }

    @Override
    public ResponseEntity<List<ClienteEntity>> ListarCliente()
    {
        try
        {
            return new ResponseEntity<>(clienteRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Cliente> BuscarClientePorId(Long id)
    {
        try
        {
            if(id != null)
            {
                if(clienteRepository.existsById(id))
                {
                    ClienteEntity entity = clienteRepository.findById(id).get();
                    Cliente response = new Cliente(entity.getNome(),entity.getSobrenome(),entity.getRg(),
                            entity.getCpf(), entity.getCodigoIdentificador(), entity.getDataNascimento(),entity.getEndereco().getCep(),entity.getEndereco().getLogradouro(),
                            entity.getEndereco().getNumero(),entity.getEndereco().getComplemento(),entity.getEndereco().getBairro(),
                            entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),entity.getContato().getEmail(),
                            entity.getContato().getTelefone(),entity.getContato().getCelular());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                { throw new EntityNotFoundException();}
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
    public ResponseEntity<Cliente> BuscarClientePorNome(String nome)
    {
        try
        {
            if(nome != null)
            {
                if(clienteRepository.existsBynome(nome))
                {
                    ClienteEntity entity = clienteRepository.findBynome(nome).get();
                    Cliente response = new Cliente(entity.getNome(),entity.getSobrenome(),entity.getRg(),
                            entity.getCpf(), entity.getCodigoIdentificador(), entity.getDataNascimento(),entity.getEndereco().getCep(),entity.getEndereco().getLogradouro(),
                            entity.getEndereco().getNumero(),entity.getEndereco().getComplemento(),entity.getEndereco().getBairro(),
                            entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),entity.getContato().getEmail(),
                            entity.getContato().getTelefone(),entity.getContato().getCelular());
                    return new ResponseEntity<>(response, HttpStatus.OK);
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
    public ResponseEntity<Cliente> BuscarClientePorCpf(Long cpf)
    {
        try
        {
            if(cpf != null)
            {
                if(clienteRepository.existsBycpf(cpf))
                {
                    ClienteEntity entity = clienteRepository.findBycpf(cpf).get();
                    Cliente response = new Cliente(entity.getNome(),entity.getSobrenome(),entity.getRg(),
                            entity.getCpf(), entity.getCodigoIdentificador(), entity.getDataNascimento(),entity.getEndereco().getCep(),entity.getEndereco().getLogradouro(),
                            entity.getEndereco().getNumero(),entity.getEndereco().getComplemento(),entity.getEndereco().getBairro(),
                            entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),entity.getContato().getEmail(),
                            entity.getContato().getTelefone(),entity.getContato().getCelular());
                    return new ResponseEntity<>(response, HttpStatus.OK);
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
    public ResponseEntity<Cliente> NovoCliente(String nome, String sobrenome, Long cpf, Long rg,
                                               LocalDate dataNascimento, String numero, String cep,
                                               String email,Long telefone, Long celular)
    {
        try
        {
            if(nome != null && sobrenome != null && cpf != null && rg != null &&
            dataNascimento != null && numero != null && cep != null &&  email != null &&
                    telefone != null && celular != null)
            {
                ClienteEntity entity = new ClienteEntity();
                ContatoEntity contato = new ContatoEntity();
                entity.setNome(nome);
                entity.setSobrenome(sobrenome);
                entity.setRg(rg);
                entity.setCpf(cpf);
                entity.setDataNascimento(dataNascimento);
                int dig = (int) (111111 + Math.random() * 999999);
                String codigo = "MS_"+dig;
                entity.setCodigoIdentificador(codigo);
                Endereco record = restTemplate
                    .getForEntity(
                            String.format("http://viacep.com.br/ws/%s/json/", cep),
                             Endereco.class).getBody();
                    EnderecoEntity endereco = new EnderecoEntity(record);
                    endereco.setNumero(numero);
                    endereco.setTimeStamp(LocalDateTime.now());
                    System.out.println(record);
                    System.out.println(endereco);
                    enderecoRepository.save(endereco);
                    entity.setEndereco(endereco);
                    contato.setEmail(email);
                    contato.setTelefone(telefone);
                    contato.setCelular(celular);
                    contato.setTimeStamp(LocalDateTime.now());
                    contatoRepository.save(contato);
                    entity.setContato(contato);
                    entity.setTimeStamp(LocalDateTime.now());
                    clienteRepository.save(entity);
                Cliente response = new Cliente(entity.getNome(),entity.getSobrenome(),entity.getRg(),
                        entity.getCpf(), entity.getCodigoIdentificador(), entity.getDataNascimento(),entity.getEndereco().getCep(),entity.getEndereco().getLogradouro(),
                        entity.getEndereco().getNumero(),entity.getEndereco().getComplemento(),entity.getEndereco().getBairro(),
                        entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),entity.getContato().getEmail(),
                        entity.getContato().getTelefone(),entity.getContato().getCelular());
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
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
    public ResponseEntity<Cliente> AlterarDados(Long id, String nome, String sobrenome,
                                                Long cpf, Long rg, LocalDate dataNascimento)
    {
        try
        {
            if(id != null)
            {
                if(clienteRepository.existsById(id))
                {
                    ClienteEntity entity = clienteRepository.findById(id).get();
                    if(nome != null) {entity.setNome(nome);}
                    if(sobrenome != null) {entity.setSobrenome(sobrenome);}
                    if(rg != null) { entity.setRg(rg);}
                    if(cpf != null) { entity.setCpf( cpf);}
                    if(dataNascimento != null) { entity.setDataNascimento(dataNascimento);}
                    entity.setTimeStamp(LocalDateTime.now());
                    clienteRepository.save(entity);
                    Cliente response = new Cliente(entity.getNome(),entity.getSobrenome(),entity.getRg(),
                            entity.getCpf(), entity.getCodigoIdentificador(), entity.getDataNascimento(),entity.getEndereco().getCep(),entity.getEndereco().getLogradouro(),
                            entity.getEndereco().getNumero(),entity.getEndereco().getComplemento(),entity.getEndereco().getBairro(),
                            entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),entity.getContato().getEmail(),
                            entity.getContato().getTelefone(),entity.getContato().getCelular());
                    return new ResponseEntity<>(response, HttpStatus.OK);
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
    public ResponseEntity<Cliente> AlterarEndereco(Long id, String numero, Long cep)
    {
        try
        {
            if(id != null && numero != null && cep != null)
            {
                if(clienteRepository.existsById(id))
                {
                    ClienteEntity entity = clienteRepository.findById(id).get();
                    Endereco record = restTemplate
                            .getForEntity(
                                    String.format("https://viacep.com.br/ws/%s/json/", cep),
                                    Endereco.class).getBody();
                    if(enderecoRepository.existsById(entity.getEndereco().getId()))
                    {
                        EnderecoEntity endereco = enderecoRepository.findById(entity.getEndereco().getId()).get();
                        endereco.setLogradouro(record.logradouro());
                        endereco.setNumero(numero);
                        endereco.setBairro(record.bairro());
                        endereco.setComplemento(record.complemento());
                        endereco.setCep(record.cep());
                        endereco.setCidade(record.localidade());
                        endereco.setEstado(record.uf());
                        endereco.setTimeStamp(LocalDateTime.now());
                        enderecoRepository.save(endereco);
                        Cliente response = new Cliente(entity.getNome(),entity.getSobrenome(),entity.getRg(),
                                entity.getCpf(), entity.getCodigoIdentificador(), entity.getDataNascimento(),entity.getEndereco().getCep(),entity.getEndereco().getLogradouro(),
                                entity.getEndereco().getNumero(),entity.getEndereco().getComplemento(),entity.getEndereco().getBairro(),
                                entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),entity.getContato().getEmail(),
                                entity.getContato().getTelefone(),entity.getContato().getCelular());
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }

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
    public ResponseEntity<Cliente> AlterarContato(Long id, String email, Long telefone, Long celular)
    {
        try
        {
            if(id != null)
            {
                if(clienteRepository.existsById(id))
                {
                    ClienteEntity entity = clienteRepository.findById(id).get();
                    if(contatoRepository.existsById(entity.getContato().getId()))
                    {
                        ContatoEntity contato = contatoRepository.findById(entity.getContato().getId()).get();
                        if(email != null){contato.setEmail(email);}
                        if(telefone != null){contato.setTelefone(telefone);}
                        if( celular != null){contato.setCelular(celular);}
                        contato.setTimeStamp(LocalDateTime.now());
                        contatoRepository.save(contato);
                        Cliente response = new Cliente(entity.getNome(),entity.getSobrenome(),entity.getRg(),
                                entity.getCpf(), entity.getCodigoIdentificador(), entity.getDataNascimento(),entity.getEndereco().getCep(),entity.getEndereco().getLogradouro(),
                                entity.getEndereco().getNumero(),entity.getEndereco().getComplemento(),entity.getEndereco().getBairro(),
                                entity.getEndereco().getCidade(),entity.getEndereco().getEstado(),entity.getContato().getEmail(),
                                entity.getContato().getTelefone(),entity.getContato().getCelular());
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }

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
    public ResponseEntity<Cliente> DeletarCliente(Long id)
    {
        try
        {
            if(id != null)
            {
                if(clienteRepository.existsById(id))
                {
                    ClienteEntity entity = clienteRepository.findById(id).get();
                    if(contatoRepository.existsById(entity.getContato().getId()))
                    {
                        if(enderecoRepository.existsById(entity.getEndereco().getId()))
                        {
                            contatoRepository.deleteById(entity.getContato().getId());
                            enderecoRepository.deleteById(entity.getEndereco().getId());
                            clienteRepository.deleteById(id);
                            return new ResponseEntity<>(HttpStatus.OK);
                        }
                        else
                        {throw new EntityNotFoundException();}
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
}
