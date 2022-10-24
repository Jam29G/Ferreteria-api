package com.dev.ferreteriaapi.controllers;

import com.dev.ferreteriaapi.entities.Ubicacion;
import com.dev.ferreteriaapi.services.interfaces.IUbicacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/ubicaciones")
@CrossOrigin()
@Slf4j
public class UbicacionController {

    private final IUbicacionService ubicacionService;

    @GetMapping("")
    public ResponseEntity<List<Ubicacion>> getAll() {
        return new ResponseEntity<>(this.ubicacionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.ubicacionService.getById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Ubicacion> create(@RequestBody Ubicacion ubicacion) {
        return new ResponseEntity<>(this.ubicacionService.create(ubicacion), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.ubicacionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
