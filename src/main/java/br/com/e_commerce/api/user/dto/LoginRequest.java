package br.com.e_commerce.api.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Email(message = "Email é obrigatorio")
    private String email;

    @NotNull(message = "Email é obrigatorio")
    private String senha;

}
