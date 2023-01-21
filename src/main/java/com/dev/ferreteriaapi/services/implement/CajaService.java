package com.dev.ferreteriaapi.services.implement;

import com.dev.ferreteriaapi.entities.Caja;
import com.dev.ferreteriaapi.entities.MovimCaja;
import com.dev.ferreteriaapi.entities.Venta;
import com.dev.ferreteriaapi.repository.CajaRepo;
import com.dev.ferreteriaapi.repository.MovimCajaRepo;
import com.dev.ferreteriaapi.services.interfaces.ICajaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CajaService implements ICajaService {

    private final CajaRepo cajaRepo;
    private final MovimCajaRepo movimCajaRepo;

    @Override
    public Caja abrirCaja(Caja caja) {

        //Comprobar que no haya una caja abierta con el mismo nombre
        if( this.cajaRepo.countByNombreAndEstado(caja.getNombre(), true) != 0 ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La caja con el nombre " + caja.getNombre() + " se encuentra abierta");
        }
        //Comprobar que el usuario no tenga una caja abierta
        if( this.cajaRepo.findByUsuarioAndEstado( caja.getUsuario().getId(), true ).size() != 0 ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Su usuario ya cuenta con una caja abierta");
        }

        caja.setFechaApertura(LocalDateTime.now());
        caja.setSaldoIngr(0.00);
        caja.setAprobacion("P");

        return this.cajaRepo.save(caja);
    }

    @Override
    public List<Caja> getAllCajas(Boolean estado) {
        return this.cajaRepo.findByEstado(estado);
    }

    @Override
    public Caja getCajaUsuario(Long usuarioId) {
        if(this.cajaRepo.findByUsuarioAndEstado(usuarioId, true).size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Su usuario no cuenta con una caja abierta");
        }

        return this.cajaRepo.findByUsuarioAndEstado(usuarioId, true).get(0);
    }

    @Override
    public List<Caja> getPendienteAprob(String aprobacion, Boolean estado) {

        return this.cajaRepo.findByAprobacionAndEstadoOrderByFechaAperturaDesc(aprobacion, false);

    }

    @Override
    public Caja getCajaById(Long id) {
        return this.cajaRepo.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La caja no existe") );
    }

    @Override
    public Caja cerrarCaja(Caja caja) {
        Caja cajaCerrada = getCajaById(caja.getId());

        cajaCerrada.setEstado(false);
        cajaCerrada.setFechaCierre(LocalDateTime.now());
        cajaCerrada.setSaldoFinal(cajaCerrada.getSaldo());

        return this.cajaRepo.save(cajaCerrada);
    }

    @Override
    public Caja changeAprob(Long cajaId, String aprob) {
        Caja cajaUpdate = this.getCajaById(cajaId);

        if(cajaUpdate.getEstado()) throw  new ResponseStatusException(HttpStatus.CONFLICT, "Para cambiar el estado de aprobación de una caja, esta debe estar cerrada");


        cajaUpdate.setAprobacion(aprob);

        return this.cajaRepo.save(cajaUpdate);

    }

    @Override
    public List<Caja> getCajasByFilterDate(Boolean estado, String aprobacion, LocalDateTime inicio, LocalDateTime fin) {

        if(aprobacion != null) {
            return cajaRepo.findByEstadoAndAprobacionAndFechaAperturaBetween(estado, aprobacion, inicio, fin);
        }

        return cajaRepo.findByEstadoAndFechaAperturaBetween(estado, inicio, fin);

    }

    @Override
    public Map<String, Object> emitirGasto(Caja caja, Double monto, String motivo, Venta venta) {

        Map<String, Object> response = new HashMap<>();

        Caja cajaUpdate = this.getCajaById(caja.getId());

        if(cajaUpdate.getSaldo() < monto) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "saldo insuficiente para realizar la transacción");
        }

        if(!cajaUpdate.getEstado()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La caja se encuentra cerrada");
        }

        cajaUpdate.setSaldo( (cajaUpdate.getSaldo() - monto) );

        MovimCaja newMovimiento = new MovimCaja();

        if(venta != null) newMovimiento.setVenta(venta);

        newMovimiento.setMotivo(motivo);
        newMovimiento.setIsIngreso(false);
        newMovimiento.setMonto(monto);
        newMovimiento.setFecha(LocalDateTime.now());
        newMovimiento.setCaja(cajaUpdate);

        response.put("movimCaja", movimCajaRepo.save(newMovimiento));
        response.put("caja", cajaRepo.save(cajaUpdate));
        return response;

    }

    @Override
    public Map<String, Object> abonarCaja(Caja caja, Double monto, String motivo, Venta venta) {

        Map<String, Object> response = new HashMap<>();

        Caja cajaUpdate = this.getCajaById(caja.getId());

        if(!cajaUpdate.getEstado()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La caja se encuentra cerrada");
        }

        cajaUpdate.setSaldo( (cajaUpdate.getSaldo() + monto) );
        cajaUpdate.setSaldoIngr((cajaUpdate.getSaldoIngr() + monto));

        MovimCaja newMovimiento = new MovimCaja();

        if(venta != null) newMovimiento.setVenta(venta);

        newMovimiento.setMotivo(motivo);
        newMovimiento.setIsIngreso(true);
        newMovimiento.setMonto(monto);
        newMovimiento.setFecha(LocalDateTime.now());
        newMovimiento.setCaja(cajaUpdate);

        response.put("movimCaja", movimCajaRepo.save(newMovimiento));
        response.put("caja", cajaRepo.save(cajaUpdate));

        return response;

    }

    @Override
    public List<MovimCaja> getRegistrosCaja(Boolean isIngreso, Long cajaId) {
        return this.movimCajaRepo.findMovimientoCaja(cajaId, isIngreso);
    }

}
