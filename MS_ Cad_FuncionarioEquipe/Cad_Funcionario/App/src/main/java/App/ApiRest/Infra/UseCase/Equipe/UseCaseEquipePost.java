package App.ApiRest.Infra.UseCase.Equipe;

import App.ApiRest.Domain.Equipe;
import App.ApiRest.Infra.Gateway.EquipeGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseEquipePost {

    private final EquipeGateway equipeGateway;

    public UseCaseEquipePost(EquipeGateway equipeGateway) {
        this.equipeGateway = equipeGateway;
    }

    public ResponseEntity<Equipe> NovaEquipe(@RequestParam Long idDepartamento, @RequestParam Long[] idCargos, @RequestParam Long[] idColaboradores, @RequestParam String nome, @RequestParam String descrisao)
    { return equipeGateway.NovaEquipe(idDepartamento, idCargos, idColaboradores, nome, descrisao);}

}
