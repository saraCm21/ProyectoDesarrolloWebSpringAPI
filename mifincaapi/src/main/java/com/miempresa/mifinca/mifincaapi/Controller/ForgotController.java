package com.miempresa.mifinca.mifincaapi.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.miempresa.mifinca.mifincaapi.Service.SendEmailService;
import com.miempresa.mifinca.mifincaapi.Service.ForgotService;
import com.miempresa.mifinca.mifincaapi.Dto.MensajeResponse;
import com.miempresa.mifinca.mifincaapi.Dto.EmailRequest;
import com.miempresa.mifinca.mifincaapi.Dto.ChangePasswordRequest;

@RestController
@RequestMapping("/api/forgot")
public class ForgotController {

    @Autowired
    private SendEmailService emailService;

    @Autowired
    private ForgotService changePasswordService;

    @PostMapping("/send-email")
    public ResponseEntity<MensajeResponse> sendEmail(@RequestBody EmailRequest request) {
        boolean enviado = emailService.sendEmail(request.getEmail());

        if (enviado) {
            return ResponseEntity.ok(new MensajeResponse("Código enviado al correo"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MensajeResponse("Correo no registrado"));
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<MensajeResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        boolean cambiado = changePasswordService.cambiarPassword(
                request.getEmail(),
                request.getCodigo(),
                request.getNuevaPassword()
        );

        if (cambiado) {
            return ResponseEntity.ok(new MensajeResponse("Contraseña actualizada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeResponse("Código inválido o expirado"));
        }
    }
}