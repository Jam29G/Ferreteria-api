package com.dev.ferreteriaapi.controllers;

import com.dev.ferreteriaapi.entities.Compra;
import com.dev.ferreteriaapi.entities.DetalleProducto;
import com.dev.ferreteriaapi.entities.Empresa;
import com.dev.ferreteriaapi.services.interfaces.ICompraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/compra")
@CrossOrigin()
@Slf4j
public class CompraController {

    private final ICompraService compraService;

    @GetMapping("")
    public ResponseEntity<List<Compra>> getAll() {
        return new ResponseEntity<>(this.compraService.getAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Compra> create(@RequestBody() Compra compra) {
        return new ResponseEntity<>(compraService.create(compra), HttpStatus.CREATED);
    }

}
