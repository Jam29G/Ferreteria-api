package com.dev.ferreteriaapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detalles_compras")
public class detalles_compras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "precio", nullable = false, precision = 3)
    private Double precio;
    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
}
