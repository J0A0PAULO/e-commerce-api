package br.com.e_commerce.api.perfis;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "perfis")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Perfis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome", unique = true, nullable = false, length = 30)
    private String nome;

}
