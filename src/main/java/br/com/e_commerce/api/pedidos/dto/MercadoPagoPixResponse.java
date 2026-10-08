package br.com.e_commerce.api.pedidos.dto;

import br.com.e_commerce.api.pedidos.enums.Status;
import br.com.e_commerce.api.user.entity.Usuarios;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MercadoPagoPixResponse(

        @JsonProperty("id")
         Long id,

         @JsonProperty("status")
         Status status,

         @JsonProperty("point_of_interaction")
         PointOfInteraction pointOfInteraction

) {
    public record PointOfInteraction (

            @JsonProperty("qr_code")
            String qrCode,

            @JsonProperty("qr_code_base64")
            String qrCodeBase64
    ) {}
}
