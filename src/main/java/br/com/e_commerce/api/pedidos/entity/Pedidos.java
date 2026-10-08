package br.com.e_commerce.api.pedidos.entity;

import br.com.e_commerce.api.itemPedido.entity.ItemPedido;
import br.com.e_commerce.api.pedidos.enums.Status;
import br.com.e_commerce.api.user.entity.Usuarios;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.AGUARDANDO_PAGAMENTO;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "pagamento_id_gateway", unique = true)
    private String pagamentoIdGateway;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "criado_em", nullable = false)
    @CreationTimestamp
    private LocalDateTime criadoEm;

    @Column(name = "pago_em")
    private LocalDateTime pagoEm;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuarios;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido>  itens = new ArrayList<>();


}
