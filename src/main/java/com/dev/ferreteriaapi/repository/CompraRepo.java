package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.Compra;
import com.dev.ferreteriaapi.entities.MovimCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CompraRepo extends JpaRepository<Compra, Long> {

    @Query("select c from Compra c where c.fechaCompra between :fechaInicio and :fechaFin")
    List<Compra> findCompraByDates(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);

}
