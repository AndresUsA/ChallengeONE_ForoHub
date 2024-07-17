package alura.api.forohub.controller;

import alura.api.forohub.models.topicos.*;
import alura.api.forohub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<TopicoDto> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){
        TopicoDto topicoDto = topicoService.crearTopico(datosRegistroTopico);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicoDto);
    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> listarTopicos(@PageableDefault(size = 10)Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopicos::new));
        //return topicoRepository.findAll(paginacion).map(DatosListadoTopicos::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaTopicoId(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico= new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(),
                topico.getAutor().getId(),topico.getAutor().getNombre(),topico.getCurso().getId(),topico.getCurso().getNombre());
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico2(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(),
                topico.getAutor().getId(),topico.getAutor().getNombre(),topico.getCurso().getId(),topico.getCurso().getNombre()));
    }

    //DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

//    DELETE BASE DE DATOS
//    public void eliminarTopico(@PathVariable Long id){
//        Topico topico = topicoRepository.getReferenceById(id);
//        topicoRepository.delete(topico);
//    }
//    @PutMapping
//    @Transactional
//    public void actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
//        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
//        topico.actualizarDatos(datosActualizarTopico);
//    }

}
