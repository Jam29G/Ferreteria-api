package com.dev.ferreteriaapi.repository;

import antlr.collections.List;
import com.dev.ferreteriaapi.entities.Empresa;
import com.dev.ferreteriaapi.entities.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionRepo extends JpaRepository<Ubicacion, Long> {

    Long countByLugarAndNumeroAndZona(String lugar, String numero, String zona);


}
