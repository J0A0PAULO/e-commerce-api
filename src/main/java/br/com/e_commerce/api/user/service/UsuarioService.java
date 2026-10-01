package br.com.e_commerce.api.user.service;

import br.com.e_commerce.api.config.TokenConfig;
import br.com.e_commerce.api.exeption.GlobalExeptionHandler;
import br.com.e_commerce.api.exeption.NotFound;
import br.com.e_commerce.api.perfis.entity.Perfis;
import br.com.e_commerce.api.perfis.enums.PerfilNome;
import br.com.e_commerce.api.perfis.repository.PerfisRepository;
import br.com.e_commerce.api.user.dto.LoginRequest;
import br.com.e_commerce.api.user.dto.UsuarioRequest;
import br.com.e_commerce.api.user.dto.UsuarioResponse;
import br.com.e_commerce.api.user.dto.UsuarioToken;
import br.com.e_commerce.api.user.entity.Usuarios;
import br.com.e_commerce.api.user.mapper.UsuarioMapper;
import br.com.e_commerce.api.user.repository.UsuariosRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;
    private final PerfisRepository perfisRepository;
    private final UsuarioMapper usuarioMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;


    public UsuarioService(UsuariosRepository usuariosRepository,PasswordEncoder passwordEncoder,PerfisRepository perfisRepository,UsuarioMapper usuarioMapper, AuthenticationManager authenticationManager,TokenConfig tokenConfig) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = passwordEncoder;
        this.perfisRepository = perfisRepository;
        this.usuarioMapper = usuarioMapper;
        this.authenticationManager = authenticationManager;
        this.tokenConfig  = tokenConfig;
    }

    public UsuarioToken login (LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Usuarios usuario = (Usuarios) authentication.getPrincipal();
        String token = tokenConfig.generateToken(usuario);

        UsuarioToken usuarioToken = new UsuarioToken();
        usuarioToken.setToken(token);
        return usuarioToken;
    }




    public UsuarioResponse usuarioRegistro(UsuarioRequest request) {
        UsuarioResponse registro = registro(request, PerfilNome.ROLE_CLIENT);
        return registro;
    }
    public UsuarioResponse adminRegistro(UsuarioRequest request) {
        UsuarioResponse registro = registro(request, PerfilNome.ROLE_ADM);
        return registro;
    }


    private UsuarioResponse registro(UsuarioRequest request, PerfilNome perfilNome) {

        Usuarios usuarios = new Usuarios();
        usuarios.setNome(request.getNome());
        usuarios.setEmail(request.getEmail());
        usuarios.setSenha(passwordEncoder.encode(request.getSenha()));

        Perfis perfis = perfisRepository.findByNome(perfilNome).orElseThrow(() -> new NotFound());
        usuarios.setPerfil(perfis);

        usuariosRepository.save(usuarios);

        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setNome(usuarios.getNome());
        usuarioResponse.setEmail(usuarios.getEmail());

        return usuarioResponse;

    }
    private UsuarioResponse registroUser(UsuarioRequest request, PerfilNome perfilNome) {

        Usuarios usuarios = new Usuarios();
        usuarios.setNome(request.getNome());
        usuarios.setEmail(request.getEmail());
        usuarios.setSenha(passwordEncoder.encode(request.getSenha()));

        Perfis perfis = perfisRepository.findByNome(perfilNome).orElseThrow(() -> new NotFound());
        usuarios.setPerfil(perfis);

        usuariosRepository.save(usuarios);

        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setNome(usuarios.getNome());
        usuarioResponse.setEmail(usuarios.getEmail());

        return usuarioResponse;

    }


}
