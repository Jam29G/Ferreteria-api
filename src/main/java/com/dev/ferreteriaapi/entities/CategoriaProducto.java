package com.dev.ferreteriaapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categorias_productos")
public class CategoriaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Double precioCompra;
    private Double precioVenta;
    private Long cantidad;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaVenc;
    private Boolean is_vencido;
    private Boolean is_perecedero;

    @ManyToOne()
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;


}
