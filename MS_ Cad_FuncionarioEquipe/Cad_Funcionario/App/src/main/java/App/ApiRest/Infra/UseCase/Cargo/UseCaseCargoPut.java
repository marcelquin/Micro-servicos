package App.ApiRest.Infra.UseCase.Cargo;

import App.ApiRest.Domain.Cargo;
import App.ApiRest.Infra.Gateway.CargoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseCargoPut {

    private final CargoGateway cargoGateway;

    public UseCaseCargoPut(CargoGateway cargoGateway) {
        this.cargoGateway = cargoGateway;
    }

    public ResponseEntity<Cargo> EditarCargo(@RequestParam Long id, String nomeCargo, String descrisaoCargo,
                                             Double salario, Double cargaHorariaSemanal)
    { return cargoGateway.EditarCargo(id, nomeCargo, descrisaoCargo, salario, cargaHorariaSemanal);}

    public ResponseEntity<Cargo> desativarCargo(@RequestParam Long id, @RequestParam Boolean desativar)
    { return cargoGateway.desativarCargo(id, desativar);}


}
