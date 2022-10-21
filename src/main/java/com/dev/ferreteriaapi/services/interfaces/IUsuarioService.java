package com.dev.ferreteriaapi.services.interfaces;

import com.dev.ferreteriaapi.entities.Rol;
import com.dev.ferreteriaapi.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario saveUsuario(Usuario usuario);
    Rol saveRol(Rol rol);
    void addRolToUsuario(String username, String rolName);
    Usuario getUsuario(String username);
    Usuario updateUsuario(Usuario usuario, Long id);
    Usuario findUsuarioById(Long id);
    Boolean usuarioExist(String username);
    Boolean rolExist(String username);
    List<Usuario> getUsuarios(Boolean estado);
    List<Rol> getRoles();
    Usuario changeState(Long id, Boolean estado);
}
