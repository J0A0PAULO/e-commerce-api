package br.com.e_commerce.api.user.entity;

import br.com.e_commerce.api.perfis.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "usuarios")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 120)
    private String nome;

    @Column(name = "email", unique = true, nullable = false, length = 160)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String senha;

    @Column(name = "criado_em", nullable = false)
    @CreationTimestamp
    private LocalDateTime criado_em;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;



}
