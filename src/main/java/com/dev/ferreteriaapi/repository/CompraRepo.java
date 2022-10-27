package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepo extends JpaRepository<Compra, Long> {

}
