package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepo extends JpaRepository<Producto, Long> {

    List<Producto> findTop40ByEstado(Boolean estado);

    @Query("select count(p) from Producto p where p.nombre = :nombre or p.codigo = :codigo ")
    Long countByCodigoOrNombre(@Param("nombre") String nombre, @Param("codigo") String codigo);


    @Query(
            "select p from Producto p where ( LOWER(p.nombre) LIKE CONCAT('%', :filter ,'%') OR LOWER(p.descripcion) LIKE CONCAT('%', :filter ,'%') OR LOWER(p.codigo) LIKE CONCAT('%', :filter ,'%') ) and p.estado = :estado"
    )
    List<Producto> findProductos(@Param("filter") String filter, @Param("estado") Boolean estado);
}
