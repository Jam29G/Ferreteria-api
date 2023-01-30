package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.ControlInventarioProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ControlInventarioProductoRepo extends JpaRepository<ControlInventarioProducto, Long> {

    List<ControlInventarioProducto> findByFechaMovimientoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);


}
