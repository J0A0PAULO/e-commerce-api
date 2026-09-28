package br.com.e_commerce.api.user.mapper;

import br.com.e_commerce.api.user.dto.UsuarioRequest;
import br.com.e_commerce.api.user.entity.Usuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

     UsuarioRequest toDto(Usuarios usuarios);

     @Mapping(target = "id", ignore = true)
     Usuarios toEntity(UsuarioRequest usuarioDTO);

}
