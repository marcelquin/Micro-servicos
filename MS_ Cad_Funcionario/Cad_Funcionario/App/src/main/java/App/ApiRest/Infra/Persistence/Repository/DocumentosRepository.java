package App.ApiRest.Infra.Persistence.Repository;


import App.ApiRest.Infra.Persistence.Entity.DepartamentoEntity;
import App.ApiRest.Infra.Persistence.Entity.DocumentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentosRepository extends JpaRepository<DocumentosEntity,Long> {
}
