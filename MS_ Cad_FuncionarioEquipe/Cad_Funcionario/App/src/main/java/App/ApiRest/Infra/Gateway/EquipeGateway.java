package App.ApiRest.Infra.Gateway;

import App.ApiRest.Domain.Equipe;
import App.ApiRest.Infra.Persistence.Entity.EquipeEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EquipeGateway {

    public ResponseEntity<List<EquipeEntity>> ListarEquipes();
    public ResponseEntity<Equipe> BuscarEquipePorId(@RequestParam Long id);
    public ResponseEntity<Equipe> NovaEquipe(@RequestParam Long idDepartamento, @RequestParam Long[] idCargos, @RequestParam Long[] idColaboradores, @RequestParam String nome, @RequestParam String descrisao);
    public ResponseEntity<Equipe> ALterarDadosEquipe(@RequestParam Long idEquipe, String nome, String descrisao);
    public ResponseEntity<Equipe> AdicionarColaborador(@RequestParam Long idEquipe,@RequestParam Long[]idColaboradores);




}
