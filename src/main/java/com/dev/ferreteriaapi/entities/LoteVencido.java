package com.dev.ferreteriaapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lotes_vencidos")
public class LoteVencido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    @Column(name = "perdida", nullable = false, precision = 3)
    private Double perdida;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lote_id")
    private LoteProducto lote;


}
