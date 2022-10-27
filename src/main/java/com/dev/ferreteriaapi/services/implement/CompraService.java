package com.dev.ferreteriaapi.services.implement;

import com.dev.ferreteriaapi.entities.Compra;
import com.dev.ferreteriaapi.entities.DetalleCompra;
import com.dev.ferreteriaapi.entities.DetalleProducto;
import com.dev.ferreteriaapi.repository.CompraRepo;
import com.dev.ferreteriaapi.repository.DetalleCompraRepo;
import com.dev.ferreteriaapi.repository.DetalleProductoRepo;
import com.dev.ferreteriaapi.services.interfaces.ICompraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompraService implements ICompraService {

    private final CompraRepo compraRepo;
    private final DetalleCompraRepo detalleRepo;

    @Override
    public List<Compra> getAll() {
        return compraRepo.findAll();
    }

    @Override
    public Compra create(Compra compra) {
        List<DetalleCompra> detalle = compra.getDetalleCompra();
        compra.setDetalleCompra(null);
        Compra c = this.compraRepo.save(compra);
        detalle.forEach((detail) -> {
            detail.setCompra(c);
            this.detalleRepo.save(detail);
        });
        return c;
    }

    @Override
    public List<Compra> findCompraByDates(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return compraRepo.findCompraByDates(fechaInicio,fechaFin);
    }

}
