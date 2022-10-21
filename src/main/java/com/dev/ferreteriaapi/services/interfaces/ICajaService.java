package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.Caja;
import com.dev.ferreteriaapi.entities.MovimCaja;
import com.dev.ferreteriaapi.entities.Venta;

import java.util.List;
import java.util.Map;

public interface ICajaService {

    //Metodos de caja
    Caja abrirCaja(Caja caja);
    List<Caja> getAllCajas(Boolean estado);
    Caja getCajaUsuario(Long usuarioId);
    List<Caja> getPendienteAprob(String aprobacion, Boolean estado);
    Caja getCajaById(Long id);
    Caja cerrarCaja(Caja caja);
    Map<String, Object> emitirGasto(Caja caja, Double monto, String motivo);
    Map<String, Object> abonarCaja(Caja caja, Double monto, String motivo);

    List<MovimCaja> getRegistrosCaja(Boolean isIngreso, Long cajaId);

}
