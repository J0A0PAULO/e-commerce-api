package br.com.e_commerce.api.exeption;

public class PagamentoException extends RuntimeException {
    public PagamentoException() {
        super("Não foi possível gerar o pagamento. Tente novamente.");
    }
}
