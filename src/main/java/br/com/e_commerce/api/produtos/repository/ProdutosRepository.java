package br.com.e_commerce.api.produtos.repository;

import br.com.e_commerce.api.produtos.entity.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

    List<Produtos> findByCategoria(String categorias);
    List<Produtos> findByNomeOrderByPrecoAsc(String nome);

}
