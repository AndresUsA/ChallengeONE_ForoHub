package alura.api.forohub.models.topicos;

import alura.api.forohub.infra.ResourceNotFoundException;
import alura.api.forohub.models.curso.Curso;
import alura.api.forohub.models.usuario.Usuario;
import alura.api.forohub.repository.CursoRepository;
import alura.api.forohub.repository.TopicoRepository;
import alura.api.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public TopicoDto crearTopico(DatosRegistroTopico datosRegistroTopico) {
        Usuario autor = usuarioRepository.findById(datosRegistroTopico.autorId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + datosRegistroTopico.autorId()));

        Curso curso = cursoRepository.findById(datosRegistroTopico.cursoId())
                .orElseThrow(() -> new ResourceNotFoundException ("Curso no encontrado con id: " + datosRegistroTopico.cursoId()));

        Topico topico = new Topico(datosRegistroTopico, autor, curso);
        topicoRepository.save(topico);
        return convertirATopicoDto(topico);
    }

    private TopicoDto convertirATopicoDto(Topico topico) {
        return new TopicoDto(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getAutor().getId(),
                topico.getAutor().getNombre(),
                topico.getCurso().getId(),
                topico.getCurso().getNombre()
        );
    }
}
