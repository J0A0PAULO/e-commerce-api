package br.com.e_commerce.api.pedidos.mapper;

import br.com.e_commerce.api.itemPedido.entity.ItemPedido;
import br.com.e_commerce.api.pedidos.dto.PedidoResponse;
import br.com.e_commerce.api.pedidos.entity.Pedidos;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PedidosMapper {

    PedidoResponse toDTO(Pedidos pedido);

    @Mapping(target = "produtoId", source = "produto.id")
    @Mapping(target = "nome", source = "produto.nome")
    PedidoResponse.ItemPedidoResponse toItemDTO(ItemPedido item);
}
