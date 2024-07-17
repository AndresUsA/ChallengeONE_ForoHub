package alura.api.forohub.models.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(

        String correo,
        String contrasena
) {
}
