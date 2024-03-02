package App.ApiRest.Infra.Gateway;

import App.ApiRest.Domain.Cargo;
import App.ApiRest.Domain.Colaborador;
import App.ApiRest.Infra.Persistence.Entity.CargoEntity;
import App.ApiRest.Infra.Persistence.Entity.ColaboradorEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CargoGateway {


    public ResponseEntity<List<CargoEntity>> listarCargos();

    public ResponseEntity<Cargo> BuscarPorId(@RequestParam Long id);

    public ResponseEntity<Cargo> NovoCargo(@RequestParam String nomeCargo, @RequestParam String descrisaoCargo,
                                           @RequestParam Long idDepartamento, @RequestParam Double salario,
                                           @RequestParam Double cargaHorariaSemanal);

    public ResponseEntity<Cargo> EditarCargo(@RequestParam Long id, String nomeCargo, String descrisaoCargo,
                                             Double salario, Double cargaHorariaSemanal);

    public ResponseEntity<Cargo> desativarCargo(@RequestParam Long id, @RequestParam Boolean desativar);

}
