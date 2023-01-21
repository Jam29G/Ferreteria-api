package com.dev.ferreteriaapi.controllers;

import com.dev.ferreteriaapi.entities.Empresa;
import com.dev.ferreteriaapi.entities.Producto;
import com.dev.ferreteriaapi.entities.Ubicacion;
import com.dev.ferreteriaapi.services.interfaces.IProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/productos")
@CrossOrigin()
@Slf4j
public class ProductoController {

    private final IProductoService productoService;

    @GetMapping(value = "/image/{filename}")
    public @ResponseBody void getImage(HttpServletResponse response, @PathVariable("filename") String filename ) throws IOException {

        //String UPLOAD_FOLDER = "resources/images";
        String UPLOAD_FOLDER = "src//main//resources/images";

        File fl = new File(UPLOAD_FOLDER + "//" + filename);

        InputStream resource = new FileInputStream(fl);

        StreamUtils.copy(resource, response.getOutputStream());

        resource.close();

    }

    @GetMapping()
    public ResponseEntity<List<Producto>> getAllProductos(@RequestParam("estado") Boolean estado) {

        return new ResponseEntity<>(this.productoService.getAll(estado), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Producto>> findProductos(@RequestParam("filter") String filter, @RequestParam("estado") Boolean estado) {
        return new ResponseEntity<>(this.productoService.findProducto(filter, estado), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.productoService.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Producto> save(@RequestPart("producto") Producto producto,
                                         @RequestPart(name = "img", required = false) MultipartFile img)
    {
        return new ResponseEntity<>(productoService.create(producto, img), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@RequestPart("producto") Producto producto,
                                            @PathVariable("id") Long id,
                                         @RequestPart(name = "img", required = false) MultipartFile img)
    {
        return new ResponseEntity<>(productoService.update(producto, id, img), HttpStatus.OK);
    }

    @PutMapping("/addEmpresas/{id}")
    public ResponseEntity<Producto> addEmpresas(@PathVariable("id") Long id, @RequestBody List<Empresa> empresas) {
        return new ResponseEntity<>(productoService.addEmpresas(id, empresas), HttpStatus.OK);
    }

    @PutMapping("/removeEmpresa/{id}")
    public ResponseEntity<Producto> removeEmpresas(@PathVariable("id") Long id, @RequestBody List<Empresa> empresas) {
        return new ResponseEntity<>(productoService.deleteEmpresas(id, empresas), HttpStatus.OK);
    }

    @PutMapping("/addUbicaciones/{id}")
    public ResponseEntity<Producto> addubicaciones(@PathVariable("id") Long id, @RequestBody List<Ubicacion> ubicaciones) {
        return new ResponseEntity<>(productoService.addUbicaciones(id, ubicaciones), HttpStatus.OK);
    }

    @PutMapping("/removeUbicaciones/{id}")
    public ResponseEntity<Producto> removeubicaciones(@PathVariable("id") Long id, @RequestBody List<Ubicacion> ubicaciones) {
        return new ResponseEntity<>(productoService.deleteUbicaciones(id, ubicaciones), HttpStatus.OK);
    }

    @PutMapping("/changeState/{id}")
    public ResponseEntity<Void> changeState(@PathVariable("id") Long id, @RequestParam("estado") Boolean estado) {
        productoService.changeState(id, estado);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
