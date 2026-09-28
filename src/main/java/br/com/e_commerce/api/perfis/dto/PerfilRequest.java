package br.com.e_commerce.api.perfis.dto;


import br.com.e_commerce.api.perfis.enums.PerfilNome;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PerfilRequest {

    @NotNull(message = "O nome do perfil é obrigatorio")
    private PerfilNome perfilNome;

}
