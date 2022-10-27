package com.dev.ferreteriaapi.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "control_inventario_productos")
public class ControlInventarioProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "salida", nullable = false)
    private Boolean salida;
    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDateTime fechaMovimiento;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "precio_compra", nullable = false, precision = 3)
    private Double precioCompra;
    @Column(name = "precio_venta", nullable = false, precision = 3)
    private Double precioVenta;
    @Column(name = "monto", precision = 3, nullable = false)
    private Double monto;
    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

}
