package br.com.e_commerce.api.perfis.dto;

import br.com.e_commerce.api.perfis.enums.PerfilNome;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilResponse {

    private PerfilNome perfilNome;

}
