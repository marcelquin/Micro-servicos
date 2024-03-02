package App.ApiRest.Domain;

import java.time.LocalDate;

public record Cliente(
        String nome,
        String sobrenome,
        Long rg,
        Long cpf,
        LocalDate dataNascimento,
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        String email,
        Long telefone,
        Long celular

) {
}
