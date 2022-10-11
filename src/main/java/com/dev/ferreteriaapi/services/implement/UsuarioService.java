package com.dev.ferreteriaapi.services.implement;

import com.dev.ferreteriaapi.entities.Rol;
import com.dev.ferreteriaapi.entities.Usuario;
import com.dev.ferreteriaapi.repository.RolRepo;
import com.dev.ferreteriaapi.repository.UsuarioRepo;
import com.dev.ferreteriaapi.services.interfaces.IUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepo usuarioRepo;

    private final RolRepo rolRepo;

    //Metodo que sirve para configurar spring security la forma en que
    //debe encontrar los usuarios
    //TODO: realizar el metodo userDetail

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        log.info("Guardando un nuevo usuario: {} en la DB", usuario.getUsername());
        return usuarioRepo.save(usuario);
    }

    @Override
    public Rol saveRol(Rol rol) {
        log.info("Guardando un nuevo rol: {} en la DB", rol.getNombre());
        return rolRepo.save(rol);
    }

    @Override
    public void addRolToUsuario(String username, String rolName) {

        Usuario usuario = usuarioRepo.findByUsername(username);
        if(usuario == null) {
            log.info("El usuario con username: {} no existe", username );
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe");
        }

        Rol rol = rolRepo.findByNombre(rolName);
        if(rol == null) {
            log.info("El rol con nombre: {} no existe", rolName );
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El rol no existe");
        }

        log.info("Agregando rol: {} al usuario: {}", rolName, username);
        usuario.getRoles().add(rol);

    }

    @Override
    public Usuario getUsuario(String username) {
        Usuario usuario = usuarioRepo.findByUsername(username);
        log.info("Buscando usuario: {}", usuario.getUsername());
        if(usuario.getUsername() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe");

        return usuario;
    }

    @Override
    public Boolean usuarioExist(String username) {
        return usuarioRepo.countByUsername(username) > 0;
    }

    @Override
    public Boolean rolExist(String rolName) {
        return usuarioRepo.countByUsername(rolName) > 0;
    }

    @Override
    public List<Usuario> getUsuarios() {
        log.info("Buscando todos los usuarios");
        return usuarioRepo.findAll();
    }


}
