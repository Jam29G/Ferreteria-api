package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.Caja;
import com.dev.ferreteriaapi.entities.MovimCaja;

import java.util.List;

public interface ICajaService {

    //Metodos de caja
    Caja abrirCaja(Caja caja);
    List<Caja> getAllCajas(Boolean estado);
    Caja getCajaUsuario(Long usuarioId);
    List<Caja> getPendienteAprob(String aprobacion);
    Caja getCajaById(Long id);
    Caja cerrarCaja(Caja caja);
    Caja emitirGasto(Caja caja, Double monto, String motivo);
    Caja abonarCaja(Caja caja, Double monto, String motivo);

    //Metodos para movimientos de caja
    MovimCaja sacarSaldoCaja();
    MovimCaja abonarSaldoCaja();

}
