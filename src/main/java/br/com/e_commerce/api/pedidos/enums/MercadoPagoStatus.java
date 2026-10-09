package br.com.e_commerce.api.pedidos.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MercadoPagoStatus  {
    PENDING("pending"),
    APPROVED("approved"),
    AUTHORIZED("authorized"),
    IN_PROCESS("in_process"),
    IN_MEDIATION("in_mediation"),
    REJECTED("rejected"),
    CANCELLED("cancelled"),
    REFUNDED("refunded"),
    CHARGED_BACK("charged_back"),
    UNKNOWN("unknown");

    private final String codigo;

    MercadoPagoStatus(String codigo) {
        this.codigo = codigo;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static MercadoPagoStatus from(String valor) {
        for (MercadoPagoStatus s : values()) {
            if (s.codigo.equalsIgnoreCase(valor)) return s;
        }
        return UNKNOWN;
    }
}
