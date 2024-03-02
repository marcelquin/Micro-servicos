package App.ApiRest.Infra.UseCase.Departamento;

import App.ApiRest.Domain.Departamento;
import App.ApiRest.Infra.Gateway.DepartamentoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseDepartamentoPut {

    private final DepartamentoGateway departamentoGateway;

    public UseCaseDepartamentoPut(DepartamentoGateway departamentoGateway) {
        this.departamentoGateway = departamentoGateway;
    }

    public ResponseEntity<Departamento> EditarDepartamento(@RequestParam Long id, @RequestParam String depNome, @RequestParam String depDescrisao)
    { return departamentoGateway.EditarDepartamento(id, depNome, depDescrisao);}

    public ResponseEntity<Departamento> desativarDepartamento(@RequestParam Long id, @RequestParam Boolean desativar)
    { return departamentoGateway.desativarDepartamento(id, desativar);}

}
