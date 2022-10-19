package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.MovimCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovimCajaRepo extends JpaRepository<MovimCaja, Long> {

    @Query("select m from MovimCaja m where m.caja.id = :cajaId and m.isIngreso = :isIngreso")
    List<MovimCaja> findMovimientoCaja(@Param("cajaId") Long cajaId, @Param("isIngreso") Boolean isIngreso);
}
