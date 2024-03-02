package App.ApiRest.Infra.Persistence.Entity;

import App.ApiRest.Domain.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;

    private String numero;

    private String complemento;

    private String cep;

    private String bairro;

    private String cidade;

    @Column(length = 2)
    private String estado;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public EnderecoEntity(Endereco record) {
        this.logradouro = record.logradouro();
        this.complemento = record.complemento();
        this.bairro = record.bairro();
        this.cidade = record.localidade();
        this.estado = record.uf();
    }
}
