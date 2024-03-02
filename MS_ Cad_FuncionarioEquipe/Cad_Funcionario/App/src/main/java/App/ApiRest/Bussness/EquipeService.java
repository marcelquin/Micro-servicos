package App.ApiRest.Bussness;

import App.ApiRest.Domain.Equipe;
import App.ApiRest.Infra.Exceptions.EntityNotFoundException;
import App.ApiRest.Infra.Exceptions.NullargumentsException;
import App.ApiRest.Infra.Gateway.EquipeGateway;
import App.ApiRest.Infra.Persistence.Entity.CargoEntity;
import App.ApiRest.Infra.Persistence.Entity.ColaboradorEntity;
import App.ApiRest.Infra.Persistence.Entity.DepartamentoEntity;
import App.ApiRest.Infra.Persistence.Entity.EquipeEntity;
import App.ApiRest.Infra.Persistence.Repository.CargoRepository;
import App.ApiRest.Infra.Persistence.Repository.ColaboradorRepository;
import App.ApiRest.Infra.Persistence.Repository.DepartamentoRepository;
import App.ApiRest.Infra.Persistence.Repository.EquipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquipeService implements EquipeGateway {

    private final EquipeRepository equipeRepository;
    private final DepartamentoRepository departamentoRepository;
    private final CargoRepository cargoRepository;
    private final ColaboradorRepository colaboradorRepository;

    public EquipeService(EquipeRepository equipeRepository, DepartamentoRepository departamentoRepository, CargoRepository cargoRepository, ColaboradorRepository colaboradorRepository) {
        this.equipeRepository = equipeRepository;
        this.departamentoRepository = departamentoRepository;
        this.cargoRepository = cargoRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    public ResponseEntity<List<EquipeEntity>> ListarEquipes()
    {
        try
        {
            return new ResponseEntity<>(equipeRepository.findAll(), HttpStatus.OK);
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Equipe> BuscarEquipePorId(Long id)
    {
        try
        {
            if(id != null)
            {
                if(equipeRepository.existsById(id))
                {
                    EquipeEntity entity = equipeRepository.findById(id).get();
                    List<String> cargosList = new ArrayList<>();
                    entity.getCargos().forEach(cargo -> cargosList.add(cargo.getNome()));
                    Equipe response = new Equipe(entity.getNome(),entity.getDescrisao(),entity.getDepartamento().getNome(),cargosList);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                {  throw new EntityNotFoundException();}
            }
            else
            {  throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Equipe> NovaEquipe(Long idDepartamento, Long[] idCargos, Long[] idColaboradores, String nome, String descrisao)
    {
        try
        {
            if(idDepartamento != null && idCargos != null && idColaboradores != null && nome != null && descrisao != null)
            {
                if(departamentoRepository.existsById(idDepartamento))
                {
                    DepartamentoEntity departamento = departamentoRepository.findById(idDepartamento).get();
                    EquipeEntity entity = new EquipeEntity();
                    entity.setNome(nome);
                    entity.setDescrisao(descrisao);
                    entity.setDepartamento(departamento);
                    entity.setTimeStamp(LocalDateTime.now());
                    List<CargoEntity> cargosList = new ArrayList<>();
                    List<String> cargosListString = new ArrayList<>();
                    List<ColaboradorEntity> colaboradorList = new ArrayList<>();
                    for(Long cargo : idCargos)
                    {
                        if(cargoRepository.existsById(cargo))
                        {
                            CargoEntity cargoEntity = cargoRepository.findById(cargo).get();
                            cargosList.add(cargoEntity);
                            cargosListString.add(cargoEntity.getNome());
                        }
                        else
                        {  throw new EntityNotFoundException();}
                    }
                    for(Long colaborador : idColaboradores)
                    {
                        if(colaboradorRepository.existsById(colaborador))
                        {
                            ColaboradorEntity colaboradorEntity = colaboradorRepository.findById(colaborador).get();
                            colaboradorList.add(colaboradorEntity);
                        }
                        else
                        {  throw new EntityNotFoundException();}
                    }
                    entity.setCargos(cargosList);
                    entity.setColaboradores(colaboradorList);
                    equipeRepository.save(entity);
                    Equipe response = new Equipe(entity.getNome(),entity.getDescrisao(),
                            entity.getDepartamento().getNome(),cargosListString);
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }
                else
                {  throw new EntityNotFoundException();}
            }
            else
            {  throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Equipe> ALterarDadosEquipe(Long idEquipe, String nome, String descrisao)
    {
        try
        {
            if(idEquipe != null)
            {
                if(equipeRepository.existsById(idEquipe))
                {
                    EquipeEntity entity = equipeRepository.findById(idEquipe).get();
                    if(nome != null) { entity.setNome(nome);}
                    if(descrisao != null) {entity.setDescrisao(descrisao);}
                    entity.setTimeStamp(LocalDateTime.now());
                    equipeRepository.save(entity);
                    List<String> cargoList = new ArrayList<>();
                    entity.getCargos().forEach(cargoInterno -> cargoList.add(cargoInterno.getNome()));
                    Equipe response = new Equipe(entity.getNome(),entity.getDescrisao(),
                            entity.getDepartamento().getNome(),cargoList);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                {  throw new EntityNotFoundException();}
            }
            else
            {  throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Equipe> AdicionarColaborador(Long idEquipe, Long[]idColaboradores)
    {
        try
        {
            if(idEquipe != null && idColaboradores != null)
            {
                if(equipeRepository.existsById(idEquipe))
                {
                    EquipeEntity entity = equipeRepository.findById(idEquipe).get();
                    List<ColaboradorEntity> colaboradorList = new ArrayList<>();
                    for(Long id : idColaboradores)
                        if(colaboradorRepository.existsById(id))
                        {
                          ColaboradorEntity colaboradorEntity = colaboradorRepository.findById(id).get();
                          colaboradorList.add(colaboradorEntity);
                        }
                        else
                        {  throw new EntityNotFoundException(); }
                        entity.getColaboradores().addAll(colaboradorList);
                        entity.setTimeStamp(LocalDateTime.now());
                        equipeRepository.save(entity);
                        List<String> cargoList = new ArrayList<>();
                        entity.getCargos().forEach(cargoInterno -> cargoList.add(cargoInterno.getNome()));
                        Equipe response = new Equipe(entity.getNome(),entity.getDescrisao(),entity.getDepartamento().getNome(),cargoList);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                {  throw new EntityNotFoundException();}
            }
            else
            {  throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

}
