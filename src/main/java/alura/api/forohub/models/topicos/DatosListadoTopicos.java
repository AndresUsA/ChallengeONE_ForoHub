package alura.api.forohub.models.topicos;

import java.time.LocalDateTime;

public record DatosListadoTopicos(

        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autorNombre,
        String cursoNombre
) {

    //QUITAR ID
    public DatosListadoTopicos(Topico topico){
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(),
                topico.getAutor().getNombre(),topico.getCurso().getNombre());
    }
}
