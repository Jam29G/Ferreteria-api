package com.dev.ferreteriaapi.controllers;

import com.dev.ferreteriaapi.entities.Compra;
import com.dev.ferreteriaapi.entities.Empresa;
import com.dev.ferreteriaapi.entities.Venta;
import com.dev.ferreteriaapi.services.interfaces.ICompraService;
import com.dev.ferreteriaapi.services.interfaces.IVentaService;
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
@RequestMapping(value = "/api/venta")
@CrossOrigin()
@Slf4j
public class VentaController {

    private final IVentaService ventaService;

    @GetMapping("")
    public ResponseEntity<List<Venta>> getAll() {
        return new ResponseEntity<>(this.ventaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getById(@PathVariable() Long id) {
        return new ResponseEntity<>(this.ventaService.getVentaById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Venta> create(@RequestBody() Venta venta) {
        return new ResponseEntity<>(ventaService.create(venta), HttpStatus.CREATED);
    }

    @GetMapping("/rangeDate")
    public ResponseEntity<List<Venta>> getVentasByDates(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime start = LocalDateTime.parse(fechaInicio, formatter);
        LocalDateTime end = LocalDateTime.parse(fechaFin, formatter);

        if(!start.isBefore(end)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rango de fechas invalido");
        }

        return new ResponseEntity<>(this.ventaService.findVentaByDates(start, end), HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Venta>> getVentaByDates(@RequestParam("fecha") String fecha) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime start = LocalDateTime.parse(fecha + " 00:00", formatter);
        LocalDateTime end = LocalDateTime.parse(fecha + " 23:59", formatter);

        return new ResponseEntity<>(this.ventaService.findVentaByDates(start, end), HttpStatus.OK);
    }

}
