package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.Caja;
import com.dev.ferreteriaapi.entities.MovimCaja;
import com.dev.ferreteriaapi.entities.Venta;

import java.time.LocalDateTime;
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
    Caja changeAprob(Long cajaId, String aprob);
    List<Caja> getCajasByFilterDate(
            Boolean estado, String aprobacion, LocalDateTime inicio, LocalDateTime fin
    );

    Map<String, Object> emitirGasto(Caja caja, Double monto, String motivo, Venta venta);
    Map<String, Object> abonarCaja(Caja caja, Double monto, String motivo, Venta venta);
    List<MovimCaja> getRegistrosCaja(Boolean isIngreso, Long cajaId);


}
