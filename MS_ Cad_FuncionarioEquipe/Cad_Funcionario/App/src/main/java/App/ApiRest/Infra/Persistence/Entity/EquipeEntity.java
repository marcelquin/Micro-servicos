package App.ApiRest.Infra.Persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "Equipe")
public class EquipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descrisao;

    @OneToOne
    @JoinColumn(name = "departamentoEntity_id", referencedColumnName = "id")
    private DepartamentoEntity departamento;

    @OneToMany
    private List<CargoEntity> cargos;

    @OneToMany
    private List<ColaboradorEntity> colaboradores;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;
}
