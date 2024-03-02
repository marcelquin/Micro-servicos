package App.ApiRest.Bussness;

import App.ApiRest.Domain.Cargo;
import App.ApiRest.Infra.Exceptions.EntityNotFoundException;
import App.ApiRest.Infra.Exceptions.NullargumentsException;
import App.ApiRest.Infra.Persistence.Entity.CargoEntity;
import App.ApiRest.Infra.Persistence.Entity.DepartamentoEntity;
import App.ApiRest.Infra.Persistence.Repository.CargoRepository;
import App.ApiRest.Infra.Persistence.Repository.DepartamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CargoServiceTest {

    @InjectMocks
    CargoService service;
    @Mock
    CargoRepository cargoRepository;
    @Mock
    DepartamentoRepository departamentoRepository;

    private Long id = 1L;

    private DepartamentoEntity departamento = new DepartamentoEntity(1L,"teste","teste",true, LocalDateTime.now());
    private CargoEntity entity = new CargoEntity(1L, "teste","teste",departamento,1234.0,44.0,true,LocalDateTime.now());
    private Cargo response = new Cargo("teste","teste","teste",1234.0,44.0,true);

    //Busca Por id com sucesso
    @Test
    void DeveBuscarComSucesso()
    {
        service.BuscarPorId(1L);
        when(cargoRepository.existsById(1L)).thenReturn(true);
        when(cargoRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));

        assertEquals(true, cargoRepository.existsById(1L));
        assertEquals(Optional.ofNullable(entity), cargoRepository.findById(1L));

    }
    //Busca Por id sem sucesso
    @Test
    void BuscaPorIdDeveCairNaExceptionNullArguments()
    {
        service.BuscarPorId(null);

        assertEquals(null,service.BuscarPorId(null));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(cargoRepository);
    }

    @Test
    void BuscaPorIdDeveCairNaExceptionEntiyNotFound()
    {
        service.BuscarPorId(1L);
        when(cargoRepository.existsById(1L)).thenReturn(false);

        assertEquals(false,cargoRepository.existsById(1l));
        assertThat(EntityNotFoundException.class,notNullValue());

    }

    @Test
    void BuscaPorIdDeveCairNaExceptionRepositoryFalha()
    {
        service.BuscarPorId(1L);
        when(cargoRepository.existsById(1L)).thenReturn(true);
        when(cargoRepository.findById(1L)).thenReturn(null);

        assertEquals(true,cargoRepository.existsById(1L));
        assertEquals(null,cargoRepository.findById(1L));
        assertThat(EntityNotFoundException.class,notNullValue());
    }

    //Novo Cadastro com sucesso
    @Test
    void DeveSalvarNovoRegistroComSumesso()
    {
        service.NovoCargo("teste","teste",1L,1234.0,44.0);
        when(cargoRepository.save(entity)).thenReturn(entity);

        assertEquals(entity,cargoRepository.save(entity));
    }

    //Novo Cadastro sem sucesso

    @Test
    void NovoCadastroDeveCairNaExceptionNullArgumentoUmArgumentoNulo()
    {
        service.NovoCargo("teste","teste",1L,null,44.0);

        assertEquals(null, service.NovoCargo("teste","teste",1L,null,44.0));
        assertThat(NullargumentsException.class, notNullValue());
        verifyNoInteractions(cargoRepository);
        verifyNoInteractions(departamentoRepository);
    }

    @Test
    void NovoCadastroDeveCairNaExceptionNullArgumentoTodosArgumentosNulos()
    {
        service.NovoCargo(null,null,null,null,null);

        assertEquals(null, service.NovoCargo(null,null,null,null,null));
        assertThat(NullargumentsException.class, notNullValue());
        verifyNoInteractions(cargoRepository);
        verifyNoInteractions(departamentoRepository);
    }

    @Test
    void NovoCadastroDeveCairNaExceptionRepositoryFalha()
    {
        service.NovoCargo("teste","teste",1L,1234.0,44.0);
        when(cargoRepository.save(entity)).thenReturn(null);

        assertEquals(null,cargoRepository.save(entity));
        assertThat(Exception.class,notNullValue());
        assertEquals(null,service.NovoCargo("teste","teste",1L,1234.0,44.0));
    }


    //Editar Registro com sucesso
    @Test
    void DeveEditarComSucesso()
    {
        service.EditarCargo(1L,"teste2","teste2",12.0,12.0);
        when(cargoRepository.existsById(1L)).thenReturn(true);
        when(cargoRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        entity.setNome("teste2");
        entity.setDescrisao("teste2");
        entity.setSalario(12.0);
        entity.setCargaHorariaSemanal(12.0);
        when(cargoRepository.save(entity)).thenReturn(entity);

        assertEquals(true,cargoRepository.existsById(1L));
        assertEquals(Optional.ofNullable(entity),cargoRepository.findById(1L));
        assertEquals("teste2", entity.getNome());
        assertEquals("teste2", entity.getDescrisao());
        assertEquals(12.0, entity.getSalario());
        assertEquals(12.0, entity.getCargaHorariaSemanal());
        assertEquals(entity, cargoRepository.save(entity));
    }
    //Editar Registro sem sucesso
    @Test
    void EditarRegistroDeveCairNaExeptionNullArgumentosIdNulo()
    {
        service.EditarCargo(null,"teste","teste",12.0,12.0);

        assertEquals(null,service.EditarCargo(null,"teste","teste",12.0,12.0));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(cargoRepository);
    }

    @Test
    void EditarRegistroDeveCairNaExceptionEntitiNotFound()
    {
        service.EditarCargo(1L,"teste","teste",12.0,12.0);
        when(cargoRepository.existsById(1L)).thenReturn(false);

        assertEquals(false,cargoRepository.existsById(1L));
        assertThat(EntityNotFoundException.class,notNullValue());
    }

    @Test
    void EditarRegistroDeveCairNaExceptionRepositoryFalha()
    {
        service.EditarCargo(1L,"teste","teste",12.0,12.0);
        when(cargoRepository.existsById(1L)).thenReturn(true);
        when(cargoRepository.findById(1L)).thenReturn(Optional.empty());

        assertEquals(true,cargoRepository.existsById(1L));
        assertEquals(Optional.empty(), cargoRepository.findById(1L));
        assertThat(EntityNotFoundException.class,notNullValue());
    }

    //Desativar cargo com sucesso
    @Test
    void DeveDesativarregistroComSucesso()
    {
        service.desativarCargo(1L, false);
        when(cargoRepository.existsById(1L)).thenReturn(true);
        when(cargoRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        entity.setAtivo(false);
        when(cargoRepository.save(entity)).thenReturn(entity);

        assertEquals(true,cargoRepository.existsById(1L));
        assertEquals(Optional.ofNullable(entity),cargoRepository.findById(1L));
        assertEquals(false, entity.getAtivo());
        assertEquals(entity, cargoRepository.save(entity));
    }
    //Desativar cargo sem sucesso
    @Test
    void desativarCargoDeveCairNaExceptionNullArgumentsUmArgumentoNulo()
    {
        service.desativarCargo(1L, null);

        assertEquals(null,service.desativarCargo(1L, null));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(cargoRepository);
        verifyNoInteractions(departamentoRepository);
    }

    @Test
    void desativarCargoDeveCairNaExceptionNullArgumentsTodosArgumentosNulos()
    {
        service.desativarCargo(null, null);

        assertEquals(null,service.desativarCargo(null, null));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(cargoRepository);
        verifyNoInteractions(departamentoRepository);
    }

    @Test
    void desativarCargoDeveCairNaExceptionEntityNotFound()
    {
        service.desativarCargo(1L, false);
        when(cargoRepository.existsById(1L)).thenReturn(false);
        when(cargoRepository.findById(1L)).thenReturn(Optional.empty());

        assertEquals(false,cargoRepository.existsById(1L));
        assertEquals(Optional.empty(),cargoRepository.findById(1L));
        assertThat(EntityNotFoundException.class,notNullValue());
    }

    @Test
    void desativarCargoDeveCairNaExceptionRepositoryFalha()
    {
        service.desativarCargo(1L, false);
        when(cargoRepository.existsById(1L)).thenReturn(true);
        when(cargoRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        entity.setAtivo(false);
        when(cargoRepository.save(entity)).thenReturn(null);

        assertEquals(true,cargoRepository.existsById(1L));
        assertEquals(Optional.ofNullable(entity),cargoRepository.findById(1L));
        assertEquals(false,entity.getAtivo());
        assertEquals(null, cargoRepository.save(entity));
        assertThat(Exception.class,notNullValue());
    }
}