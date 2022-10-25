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
@Table(name = "lotes_productos")
public class LoteProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "precio_compra", nullable = false, precision = 3)
    private Double precioCompra;
    @Column(name = "precio_venta", nullable = false, precision = 3)
    private Double precioVenta;
    @Column(name = "cantidad", nullable = false)
    private Long cantidad;
    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDateTime fechaIngreso;
    @Column(name = "fecha_venc")
    private LocalDateTime fechaVenc;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;

    @OneToOne(mappedBy = "lote")
    private LoteVencido loteVencido;

}
