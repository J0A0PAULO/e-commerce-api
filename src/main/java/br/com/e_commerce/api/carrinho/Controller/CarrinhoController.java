package br.com.e_commerce.api.carrinho.Controller;

import br.com.e_commerce.api.carrinho.dto.CarrinhoRequest;
import br.com.e_commerce.api.carrinho.dto.CarrinhoResponse;
import br.com.e_commerce.api.carrinho.service.CarrinhoService;
import br.com.e_commerce.api.user.dto.UsuarioRequest;
import br.com.e_commerce.api.user.entity.Usuarios;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<CarrinhoResponse> adicionarProdutoAoCarrinho(@RequestBody @Valid CarrinhoRequest carrinhoRequest, @AuthenticationPrincipal Usuarios usuarios) {
        CarrinhoResponse carrinhoResponse = carrinhoService.adicionarCarrinho(usuarios, carrinhoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoResponse);
    }

    @PutMapping("/alterarQuantidade")
    public ResponseEntity<CarrinhoResponse> alterarQuantidade(@AuthenticationPrincipal Usuarios usuario,@RequestBody @Valid CarrinhoRequest carrinhoRequest) {
        CarrinhoResponse carrinhoResponse = carrinhoService.alterarQuantidade(carrinhoRequest, usuario);
        return ResponseEntity.ok(carrinhoResponse);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CarrinhoResponse>> listar(@AuthenticationPrincipal Usuarios usuario) {
        List<CarrinhoResponse> carrinhoResponses = carrinhoService.listarCarrinho(usuario);
        return ResponseEntity.ok(carrinhoResponses);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        carrinhoService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
