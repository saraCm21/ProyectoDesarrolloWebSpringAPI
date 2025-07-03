package com.miempresa.mifinca.mifincaapi.Dto;

public class LoginResponse {
    private String mensaje;
    private String rol;
    private String nombre;
    private String codigo_user;

    public LoginResponse(String mensaje, String rol, String nombre, String codigo_user) {
        this.mensaje = mensaje;
        this.rol = rol;
        this.nombre = nombre;
        this.codigo_user = codigo_user;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getRol() {
        return rol;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getCodigo_user() {
        return codigo_user;
    }
}