package App.ApiRest.Infra.Persistence.Repository;

import App.ApiRest.Infra.Persistence.Entity.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity,Long> {
}
