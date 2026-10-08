package br.com.e_commerce.api.pedidos.dto;


public record MercadoPagoWebhookRequest(
        String action,
        String type,
        Data data
) {
    public record Data(
            String id
    ){
    }
}
