package br.com.e_commerce.api.exeption;

public class BadRequest extends RuntimeException {

    public BadRequest(){
        super("Estoque insuficiente para: ");
    }

}
