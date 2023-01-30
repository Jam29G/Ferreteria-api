package com.dev.ferreteriaapi.services.implement;

import com.dev.ferreteriaapi.entities.ControlInventarioProducto;
import com.dev.ferreteriaapi.repository.ControlInventarioProductoRepo;
import com.dev.ferreteriaapi.services.interfaces.IControlInventarioProductoService;
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
public class ControlInventarioService implements IControlInventarioProductoService {
    private final ControlInventarioProductoRepo repository;

    @Override
    public ControlInventarioProducto findRegistroById(Long id) {
        return this.repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el detalle solicitado"));
    }

    @Override
    public List<ControlInventarioProducto> getByDateRange(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return this.repository.findByFechaMovimientoBetween(fechaInicio, fechaFin);
    }

    @Override
    public List<ControlInventarioProducto> getTodayRecords() {
        LocalDateTime dateStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime dateEnd = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(0);

        return this.repository.findByFechaMovimientoBetween(dateStart, dateEnd);

    }


}
