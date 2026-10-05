package br.com.e_commerce.api.carrinho.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoResponse {

    private Long idProduto;
    private Integer quantidade;

}
