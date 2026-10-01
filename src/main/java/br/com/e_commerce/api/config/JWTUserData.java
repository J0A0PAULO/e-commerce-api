package br.com.e_commerce.api.config;

import br.com.e_commerce.api.perfis.enums.PerfilNome;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Bag;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTUserData {

    private Long userId;
    private String email;
    private PerfilNome perfilNome;

    public List<PerfilNome> rolesList() {
        if (perfilNome != null) {
            return List.of(perfilNome);
        } else
            return List.of();
    }

}
