package br.com.e_commerce.api.perfis.Service;

import br.com.e_commerce.api.exeption.BadRequest;
import br.com.e_commerce.api.exeption.GlobalExeptionHandler;
import br.com.e_commerce.api.exeption.NotFound;
import br.com.e_commerce.api.perfis.dto.PerfilRequest;
import br.com.e_commerce.api.perfis.dto.PerfilResponse;
import br.com.e_commerce.api.perfis.entity.Perfis;
import br.com.e_commerce.api.perfis.enums.PerfilNome;
import br.com.e_commerce.api.perfis.mapper.PerfilMapper;
import br.com.e_commerce.api.perfis.repository.PerfisRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilService {


    private final PerfisRepository perfisRepository;
    private final PerfilMapper perfilMapper;
    private final GlobalExeptionHandler globalExeptionHandler;

    public PerfilService(PerfisRepository perfisRepository, PerfilMapper perfilMapper, GlobalExeptionHandler globalExeptionHandler) {
        this.perfisRepository = perfisRepository;
        this.perfilMapper = perfilMapper;
        this.globalExeptionHandler = globalExeptionHandler;
    }

    public PerfilResponse alterar(Long id, PerfilRequest request) {

       return   perfisRepository.findById(id)
                .map(perfilEncontrado -> {
                    perfilEncontrado.setId(id);
                    perfilEncontrado.setNome(perfilMapper.toEntity(request).getNome());
                    perfisRepository.save(perfilEncontrado);
                   return perfilMapper.toDTO(perfilEncontrado);
                }).orElseThrow(() -> new NotFound());
    }

}
