package App.ApiRest.Infra.Persistence.Repository;


import App.ApiRest.Infra.Persistence.Entity.DepartamentoEntity;
import App.ApiRest.Infra.Persistence.Entity.FilhosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilhosRepository extends JpaRepository<FilhosEntity,Long> {
}
