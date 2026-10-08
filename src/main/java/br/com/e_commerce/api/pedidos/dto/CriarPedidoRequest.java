package br.com.e_commerce.api.pedidos.dto;


import java.math.BigDecimal;
import java.util.List;

public record CriarPedidoRequest (
        List<ItemPedidoRequest> itens
){
    public record ItemPedidoRequest(
            Long produtoId,
            Integer quantidade
    ) {}
}
