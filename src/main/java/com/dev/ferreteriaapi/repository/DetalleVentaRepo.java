package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepo extends JpaRepository<DetalleVenta, Long> {
}
