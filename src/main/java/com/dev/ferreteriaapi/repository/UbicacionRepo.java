package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionRepo extends JpaRepository<Empresa, Long> {



}
