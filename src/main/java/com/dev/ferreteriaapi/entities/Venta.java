package com.dev.ferreteriaapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cliente", nullable = false, length = 60)
    private String cliente;
    @Column(name = "num_factura", nullable = false, length = 30)
    private String numFactura;
    @Column(name = "direccion", nullable = true ,length = 180)
    private String direccion;
    @Column(name = "departamento", nullable = true, length = 80)
    private String departamento;
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;
    @Column(name = "num_registro", nullable = true, length = 30)
    private String numRegistro;
    @Column(name = "giro", nullable = true, length = 80)
    private String giro;
    @Column(name = "is_cred_fisc", nullable = false)
    private Boolean isCredFisc;
    @Column(name = "montoFinal", nullable = false)
    private Double montoFinal;
    @Column(name = "cambio", nullable = false)
    private Double cambio;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "caja_id", nullable = false)
    private Caja caja;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.MERGE)
    private List<DetalleVenta> detalleVentas;

}
