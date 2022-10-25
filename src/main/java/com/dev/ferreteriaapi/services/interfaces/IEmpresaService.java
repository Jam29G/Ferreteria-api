package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.Empresa;
import com.dev.ferreteriaapi.entities.Producto;

import java.util.List;

public interface IEmpresaService {

    List<Empresa> getAll(Boolean estado);
    List<Producto> getProductosRelacionados(Long id);
    Empresa getEmpresaById(Long id);
    Empresa create(Empresa empresa);
    Empresa updateEmpresa(Empresa empresa, Long id);
    Empresa changeState(Long id, Boolean estado);

    List<Empresa> findEmpresa(String search, Boolean estado);


}
