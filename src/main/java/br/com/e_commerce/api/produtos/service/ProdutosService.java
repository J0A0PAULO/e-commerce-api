package br.com.e_commerce.api.produtos.service;

import br.com.e_commerce.api.exeption.NotFound;
import br.com.e_commerce.api.produtos.dto.ProdutoCategoriaRequest;
import br.com.e_commerce.api.produtos.dto.ProdutoRequest;
import br.com.e_commerce.api.produtos.dto.ProdutoResponse;
import br.com.e_commerce.api.produtos.entity.Produtos;
import br.com.e_commerce.api.produtos.mapper.ProdutosMapper;
import br.com.e_commerce.api.produtos.repository.ProdutosRepository;
import br.com.e_commerce.api.user.entity.Usuarios;
import br.com.e_commerce.api.user.repository.UsuariosRepository;
import br.com.e_commerce.api.user.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {


    private final ProdutosRepository produtosRepository;
    private final ProdutosMapper produtosMapper;
    private final UsuariosRepository usuariosRepository;

    public ProdutosService(ProdutosRepository produtosRepository, ProdutosMapper produtosMapper, UsuariosRepository usuariosRepository) {
        this.produtosRepository = produtosRepository;
        this.produtosMapper = produtosMapper;
        this.usuariosRepository = usuariosRepository;
    }

    @Transactional
    public List<ProdutoResponse> listarTodosProdutos() {

        List<ProdutoResponse> list = produtosRepository.findByAtivoTrue(true)
                .stream().map(produtos -> produtosMapper.toDTO(produtos)).toList();

        return list;
    }

    @Transactional
    public ProdutoResponse listarPorID(Long id) {
        Produtos produtosPorID = produtosRepository.findById(id).orElseThrow(() -> new NotFound());
        return produtosMapper.toDTO(produtosPorID);
    }

    @Transactional
    public ProdutoResponse alterarProduto(Long id, ProdutoRequest request) {

        Produtos produtoEncontrado = produtosRepository.findById(id).orElseThrow(() -> new NotFound());

        produtosMapper.toEntityConvert(request, produtoEncontrado);

        Produtos save = produtosRepository.save(produtoEncontrado);

        return produtosMapper.toDTO(save);

    }

    @Transactional
    public ProdutoResponse criarProduto(ProdutoRequest request, Long id) {

        Usuarios usuarios = usuariosRepository.findById(id).orElseThrow(() -> new NotFound());

        Produtos produto = produtosMapper.toEntity(request);
        produto.setCriadoPor(usuarios);

        produtosRepository.save(produto);

        return produtosMapper.toDTO(produto);

    }

    @Transactional
    public List<ProdutoResponse> listarPorCategoria(String categoria){

        List<ProdutoResponse> listCategorias = produtosRepository.findByCategoria(categoria)
                .stream()
                .map(produtos -> produtosMapper.toDTO(produtos)).toList();

        return listCategorias;
    }

    @Transactional
    public List<ProdutoResponse> listarProdutosPorPrecoCrecente(String nome) {

        List<ProdutoResponse> listarPrecoCrecente = produtosRepository.findByCategoriaOrderByPrecoAsc(nome)
                .stream().map(produtos -> produtosMapper.toDTO(produtos)).toList();

        return listarPrecoCrecente;

    }

    @Transactional
    public void deletar(Long id) {
        produtosRepository.deleteById(id);
    }


}
