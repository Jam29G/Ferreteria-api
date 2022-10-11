package com.dev.ferreteriaapi;

import com.dev.ferreteriaapi.entities.Rol;
import com.dev.ferreteriaapi.entities.Usuario;
import com.dev.ferreteriaapi.services.interfaces.IUsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class FerreteriaapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FerreteriaapiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(IUsuarioService usuarioService, BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			usuarioService.saveRol(new Rol(null, "ROLE_ADMIN"));
			usuarioService.saveRol(new Rol(null, "ROLE_GERENTE"));
			usuarioService.saveRol(new Rol(null, "ROLE_CAJERO"));

			usuarioService.saveUsuario(new Usuario(
					null,
					"Jamil",
					"Gutierrez",
					"jam29",
					passwordEncoder.encode("12345"),
					true,
					new ArrayList<>()
			));

			usuarioService.saveUsuario(new Usuario(
					null,
					"Jose",
					"Leon",
					"jose12",
					passwordEncoder.encode("12345"),
					true,
					new ArrayList<>()
			));

			usuarioService.saveUsuario(new Usuario(
					null,
					"admin",
					"admin",
					"admin",
					"12345",
					true,
					new ArrayList<>()
			));

			usuarioService.addRolToUsuario("jam29", "ROLE_GERENTE");
			usuarioService.addRolToUsuario("jam29", "ROLE_CAJERO");

			usuarioService.addRolToUsuario("jose12", "ROLE_CAJERO");

			usuarioService.addRolToUsuario("admin", "ROLE_ADMIN");
			usuarioService.addRolToUsuario("admin", "ROLE_GERENTE");
			usuarioService.addRolToUsuario("admin", "ROLE_CAJERO");


		};
	}

}
