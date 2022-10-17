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
@Table(name = "cajas")
public class Caja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;
    @Column(name = "saldo", nullable = false, precision = 3)
    private Double saldo;
    @Column(name = "saldo_inic", nullable = false, precision = 3)
    private Double saldoInic;
    @Column(name = "saldo_final", precision = 3)
    private Double saldoFinal;
    @Column(name = "saldo_ingr", precision = 3)
    private Double saldoIngr;
    @Column(name = "estado", nullable = false)
    private Boolean estado;
    @Column(name = "fecha_apertura", nullable = false)
    private LocalDateTime fechaApertura;
    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;
    @Column(name = "aprobacion")
    private Boolean aprobacion;

    @ManyToOne()
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

}
