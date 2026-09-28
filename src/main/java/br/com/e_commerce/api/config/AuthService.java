package br.com.e_commerce.api.config;

import br.com.e_commerce.api.user.repository.UsuariosRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UsuariosRepository usuariosRepository;

    public AuthService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        return usuariosRepository.findBynome(nome).orElseThrow(() -> new UsernameNotFoundException("usuario não encontrado" + nome));
    }
}
