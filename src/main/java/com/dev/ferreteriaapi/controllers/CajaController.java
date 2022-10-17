package com.dev.ferreteriaapi.controllers;

import com.dev.ferreteriaapi.entities.Caja;
import com.dev.ferreteriaapi.services.interfaces.ICajaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/cajas")
@CrossOrigin()
public class CajaController {

    private final ICajaService cajaService;

    //Obtener todas las cajas
    @GetMapping()
    public ResponseEntity<List<Caja>> getAll(@RequestParam(name = "estado") Boolean estado ) {
        return ResponseEntity.ok().body(cajaService.getAllCajas(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caja> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cajaService.getCajaById(id), HttpStatus.OK);
    }


}
