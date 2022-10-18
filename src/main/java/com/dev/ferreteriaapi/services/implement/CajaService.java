package com.dev.ferreteriaapi.services.implement;

import com.dev.ferreteriaapi.entities.Caja;
import com.dev.ferreteriaapi.entities.MovimCaja;
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
import java.util.List;

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
    public List<Caja> getPendienteAprob(String aprobacion) {
        return this.cajaRepo.findByAprobacionAndEstado(aprobacion, false);
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
    public Caja emitirGasto(Caja caja, Double monto, String motivo) {

        Caja cajaUpdate = this.getCajaById(caja.getId());

        if(cajaUpdate.getSaldo() < monto) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "saldo insuficiente para realizar la transacción");
        }

        if(!cajaUpdate.getEstado()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La caja se encuentra cerrada");
        }

        cajaUpdate.setSaldo( (cajaUpdate.getSaldo() - monto) );

        MovimCaja newMovimiento = new MovimCaja();

        newMovimiento.setMotivo(motivo);
        newMovimiento.setIsIngreso(false);
        newMovimiento.setMonto(monto);
        newMovimiento.setFecha(LocalDateTime.now());
        newMovimiento.setCaja(cajaUpdate);

        movimCajaRepo.save(newMovimiento);

        return cajaRepo.save(cajaUpdate);

    }

    @Override
    public Caja abonarCaja(Caja caja, Double monto, String motivo) {

        Caja cajaUpdate = this.getCajaById(caja.getId());

        if(!cajaUpdate.getEstado()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La caja se encuentra cerrada");
        }

        cajaUpdate.setSaldo( (cajaUpdate.getSaldo() + monto) );
        cajaUpdate.setSaldoIngr((cajaUpdate.getSaldoIngr() + monto));

        MovimCaja newMovimiento = new MovimCaja();

        newMovimiento.setMotivo(motivo);
        newMovimiento.setIsIngreso(true);
        newMovimiento.setMonto(monto);
        newMovimiento.setFecha(LocalDateTime.now());
        newMovimiento.setCaja(cajaUpdate);

        movimCajaRepo.save(newMovimiento);

        return cajaRepo.save(cajaUpdate);

    }

    @Override
    public MovimCaja sacarSaldoCaja() {
        return null;
    }

    @Override
    public MovimCaja abonarSaldoCaja() {
        return null;
    }
}
