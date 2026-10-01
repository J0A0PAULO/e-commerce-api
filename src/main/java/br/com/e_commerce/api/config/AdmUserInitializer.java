package br.com.e_commerce.api.config;

import br.com.e_commerce.api.perfis.entity.Perfis;
import br.com.e_commerce.api.perfis.enums.PerfilNome;
import br.com.e_commerce.api.perfis.repository.PerfisRepository;
import br.com.e_commerce.api.user.entity.Usuarios;
import br.com.e_commerce.api.user.repository.UsuariosRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdmUserInitializer {

    private  final PasswordEncoder passwordEncoder;
    private final UsuariosRepository usuariosRepository;
    private final PerfisRepository perfisRepository;


    public AdmUserInitializer(PasswordEncoder passwordEncoder, UsuariosRepository usuariosRepository,PerfisRepository perfisRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuariosRepository = usuariosRepository;
        this.perfisRepository = perfisRepository;
    }

    @Bean
    public CommandLineRunner createAdmUser() {

        return args -> {

            Perfis perfilADm = perfisRepository.findByNome(PerfilNome.ROLE_ADM ).orElseGet( () -> {
                Perfis novoPerfil = new Perfis();
                novoPerfil.setNome(PerfilNome.ROLE_ADM);
                perfisRepository.save(novoPerfil);
                return novoPerfil;
            });

            if (usuariosRepository.findByEmail("admin@gmail.com").isEmpty()){
                Usuarios admin = new Usuarios();
                admin.setNome("admin");
                admin.setSenha(passwordEncoder.encode("123"));
                admin.setEmail("admin@gmail.com");
                admin.setPerfil(perfilADm);
                usuariosRepository.save(admin);
            }

       };
    }

}
