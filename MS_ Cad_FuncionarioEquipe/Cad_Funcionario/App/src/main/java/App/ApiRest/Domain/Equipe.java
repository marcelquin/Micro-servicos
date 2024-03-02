package App.ApiRest.Domain;

import java.util.List;

public record Equipe(

        String nome,
        String descrisao,
        String departamento,
        List<String> cargos


) {
}
