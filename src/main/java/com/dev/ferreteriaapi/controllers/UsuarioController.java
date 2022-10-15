package com.dev.ferreteriaapi.controllers;

import com.dev.ferreteriaapi.entities.Rol;
import com.dev.ferreteriaapi.entities.Usuario;
import com.dev.ferreteriaapi.services.interfaces.IUsuarioService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/usuarios")
@CrossOrigin()
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @Secured({"ROLE_GERENTE"})
    @GetMapping()
    public ResponseEntity<List<Usuario>> getUsuarios(@RequestParam(name = "estado") Boolean estado) {
        return ResponseEntity.ok().body(usuarioService.getUsuarios(estado));
    }

    @Secured({"ROLE_GERENTE"})
    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> getRoles() {
        return ResponseEntity.ok().body(usuarioService.getRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Long id ) {
        return new ResponseEntity<>(usuarioService.findUsuarioById(id), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Usuario> getUsuarioByUsername(@PathVariable("username") String username ) {
        return new ResponseEntity<>(usuarioService.getUsuario(username), HttpStatus.OK);
    }

    @GetMapping("/exist/{username}")
    public ResponseEntity<Boolean> usuarioExist(@PathVariable("username") String username ) {
        return new ResponseEntity<>(usuarioService.usuarioExist(username), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {

        if( usuarioService.usuarioExist(usuario.getUsername()) ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario con el username: " + usuario.getUsername() + "ya existe");
        }

        return  new ResponseEntity<>(usuarioService.saveUsuario(usuario), HttpStatus.CREATED);
    }

    @PostMapping(value = "/roles")
    public ResponseEntity<Rol> saveRol(@RequestBody Rol rol) {

        if( usuarioService.rolExist(rol.getNombre()) ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El rol con el nombre: " + rol.getNombre() + "ya existe");
        }

        return  new ResponseEntity<>(usuarioService.saveRol(rol), HttpStatus.CREATED);
    }

    @PostMapping(value = "/roles/addToUsuario")
    public ResponseEntity<?>AddRolToUsuario(@RequestBody RolToUsuarioForm form) {
        usuarioService.addRolToUsuario(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

}

@Data
class RolToUsuarioForm {
    private String username;
    private String roleName;
}
