package alura.api.forohub.models.topicos;


import java.time.LocalDateTime;
import java.util.List;

public record TopicoDto(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Long autorId,
        String autorNombre,
        Long cursoId,
        String cursoNombre
) {
}
