package com.miempresa.mifinca.mifincaapi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.miempresa.mifinca.mifincaapi.Repository.UsuarioRepository;
import com.miempresa.mifinca.mifincaapi.Model.Usuario; // Asegúrate de que la ruta del paquete sea correcta

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class SendEmailService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            return false;
        }

        int codigo = new Random().nextInt(900000) + 100000;
        usuario.setCodRecuperacion(codigo);
        usuario.setTimeLimit(LocalDateTime.now().plusMinutes(15));
        usuarioRepository.save(usuario);

        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setFrom("sasacm0610@gmail.com");
            mensaje.setTo(usuario.getEmail());
            mensaje.setSubject("Código de recuperación");
            mensaje.setText("Tu código de recuperación es: " + codigo + ". Tiene validez de 15 minutos.");
            mailSender.send(mensaje);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
