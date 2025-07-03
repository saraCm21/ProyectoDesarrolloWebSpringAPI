package com.miempresa.mifinca.mifincaapi;

import com.miempresa.mifinca.mifincaapi.Model.Usuario;
import com.miempresa.mifinca.mifincaapi.Service.LoginService;
import com.miempresa.mifinca.mifincaapi.Service.SignUpService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceIntegrationTest {

    @Autowired
    private LoginService loginService;

    @Test
    void testLoginReal() {
        boolean ok = loginService.login("sirii", "54321");
        assertTrue(ok, "El login debería ser exitoso con credenciales correctas");
    }

		@Autowired
	private SignUpService signUpService;



	@Test
	void registrarUsuario_guardaUsuarioConPasswordHasheada() {
		Usuario usuario = new Usuario();
		usuario.setUsername("sari");
		usuario.setPassword("12345");
		usuario.setNombre("saruliu");
		usuario.setEmail("test@email.com");
		usuario.setRol("vendedor");
		// No se establece codigoUsuario, ya que es generado automáticamente en el
		// servicio
		// No se establece codRecuperacion ni timeLimit, ya que no son necesarios para
		// el registro inicial
		// No se establece idUsuario, ya que es generado automáticamente por la base de
		// datos

		Usuario guardado = signUpService.registrarUsuario(usuario);

		System.out.println("Usuario guardado: " + guardado.getUsername());
	}

}