package br.com.e_commerce.api.config;

import br.com.e_commerce.api.perfis.enums.PerfilNome;
import br.com.e_commerce.api.user.entity.Usuarios;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
public class TokenConfig {


    @Value("${SPRING_SECURITY_SECRET}")
    public String secret;


    public String generateToken(Usuarios usuarios) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim("usuario_id", usuarios.getId())
                .withClaim("roles", List.of(usuarios.getPerfil().getNome().name()))
                .withSubject(usuarios.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(5000))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validationToken(String token) {

        try {

        Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decode = JWT.require(algorithm)
                    .build().verify(token);



            List<String> roles = decode.getClaim("roles").asList(String.class);

            PerfilNome perfil;

                   if (roles != null && !roles.isEmpty()) {

                    perfil = PerfilNome.valueOf(roles.get(0));
                   } else {
                       perfil = null;
                   }

            return Optional.of(JWTUserData.builder().userId(decode.getClaim("usuario_id").asLong())
                    .email(decode.getSubject())
                    .perfilNome(perfil).build());


        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }

    }

}
