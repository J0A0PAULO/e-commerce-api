package br.com.e_commerce.api.user.dto;


public class UsuarioResponse {

    private String token;
    private String tipo = "Bearer";

    public UsuarioResponse(String token) {
        this.token = token;
    }
}
