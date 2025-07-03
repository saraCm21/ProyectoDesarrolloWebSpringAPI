package com.miempresa.mifinca.mifincaapi.Service;

import com.miempresa.mifinca.mifincaapi.Model.Usuario;
import com.miempresa.mifinca.mifincaapi.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean login(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            return false;
        }

        return passwordEncoder.matches(password, usuario.getPassword());
    }
}