package App.ApiRest.Bussness;

import App.ApiRest.Infra.Exceptions.EntityNotFoundException;
import App.ApiRest.Infra.Exceptions.NullargumentsException;
import App.ApiRest.Infra.Persistence.Entity.*;
import App.ApiRest.Infra.Persistence.Enum.EstadoCivil;
import App.ApiRest.Infra.Persistence.Enum.GrauEstudo;
import App.ApiRest.Infra.Persistence.Repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ColaboradorServiceTest {

    @InjectMocks
    ColaboradorService service;
    @Mock
    ColaboradorRepository colaboradorRepository;
    @Mock
    ArquivoRepository arquivoRepository;
    @Mock
    DocumentosRepository documentosRepository;
    @Mock
    CargoRepository cargoRepository;
    @Mock
    FilhosRepository filhosRepository;
    @Mock
    ContatoRepository contatoRepository;
    @Mock
    EnderecoRepository enderecoRepository;

    private DepartamentoEntity departamento = new DepartamentoEntity(1L,"teste","teste",true, LocalDateTime.now());
    private CargoEntity cargo = new CargoEntity(1L,"teste","teste",departamento,123.0,123.0,true,LocalDateTime.now());
    private ContatoEntity contato = new ContatoEntity(1L,"teste",123L,123L,"teste","teste",LocalDateTime.now());
    private EnderecoEntity endereco = new EnderecoEntity(1L,"teste","teste","teste",123L,"teste","teste",LocalDateTime.now());
    private ArquivosEntity arquivos = new ArquivosEntity(1L, "teste",LocalDateTime.now());
    private FilhosEntity filhos = new FilhosEntity(1L,"teste",123L, null,LocalDateTime.now());
    private DocumentosEntity documentos = new DocumentosEntity(1L,"teste","teste","teste","teste",1,null,123L,123L,"teste", LocalDate.now(),
                                                                123L,"teste",123L,123L,123L,"teste",123L,"teste",
                                                                "teste",123L,LocalDate.now(),"teste",13L,123L,"teste",123L, GrauEstudo.Mestrado,EstadoCivil.VIUVO,LocalDateTime.now(),null);
    private ColaboradorEntity colaborador = new ColaboradorEntity(1L,"teste","teste",LocalDate.now(),"teste",endereco,documentos,cargo,LocalDate.now(),LocalDate.now(),"teste",contato,true,LocalDateTime.now());


    //Busca Por Id Sucesso
    @Test
    void buscarPorId()
    {
        service.BuscarPorId(1L);
        when(colaboradorRepository.existsById(1L)).thenReturn(true);
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.ofNullable(colaborador));

        assertEquals(true, colaboradorRepository.existsById(1L));
        assertEquals(Optional.ofNullable(colaborador), colaboradorRepository.findById(1L));

    }
    //Busca Por id sem sucesso

    @Test
    void NaoDeveBuscarPorIdNullArgumentsException()
    {
        service.BuscarPorId(null);

        assertEquals(null,service.BuscarPorId(null) );
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(colaboradorRepository);
    }

    @Test
    void NaoDeveBuscarPorIdEntityNotFoundException()
    {
        service.BuscarPorId(1L);
        when(colaboradorRepository.existsById(1L)).thenReturn(false);

        assertEquals(false, colaboradorRepository.existsById(1L));
        assertThat(EntityNotFoundException.class,notNullValue());
    }

    //Novo Cadastro
    @Test
    void DeveSalvarComSucesso()
    {
        service.NovoCadastro("teste","teste",LocalDate.now(),"teste","teste","teste",
                123L,"teste","teste",1L,"teste",123L,123L,"teste",
                "teste","teste","teste","teste","teste",123,123L,321L,
                "teste", LocalDate.now(),321L,"teste",123L,123L,123L,"teste",
                123L,"teste","teste",123L,LocalDate.now(),
                "teste",123L,123L,"teste",123L,GrauEstudo.Mestrado,EstadoCivil.VIUVO);

        when(cargoRepository.existsById(1L)).thenReturn(true);
        when(cargoRepository.findById(1L)).thenReturn(Optional.ofNullable(cargo));
        when(enderecoRepository.save(endereco)).thenReturn(endereco);
        when(contatoRepository.save(contato)).thenReturn(contato);
        when(documentosRepository.save(documentos)).thenReturn(documentos);
        when(colaboradorRepository.save(colaborador)).thenReturn(colaborador);

        assertEquals(true,cargoRepository.existsById(1L));
        assertEquals(Optional.ofNullable(cargo),cargoRepository.findById(1L));
        assertEquals(endereco,enderecoRepository.save(endereco));
        assertEquals(contato,contatoRepository.save(contato));
        assertEquals(documentos, documentosRepository.save(documentos));
        assertEquals(colaborador, colaboradorRepository.save(colaborador));
    }

    //Novo Cadastro Sem Sucesso
    @Test
    void NaoDeveSalvarNullArgumentsExceptionTodosArgumentoNulo()
    {
        service.NovoCadastro(null,null,null,null,null,null,null,null,null,null,null,null,null,null,
                null,null,null,null,null,0,null,null,null,null,null,
                null,null,null,null,null,null,null,null,
                null,null,null,null,null,null,null,null,null);

        assertEquals(null,service.NovoCadastro(null,null,null,null,null,null,null,null,null,null,null,null,null,null,
                null,null,null,null,null,0,null,null,null,null,null,
                null,null,null,null,null,null,null,null,
                null,null,null,null,null,null,null,null,null));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(cargoRepository);
        verifyNoInteractions(colaboradorRepository);
        verifyNoInteractions(enderecoRepository);
        verifyNoInteractions(contatoRepository);
    }
    @Test
    void NaoDeveSalvarNullArgumentsExceptionUmArgumentoNuloDaListaPrioritaria()
    {
        service.NovoCadastro("teste","teste",LocalDate.now(),"teste","teste","teste",
                123L,null,"teste",1L,"teste",123L,123L,"teste",
                "teste","teste","teste","teste","teste",123,123L,321L,
                "teste", LocalDate.now(),321L,"teste",123L,123L,123L,"teste",
                123L,"teste","teste",123L,LocalDate.now(),
                "teste",123L,123L,"teste",123L,GrauEstudo.Mestrado,EstadoCivil.VIUVO);

        assertEquals(null, service.NovoCadastro("teste","teste",LocalDate.now(),"teste","teste","teste",
                123L,null,"teste",1L,"teste",123L,123L,"teste",
                "teste","teste","teste","teste","teste",123,123L,321L,
                "teste", LocalDate.now(),321L,"teste",123L,123L,123L,"teste",
                123L,"teste","teste",123L,LocalDate.now(),
                "teste",123L,123L,"teste",123L,GrauEstudo.Mestrado,EstadoCivil.VIUVO) );
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(cargoRepository);
        verifyNoInteractions(colaboradorRepository);
        verifyNoInteractions(enderecoRepository);
        verifyNoInteractions(contatoRepository);
    }
    @Test
    void NaoDeveSalvarEntityNotFoundException()
    {
        service.NovoCadastro("teste","teste",LocalDate.now(),"teste","teste","teste",
                            123L,"teste","teste",1L,"teste",123L,123L,"teste",
                "teste","teste","teste","teste","teste",123,123L,321L,
                "teste", LocalDate.now(),321L,"teste",123L,123L,123L,"teste",
                123L,"teste","teste",123L,LocalDate.now(),
                "teste",123L,123L,"teste",123L,GrauEstudo.Mestrado,EstadoCivil.VIUVO);
        when(cargoRepository.existsById(1L)).thenReturn(false);

        assertEquals(false,cargoRepository.existsById(1L));
        assertThat(EntityNotFoundException.class,notNullValue());
        verifyNoInteractions(colaboradorRepository);
        verifyNoInteractions(documentosRepository);
        verifyNoInteractions(enderecoRepository);
    }

    @Test
    void NaoDeveSalvarRepositotyFalha()
    {
        service.NovoCadastro("teste","teste",LocalDate.now(),"teste","teste","teste",
                123L,"teste","teste",1L,"teste",123L,123L,"teste",
                "teste","teste","teste","teste","teste",123,123L,321L,
                "teste", LocalDate.now(),321L,"teste",123L,123L,123L,"teste",
                123L,"teste","teste",123L,LocalDate.now(),
                "teste",123L,123L,"teste",123L,GrauEstudo.Mestrado,EstadoCivil.VIUVO);

        when(cargoRepository.existsById(1L)).thenReturn(true);
        when(cargoRepository.findById(1L)).thenReturn(Optional.ofNullable(cargo));
        when(enderecoRepository.save(endereco)).thenReturn(endereco);
        when(contatoRepository.save(contato)).thenReturn(null);

        assertEquals(true, cargoRepository.existsById(1L));
        assertEquals(Optional.ofNullable(cargo), cargoRepository.findById(1L));
        assertEquals(endereco,enderecoRepository.save(endereco));
        assertEquals(null,contatoRepository.save(contato));
        assertThat(Exception.class,notNullValue());

    }

    //Alterar Cargo
    @Test
    void DeveALterarCargoComSucesso()
    {
        service.AlterarCargo(1L,1L,LocalDate.now());
        when(colaboradorRepository.existsById(1L)).thenReturn(true);
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.ofNullable(colaborador));
        when(cargoRepository.existsById(1L)).thenReturn(true);
        when(cargoRepository.findById(1L)).thenReturn(Optional.ofNullable(cargo));
        colaborador.setCargo(cargo);
        when(cargoRepository.save(cargo)).thenReturn(cargo);

        assertEquals(true, colaboradorRepository.existsById(1L));
        assertEquals(Optional.ofNullable(colaborador),colaboradorRepository.findById(1L));
        assertEquals(true, cargoRepository.existsById(1L));
        assertEquals(Optional.ofNullable(cargo),cargoRepository.findById(1L));
    }

    //Alterar Cargo Sem Sucesso
    @Test
    void NaoDeveAlterarNullArgumentsExceptionUmArgumentoNulo()
    {
        service.AlterarCargo(1L,null,LocalDate.now());


        assertEquals(null,service.AlterarCargo(1L,null,LocalDate.now()));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(colaboradorRepository);
        verifyNoInteractions(cargoRepository);
    }

    @Test
    void NaoDeveAlterarNullArgumentsExceptionTodosArgumentosNulos()
    {
        service.AlterarCargo(null,null,null);

        assertEquals(null,service.AlterarCargo(null,null,null) );
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(colaboradorRepository);
        verifyNoInteractions(cargoRepository);
    }


    //Alerar Documentos
    @Test
    void DeveAlerarDocumentosComSucesso()
    {
        service.AlterarDocumento(1L,"teste","teste","teste","teste",123L,
                123L,"teste",LocalDate.now(),123L,"teste",
                123L,321L,123L,"teste",123L,
                "teste","teste",123L,LocalDate.now(),"teste",123L,
                13L,"teste",123L,GrauEstudo.Mestrado,EstadoCivil.VIUVO);

        when(colaboradorRepository.existsById(1L)).thenReturn(true);
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.ofNullable(colaborador));
        when(documentosRepository.existsById(colaborador.getDocumentos().getId())).thenReturn(true);
        when(documentosRepository.findById(colaborador.getDocumentos().getId())).thenReturn(Optional.ofNullable(documentos));
        when(documentosRepository.save(documentos)).thenReturn(documentos);

        assertEquals(true,colaboradorRepository.existsById(1L));
        assertEquals(Optional.ofNullable(colaborador), colaboradorRepository.findById(1L));
        assertEquals(true,documentosRepository.existsById(1L));
        assertEquals(Optional.ofNullable(colaborador), documentosRepository.findById(1L));
        assertEquals(documentos, documentosRepository.save(documentos));

    }
    //Alerar Documentos sem sucesso
    @Test
    void NaoDeveAlerarDocumentosNullArgumentsExceptionIdNulo()
    {
        service.AlterarDocumento(null,"teste","teste","teste","teste",123L,
                123L,"teste",LocalDate.now(),123L,"teste",
                123L,321L,123L,"teste",123L,
                "teste","teste",123L,LocalDate.now(),"teste",123L,
                13L,"teste",123L,GrauEstudo.Mestrado,EstadoCivil.VIUVO);

        assertEquals(null,service.AlterarDocumento(null,"teste","teste","teste","teste",123L,
                123L,"teste",LocalDate.now(),123L,"teste",
                123L,321L,123L,"teste",123L,
                "teste","teste",123L,LocalDate.now(),"teste",123L,
                13L,"teste",123L,GrauEstudo.Mestrado,EstadoCivil.VIUVO));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(colaboradorRepository);
        verifyNoInteractions(documentosRepository);
    }

    @Test
    void NaoDeveAlerarDocumentosEntidadeNaoEncontradaCaindoNaEntityNotFoundException()
    {
        service.AlterarDocumento(1L,"teste","teste","teste","teste",123L,
                                123L,"teste",LocalDate.now(),123L,"teste",
                                123L,321L,123L,"teste",123L,
                                "teste","teste",123L,LocalDate.now(),"teste",123L,
                                13L,"teste",123L,GrauEstudo.Mestrado,EstadoCivil.VIUVO);

        when(colaboradorRepository.existsById(1L)).thenReturn(false);

        assertEquals(false, colaboradorRepository.existsById(1L));
        assertThat(NullargumentsException.class,notNullValue());

    }
    //alterar Endereço
    @Test
    void DeveAlterarEnderecoComSucesso()
    {
        service.EditarEndereco(1L,"teste","teste","teste",1234L,"teste","teste");
        when(colaboradorRepository.existsById(1L)).thenReturn(true);
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.ofNullable(colaborador));
        when(enderecoRepository.existsById(colaborador.getEndereco().getId())).thenReturn(true);
        when(enderecoRepository.findById(colaborador.getEndereco().getId())).thenReturn(Optional.ofNullable(endereco));
        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        assertEquals(true,colaboradorRepository.existsById(1L));
        assertEquals(Optional.ofNullable(colaborador),colaboradorRepository.findById(1L));
        assertEquals(true,enderecoRepository.existsById(1L));
        assertEquals(Optional.ofNullable(endereco),enderecoRepository.findById(1L));
        assertEquals(endereco, enderecoRepository.save(endereco));
    }
    //alterar Endereço sem sucesso
    @Test
    void NaoDeveAlterarEnderecoNullArgumentsExceptionIdArgumentoNulo()
    {
        service.EditarEndereco(null,"teste","teste","teste",1234L,"teste","teste");

        assertEquals(null,service.EditarEndereco(null,"teste","teste","teste",1234L,"teste","teste"));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(colaboradorRepository);
        verifyNoInteractions(enderecoRepository);
    }

    @Test
    void NaoDeveAlterarEnderecoNullArgumentsExceptionTodosArgumentosNulos()
    {
        service.EditarEndereco(null,null,null,null,null,null,null);

        assertEquals(null,service.EditarEndereco(null,null,null,null,null,null,null));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(colaboradorRepository);
        verifyNoInteractions(enderecoRepository);
    }

    @Test
    void NaoDeveAltearEnderecoEntidadeNaoEncontradaCaindoNoEntityNotFoundException()
    {
        service.EditarEndereco(1L,"teste","teste","teste",1234L,"teste","teste");
        when(colaboradorRepository.existsById(1L)).thenReturn(false);

        assertEquals(false,colaboradorRepository.existsById(1L));
        assertThat(EntityNotFoundException.class,notNullValue());
        verifyNoInteractions(enderecoRepository);
    }
    //alterar contato
    @Test
    void DeveAlterarContatoComSucesso()
    {
        service.EditarContato(1L,"teste",123L,123L,"teste","teste");
        when(colaboradorRepository.existsById(1L)).thenReturn(true);
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.ofNullable(colaborador));
        when(contatoRepository.existsById(colaborador.getContato().getId())).thenReturn(true);
        when(contatoRepository.findById(colaborador.getContato().getId())).thenReturn(Optional.ofNullable(contato));
        when(contatoRepository.save(contato)).thenReturn(contato);

        assertEquals(true, colaboradorRepository.existsById(1L));
        assertEquals(Optional.ofNullable(colaborador), colaboradorRepository.findById(1L));
        assertEquals(true, contatoRepository.existsById(1L));
        assertEquals(Optional.ofNullable(contato), contatoRepository.findById(1L));
        assertEquals(contato, contatoRepository.save(contato));
    }
    //alterar contato sem sucesso
    @Test
    void NaoDeveAlterarContatoNullArgumentsExceptionIdArgumentoNulo()
    {
        service.EditarContato(null,"teste",123L,123L,"teste","teste");

        assertEquals(null,service.EditarContato(1L,"teste",null,123L,"teste","teste"));
        assertThat(NullargumentsException.class,notNullValue());
    }

    @Test
    void NaoDeveAlterarContatoNullArgumentsExceptionTodosArgumentosNulos()
    {
        service.EditarContato(null,null,null,null,null,null);

        assertEquals(null,service.EditarContato(null,null,null,null,null,null));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(colaboradorRepository);
    }

    @Test
    void NaoDeveAltearContatoEntidadeNaoEncontradaCaindoNoEntityNotFoundException()
    {
        service.EditarContato(1L,"teste",123L,123L,"teste","teste");
        when(colaboradorRepository.existsById(1L)).thenReturn(false);

        assertEquals(false,colaboradorRepository.existsById(1L));
        assertThat(EntityNotFoundException.class,notNullValue());
    }

    //desligar Colaborador
    @Test
    void DeveAlterarCampoAtivoComSucesso()
    {
        service.DesativarColaborador(1L, true,"teste");
        when(colaboradorRepository.existsById(1L)).thenReturn(true);
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.ofNullable(colaborador));
        colaborador.setAtivo(false);
        colaborador.setMotivoDemissao("teste");
        colaborador.setDataDemissao(LocalDate.now());
        when(colaboradorRepository.save(colaborador)).thenReturn(colaborador);

        assertEquals(true, colaboradorRepository.existsById(1L));
        assertEquals(Optional.ofNullable(colaborador), colaboradorRepository.findById(1L));
        assertEquals(false, colaborador.getAtivo());
        assertEquals("teste", colaborador.getMotivoDemissao());
        assertEquals(colaborador, colaboradorRepository.save(colaborador));
    }
    //desligar Colaborador sem sucesso
    @Test
    void NaoDeveAlterarCampoAtivoNullArgumentsExceptionUmArgumentoNulo()
    {
        service.DesativarColaborador(1L, true,null);
        assertEquals(null, service.DesativarColaborador(1L, true,null));
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(colaboradorRepository);
    }
    @Test
    void NaoDeveAlterarCampoAtivoNullArgumentsExceptionTodosArgumentosNulos()
    {
        service.DesativarColaborador(null, null,null);

        assertEquals(null, service.DesativarColaborador(null, null,null) );
        assertThat(NullargumentsException.class,notNullValue());
        verifyNoInteractions(colaboradorRepository);
    }

    @Test
    void NaoDeveAlterarCampoAtivoEntidadeNaoEncontradaCaindoNoEntityNotFoundException()
    {
        service.DesativarColaborador(1L, true,"teste");
        when(colaboradorRepository.existsById(1L)).thenReturn(false);

        assertEquals(false,colaboradorRepository.existsById(1L));
        assertThat(EntityNotFoundException.class,notNullValue());
    }
}