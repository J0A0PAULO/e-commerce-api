package br.com.e_commerce.api.carrinho.service;

import br.com.e_commerce.api.carrinho.Entity.Carrinho;
import br.com.e_commerce.api.carrinho.repository.CarrinhoRepository;
import br.com.e_commerce.api.carrinho.dto.CarrinhoRequest;
import br.com.e_commerce.api.carrinho.dto.CarrinhoResponse;
import br.com.e_commerce.api.carrinho.mapper.CarrinhoMapper;
import br.com.e_commerce.api.exeption.NotFound;
import br.com.e_commerce.api.produtos.entity.Produtos;
import br.com.e_commerce.api.produtos.repository.ProdutosRepository;
import br.com.e_commerce.api.user.entity.Usuarios;
import br.com.e_commerce.api.user.repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoMapper carrinhoMapper;
    private final ProdutosRepository produtosRepository;
    private final UsuariosRepository usuariosRepository;


    public CarrinhoService(CarrinhoRepository carrinhoRepository, CarrinhoMapper carrinhoMapper, ProdutosRepository produtosRepository, UsuariosRepository usuariosRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.carrinhoMapper = carrinhoMapper;
        this.produtosRepository = produtosRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public CarrinhoResponse adicionarCarrinho(Long id, CarrinhoRequest request) {

        Produtos produto = produtosRepository.findById(request.getIdProduto()).orElseThrow(() -> new NotFound());

        Usuarios usuario = usuariosRepository.findById(id).orElseThrow(() -> new NotFound());

        Optional<Carrinho> byUsuarioAndProduto = carrinhoRepository.findByUsuarioIdAndProduto(id, produto);

        if (byUsuarioAndProduto.isPresent()) {

            Carrinho carrinho = byUsuarioAndProduto.get();
            carrinho.setQuantidade(carrinho.getQuantidade() + request.getQuantidade());

            Carrinho save = carrinhoRepository.save(carrinho);

            return carrinhoMapper.toDTO(save);
        } else {
            Carrinho primeiroProduto = new Carrinho();
            primeiroProduto.setProduto(produto);
            primeiroProduto.setUsuario(usuario);
            primeiroProduto.setQuantidade(request.getQuantidade());
            Carrinho save = carrinhoRepository.save(primeiroProduto);
            return carrinhoMapper.toDTO(save);
        }
    }


    public List<CarrinhoResponse> listarCarrinho(Long usuarioId) {
        List<CarrinhoResponse> listaProdutos = carrinhoRepository.findByUsuarioId(usuarioId).stream().map(produtos -> carrinhoMapper.toDTO(produtos)).toList();
        return listaProdutos;
    }

    public CarrinhoResponse alterarQuantidade(CarrinhoRequest request, Long id) {

        Produtos produto = produtosRepository.findById(request.getIdProduto()).orElseThrow(() -> new NotFound());

        Carrinho carrinho = carrinhoRepository.findByUsuarioIdAndProduto(id, produto).orElseThrow(() -> new NotFound());

        carrinho.setQuantidade(request.getQuantidade());

        carrinhoRepository.save(carrinho);

        return carrinhoMapper.toDTO(carrinho);
    }

    public void deletar(Long id) {
        carrinhoRepository.deleteById(id);
    }

}


