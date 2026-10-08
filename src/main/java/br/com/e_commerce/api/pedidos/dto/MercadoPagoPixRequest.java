package br.com.e_commerce.api.pedidos.dto;

import br.com.e_commerce.api.user.entity.Usuarios;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record MercadoPagoPixRequest(

        @JsonProperty("transaction_amount")
        BigDecimal preco,

        @JsonProperty("payment_method_id")
        String metodoPagamento,

        @JsonProperty("payer")
        Pagamento pagamentoDTO
) {

    public record Pagamento(
            String email
    ){

    }

}
