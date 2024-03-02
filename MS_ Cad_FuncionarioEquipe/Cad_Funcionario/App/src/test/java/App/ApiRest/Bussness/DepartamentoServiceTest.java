package App.ApiRest.Bussness;

import App.ApiRest.Domain.Departamento;
import App.ApiRest.Infra.Exceptions.EntityNotFoundException;
import App.ApiRest.Infra.Exceptions.NullargumentsException;
import App.ApiRest.Infra.Persistence.Entity.DepartamentoEntity;
import App.ApiRest.Infra.Persistence.Repository.DepartamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DepartamentoServiceTest {

    @InjectMocks
    DepartamentoService service;

    @Mock
    DepartamentoRepository repository;

    private Long id= 1L;
    private Departamento response = new Departamento("teste","teste",true);
    private DepartamentoEntity entity = new DepartamentoEntity(1L,"test","teste",true, LocalDateTime.now());

    //Busca por id sucesso
    @Test
    void DeveBUscarDepartamentoComSucessp()
    {
        service.BuscarDepartamentoPorId(1L);
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(entity));

        assertEquals(true, repository.existsById(1L));
        assertEquals(Optional.ofNullable(entity), repository.findById(1L));
    }
    //Busca por id sem sucesso

    @Test
    void BuscaDeveCairNaExceptionNullArguments()
    {
        service.BuscarDepartamentoPorId(null);

        assertEquals(null, service.BuscarDepartamentoPorId(null));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(repository);
    }

    @Test
    void BuscaDeveCairNaExceptionEntityNotFound()
    {
        service.BuscarDepartamentoPorId(1L);
        when(repository.existsById(1L)).thenReturn(false);

        assertEquals(false,repository.existsById(1L));
        assertThat(EntityNotFoundException.class, notNullValue());
    }

    //Novo Cadastro sucesso
    @Test
    void DeveSalvarNovoDepartamentoComSucesso()
    {
        service.NovoDepartamento("teste","teste");
        when(repository.save(entity)).thenReturn(entity);

        assertEquals(entity,repository.save(entity));
    }

    //Novo Cadastro sem sucess
    @Test
    void NovoCadastroDeveCairNaExceptionNullArgumentUmArgumentoNulo()
    {
        service.NovoDepartamento("teste",null);

        assertEquals(null, service.NovoDepartamento("teste",null));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(repository);
    }

    @Test
    void NovoCadastroDeveCairNaExceptionNullArgumentTodosArgumenosNulos()
    {
        service.NovoDepartamento(null,null);

        assertEquals(null, service.NovoDepartamento(null,null));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(repository);
    }
    @Test
    void NovoCadastroDeveCairNaExptionRepositoryFalha()
    {
        service.NovoDepartamento("teste","teste");
        when(repository.save(entity)).thenReturn(null);

        assertEquals(null,repository.save(entity));
        assertThat(Exception.class,notNullValue());
    }

    //Editar Departamento
    @Test
    void EditarDepartamentoDeveEditarComSucesso()
    {
        service.EditarDepartamento(1L,"teste2","teste2");
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        entity.setDescrisao("teste2");
        entity.setNome("teste2");
        when(repository.save(entity)).thenReturn(entity);

        assertEquals(true,repository.existsById(1L));
        assertEquals(Optional.ofNullable(entity),repository.findById(1L));
        assertEquals(entity,repository.save(entity));
        assertEquals("teste2",entity.getNome());
        assertEquals("teste2",entity.getDescrisao());
    }
    //Editar Departamento sem sucesso
    @Test
    void EditarDepartamentoDeveCairNaExceptionNullArgumentIdNulo()
    {
        service.EditarDepartamento(null,"teste2","teste2");

        assertEquals(null,service.EditarDepartamento(null,"teste2","teste2"));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(repository);
    }


    @Test
    void EditarDepartamentoDeveCairNaExptionRepositoryFalha()
    {
        service.EditarDepartamento(1L,"teste2","teste2");
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        entity.setDescrisao("teste2");
        entity.setNome("teste2");
        when(repository.save(entity)).thenReturn(null);

        assertEquals(true,repository.existsById(1L));
        assertEquals(Optional.ofNullable(entity),repository.findById(1L));
        assertEquals("teste2",entity.getNome());
        assertEquals("teste2",entity.getDescrisao());
        assertEquals(null,repository.save(entity));
        assertThat(Exception.class,notNullValue());
    }

    @Test
    void EditarDepartamentoDeveCairNaExceptionEntityNotFound()
    {
        service.EditarDepartamento(1L,"teste2","teste2");
        when(repository.existsById(1L)).thenReturn(false);


        assertEquals(false,repository.existsById(1L));
        assertThat(EntityNotFoundException.class,notNullValue());
    }

    //Desativar Departamento Sussesso
    @Test
    void desativarDepartamentoComSussesso()
    {
        service.desativarDepartamento(1L,true);
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        entity.setAtivo(false);
        when(repository.save(entity)).thenReturn(entity);

        assertEquals(true,repository.existsById(1L));
        assertEquals(Optional.ofNullable(entity),repository.findById(1L));
        assertEquals(entity,repository.save(entity));
        assertEquals(false, entity.getAtivo());
    }

    //Desativar Departamento Sem Sussesso
    @Test
    void desativarDepartamentoDeveCairNaExceptionNullArgumentUmArgumentoNulo()
    {
        service.desativarDepartamento(1L, null);

        assertEquals(null, service.desativarDepartamento(1L, null));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(repository);
    }

    @Test
    void desativarDepartamentoDeveCairNaExceptionNullArgumentTodosArgumentoNulo()
    {
        service.desativarDepartamento(null, null);

        assertEquals(null, service.desativarDepartamento(null, null));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(repository);
    }
    @Test
    void desativarDepartamentoDeveCairNaExceptionEntityNotFound()
    {
        service.desativarDepartamento(1L,true);
        when(repository.existsById(1L)).thenReturn(false);


        assertEquals(false,repository.existsById(1L));
        assertThat(EntityNotFoundException.class,notNullValue());
    }

    @Test
    void desativarDepartamentoDeveCairNaExceptionRepositoryFalha()
    {
        service.desativarDepartamento(1L,true);
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertEquals(true,repository.existsById(1L));
        assertEquals(Optional.empty(),repository.findById(1L));
        assertThat(EntityNotFoundException.class,notNullValue());
    }
}