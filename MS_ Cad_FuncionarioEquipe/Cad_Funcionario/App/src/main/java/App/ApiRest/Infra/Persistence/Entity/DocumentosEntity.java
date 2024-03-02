package App.ApiRest.Infra.Persistence.Entity;

import App.ApiRest.Infra.Persistence.Enum.EstadoCivil;
import App.ApiRest.Infra.Persistence.Enum.GrauEstudo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "Documentos")
public class DocumentosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "Local_Nascimento")
    private String localNascimento;

    @JoinColumn(name = "Estado_Nascimento")
    private String estadoNascimento;

    @JoinColumn(name = "Nome_Mae")
    private String nomeMae;

    @JoinColumn(name = "Nome_Pai")
    private String nomePai;

    private int numeroFilhos;

    @OneToMany
    @JoinColumn(name = "Filhos_Numero")
    private List<FilhosEntity> filhos;

    private Long cPF;

    private Long rG;

    @JoinColumn(name = "RG_Orgao")
    private String rGOrgao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JoinColumn(name = "RG_Data_Emissao")
    private LocalDate rGDataEmissao;

    @JoinColumn(name = "Carteira_Trabalho_Numero")
    private Long carteiraTrabalhoNumero;

    @JoinColumn(name = "Carteira_Trabalho_Serie")
    private String carteiraTrabalhoSerie;

    @JoinColumn(name = "Titulo_Eleitor_Numero")
    private Long tituloEleitorNumero;

    @JoinColumn(name = "Titulo_Eleitor_Zona")
    private Long tituloEleitorZona;

    @JoinColumn(name = "Titulo_Eleitor_Sessao")
    private Long tituloEleitorSessao;

    @JoinColumn(name = "Titulo_Eleitor_Estado")
    private String tituloEleitorEstado;

    @JoinColumn(name = "Carteira_Reservista_Numero")
    private Long carteiraReservistaNumero;

    @JoinColumn(name = "Carteira_Reservista_Serie")
    private String carteiraReservistaSerie;

    @JoinColumn(name = "Carteira_Reservista_Categoria")
    private String carteiraReservistaCategoria;

    @JoinColumn(name = "Pis_Numero")
    private Long pisNumero;

    @JoinColumn(name = "Pis_Data_Cadastro")
    private LocalDate pisDataCadastro;

    @JoinColumn(name = "Pis_Banco")
    private String pisBanco;

    @JoinColumn(name = "Pis_Agencia")
    private Long pisAgencia;

    @JoinColumn(name = "Banco_Conta")
    private Long bancoConta;

    private String banco;

    @JoinColumn(name = "Banco_Agencia")
    private Long bancoAgencia;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "Grau_Estudo")
    private GrauEstudo grauEstudo;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "Estado_Civil")
    private EstadoCivil estadoCivil;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    @OneToMany
    private List<ArquivosEntity> arquivos;
}
