package com.dev.ferreteriaapi.services.implement;

import com.dev.ferreteriaapi.entities.Producto;
import com.dev.ferreteriaapi.entities.Ubicacion;
import com.dev.ferreteriaapi.repository.UbicacionRepo;
import com.dev.ferreteriaapi.services.interfaces.IUbicacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UbicacionService implements IUbicacionService {

    public final UbicacionRepo ubicacionRepo;

    @Override
    public List<Ubicacion> getAll() {
        return this.ubicacionRepo.findAll();
    }

    @Override
    public Ubicacion getById(Long id) {
        return this.ubicacionRepo.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La empresa no existe"));
    }

    @Override
    public Ubicacion create(Ubicacion ubicacion) {

        if(this.ubicacionRepo.countByLugarAndNumeroAndZona(ubicacion.getLugar(), ubicacion.getNumero(), ubicacion.getZona()) != 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una ubicación registrada con las misma información");
        }

        return this.ubicacionRepo.save(ubicacion);
    }


    @Override
    public void delete(Long id) {
        Ubicacion ubicacion = this.getById(id);

        for(Producto producto : ubicacion.productosRelacionados()) {
            producto.getUbicaciones().remove(ubicacion);
        }

        this.ubicacionRepo.flush();

        ubicacion = this.ubicacionRepo.save(ubicacion);

        ubicacion.productosRelacionados().clear();

        this.ubicacionRepo.delete(ubicacion);

    }


}
