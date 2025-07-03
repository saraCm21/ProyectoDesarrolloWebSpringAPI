package com.miempresa.mifinca.mifincaapi.Model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "codigo_usuario", length = 20)
    private String codigoUsuario;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "rol")
    private String rol;

    @Column(name = "cod_recuperacion")
    private Integer codRecuperacion;

    @Column(name = "time_limit")
    private LocalDateTime timeLimit;


}

