package com.dev.ferreteriaapi.services.implement;

import com.dev.ferreteriaapi.entities.DetalleProducto;
import com.dev.ferreteriaapi.entities.Empresa;
import com.dev.ferreteriaapi.entities.Producto;
import com.dev.ferreteriaapi.repository.DetalleProductoRepo;
import com.dev.ferreteriaapi.services.interfaces.IDetalleProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DetalleProductoService implements IDetalleProductoService {
    private final DetalleProductoRepo detalleProductoRepo;
    private final EmpresaService empresaService;


    @Override
    public DetalleProducto create(DetalleProducto detalle) {
        return this.detalleProductoRepo.save(detalle);
    }

    @Override
    public DetalleProducto update(DetalleProducto detalle, Long id) {
        DetalleProducto detalleUpdate = this.getById(id);

        detalleUpdate.setPrecioVenta(detalle.getPrecioVenta());

        return this.detalleProductoRepo.save(detalleUpdate);
    }

    @Override
    public List<DetalleProducto> getDetallesByEmpresa(Long empresaId, Boolean estado) {
        Empresa empresa = this.empresaService.getEmpresaById(empresaId);

        List<Producto> productos = empresa.productosProveedor();

        return this.detalleProductoRepo.findDetalleByProducto(productos, estado);
    }

    @Override
    public DetalleProducto getById(Long id) {
        return this.detalleProductoRepo.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El detalle no existe"));
    }

    @Override
    public List<DetalleProducto> getByProductoId(Long id, Boolean estado) {
        return this.detalleProductoRepo.findByProductoId(id, estado);
    }

    @Override
    public void changeState(Long id, Boolean estado) {
        DetalleProducto detalleUpdate = this.getById(id);

        if(detalleUpdate.getCantidad() != null) {
            if(detalleUpdate.getCantidad() > 0) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Para deshabilitar el detalle, este debe tener una cantidad de 0");
            }
        }

        detalleUpdate.setEstado(estado);

        this.detalleProductoRepo.save(detalleUpdate);
    }



}
