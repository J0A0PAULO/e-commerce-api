package br.com.e_commerce.api.produtos.mapper;


import br.com.e_commerce.api.produtos.dto.ProdutoRequest;
import br.com.e_commerce.api.produtos.dto.ProdutoResponse;
import br.com.e_commerce.api.produtos.entity.Produtos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutosMapper {

     ProdutoRequest toDTO(Produtos produtos);

     @Mapping(target = "id", ignore = true)
     Produtos toEntity(ProdutoResponse produtoResponse);

}
