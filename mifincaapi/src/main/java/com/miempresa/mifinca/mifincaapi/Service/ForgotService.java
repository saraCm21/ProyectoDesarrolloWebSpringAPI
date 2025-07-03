package com.miempresa.mifinca.mifincaapi.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.miempresa.mifinca.mifincaapi.Repository.UsuarioRepository;
import com.miempresa.mifinca.mifincaapi.Model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;

@Service
public class ForgotService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean cambiarPassword(String email, int codigo, String nuevaPassword) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null)
            return false;

        // Verifica código y tiempo límite
        if (usuario.getCodRecuperacion() == null || usuario.getTimeLimit() == null)
            return false;
        if (!usuario.getCodRecuperacion().equals(codigo))
            return false;
        if (usuario.getTimeLimit().isBefore(LocalDateTime.now()))
            return false;

        // Cambia la contraseña y limpia el código de recuperación
        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuario.setCodRecuperacion(null);
        usuario.setTimeLimit(null);
        usuarioRepository.save(usuario);
        return true;
    }
}