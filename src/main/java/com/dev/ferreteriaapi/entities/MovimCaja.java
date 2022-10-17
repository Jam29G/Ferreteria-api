package com.dev.ferreteriaapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movims_cajas")
public class MovimCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "motivo", nullable = false, length = 200)
    private String motivo;
    @Column(name = "is_ingreso", nullable = false)
    private Boolean isIngreso;
    @Column(name = "monto", nullable = false, precision = 3)
    private Double monto;
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @ManyToOne()
    @JoinColumn(name = "caja_id", nullable = false)
    private Caja caja;

    @ManyToOne()
    @JoinColumn(name = "venta_id")
    private Venta venta;

}
