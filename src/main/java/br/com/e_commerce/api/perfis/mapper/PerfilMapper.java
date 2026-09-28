package br.com.e_commerce.api.perfis.mapper;


import br.com.e_commerce.api.perfis.dto.PerfilRequest;
import br.com.e_commerce.api.perfis.dto.PerfilResponse;
import br.com.e_commerce.api.perfis.entity.Perfis;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PerfilMapper {

    Perfis toEntity(PerfilRequest perfis);

    PerfilResponse toDTO(Perfis perfis);

}
