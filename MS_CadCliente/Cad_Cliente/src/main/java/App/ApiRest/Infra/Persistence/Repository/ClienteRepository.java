package App.ApiRest.Infra.Persistence.Repository;

import App.ApiRest.Infra.Persistence.Entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {

    Boolean existsBynome(String nome);
    Boolean existsBycpf(Long cpf);

    Optional<ClienteEntity> findBynome(String nome);
    Optional<ClienteEntity> findBycpf(Long cpf);
}
