package App.ApiRest.Infra.Persistence.Entity;

import App.ApiRest.Domain.Colaborador;
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
@Table(name = "Contato")
public class ContatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private Long telefone;

    private Long celular;

    private String instagram;

    private String site;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public ContatoEntity(Colaborador record) {
        this.email = record.email();
        this.telefone = record.telefone();
        this.celular = record.celular();
        this.instagram = record.instagram();
        this.site = record.site();
    }
}
