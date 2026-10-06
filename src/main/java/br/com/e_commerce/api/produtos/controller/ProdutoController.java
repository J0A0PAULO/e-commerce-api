package br.com.e_commerce.api.produtos.controller;

import br.com.e_commerce.api.config.JWTUserData;
import br.com.e_commerce.api.produtos.dto.ProdutoCategoriaRequest;
import br.com.e_commerce.api.produtos.dto.ProdutoRequest;
import br.com.e_commerce.api.produtos.dto.ProdutoResponse;
import br.com.e_commerce.api.produtos.service.ProdutosService;
import br.com.e_commerce.api.user.repository.UsuariosRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutosService produtosService;
    private final UsuariosRepository usuariosRepository;

    public ProdutoController(ProdutosService produtosService,UsuariosRepository usuariosRepository) {
        this.produtosService = produtosService;
        this.usuariosRepository = usuariosRepository;
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarProdutos() {
        List<ProdutoResponse> produtoResponses = produtosService.listarTodosProdutos();
        return ResponseEntity.ok(produtoResponses);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> listarProdutoPorId(@PathVariable Long id) {
        ProdutoResponse produtoResponse = produtosService.listarPorID(id);
        return ResponseEntity.ok(produtoResponse);
    }

    @PreAuthorize("hasRole('ADM')")
    @PostMapping("/criar")
    public ResponseEntity<ProdutoResponse> criarProduto(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal JWTUserData userData){
        ProdutoResponse produtoResponse = produtosService.criarProduto(produtoRequest, userData.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);
    }

    @PreAuthorize("hasRole('ADM')")
    @PutMapping("/alterar/{id}")
    public ResponseEntity<ProdutoResponse> alterar(@PathVariable Long id, @RequestBody @Valid ProdutoRequest produtoRequest){
        ProdutoResponse produtoResponse = produtosService.alterarProduto(id, produtoRequest);
        return ResponseEntity.ok(produtoResponse);
    }

    @PreAuthorize("hasRole('ADM')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        produtosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/categoria")
    public ResponseEntity<List<ProdutoResponse>> listarPorCateogira(@RequestParam("nome") String nome) {
        List<ProdutoResponse> listarPorCategoria = produtosService.listarPorCategoria(nome);

        return ResponseEntity.ok(listarPorCategoria);

    }
    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/categoria/ordenar")
    public ResponseEntity<List<ProdutoResponse>> listarOrdenadoPorPreco(@RequestParam("nome") String nome){

        List<ProdutoResponse> listarPorPreco = produtosService.listarProdutosPorPrecoCrecente(nome);
        return ResponseEntity.ok(listarPorPreco);

    }

}
