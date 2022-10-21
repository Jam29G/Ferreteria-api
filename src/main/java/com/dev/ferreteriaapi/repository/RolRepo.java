package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepo extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
    Long countByNombre(String nombre);
}
