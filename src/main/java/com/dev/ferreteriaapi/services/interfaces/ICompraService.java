package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.Compra;
import com.dev.ferreteriaapi.entities.DetalleProducto;

import java.util.List;

public interface ICompraService {

    List<Compra> getAll();

    Compra create(Compra compra);

}
