package alura.api.forohub.models.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(
        @NotBlank
        String nombre
) {
}
