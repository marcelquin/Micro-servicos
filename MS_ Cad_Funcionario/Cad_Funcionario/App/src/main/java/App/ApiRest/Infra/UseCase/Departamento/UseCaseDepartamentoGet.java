package App.ApiRest.Infra.UseCase.Departamento;

import App.ApiRest.Domain.Departamento;
import App.ApiRest.Infra.Gateway.DepartamentoGateway;
import App.ApiRest.Infra.Persistence.Entity.DepartamentoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseDepartamentoGet {

    private final DepartamentoGateway departamentoGateway;

    public UseCaseDepartamentoGet(DepartamentoGateway departamentoGateway) {
        this.departamentoGateway = departamentoGateway;
    }

    public ResponseEntity<List<DepartamentoEntity>> listarDepartamento()
    { return departamentoGateway.listarDepartamento();}

    public ResponseEntity<Departamento> BuscarDepartamentoPorId(@RequestParam Long id)
    { return departamentoGateway.BuscarDepartamentoPorId(id);}


}
