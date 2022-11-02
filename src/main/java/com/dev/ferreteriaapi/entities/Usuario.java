package com.dev.ferreteriaapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column( name = "nombre", nullable = false, length = 40)
    private String nombre;

    @Column( name = "apellido", length = 40)
    private String apellido;

    @Column( name = "username", nullable = false, length = 60, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @ManyToMany( cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Rol> roles = new ArrayList<>();

    @PrePersist
    public void setEstado() {
        this.estado = true;
    }
}
