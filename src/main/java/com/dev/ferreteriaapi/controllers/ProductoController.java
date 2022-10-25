package com.dev.ferreteriaapi.controllers;

import com.dev.ferreteriaapi.entities.Producto;
import com.dev.ferreteriaapi.services.interfaces.IProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/productos")
@CrossOrigin()
@Slf4j
public class ProductoController {

    private final IProductoService productoService;

    @GetMapping()
    public ResponseEntity<List<Producto>> getAllProductos(@RequestParam("estado") Boolean estado) {

        return new ResponseEntity<>(this.productoService.getAll(estado), HttpStatus.OK);
    }

}
