package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.Empresa;
import com.dev.ferreteriaapi.entities.Venta;

import java.time.LocalDateTime;
import java.util.List;

public interface IVentaService {

    List<Venta> getAll();
    Venta getVentaById(Long id);
    List<Venta> findVentaByDates(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    Venta create(Venta venta);

}
