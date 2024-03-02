package App.ApiRest.Infra.UseCase.Departamento;

import App.ApiRest.Domain.Departamento;
import App.ApiRest.Infra.Gateway.DepartamentoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseDepartamentoPost {

    private final DepartamentoGateway departamentoGateway;

    public UseCaseDepartamentoPost(DepartamentoGateway departamentoGateway) {
        this.departamentoGateway = departamentoGateway;
    }

    public ResponseEntity<Departamento> NovoDepartamento(@RequestParam String nome, @RequestParam String descrisao)
    { return  departamentoGateway.NovoDepartamento(nome,descrisao);}

}
