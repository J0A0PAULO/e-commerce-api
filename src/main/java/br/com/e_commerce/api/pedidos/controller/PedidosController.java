package br.com.e_commerce.api.pedidos.controller;

import br.com.e_commerce.api.config.JWTUserData;
import br.com.e_commerce.api.pedidos.dto.CriarPedidoRequest;
import br.com.e_commerce.api.pedidos.dto.PedidoReponse;
import br.com.e_commerce.api.pedidos.service.PedidosService;
import br.com.e_commerce.api.user.entity.Usuarios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    private final PedidosService pedidosService;

    public PedidosController(PedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/comprar")
    public ResponseEntity<PedidoReponse> comprarProdutos(@AuthenticationPrincipal JWTUserData usuarios, @RequestBody CriarPedidoRequest criarPedidoRequest) {
        PedidoReponse pedidoReponse = pedidosService.criarPedido(usuarios.getUserId(), criarPedidoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoReponse);
    }

    @PreAuthorize("hasRole('CLIENT', 'ADM')")
    public ResponseEntity<List<PedidoReponse>> listarPedidos() {
        List<PedidoReponse> pedidoReponses = pedidosService.mostrarPedidos();
        return ResponseEntity.ok(pedidoReponses);
    }

}
