package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.Caja;

import java.util.List;

public interface ICajaService {
    Caja abrirCaja();
    List<Caja> getAllCajas();
    List<Caja> getPendienteAprob();
    Caja getCajaById(Long id);
    Caja updateCaja(Caja caja);
    Caja emitirGasto(Caja caja);
    Caja abonarCaja(Caja caja);
}
