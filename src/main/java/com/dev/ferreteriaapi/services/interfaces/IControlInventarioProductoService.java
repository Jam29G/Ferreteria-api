package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.ControlInventarioProducto;

import java.time.LocalDateTime;
import java.util.List;

public interface IControlInventarioProductoService {

    ControlInventarioProducto findRegistroById(Long id);

    List<ControlInventarioProducto> getByDateRange(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<ControlInventarioProducto> getTodayRecords();

}
