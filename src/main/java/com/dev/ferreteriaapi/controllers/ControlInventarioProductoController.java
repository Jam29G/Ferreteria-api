package com.dev.ferreteriaapi.controllers;

import com.dev.ferreteriaapi.entities.ControlInventarioProducto;
import com.dev.ferreteriaapi.services.implement.ControlInventarioService;
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
@RequestMapping(value = "/api/controlInventario")
@CrossOrigin()
@Slf4j
public class ControlInventarioProductoController {

    private final ControlInventarioService controlInventarioService;

    @GetMapping("")
    public ResponseEntity<List<ControlInventarioProducto>> getTodayRecords() {
        return new ResponseEntity<>(this.controlInventarioService.getTodayRecords(), HttpStatus.OK);
    }

    @GetMapping("/getByRange")
    public ResponseEntity<List<ControlInventarioProducto>> getByRange(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime start = LocalDateTime.parse(fechaInicio, formatter).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime end = LocalDateTime.parse(fechaFin, formatter).withHour(23).withMinute(59).withSecond(59).withNano(0);

        if(!start.isBefore(end)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rango de fechas invalido");
        }

        return new ResponseEntity<>(this.controlInventarioService.getByDateRange(start, end), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ControlInventarioProducto> getById(@PathVariable() Long id) {
        return new ResponseEntity<>(this.controlInventarioService.findRegistroById(id), HttpStatus.OK);
    }

}
