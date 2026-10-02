package br.com.e_commerce.api.produtos.dto;

import br.com.e_commerce.api.user.entity.Usuarios;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequest {

    @NotBlank(message = "nome não pode ser nulo")
    private String nome;

    @NotBlank(message = "descricao não pode ser nulo")
    private String descricao;

    @Positive(message = "preco não pode ser nulo")
    private BigDecimal preco;

    @NotBlank(message = "categoria não pode ser nulo")
    private String categoria;

    @NotNull(message = "status ativo é obrigatorio")
    private Boolean ativo = true;

    @NotNull(message = "o id do criador é obrigatorio")
    private Long id;
}
