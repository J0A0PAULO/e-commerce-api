package br.com.e_commerce.api.produtos.repository;

import br.com.e_commerce.api.produtos.entity.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

}
