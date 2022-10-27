package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.Compra;
import com.dev.ferreteriaapi.entities.DetalleProducto;

import java.time.LocalDateTime;
import java.util.List;

public interface ICompraService {

    List<Compra> getAll();

    Compra create(Compra compra);

    List<Compra> findCompraByDates(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
