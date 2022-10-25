package com.dev.ferreteriaapi.services.implement;

import com.dev.ferreteriaapi.entities.Empresa;
import com.dev.ferreteriaapi.entities.Producto;
import com.dev.ferreteriaapi.repository.EmpresaRepo;
import com.dev.ferreteriaapi.services.interfaces.IEmpresaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmpresaService implements IEmpresaService {

    private final EmpresaRepo empresaRepo;

    @Override
    public List<Empresa> getAll(Boolean estado) {
        return empresaRepo.findTop40ByEstado(estado);
    }

    @Override
    public List<Producto> getProductosRelacionados(Long id) {
        Empresa empresa = this.getEmpresaById(id);

        List<Producto> productos = empresa.productosProveedor();

        productos.removeIf(p -> Objects.equals( p.getEstado(), false ));

        return  productos;
    }


    @Override
    public Empresa getEmpresaById(Long id) {
        return this.empresaRepo.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La empresa no existe") );
    }

    @Override
    public Empresa create(Empresa empresa) {


        if(this.empresaRepo.countByNombre( empresa.getNombre() ) != 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La empresa con el nombre: " + empresa.getNombre() + " ya existe" );
        }

        if(this.empresaRepo.countByCorreo( empresa.getCorreo() ) != 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La empresa con el correo: " + empresa.getCorreo() + " ya existe" );
        }

        if(this.empresaRepo.countByDireccion( empresa.getDireccion() ) != 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La empresa con la direccion: " + empresa.getDireccion() + " ya existe" );
        }

        if(this.empresaRepo.countByTelefono( empresa.getTelefono() ) != 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La empresa con el telefono: " + empresa.getTelefono() + " ya existe" );
        }


        return this.empresaRepo.save(empresa);


    }

    @Override
    public Empresa updateEmpresa(Empresa empresa, Long id) {
        Empresa empresaUpdate = this.getEmpresaById(id);

        if(!empresa.getCorreo().equals(empresaUpdate.getCorreo()) ) {
            if(this.empresaRepo.countByCorreo( empresa.getCorreo() ) != 0) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "La empresa con el correo: " + empresa.getCorreo() + " ya existe" );
            }
        }

        if(!empresa.getDireccion().equals(empresaUpdate.getDireccion())) {
            if(this.empresaRepo.countByDireccion( empresa.getDireccion() ) != 0) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "La empresa con la direccion: " + empresa.getDireccion() + " ya existe" );
            }
        }

        if(!empresa.getTelefono().equals(empresaUpdate.getTelefono())) {
            if(this.empresaRepo.countByTelefono( empresa.getTelefono() ) != 0) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "La empresa con el telefono: " + empresa.getTelefono() + " ya existe" );
            }
        }

        empresaUpdate.setDireccion(empresa.getDireccion());
        empresaUpdate.setCorreo(empresa.getCorreo());
        empresaUpdate.setTelefono(empresa.getTelefono());

        return this.empresaRepo.save(empresaUpdate);

    }

    @Override
    public Empresa changeState(Long id, Boolean estado) {
        Empresa empresaUpdate = getEmpresaById(id);

        empresaUpdate.setEstado(estado);

        return  this.empresaRepo.save(empresaUpdate);
    }

    @Override
    public List<Empresa> findEmpresa(String search, Boolean estado) {
        return this.empresaRepo.searchEmpresa(search, estado);
    }
}
