package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.Caja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CajaRepo extends JpaRepository<Caja, Long> {

    List<Caja> findByEstado(Boolean estado);
    Long countByNombreAndEstado(String nombre, Boolean estado);
    List<Caja> findByAprobacionAndEstado(Boolean aprobacion, Boolean estado);

}
