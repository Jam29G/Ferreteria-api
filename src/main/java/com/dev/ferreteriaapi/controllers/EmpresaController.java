package com.dev.ferreteriaapi.controllers;

import com.dev.ferreteriaapi.entities.Empresa;
import com.dev.ferreteriaapi.entities.Producto;
import com.dev.ferreteriaapi.services.interfaces.IEmpresaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/empresas")
@CrossOrigin()
@Slf4j
public class EmpresaController {
    private final IEmpresaService empresaService;

    @GetMapping("")
    public ResponseEntity<List<Empresa>> getAll(@RequestParam() Boolean estado) {
        return new ResponseEntity<>(this.empresaService.getAll(estado), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getById(@PathVariable() Long id) {
        return new ResponseEntity<>(this.empresaService.getEmpresaById(id), HttpStatus.OK);
    }


    @GetMapping("/find")
    public ResponseEntity<List<Empresa>> findEmpresa(@RequestParam() String search, @RequestParam() Boolean estado) {
        return new ResponseEntity<>(this.empresaService.findEmpresa(search, estado), HttpStatus.OK);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<List<Producto>> getProductosRelacionados(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.empresaService.getProductosRelacionados(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Empresa> create(@RequestBody Empresa empresa) {
        return new ResponseEntity<>(this.empresaService.create(empresa), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> update(@RequestBody Empresa empresa, @PathVariable("id") Long id) {
        return new ResponseEntity<>(this.empresaService.updateEmpresa(empresa, id), HttpStatus.OK);
    }

    @PutMapping("changeState/{id}")
    public ResponseEntity<Empresa> changeState(@PathVariable("id") Long id, @RequestParam("estado") Boolean estado ) {
        return new ResponseEntity<>(this.empresaService.changeState(id, estado), HttpStatus.OK);
    }


}
