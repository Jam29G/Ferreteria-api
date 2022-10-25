package com.dev.ferreteriaapi.entities;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ubicaciones")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private Long id;

    @Column(name = "zona", nullable = false)
    @Getter @Setter
    private String zona;

    @Column(name = "numero", nullable = false)
    @Getter @Setter
    private String numero;

    @Column(name = "lugar", nullable = false)
    @Getter @Setter
    private String lugar;

    @JsonIgnore
    @ManyToMany(mappedBy = "ubicaciones", cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<Producto> productos = new ArrayList<>();

    public List<Producto> productosRelacionados() {
        return this.productos;
    }


}
