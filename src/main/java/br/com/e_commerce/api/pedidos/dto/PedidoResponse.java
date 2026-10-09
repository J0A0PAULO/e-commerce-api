package br.com.e_commerce.api.pedidos.dto;

import br.com.e_commerce.api.pedidos.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public record PedidoResponse(
        Long id,
        Status status,
        BigDecimal valorTotal,
        String qrCode,
        LocalDateTime criadoEm,
        List<ItemPedidoResponse> itens
) {
    public record ItemPedidoResponse(Long produtoId, String nome,
                                     Integer quantidade, BigDecimal precoUnitario) {}
}