package br.com.e_commerce.api.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {

    @NotNull(message = "usuario é obrigatorio")
    private String nome;

    @Email(message = "email é obrigatorio")
    private String email;

    @NotNull(message = "usuario é obrigatorio")
    private String senha;

}
