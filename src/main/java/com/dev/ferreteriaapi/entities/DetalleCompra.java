package com.dev.ferreteriaapi.entities;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detalles_compras")
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;

    @Column(name = "precio", nullable = false, precision = 3)
    @Getter
    @Setter
    private Double precio;

    @Column(name = "cantidad", nullable = false)
    @Getter
    @Setter
    private Long cantidad;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @Setter
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "detalle_prod_id", nullable = false)
    @Getter
    @Setter
    private DetalleProducto detalleProducto;
}
