package com.example.contactos.gestion_contactos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contactos")
public class ContactController {

    @Autowired
    private ContactoRepository contactoRepository;

    @GetMapping
    public ResponseEntity<List<Contacto>> obtenerTodosLosContactos() {
        List<Contacto> contactos = contactoRepository.findAll();
        return new ResponseEntity<>(contactos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contacto> obtenerContactoPorId(@PathVariable Long id) {
        Optional<Contacto> contacto = contactoRepository.findById(id);
        return contacto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Contacto> crearContacto(@RequestBody Contacto nuevoContacto) {
        Contacto contactoGuardado = contactoRepository.save(nuevoContacto);
        return new ResponseEntity<>(contactoGuardado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contacto> actualizarContacto(@PathVariable Long id, @RequestBody Contacto contactoActualizado) {
        Optional<Contacto> contactoExistente = contactoRepository.findById(id);
        if (contactoExistente.isPresent()) {
            contactoActualizado.setId(id); // Aseguramos que el ID sea el correcto
            Contacto contactoGuardado = contactoRepository.save(contactoActualizado);
            return new ResponseEntity<>(contactoGuardado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContacto(@PathVariable Long id) {
        if (contactoRepository.existsById(id)) {
            contactoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}