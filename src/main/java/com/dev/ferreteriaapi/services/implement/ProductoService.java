package com.dev.ferreteriaapi.services.implement;

import com.dev.ferreteriaapi.entities.Empresa;
import com.dev.ferreteriaapi.entities.Producto;
import com.dev.ferreteriaapi.entities.Ubicacion;
import com.dev.ferreteriaapi.repository.ProductoRepo;
import com.dev.ferreteriaapi.services.interfaces.IProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductoService implements IProductoService {

    private final ProductoRepo productoRepo;
    private final FileUploadService fileManager;

    @Override
    public List<Producto> getAll(Boolean estado) {
        return this.productoRepo.findTop40ByEstado(estado);
    }

    @Override
    public Producto getById(Long id) {
        return this.productoRepo.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El producto no existe"));
    }

    @Override
    public Producto create(Producto producto, MultipartFile img) {

        if(this.productoRepo.countByCodigoOrNombre(producto.getNombre(), producto.getCodigo()) != 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El producto con el nombre y el codigo ingresado ya existe");
        }

        if(img != null && !img.isEmpty()) {
            try {
                String imageName = fileManager.copiFile(img);
                producto.setImg(imageName);
            }catch(IOException ex) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al subir la imagen " + ex.getMessage());
            }
        }

        return this.productoRepo.save(producto);
    }

    @Override
    public List<Producto> findProducto(String filter, Boolean estado) {
        return this.productoRepo.findProductos(filter, estado);
    }

    @Override
    public Producto update(Producto producto, Long id, MultipartFile img) {

        Producto productoUpdate = this.getById(id);

        String oldImage = producto.getImg();

        if(img != null && !img.isEmpty()) {
            try {
                String imageName = fileManager.copiFile(img);
                productoUpdate.setImg(imageName);
            }catch(IOException ex) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al subir la imagen " + ex.getMessage());
            }
        }


        productoUpdate.setDescuentoMax((producto.getDescuentoMax()));
        productoUpdate.setDescripcion(producto.getDescripcion());

        if(img != null) {
            fileManager.deleteFile(oldImage);
        };

        productoUpdate = this.productoRepo.save(productoUpdate);

        return productoUpdate;
    }

    @Override
    public Producto addEmpresas(Long id, List<Empresa> empresas) {
        Producto productoUpdate = this.getById(id);
        for(Empresa empresa : empresas) {
            productoUpdate.addEmpresa(empresa);
        }

        return this.productoRepo.save(productoUpdate);
    }

    @Override
    public Producto deleteEmpresas(Long id, List<Empresa> empresas) {
        Producto productoUpdate = this.getById(id);
        for(Empresa empresa: empresas) {
            productoUpdate.removeEmpresa(empresa);
        }

        return this.productoRepo.save(productoUpdate);
    }

    @Override
    public Producto addUbicaciones(Long id, List<Ubicacion> ubicaciones) {
        Producto productoUpdate = this.getById(id);
        for(Ubicacion ubicacion : ubicaciones) {
            productoUpdate.addUbicacion(ubicacion);
        }

        return this.productoRepo.save(productoUpdate);
    }

    @Override
    public Producto deleteUbicaciones(Long id, List<Ubicacion> ubicaciones) {
        Producto productoUpdate = this.getById(id);
        for(Ubicacion ubicacion : ubicaciones) {
            productoUpdate.removeUbicacion(ubicacion);
        }

        return this.productoRepo.save(productoUpdate);
    }

    @Override
    public void changeState(Long id, Boolean estado) {

        Producto productoUpdate = this.getById(id);

        productoUpdate.setEstado(estado);

        this.productoRepo.save(productoUpdate);

    }
}
