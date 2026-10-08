package br.com.e_commerce.api.pedidos.mapper;

import br.com.e_commerce.api.pedidos.dto.MercadoPagoPixRequest;
import br.com.e_commerce.api.pedidos.dto.MercadoPagoPixResponse;
import br.com.e_commerce.api.pedidos.entity.Pedidos;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PedidosRepository {

    Pedidos toEntity(MercadoPagoPixRequest request);

    MercadoPagoPixResponse toDTO(Pedidos pedidos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntityConvert(MercadoPagoPixRequest pedido, @MappingTarget Pedidos pedidos);

}
