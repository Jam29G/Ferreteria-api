package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.DetalleProducto;
import com.dev.ferreteriaapi.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleProductoRepo extends JpaRepository<DetalleProducto, Long> {

    List<DetalleProducto> findByIsVencido(Boolean vencido);

    @Query("select d from DetalleProducto d where d.producto.id = :productoId and d.estado = :estado")
    List<DetalleProducto> findByProductoId(@Param("productoId") Long productoId, @Param("estado") Boolean estado);

    @Query("select d from DetalleProducto d where d.producto IN :productos and d.estado = :estado ORDER BY d.producto.id")
    List<DetalleProducto> findDetalleByProducto(@Param("productos")List<Producto> productos, @Param("estado") Boolean estado);


}
