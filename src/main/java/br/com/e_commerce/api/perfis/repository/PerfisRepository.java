package br.com.e_commerce.api.perfis.repository;

import br.com.e_commerce.api.perfis.entity.Perfis;
import br.com.e_commerce.api.perfis.enums.PerfilNome;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PerfisRepository extends JpaRepository<Perfis, Long> {

    Optional<Perfis> findByNome(PerfilNome perfilNome);

}
