package br.com.e_commerce.api.carrinho.dto;

import br.com.e_commerce.api.user.entity.Usuarios;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoRequest {

    @NotNull(message = "o id do produto é obrigatorio")
    private Long idProduto;

    @Min(value = 1, message = "quantidade tem de ser 0 ou maior")
    @NotNull(message = "quantidade é obrigatoria")
    private Integer quantidade;
}
