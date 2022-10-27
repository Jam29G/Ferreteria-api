package com.dev.ferreteriaapi.controllers;

import com.dev.ferreteriaapi.entities.Compra;
import com.dev.ferreteriaapi.entities.DetalleProducto;
import com.dev.ferreteriaapi.entities.Empresa;
import com.dev.ferreteriaapi.entities.Producto;
import com.dev.ferreteriaapi.services.interfaces.ICompraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("/rangeDate")
    public ResponseEntity<List<Compra>> getComprasByDates(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime start = LocalDateTime.parse(fechaInicio, formatter);
        LocalDateTime end = LocalDateTime.parse(fechaFin, formatter);

        if(!start.isBefore(end)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rango de fechas invalido");
        }

        return new ResponseEntity<>(this.compraService.findCompraByDates(start, end), HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Compra>> getComprasByDates(@RequestParam("fecha") String fecha) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime start = LocalDateTime.parse(fecha + " 00:00", formatter);
        LocalDateTime end = LocalDateTime.parse(fecha + " 23:59", formatter);

        return new ResponseEntity<>(this.compraService.findCompraByDates(start, end), HttpStatus.OK);
    }


}
