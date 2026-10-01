package br.com.e_commerce.api.produtos.entity;

import br.com.e_commerce.api.user.entity.Usuarios;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", columnDefinition="TEXT")
    private String descricao;

    @Column(name = "preco", nullable = false)
    @DecimalMin(value = "0.0", message = "preço deve maior ou igual a zero")
    private BigDecimal preco;

    @Column(name = "estoque", nullable = false)
    @Min(value = 0, message = "estoque do produto deve ser maior ou igual a zero")  private Integer estoque;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "criado_em")
    @CreationTimestamp
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    @UpdateTimestamp
    private LocalDateTime atualizadoEm;

    @ManyToOne
    @JoinColumn(name = "criado_por_id")
    private Usuarios criadoPor;

}
