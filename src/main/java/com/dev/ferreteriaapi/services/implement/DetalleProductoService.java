package com.dev.ferreteriaapi.services.implement;

import com.dev.ferreteriaapi.entities.*;
import com.dev.ferreteriaapi.repository.ControlInventarioProductoRepo;
import com.dev.ferreteriaapi.repository.DetalleProductoRepo;
import com.dev.ferreteriaapi.services.interfaces.IDetalleProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DetalleProductoService implements IDetalleProductoService {
    private final DetalleProductoRepo detalleProductoRepo;
    private final EmpresaService empresaService;

    private final ControlInventarioProductoRepo controlInventarioProductoRepo;


    @Override
    public DetalleProducto create(DetalleProducto detalle) {

        if(detalle.getPrecioCompra() >= detalle.getPrecioVenta()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El precio de compra no puede ser mayor al precio de venta");
        }

        return this.detalleProductoRepo.save(detalle);
    }

    @Override
    public List<DetalleProducto> getAllDetalle() {
        return detalleProductoRepo.findTop60ByCantidadGreaterThanAndEstado(0L, true);
    }


    @Override
    public List<DetalleProducto> findDetalle(String filter) {
        return detalleProductoRepo.findByNombreAndCodigoAndEstado(filter);
    }

    @Override
    public List<DetalleProducto> getProductosVencidos() {
        return detalleProductoRepo.findProductosCaducados();
    }

    @Override
    public DetalleProducto update(DetalleProducto detalle, Long id) {
        DetalleProducto detalleUpdate = this.getById(id);

        detalleUpdate.setPrecioVenta(detalle.getPrecioVenta());

        if(detalle.getPrecioCompra() >= detalle.getPrecioVenta()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El precio de compra no puede ser mayor al precio de venta");
        }

        return this.detalleProductoRepo.save(detalleUpdate);
    }

    @Override
    public DetalleProducto updateCantidad(DetalleProducto detalle, Long id, Long usuarioId) {
        DetalleProducto detalleUpdate = this.getById(id);
        boolean salida = true;
        long cantidad = 0L;
        String motivo = "";
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        if(detalle.getCantidad() > detalleUpdate.getCantidad()) {
            salida = false;
            cantidad = detalle.getCantidad() - detalleUpdate.getCantidad();
            motivo = "Ingreso de producto";
        } else {
            cantidad = detalleUpdate.getCantidad() - detalle.getCantidad();
            motivo = "Retiro de producto";
        }


        detalleUpdate.setCantidad(detalle.getCantidad());

        //Registrando en el inventario
        ControlInventarioProducto control = new ControlInventarioProducto();

        control.setSalida(salida);
        control.setFechaMovimiento(LocalDateTime.now());
        control.setPrecioCompra(detalleUpdate.getPrecioCompra());
        control.setPrecioVenta(detalleUpdate.getPrecioVenta());
        control.setMonto(detalleUpdate.getPrecioCompra() * cantidad);
        control.setCantidad(cantidad);
        control.setObservacion(motivo);
        control.setProducto(detalleUpdate.getProducto());
        control.setUsuario(usuario);

        controlInventarioProductoRepo.save(control);

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
    public boolean checkPerecederos() {

        LocalDateTime fechaInic = LocalDateTime.now().withSecond(0).withMinute(0).withHour(0).withNano(0).plusDays(1);
        LocalDateTime fechaFin = LocalDateTime.now().withSecond(59).withMinute(59).withHour(23).withNano(0).plusDays(1);

        List<DetalleProducto> productosVencidos = this.detalleProductoRepo.findByFechaVencBetweenAndIsVencido(fechaInic, fechaFin, false);

        if(productosVencidos.size() == 0) return false;

        Usuario admin = new Usuario();
        admin.setId(1L);


        productosVencidos.forEach(producto -> {
            producto.setIsVencido(true);
            this.detalleProductoRepo.save(producto);

            ControlInventarioProducto control = new ControlInventarioProducto();
            control.setSalida(true);
            control.setFechaMovimiento(LocalDateTime.now());
            control.setObservacion("Producto vencido");
            control.setPrecioCompra(producto.getPrecioCompra());
            control.setPrecioVenta(producto.getPrecioVenta());
            control.setMonto(producto.getCantidad() * producto.getPrecioCompra());
            control.setCantidad(producto.getCantidad());
            control.setProducto(producto.getProducto());
            control.setUsuario(admin);

            controlInventarioProductoRepo.save(control);
        });
        return true;
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
