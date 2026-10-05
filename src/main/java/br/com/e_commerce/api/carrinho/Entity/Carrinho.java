package br.com.e_commerce.api.carrinho.Entity;

import br.com.e_commerce.api.produtos.entity.Produtos;
import br.com.e_commerce.api.user.entity.Usuarios;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrinho_itens")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade", nullable = false)
    @Min(value = 1, message = "numero começa em 1")
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produtos produto;

}