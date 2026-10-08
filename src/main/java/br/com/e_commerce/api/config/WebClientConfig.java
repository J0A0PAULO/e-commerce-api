package br.com.e_commerce.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;



@Configuration
public class WebClientConfig {

    @Value("${mercadopago.base-url}")
    private String url;

    @Value("${token}")
    private String token;

    public WebClient webClient() {
        return WebClient.builder().
                defaultHeader("Authorization", "Bearer" + token).
                baseUrl(url).build();
    }

}
