package com.dev.ferreteriaapi.controllers;

import com.dev.ferreteriaapi.entities.DetalleProducto;
import com.dev.ferreteriaapi.services.interfaces.IDetalleProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/detalleProducto")
@CrossOrigin()
@Slf4j
public class DetalleProductoController {
    private final IDetalleProductoService detalleProductoService;

    @GetMapping("/empresa/{id}")
    public ResponseEntity<List<DetalleProducto>> getDetallesByEmpresa(@PathVariable("id") Long id, @RequestParam("estado") Boolean estado) {
        return new ResponseEntity<>(detalleProductoService.getDetallesByEmpresa(id,estado), HttpStatus.OK);
    }

    @GetMapping("/find/caducados")
    public ResponseEntity<List<DetalleProducto>> getProductosCaducados() {
        return new ResponseEntity<>(detalleProductoService.getProductosVencidos(), HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<List<DetalleProducto>> getAllDetalle() {
        return new ResponseEntity<>(detalleProductoService.getAllDetalle(), HttpStatus.OK);
    }

    @GetMapping("/find/{filter}")
    public ResponseEntity<List<DetalleProducto>> getByFilter(@PathVariable("filter") String filter) {
        return new ResponseEntity<>(detalleProductoService.findDetalle(filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleProducto> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(detalleProductoService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<List<DetalleProducto>> getDetallesByProductoId(@PathVariable("id") Long id, @RequestParam("estado") Boolean estado) {
        return new ResponseEntity<>(detalleProductoService.getByProductoId(id, estado), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<DetalleProducto> create(@RequestBody() DetalleProducto detalleProducto) {
        return new ResponseEntity<>(detalleProductoService.create(detalleProducto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleProducto> update(@RequestBody() DetalleProducto detalleProducto, @PathVariable("id") Long id) {
        return new ResponseEntity<>(this.detalleProductoService.update(detalleProducto, id), HttpStatus.OK);
    }

    @PutMapping("changeState/{id}")
    public ResponseEntity<Void> changeState(@RequestParam("estado") Boolean estado, @PathVariable("id") Long id ) {
        this.detalleProductoService.changeState(id, estado);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
