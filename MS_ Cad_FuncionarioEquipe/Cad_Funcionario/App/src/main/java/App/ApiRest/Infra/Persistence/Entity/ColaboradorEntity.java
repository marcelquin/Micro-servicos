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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "Colaborador")
public class ColaboradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String Sobrenome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JoinColumn(name = "data_Nascimento")
    private LocalDate dataNascimento;

    @JoinColumn(unique = true)
    private String matricula;

    @OneToOne
    @JoinColumn(name = "enderecoEntity_id", referencedColumnName = "id")
    private EnderecoEntity endereco;

    @OneToOne
    @JoinColumn(name = "documentosEntity_id", referencedColumnName = "id")
    private DocumentosEntity documentos;

    @OneToOne
    @JoinColumn(name = "equipeEntity_id", referencedColumnName = "id")
    private EquipeEntity equipe;

    @OneToOne
    @JoinColumn(name = "cargoEntity_id", referencedColumnName = "id")
    private CargoEntity cargo;

    @JoinColumn(name = "Data_Admissao")
    private LocalDate dataAdmissao;

    @JoinColumn(name = "Data_Demissao")
    private LocalDate dataDemissao;

    private String motivoDemissao;

    @OneToOne
    @JoinColumn(name = "contatoEntity_id", referencedColumnName = "id")
    private ContatoEntity contato;

    private Boolean ativo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

}

