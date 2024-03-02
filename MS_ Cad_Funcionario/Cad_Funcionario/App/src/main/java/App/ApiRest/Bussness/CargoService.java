package App.ApiRest.Bussness;

import App.ApiRest.Domain.Cargo;
import App.ApiRest.Infra.Exceptions.EntityNotFoundException;
import App.ApiRest.Infra.Exceptions.NullargumentsException;
import App.ApiRest.Infra.Gateway.CargoGateway;
import App.ApiRest.Infra.Persistence.Entity.CargoEntity;
import App.ApiRest.Infra.Persistence.Entity.DepartamentoEntity;
import App.ApiRest.Infra.Persistence.Repository.CargoRepository;
import App.ApiRest.Infra.Persistence.Repository.DepartamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CargoService implements CargoGateway {

    private final CargoRepository cargoRepository;
    private final DepartamentoRepository departamentoRepository;

    public CargoService(CargoRepository cargoRepository, DepartamentoRepository departamentoRepository) {
        this.cargoRepository = cargoRepository;
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public ResponseEntity<List<CargoEntity>> listarCargos()
    {
        try
        {
          return new ResponseEntity<>(cargoRepository.findAll(), HttpStatus.OK);
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Cargo> BuscarPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                if(cargoRepository.existsById(id))
                {
                    CargoEntity entity = cargoRepository.findById(id).get();
                    Cargo response = new Cargo(entity.getNome(),entity.getDescrisao(),entity.getDepartamento().getNome(),entity.getSalario(),entity.getCargaHorariaSemanal(),entity.getAtivo());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                {throw new EntityNotFoundException();}
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
    public ResponseEntity<Cargo> NovoCargo(String nome, String descrisao, Long idDepartamento, Double salario, Double cargaHorariaSemanal)
    {
        try
        {
            if(nome != null && descrisao != null && idDepartamento != null && salario != null && cargaHorariaSemanal != null)
            {
                CargoEntity entity = new CargoEntity();
                entity.setNome(nome);
                entity.setDescrisao(descrisao);
                entity.setAtivo(Boolean.TRUE);
                entity.setSalario(salario);
                entity.setCargaHorariaSemanal(cargaHorariaSemanal);
                if(departamentoRepository.existsById(idDepartamento))
                {
                    DepartamentoEntity departamento = departamentoRepository.findById(idDepartamento).get();
                    entity.setDepartamento(departamento);
                    entity.setTimeStamp(LocalDateTime.now());
                    cargoRepository.save(entity);
                    Cargo response = new Cargo(entity.getNome(), entity.getDescrisao(),entity.getDepartamento().getNome(),entity.getSalario(),entity.getCargaHorariaSemanal(),entity.getAtivo());
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }
                else
                {throw new EntityNotFoundException();}
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
    public ResponseEntity<Cargo> EditarCargo(Long id, String nomeCargo, String descrisaoCargo, Double salario, Double cargaHorariaSemanal)
    {
        try
        {
            if(id != null)
            {
                if(cargoRepository.existsById(id))
                {
                    CargoEntity entity = cargoRepository.findById(id).get();
                    entity.setNome(nomeCargo);
                    entity.setDescrisao(descrisaoCargo);
                    entity.setSalario(salario);
                    entity.setCargaHorariaSemanal(cargaHorariaSemanal);
                    entity.setTimeStamp(LocalDateTime.now());
                    Cargo response = new Cargo(entity.getNome(),entity.getDescrisao(),entity.getDepartamento().getNome(),entity.getSalario(),entity.getCargaHorariaSemanal(),entity.getAtivo());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                {throw new EntityNotFoundException();}
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
    public ResponseEntity<Cargo> desativarCargo(Long id, Boolean desativar)
    {
        try
        {
            if(id != null && desativar != null)
            {
                if(cargoRepository.existsById(id))
                {
                    CargoEntity entity = cargoRepository.findById(id).get();
                    entity.setAtivo(Boolean.FALSE);
                    cargoRepository.save(entity);
                    Cargo response = new Cargo(entity.getNome(),entity.getDescrisao(),entity.getDepartamento().getNome(),entity.getSalario(),entity.getCargaHorariaSemanal(),entity.getAtivo());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                {throw new EntityNotFoundException();}
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
