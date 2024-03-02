package App.ApiRest.Bussness;

import App.ApiRest.Domain.Departamento;
import App.ApiRest.Infra.Exceptions.EntityNotFoundException;
import App.ApiRest.Infra.Exceptions.NullargumentsException;
import App.ApiRest.Infra.Gateway.DepartamentoGateway;
import App.ApiRest.Infra.Persistence.Entity.DepartamentoEntity;
import App.ApiRest.Infra.Persistence.Repository.DepartamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepartamentoService implements DepartamentoGateway {

    private final DepartamentoRepository repository;

    public DepartamentoService(DepartamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<DepartamentoEntity>> listarDepartamento()
    {
        try
        {
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Departamento> BuscarDepartamentoPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                if(repository.existsById(id))
                {
                    DepartamentoEntity entity = repository.findById(id).get();
                    Departamento response = new Departamento(entity.getNome(), entity.getDescrisao(),entity.getAtivo());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                { throw new EntityNotFoundException();}
            }
            else
            { throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Departamento> NovoDepartamento(String nome, String descrisao)
    {
        try
        {
            if (nome != null && descrisao != null)
            {
                Departamento response = new Departamento(nome, descrisao, true);
                DepartamentoEntity entity = new DepartamentoEntity(response);
                entity.setAtivo(Boolean.TRUE);
                entity.setTimeStamp(LocalDateTime.now());
                repository.save(entity);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else
            {throw new NullargumentsException();  }
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Departamento> EditarDepartamento(Long id, String depNome, String depDescrisao)
    {
        try
        {
            if(id != null)
            {
                if(repository.existsById(id))
                {
                    DepartamentoEntity entity = repository.findById(id).get();
                    if(depNome != null)
                    {
                        entity.setNome(depNome);
                    }
                    if(depDescrisao != null)
                    {
                        entity.setDescrisao(depDescrisao);
                    }
                    repository.save(entity);
                    Departamento response = new Departamento(entity.getNome(), entity.getDescrisao(),entity.getAtivo());
                    return new ResponseEntity<>(response, HttpStatus.OK);

                }
                else
                { throw new EntityNotFoundException();}
            }
            else
            { throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Departamento> desativarDepartamento(Long id, Boolean desativar)
    {
        try
        {
            if(id != null && desativar != null)
            {
                if(repository.existsById(id))
                {
                    DepartamentoEntity entity = repository.findById(id).get();
                    entity.setAtivo(Boolean.FALSE);
                    repository.save(entity);
                    Departamento response = new Departamento(entity.getNome(),entity.getDescrisao(),entity.getAtivo());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                { throw new EntityNotFoundException();}
            }
            else
            { throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }
}
