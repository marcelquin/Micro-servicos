package App.ApiRest.Infra.UseCase.Colaboador;

import App.ApiRest.Domain.Colaborador;
import App.ApiRest.Infra.Gateway.ColaboradorGateway;
import App.ApiRest.Infra.Persistence.Entity.ColaboradorEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public class UseCaseColaboradorGet {

    private final ColaboradorGateway colaboradorGateway;

    public UseCaseColaboradorGet(ColaboradorGateway colaboradorGateway) {
        this.colaboradorGateway = colaboradorGateway;
    }

    public ResponseEntity<List<ColaboradorEntity>> listarColaborador()
    { return colaboradorGateway.listarColaborador();}

    public ResponseEntity<Colaborador> BuscarPorId(@RequestParam Long id)
    { return colaboradorGateway.BuscarPorId(id);}

    public ResponseEntity<Resource> downloadFiles(@RequestParam Long id) throws IOException
    { return colaboradorGateway.downloadFiles(id);}
}
