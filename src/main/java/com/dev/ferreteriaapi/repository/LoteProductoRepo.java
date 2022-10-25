package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.LoteProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteProductoRepo extends JpaRepository<LoteProducto, Long> {
}
