package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.Producto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductoService {

    List<Producto> getAll(Boolean estado);

    Producto getById(Long id);

    Producto create(Producto producto, MultipartFile img);

    Producto update(Producto producto, Long id, MultipartFile img);

    void changeState(Long id, Boolean estado);
}
