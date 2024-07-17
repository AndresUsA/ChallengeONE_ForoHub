package alura.api.forohub.repository;

import alura.api.forohub.models.topicos.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {
    Page<Topico> findByStatusTrue(Pageable paginacion);
    Optional<Topico> findByIdAndStatusTrue(Long id);
}
