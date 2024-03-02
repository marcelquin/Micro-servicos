package App.ApiRest.Domain;

import java.time.LocalDate;

public record Colaborador(
        String nome,
        String Sobrenome,
        LocalDate dataNascimento,
        String matricula,
        String Departamento,
        String Cargo,
        Double Salario,
        Double cargaHorariaSemanal,
        String logradouro,
        String numero,
        String bairro,
        Long cep,
        String cidade,
        String estado,
        String email,
        Long telefone,
        Long celular,
        String instagram,
        String site
) {
}
