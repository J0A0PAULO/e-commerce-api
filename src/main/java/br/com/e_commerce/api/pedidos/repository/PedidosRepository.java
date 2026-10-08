package br.com.e_commerce.api.pedidos.repository;

import br.com.e_commerce.api.pedidos.entity.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {

    Optional<Pedidos> findByPagamentoIdGateway(String pagamentoIdGateway);

}
