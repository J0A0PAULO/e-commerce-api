package br.com.e_commerce.api.pedidos.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.util.List;

public record CriarPedidoRequest(
        @NotEmpty(message = "o pedido precisa ter pelo menos um item")
        @Valid
        List<ItemPedidoRequest> itens
) {
    public record ItemPedidoRequest(
            @NotNull(message = "produtoId é obrigatório")
            Long produtoId,

            @NotNull(message = "quantidade é obrigatória")
            @Positive(message = "quantidade deve ser maior que zero")
            Integer quantidade
    ) {}
}