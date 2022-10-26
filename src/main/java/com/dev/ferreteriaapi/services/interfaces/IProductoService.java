package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.Empresa;
import com.dev.ferreteriaapi.entities.Producto;
import com.dev.ferreteriaapi.entities.Ubicacion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductoService {

    List<Producto> getAll(Boolean estado);

    Producto getById(Long id);

    Producto create(Producto producto, MultipartFile img);

    List<Producto> findProducto(String filter, Boolean estado);

    Producto update(Producto producto, Long id, MultipartFile img);

    Producto addEmpresas(Long id, List<Empresa> empresas);

    Producto deleteEmpresas(Long id, List<Empresa> empresas);
    Producto addUbicaciones(Long id, List<Ubicacion> ubicaciones);
    Producto deleteUbicaciones(Long id, List<Ubicacion> ubicaciones);

    void changeState(Long id, Boolean estado);
}
