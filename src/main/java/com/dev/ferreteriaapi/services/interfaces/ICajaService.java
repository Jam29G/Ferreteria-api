package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.Caja;

import java.util.List;

public interface ICajaService {
    Caja abrirCaja(Caja caja);
    List<Caja> getAllCajas(Boolean estado);
    Caja getCajaUsuario(Long usuarioId);
    List<Caja> getPendienteAprob(Boolean aprobacion, Boolean estado);
    Caja getCajaById(Long id);
    Caja cerrarCaja(Caja caja);
    Caja emitirGasto(Caja caja);
    Caja abonarCaja(Caja caja);
}
