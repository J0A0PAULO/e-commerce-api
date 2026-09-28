package br.com.e_commerce.api.perfis.Controller;

import br.com.e_commerce.api.perfis.Service.PerfilService;
import br.com.e_commerce.api.perfis.dto.PerfilRequest;
import br.com.e_commerce.api.perfis.dto.PerfilResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

        @PutMapping("/alterar/{id}")
        public ResponseEntity<PerfilResponse> alterar(@PathVariable Long id, @Valid @RequestBody PerfilRequest perfilRequest) {
            PerfilResponse alterar = perfilService.alterar(id, perfilRequest);
            return ResponseEntity.ok(alterar);
        }

}
