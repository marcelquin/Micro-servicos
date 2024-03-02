package App.ApiRest.Infra.Gateway;

import App.ApiRest.Domain.Departamento;
import App.ApiRest.Infra.Persistence.Entity.DepartamentoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DepartamentoGateway {


    public ResponseEntity<List<DepartamentoEntity>> listarDepartamento();

    public ResponseEntity<Departamento> BuscarDepartamentoPorId(@RequestParam Long id);

    public ResponseEntity<Departamento> NovoDepartamento(@RequestParam String depNome, @RequestParam String depDescrisao);

    public ResponseEntity<Departamento> EditarDepartamento(@RequestParam Long id,@RequestParam String nome, @RequestParam String descrisao);

    public ResponseEntity<Departamento> desativarDepartamento(@RequestParam Long id, @RequestParam Boolean desativar);

}
