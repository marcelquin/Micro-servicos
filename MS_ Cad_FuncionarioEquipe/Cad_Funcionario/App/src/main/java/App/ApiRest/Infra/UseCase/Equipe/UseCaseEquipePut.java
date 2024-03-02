package App.ApiRest.Infra.UseCase.Equipe;

import App.ApiRest.Domain.Equipe;
import App.ApiRest.Infra.Gateway.EquipeGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseEquipePut {

    private final EquipeGateway equipeGateway;

    public UseCaseEquipePut(EquipeGateway equipeGateway) {
        this.equipeGateway = equipeGateway;
    }

    public ResponseEntity<Equipe> ALterarDadosEquipe(@RequestParam Long idEquipe, String nome, String descrisao)
    { return equipeGateway.ALterarDadosEquipe(idEquipe, nome, descrisao);}
    public ResponseEntity<Equipe> AdicionarColaborador(@RequestParam Long idEquipe,@RequestParam Long[]idColaboradores)
    { return equipeGateway.AdicionarColaborador(idEquipe, idColaboradores);}
}
