package br.com.e_commerce.api.user.controller;

import br.com.e_commerce.api.user.dto.LoginRequest;
import br.com.e_commerce.api.user.dto.UsuarioRequest;
import br.com.e_commerce.api.user.dto.UsuarioResponse;
import br.com.e_commerce.api.user.dto.UsuarioToken;
import br.com.e_commerce.api.user.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class AuthController {


    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioToken> login(@RequestBody @Valid LoginRequest request) {
        UsuarioToken login = usuarioService.login(request);
        return ResponseEntity.ok(login);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponse> register(@RequestBody @Valid UsuarioRequest request) {
        UsuarioResponse registrar = usuarioService.usuarioRegistro(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrar);
    }

    @PostMapping("/admin/register")
    @PreAuthorize("hasRole(ADM)")
    public ResponseEntity<UsuarioResponse> adminRegister(@RequestBody @Valid UsuarioRequest request) {
        UsuarioResponse usuarioResponse = usuarioService.adminRegistro(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
    }

}
