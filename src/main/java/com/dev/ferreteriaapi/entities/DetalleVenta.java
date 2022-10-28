package com.dev.ferreteriaapi.entities;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detalles_ventas")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @Column(name = "precio_compra", nullable = false, precision = 3)
    private Double precioCompra;
    @Getter @Setter
    @Column(name = "precio_venta", nullable = false, precision = 3)
    private Double precioVenta;
    @Getter @Setter
    @Column(name = "cantidad", nullable = false)
    private Long cantidad;
    @Getter @Setter
    @Column(name = "descuento", nullable = false, precision = 3)
    private Double descuento;
    @Getter @Setter
    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne(cascade = CascadeType.MERGE)
    @Getter
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @Setter
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

}
