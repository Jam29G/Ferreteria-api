package com.dev.ferreteriaapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 20, unique = true)
    private String codigo;
    @Column(name = "nombre", nullable = false, length = 80, unique = true)
    private String nombre;
    @Column(name = "descuentoMax", nullable = false, precision = 3)
    private Double descuentoMax;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "estado", nullable = false)
    private Boolean estado;
    @Column(name = "img")
    private String img;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "proveedores_productos",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "empresa_id")
    )
    private List<Empresa> proveedores = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "ubicaciones_productos",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "ubicacion_id")
    )
    private List<Ubicacion> ubicaciones = new ArrayList<>();

    public void addEmpresa(Empresa empresa) {
        this.proveedores.add(empresa);
    }

    public void removeEmpresa(Empresa empresa) {
        this.proveedores.removeIf( p -> Objects.equals( p.getId(), empresa.getId() ));
    }

    public void addUbicacion(Ubicacion ubicacion) {
        this.ubicaciones.add(ubicacion);
    }

    public void removeUbicacion(Ubicacion ubicacion) {
        this.ubicaciones.removeIf( u -> Objects.equals( u.getId(), ubicacion.getId() ) );
    }

}
