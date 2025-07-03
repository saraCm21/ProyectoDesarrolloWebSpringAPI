package com.miempresa.mifinca.mifincaapi.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.miempresa.mifinca.mifincaapi.Repository.UsuarioRepository;
import com.miempresa.mifinca.mifincaapi.Model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.miempresa.mifinca.mifincaapi.Dto.LoginRequest;
import com.miempresa.mifinca.mifincaapi.Dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername());
        if (usuario == null || !passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        return ResponseEntity.ok(new LoginResponse("Login exitoso", usuario.getRol(), usuario.getNombre(), usuario.getCodigoUsuario()));
    }
}