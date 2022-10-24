package com.dev.ferreteriaapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ubicaciones")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "zona", nullable = false)
    private String zona;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "lugar", nullable = false)
    private String lugar;

    @JsonIgnore
    @ManyToMany(mappedBy = "ubicaciones")
    private List<Producto> productos = new ArrayList<>();



}
