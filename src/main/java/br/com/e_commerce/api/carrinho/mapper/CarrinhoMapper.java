package br.com.e_commerce.api.carrinho.mapper;

import br.com.e_commerce.api.carrinho.Entity.Carrinho;
import br.com.e_commerce.api.carrinho.dto.CarrinhoRequest;
import br.com.e_commerce.api.carrinho.dto.CarrinhoResponse;
import br.com.e_commerce.api.user.entity.Usuarios;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CarrinhoMapper {

    @Mapping(target = "idProduto", source = "produto.id")
    CarrinhoResponse toDTO(Carrinho carrinho);

    @Mapping(target = "id", ignore = true)
    Carrinho toEntity(CarrinhoRequest carrinhoRequest);

    CarrinhoResponse toResponse(Carrinho carrinho);
    void toEntityConvert(CarrinhoRequest carrinhoRequest, @MappingTarget Carrinho carrinho);

}
