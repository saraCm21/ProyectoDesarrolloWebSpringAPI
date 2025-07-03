package com.miempresa.mifinca.mifincaapi.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.miempresa.mifinca.mifincaapi.Model.Usuario;
import com.miempresa.mifinca.mifincaapi.Repository.UsuarioRepository;

@Service
public class SignUpService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Usuario registrarUsuario(Usuario usuario) {
        String codigo = "%06d".formatted(new Random().nextInt(1000000));
        usuario.setCodigoUsuario(codigo);
        String hashedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(hashedPassword);
        return usuarioRepository.save(usuario);
    }
}