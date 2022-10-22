package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.Caja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CajaRepo extends JpaRepository<Caja, Long> {

    List<Caja> findByEstado(Boolean estado);
    Long countByNombre(String nombre);
    Long countByNombreAndEstado(String nombre, Boolean estado);
    List<Caja> findByAprobacionAndEstadoOrderByFechaAperturaDesc(String aprobacion, Boolean estado);
    @Query("select c from Caja c where c.usuario.id = :usuarioId and c.estado = :estado")
    List<Caja> findByUsuarioAndEstado(@Param("usuarioId") Long usuarioId, @Param("estado") Boolean estado);

    @Query(
            "select c from Caja c where c.estado = :estado and c.aprobacion = :aprobacion and c.fechaApertura between :fechaInic and :fechaFin"
    )
    List<Caja>findByEstadoAndAprobacionAndFechaAperturaBetween(
            //Boolean estado, String aprobacion, LocalDateTime inicio, LocalDateTime fin
            @Param("estado") Boolean estado,
            @Param("aprobacion") String aprobacion,
            @Param("fechaInic") LocalDateTime fechaInic,
            @Param("fechaFin") LocalDateTime fechaFin
    );

    @Query(
            "select c from Caja c where c.estado = :estado and (c.aprobacion = 'P' OR c.aprobacion = 'A') and c.fechaApertura between :fechaInic and :fechaFin"
    )
    List<Caja>findByEstadoAndFechaAperturaBetween(
            //Boolean estado, String aprobacion, LocalDateTime inicio, LocalDateTime fin
            @Param("estado") Boolean estado,
            @Param("fechaInic") LocalDateTime fechaInic,
            @Param("fechaFin") LocalDateTime fechaFin
    );

}
