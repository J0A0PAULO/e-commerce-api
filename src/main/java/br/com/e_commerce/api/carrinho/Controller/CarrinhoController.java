package br.com.e_commerce.api.carrinho.controller;

import br.com.e_commerce.api.carrinho.dto.CarrinhoRequest;
import br.com.e_commerce.api.carrinho.dto.CarrinhoResponse;
import br.com.e_commerce.api.carrinho.service.CarrinhoService;
import br.com.e_commerce.api.config.JWTUserData;
import br.com.e_commerce.api.user.entity.Usuarios;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/adicionar")
    public ResponseEntity<CarrinhoResponse> adicionarProdutoAoCarrinho(@RequestBody @Valid CarrinhoRequest carrinhoRequest, @AuthenticationPrincipal JWTUserData jwtUserData) {
        CarrinhoResponse carrinhoResponse = carrinhoService.adicionarCarrinho(jwtUserData.getUserId(), carrinhoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoResponse);
    }


    @PutMapping("/alterarQuantidade")
    public ResponseEntity<CarrinhoResponse> alterarQuantidade(@AuthenticationPrincipal JWTUserData jwtUserData,@RequestBody @Valid CarrinhoRequest carrinhoRequest) {
        CarrinhoResponse carrinhoResponse = carrinhoService.alterarQuantidade(carrinhoRequest, jwtUserData.getUserId());
        return ResponseEntity.ok(carrinhoResponse);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/listar")
    public ResponseEntity<List<CarrinhoResponse>> listar(@AuthenticationPrincipal JWTUserData usuario) {
        List<CarrinhoResponse> carrinhoResponses = carrinhoService.listarCarrinho(usuario.getUserId());
        return ResponseEntity.ok(carrinhoResponses);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        carrinhoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
