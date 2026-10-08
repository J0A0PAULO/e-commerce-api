package br.com.e_commerce.api.itemPedido.entity;

import br.com.e_commerce.api.pedidos.entity.Pedidos;
import br.com.e_commerce.api.produtos.entity.Produtos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "itens_pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedidos pedido;

    @JoinColumn(name = "produto_id")
    @ManyToOne
    private Produtos produto;

}
