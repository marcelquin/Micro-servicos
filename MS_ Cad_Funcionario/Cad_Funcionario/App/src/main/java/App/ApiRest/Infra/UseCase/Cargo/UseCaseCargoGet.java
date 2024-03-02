package App.ApiRest.Infra.UseCase.Cargo;

import App.ApiRest.Domain.Cargo;
import App.ApiRest.Infra.Gateway.CargoGateway;
import App.ApiRest.Infra.Persistence.Entity.CargoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseCargoGet {

    private final CargoGateway cargoGateway;

    public UseCaseCargoGet(CargoGateway cargoGateway) {
        this.cargoGateway = cargoGateway;
    }

    public ResponseEntity<List<CargoEntity>> listarCargos()
    { return  cargoGateway.listarCargos();}

    public ResponseEntity<Cargo> BuscarPorId(@RequestParam Long id)
    { return cargoGateway.BuscarPorId(id);}
}
