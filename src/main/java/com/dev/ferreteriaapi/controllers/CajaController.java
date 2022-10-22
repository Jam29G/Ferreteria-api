package com.dev.ferreteriaapi.controllers;

import com.dev.ferreteriaapi.entities.Caja;
import com.dev.ferreteriaapi.entities.MovimCaja;
import com.dev.ferreteriaapi.services.interfaces.ICajaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/cajas")
@CrossOrigin()
@Slf4j
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

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Caja> getUsuarioCaja(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cajaService.getCajaUsuario(id), HttpStatus.OK);
    }

    @GetMapping("/movimientos")
    public ResponseEntity<List<MovimCaja>> getRegistroCaja(
            @RequestParam("cajaId") Long cajaId,
            @RequestParam("isIngreso") Boolean isIngreso
    ) {
        return ResponseEntity.ok().body(cajaService.getRegistrosCaja(isIngreso, cajaId));
    }

    @GetMapping("/aprobaciones")
    public ResponseEntity<List<Caja>> getCajasPendAprob() {
        return ResponseEntity.ok().body(cajaService.getPendienteAprob("P", false));
    }

    @GetMapping("/todayFilter")
    public ResponseEntity<List<Caja>> getCajaFilterNow(
            @RequestParam("estado") Boolean estado,
            @RequestParam(value = "aprobacion", required = false) String aprobacion
    ) {
        LocalDateTime fechaInic = LocalDateTime.now().with(LocalTime.of(0, 0));
        LocalDateTime fechaFin = fechaInic.plusDays(1);

        return ResponseEntity.ok()
                .body(this.cajaService.getCajasByFilterDate(estado, aprobacion, fechaInic, fechaFin));
    }

    @GetMapping("/rangeDate")
    public ResponseEntity<List<Caja>> getCajaDateFilter(
            @RequestParam("estado") Boolean estado,
            @RequestParam(value = "aprobacion", required = false) String aprobacion,
            @RequestParam("fechaInic") String fechaInic,
            @RequestParam("fechaFin") String fechaFin
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime start = LocalDateTime.parse(fechaInic, formatter);
        LocalDateTime end = LocalDateTime.parse(fechaFin, formatter);

        if(!start.isBefore(end)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rango de fechas invalido");
        }
        return ResponseEntity.ok()
                .body(this.cajaService.getCajasByFilterDate(estado, aprobacion, start, end));
    }

    @PostMapping()
    public ResponseEntity<Caja> abrirCaja(@RequestBody Caja caja) {
        return new ResponseEntity<>(cajaService.abrirCaja(caja), HttpStatus.OK);
    }

    @PutMapping("/cerrar")
    public ResponseEntity<Caja> cerrarCaja(@RequestBody Caja caja) {
        return new ResponseEntity<>(cajaService.cerrarCaja(caja), HttpStatus.OK);
    }

    @PutMapping("/aprobacion")
    public ResponseEntity<Caja> changeAprob(@RequestParam("id") Long id, @RequestParam("aprob") String aprob) {
        return new ResponseEntity<>(cajaService.changeAprob(id, aprob), HttpStatus.OK);
    }

    @PutMapping("/emitirGasto")
    public ResponseEntity<Map<String, Object>> emitirGasto(@RequestBody Caja caja, @RequestParam("monto") Double monto, @RequestParam("motivo") String motivo) {

        return new ResponseEntity<>(cajaService.emitirGasto(caja, monto, motivo), HttpStatus.OK);

    }

    @PutMapping("/abonarCaja")
    public ResponseEntity<Map<String, Object>> abonarCaja(@RequestBody Caja caja, @RequestParam("monto") Double monto, @RequestParam("motivo") String motivo) {

        return new ResponseEntity<>(cajaService.abonarCaja(caja, monto, motivo), HttpStatus.OK);

    }


}
