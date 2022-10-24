package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.Ubicacion;

import java.util.List;

public interface IUbicacionService {

    List<Ubicacion> getAll();

    Ubicacion getById(Long id);

    Ubicacion create(Ubicacion ubicacion);

    void delete(Long id);
}
