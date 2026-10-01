package br.com.e_commerce.api.user.entity;

import br.com.e_commerce.api.perfis.entity.Perfis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuarios implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 120)
    private String nome;

    @Column(name = "email", unique = true, nullable = false, length = 160)
    private String email;

    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

    @Column(name = "criado_em", nullable = false)
    @CreationTimestamp
    private LocalDateTime criado_em;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfis perfil;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.perfil == null || this.perfil.getNome() == null) {
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority(this.perfil.getNome().name()));
    }

    @Override
    public @Nullable String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
