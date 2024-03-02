package App.ApiRest.Infra.Persistence.Repository;

import App.ApiRest.Infra.Persistence.Entity.ArquivosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<ArquivosEntity, Long> {

    public Boolean existsBynomeArquivo(String nomeArquivo);

    public ArquivosEntity findBynomeArquivo(String nomeArquivo);
}
