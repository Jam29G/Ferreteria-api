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
@Table(name = "empresas")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 244)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @JsonIgnore
    @ManyToMany(mappedBy = "proveedores")
    private List<Producto> productos = new ArrayList<>();
}
