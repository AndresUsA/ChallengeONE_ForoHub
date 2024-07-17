package alura.api.forohub.models.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        //@NotNull
        LocalDateTime fechaCreacion,
        @NotNull
        Long autorId,
        @NotNull
        Long cursoId
) {
}
