package App.ApiRest.Infra.Persistence.Entity;


import App.ApiRest.Domain.Departamento;
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
@Table(name = "Departamento")
public class DepartamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "Depattamento_Nome")
    private String Nome;

    @JoinColumn(name = "Departamento_Descricao")
    private String Descrisao;

    private Boolean ativo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public DepartamentoEntity(Departamento record) {
        Nome = record.Nome();
        Descrisao = record.Descrisao();
    }
}
