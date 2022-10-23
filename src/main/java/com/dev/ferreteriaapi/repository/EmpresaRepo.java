package com.dev.ferreteriaapi.repository;

import com.dev.ferreteriaapi.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpresaRepo extends JpaRepository<Empresa, Long> {

    Long countByNombre(String nombre);
    Long countByDireccion(String direccion);
    Long countByTelefono(String telefono);
    Long countByCorreo(String correo);

    List<Empresa> findTop40ByEstado(Boolean estado);

    @Query(
            "select e from Empresa e where ( LOWER(e.nombre) LIKE CONCAT('%', :search ,'%') OR LOWER(e.telefono) LIKE CONCAT('%', :search ,'%') OR LOWER(e.correo) LIKE CONCAT('%', :search ,'%') ) AND e.estado = :estado"
    )
    List<Empresa> searchEmpresa(@Param("search") String search, @Param("estado") Boolean estado);


}
