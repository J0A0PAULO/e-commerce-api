package br.com.e_commerce.api.carrinho.mapper;

import br.com.e_commerce.api.carrinho.Entity.Carrinho;
import br.com.e_commerce.api.carrinho.dto.CarrinhoRequest;
import br.com.e_commerce.api.carrinho.dto.CarrinhoResponse;
import br.com.e_commerce.api.user.entity.Usuarios;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CarrinhoMapper {

    CarrinhoResponse toDTO(Carrinho carrinho);

    Carrinho toEntity(CarrinhoRequest carrinhoRequest);

    void toEntityConvert(CarrinhoRequest carrinhoRequest, @MappingTarget Carrinho carrinho);

}
