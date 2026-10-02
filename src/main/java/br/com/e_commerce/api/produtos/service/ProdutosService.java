package br.com.e_commerce.api.produtos.service;

import br.com.e_commerce.api.exeption.NotFound;
import br.com.e_commerce.api.produtos.dto.ProdutoCategoriaRequest;
import br.com.e_commerce.api.produtos.dto.ProdutoRequest;
import br.com.e_commerce.api.produtos.dto.ProdutoResponse;
import br.com.e_commerce.api.produtos.entity.Produtos;
import br.com.e_commerce.api.produtos.mapper.ProdutosMapper;
import br.com.e_commerce.api.produtos.repository.ProdutosRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {


    private final ProdutosRepository produtosRepository;
    private final ProdutosMapper produtosMapper;

    public ProdutosService(ProdutosRepository produtosRepository, ProdutosMapper produtosMapper) {
        this.produtosRepository = produtosRepository;
        this.produtosMapper = produtosMapper;
    }

    public List<ProdutoResponse> listarTodosProdutos() {

        List<ProdutoResponse> list = produtosRepository.findAll()
                .stream().map(produtos -> produtosMapper.toDTO(produtos)).toList();

        return list;
    }

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
    public ProdutoResponse criarProduto(ProdutoRequest request) {

        Produtos produto = produtosMapper.toEntity(request);

        produtosRepository.save(produto);

        return produtosMapper.toDTO(produto);

    }

    public List<ProdutoResponse> listarPorCategoria(String categoria){

        List<ProdutoResponse> listCategorias = produtosRepository.findByCategoria(categoria)
                .stream()
                .map(produtos -> produtosMapper.toDTO(produtos)).toList();

        return listCategorias;
    }

    public List<ProdutoResponse> listarProdutosPorPrecoCrecente(String nome) {

        List<ProdutoResponse> listarPrecoCrecente = produtosRepository.findByNomeOrderByPrecoAsc(nome)
                .stream().map(produtos -> produtosMapper.toDTO(produtos)).toList();

        return listarPrecoCrecente;

    }

    @Transactional
    public void deletar(Long id) {
        produtosRepository.deleteById(id);
    }


}
