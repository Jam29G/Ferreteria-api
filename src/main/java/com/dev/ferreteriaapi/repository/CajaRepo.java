package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.Caja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CajaRepo extends JpaRepository<Caja, Long> {

    List<Caja> findByEstado(Boolean estado);
    Long countByNombre(String nombre);
    Long countByNombreAndEstado(String nombre, Boolean estado);
    List<Caja> findByAprobacionAndEstado(String aprobacion, Boolean estado);
    @Query("select c from Caja c where c.usuario.id = :usuarioId and c.estado = :estado")
    List<Caja> findByUsuarioAndEstado(@Param("usuarioId") Long usuarioId, @Param("estado") Boolean estado);

}
