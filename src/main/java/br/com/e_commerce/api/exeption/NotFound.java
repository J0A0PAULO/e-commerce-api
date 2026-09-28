package br.com.e_commerce.api.exeption;

public class NotFound extends RuntimeException{

    public NotFound(){
        super("não encontrado");
    }
}
