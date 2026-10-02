package br.com.e_commerce.api.produtos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoCategoriaRequest {

    @NotBlank(message = "Categoria é obrigatoria")
    private String categoria;

}
