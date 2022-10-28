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
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "numFactura", length = 30, nullable = false)
    private String numFactura;
    @Column(name = "fechaCompra", nullable = false)
    private LocalDateTime fechaCompra;
    @Column(name = "monto", nullable = false, precision = 3)
    private Double monto;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private List<DetalleCompra> detalleCompra;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

}
