package com.dev.ferreteriaapi.entities;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empresas")

public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private Long id;

    @Column(name = "nombre", nullable = false, length = 120)
    @Getter @Setter
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 244)
    @Getter @Setter
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 10)
    @Getter @Setter
    private String telefono;

    @Column(name = "correo")
    @Getter @Setter
    private String correo;

    @Column(name = "estado", nullable = false)
    @Getter @Setter
    private Boolean estado;

    @JsonIgnore
    @ManyToMany(mappedBy = "proveedores", fetch = FetchType.LAZY)
    private List<Producto> productos = new ArrayList<>();

    public List<Producto> productosProveedor() {
        return this.productos;
    }
}
