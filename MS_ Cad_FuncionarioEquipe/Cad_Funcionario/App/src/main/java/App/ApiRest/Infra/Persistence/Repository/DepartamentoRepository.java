package App.ApiRest.Infra.Persistence.Repository;


import App.ApiRest.Infra.Persistence.Entity.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity,Long> {
}
