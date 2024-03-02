package App.ApiRest.Infra.Persistence.Entity;

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
@Table(name = "Cargo")
public class CargoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "Nome_Cargo")
    private String nome;

    @JoinColumn(name = "Descrisao_Cargo")
    private String descrisao;

    @OneToOne
    @JoinColumn(name = "departamento", referencedColumnName = "id")
    private DepartamentoEntity departamento;

    private  Double salario;

    @JoinColumn(name = "Carga_Horaria_Semanal")
    private Double cargaHorariaSemanal;

    private Boolean ativo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;


}
