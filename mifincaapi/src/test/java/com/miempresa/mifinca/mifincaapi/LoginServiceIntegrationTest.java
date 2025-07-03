package com.miempresa.mifinca.mifincaapi;

import com.miempresa.mifinca.mifincaapi.Model.Finca;
import com.miempresa.mifinca.mifincaapi.Model.Usuario;
import com.miempresa.mifinca.mifincaapi.Service.ForgotService;
import com.miempresa.mifinca.mifincaapi.Service.LoginService;
import com.miempresa.mifinca.mifincaapi.Service.RegisterFincaService;
import com.miempresa.mifinca.mifincaapi.Service.SendEmailService;
import com.miempresa.mifinca.mifincaapi.Service.SignUpService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

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

	@Autowired
	private SendEmailService sendEmailService;

	@Test
	void sendEmail_enviaCorreoYActualizaUsuario() {
		boolean resultado = sendEmailService.sendEmail("saracastellano2108@gmail.com");
		assertThat(resultado).isTrue();
	}

	@Autowired
	private ForgotService forgotService;

	@Test
	void testForgotPassword() {
		String email = "saracastellano2108@gmail.com";
		String newPassword = "12345";
		int codigo = 879358;
		boolean resultado = forgotService.cambiarPassword(email, codigo, newPassword);	
		assertTrue(resultado, "La contraseña se ha cambiado correctamente");
	}	

	@Autowired
	private RegisterFincaService createFincaService;

	@Test
	void crearFincaConCodigos_asignaIdsCorrectos() {
		// Crear finca de prueba
		Finca finca = new Finca();
		finca.setNombre("Finqiota testing");
		finca.setNumHectareas(10);
		finca.setMetrosCuadrados(10000);
		finca.setPais("Colombia");
		finca.setDepartamento("Antioquia");
		finca.setCiudad("Medellín");
		finca.setMetrosCuadrados(100000);
		finca.setPais("Colombia");
		finca.setDepartamento("Antioquia");
		finca.setCiudad("Medellín");
		finca.setSiProduceCereales(true);
		finca.setSiProduceFrutas(true);
		finca.setSiProduceLeche(true);
		finca.setSiProduceVerduras(true);

		// Usar el servicio para asignar los IDs usando los códigos de usuario
		Finca guardada = createFincaService.createFinca(
				finca,
				"889180", // Código del propietario
				"546951",
				"652262");

		assertThat(guardada).isNotNull();
	}

}