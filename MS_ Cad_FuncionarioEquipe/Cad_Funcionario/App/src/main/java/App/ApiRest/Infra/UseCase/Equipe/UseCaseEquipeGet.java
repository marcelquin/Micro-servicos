package App.ApiRest.Infra.UseCase.Equipe;

import App.ApiRest.Domain.Equipe;
import App.ApiRest.Infra.Gateway.EquipeGateway;
import App.ApiRest.Infra.Persistence.Entity.EquipeEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseEquipeGet {

    private final EquipeGateway equipeGateway;

    public UseCaseEquipeGet(EquipeGateway equipeGateway) {
        this.equipeGateway = equipeGateway;
    }

    public ResponseEntity<List<EquipeEntity>> ListarEquipes()
    { return equipeGateway.ListarEquipes();}
    public ResponseEntity<Equipe> BuscarEquipePorId(@RequestParam Long id)
    { return equipeGateway.BuscarEquipePorId(id);}
}
