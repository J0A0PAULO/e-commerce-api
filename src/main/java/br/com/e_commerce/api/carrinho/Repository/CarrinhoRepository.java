package br.com.e_commerce.api.carrinho.repository;

import br.com.e_commerce.api.carrinho.Entity.Carrinho;
import br.com.e_commerce.api.produtos.entity.Produtos;
import br.com.e_commerce.api.user.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Optional<Carrinho> findByUsuarioIdAndProduto(Long id, Produtos produto);

    List<Carrinho> findByUsuario(Usuarios usuarios);

}
