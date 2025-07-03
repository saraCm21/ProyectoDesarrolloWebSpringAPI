package com.miempresa.mifinca.mifincaapi.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.miempresa.mifinca.mifincaapi.Repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import com.miempresa.mifinca.mifincaapi.Dto.MensajeResponse;
import com.miempresa.mifinca.mifincaapi.Dto.SignUpRequest;
import com.miempresa.mifinca.mifincaapi.Model.Usuario;
import java.util.Random;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registrarUsuario(@RequestBody SignUpRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeResponse("El correo ya está registrado"));
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setUsername(request.getUsername());
        usuario.setRol(request.getRol());
        usuario.setCodigoUsuario("%06d".formatted(new Random().nextInt(1000000)));
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        usuarioRepository.save(usuario);

        return ResponseEntity.ok(new MensajeResponse("Usuario registrado correctamente"));
    }
}