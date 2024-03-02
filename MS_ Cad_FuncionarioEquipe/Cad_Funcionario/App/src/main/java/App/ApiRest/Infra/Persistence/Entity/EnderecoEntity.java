package App.ApiRest.Infra.Persistence.Entity;

import App.ApiRest.Domain.Colaborador;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "Endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;

    private String numero;

    private String bairro;

    private Long cep;

    private String cidade;

    private String estado;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public EnderecoEntity(Colaborador record) {
        this.logradouro = record.logradouro();
        this.numero = record.numero();
        this.bairro = record.bairro();
        this.cep = record.cep();
        this.cidade = record.cidade();
        this.estado = record.estado();
    }
}
