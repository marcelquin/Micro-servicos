package App.ApiRest.Infra.Persistence.Repository;

import App.ApiRest.Infra.Exceptions.EntityNotFoundException;
import App.ApiRest.Infra.Persistence.Entity.ArquivosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ArquivoRepositoryTest {

    @Mock
    ArquivoRepository repository;

    private ArquivosEntity entity = new ArquivosEntity(1L,"teste", LocalDateTime.now());
    @Test
    void DeveEncontrarAEntidadePorNome()
    {
        when(repository.existsBynomeArquivo("teste")).thenReturn(true);

        assertEquals(true, repository.existsBynomeArquivo("teste"));
    }

    @Test
    void NaoDeveEncontrarEntidadeExcepton()
    {
        when(repository.existsBynomeArquivo("teste")).thenReturn(false);

        assertEquals(false,repository.existsBynomeArquivo("teste"));
        assertThat(EntityNotFoundException.class,notNullValue());
    }

    @Test
    void DeveBuscarPorNomeRetornandoCorpoDaEntidade()
    {
        when(repository.existsBynomeArquivo("teste")).thenReturn(true);
        when(repository.findBynomeArquivo("teste")).thenReturn(entity);

        assertEquals(true, repository.existsBynomeArquivo("teste"));
        assertEquals(entity, repository.findBynomeArquivo("teste"));
    }

    @Test
    void NaoDeveRepornarCorpoDaEntidadeCaindoNaEntityNotFound()
    {
        when(repository.existsBynomeArquivo("teste")).thenReturn(true);
        when(repository.findBynomeArquivo("teste")).thenReturn(null);

        assertEquals(true, repository.existsBynomeArquivo("teste"));
        assertEquals(null, repository.findBynomeArquivo("teste"));
        assertThat(EntityNotFoundException.class,notNullValue());

    }
}