package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.Compra;
import com.dev.ferreteriaapi.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepo extends JpaRepository<Venta, Long> {

    @Query("select c from Venta c where c.fecha between :fechaInicio and :fechaFin")
    List<Venta> findVentaByDates(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);

}
