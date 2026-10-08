package br.com.e_commerce.api.pedidos.service;

import br.com.e_commerce.api.config.WebClientConfig;
import br.com.e_commerce.api.pedidos.dto.MercadoPagoPixRequest;
import br.com.e_commerce.api.pedidos.dto.MercadoPagoPixResponse;
import br.com.e_commerce.api.user.entity.Usuarios;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MercadoPagoService {

    private final WebClientConfig mercadopagoPagoWebClient;

    public MercadoPagoService(WebClientConfig mercadopagoPagoWebClient) {
        this.mercadopagoPagoWebClient = mercadopagoPagoWebClient;
    }

    public MercadoPagoPixResponse gerarPagamentoPix(Usuarios usuarios, BigDecimal valorTotal) {

        MercadoPagoPixRequest requestMercadoPago = new MercadoPagoPixRequest(
                valorTotal,
                "pix",
                new MercadoPagoPixRequest.Pagamento(
                        usuarios.getEmail()
                )
        );

        return  mercadopagoPagoWebClient.webClient().post()
                .uri("/v1/payments")
                .bodyValue(requestMercadoPago)
                .retrieve()
                .bodyToMono(MercadoPagoPixResponse.class)
                .block();
    }

    public MercadoPagoPixResponse buscarPagamentoPorId(String pagamentoId){

        return mercadopagoPagoWebClient.webClient().get()
                .uri("/v1/payments/" + pagamentoId)
                .retrieve()
                .bodyToMono(MercadoPagoPixResponse.class)
                .block();

    }

}
