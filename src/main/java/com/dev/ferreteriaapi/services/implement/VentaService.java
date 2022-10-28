package com.dev.ferreteriaapi.services.implement;

import com.dev.ferreteriaapi.entities.*;
import com.dev.ferreteriaapi.repository.ControlInventarioProductoRepo;
import com.dev.ferreteriaapi.repository.DetalleVentaRepo;
import com.dev.ferreteriaapi.repository.VentaRepo;
import com.dev.ferreteriaapi.services.interfaces.IVentaService;
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
public class VentaService implements IVentaService {

    private final VentaRepo ventaRepo;
    private final DetalleVentaRepo detalleVentaRepo;
    private final ControlInventarioProductoRepo controlInventarioProductoRepo;

    @Override
    public List<Venta> getAll() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta getVentaById(Long id) {
        return this.ventaRepo.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta No Encontradasss") );
    }

    @Override
    public Venta create(Venta venta) {

        List<DetalleVenta> detalle = venta.getDetalleVentas();

        venta.setDetalleVentas(null);
        System.out.println(venta.getId());

        venta.setFecha(LocalDateTime.now());

        Venta v = this.ventaRepo.save(venta);

        detalle.forEach((detail) -> {
            detail.setVenta(v);
            System.out.println(detail.getId());
            this.detalleVentaRepo.save(detail);

            //Registrando en el inventario
            ControlInventarioProducto control = new ControlInventarioProducto();

            control.setSalida(true);
            control.setFechaMovimiento(LocalDateTime.now());
            control.setPrecioCompra(detail.getPrecioCompra());
            control.setPrecioVenta(detail.getPrecioVenta());
            control.setMonto(detail.getPrecioCompra() * detail.getCantidad());
            control.setCantidad(detail.getCantidad());
            control.setObservacion("Venta de productos");
            control.setProducto(detail.getProducto());
            control.setUsuario(v.getUsuario());

            controlInventarioProductoRepo.save(control);
        });

        return v;
    }

    @Override
    public List<Venta> findVentaByDates(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return ventaRepo.findVentaByDates(fechaInicio,fechaFin);
    }

}
