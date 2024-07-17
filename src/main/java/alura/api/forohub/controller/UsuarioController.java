package alura.api.forohub.controller;

import alura.api.forohub.models.usuario.DatosRegistroUsuario;
import alura.api.forohub.models.usuario.Usuario;
import alura.api.forohub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public void registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario){
        usuarioRepository.save(new Usuario(datosRegistroUsuario));
    }

}
