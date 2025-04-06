package com.example.IniciacionSpringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class HolaController {

    @GetMapping("/hola/{nombre}")
    public String hola(@PathVariable String nombre) {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String fechaEjecucion = ahora.format(formatoFecha);
        String horaEjecucion = ahora.format(formatoHora);

        return "Hola " + nombre + ", son las " + horaEjecucion + " del " + fechaEjecucion;
    }
}
