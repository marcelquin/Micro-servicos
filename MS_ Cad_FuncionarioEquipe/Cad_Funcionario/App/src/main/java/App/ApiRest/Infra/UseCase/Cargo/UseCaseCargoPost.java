package App.ApiRest.Infra.UseCase.Cargo;

import App.ApiRest.Domain.Cargo;
import App.ApiRest.Infra.Gateway.CargoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseCargoPost {

    private final CargoGateway cargoGateway;

    public UseCaseCargoPost(CargoGateway cargoGateway) {
        this.cargoGateway = cargoGateway;
    }

    public ResponseEntity<Cargo> NovoCargo(@RequestParam String nomeCargo, @RequestParam String descrisaoCargo,
                                           @RequestParam Long idDepartamento, @RequestParam Double salario,
                                           @RequestParam Double cargaHorariaSemanal)
    { return cargoGateway.NovoCargo(nomeCargo, descrisaoCargo, idDepartamento, salario, cargaHorariaSemanal);}

}
