package com.miempresa.mifinca.mifincaapi.Dto;

import com.miempresa.mifinca.mifincaapi.Model.Finca;

public class CreateFincaRequest {
    private Finca finca;
    private String codigoPropietario;
    private String codigoCapataz;
    private String codigoVendedor;

    // Getters y setters
    public Finca getFinca() {
        return finca;
    }

    public void setFinca(Finca finca) {
        this.finca = finca;
    }

    public String getCodigoPropietario() {
        return codigoPropietario;
    }

    public void setCodigoPropietario(String codigoPropietario) {
        this.codigoPropietario = codigoPropietario;
    }

    public String getCodigoCapataz() {
        return codigoCapataz;
    }

    public void setCodigoCapataz(String codigoCapataz) {
        this.codigoCapataz = codigoCapataz;
    }

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }
}

