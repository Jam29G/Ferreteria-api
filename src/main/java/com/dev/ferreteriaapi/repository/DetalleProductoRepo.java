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

    @Query("select d from DetalleProducto d where d.isVencido = false and d.is_perecedero = true")
    List<DetalleProducto> findProductosCaducados();

    @Query("select d from DetalleProducto d where d.producto IN :productos and d.estado = :estado ORDER BY d.producto.id")
    List<DetalleProducto> findDetalleByProducto(@Param("productos")List<Producto> productos, @Param("estado") Boolean estado);

    List<DetalleProducto> findTop60ByCantidadGreaterThanAndEstado(Long cantidad, Boolean estado);

    @Query(
            "select d from DetalleProducto d where ( LOWER(d.producto.nombre) LIKE CONCAT('%', :filter ,'%') OR LOWER(d.producto.codigo) LIKE CONCAT('%', :filter ,'%')  ) and d.estado = true and d.cantidad > 0 "
    )
    List<DetalleProducto> findByNombreAndCodigoAndEstado(@Param("filter") String filter);


}
