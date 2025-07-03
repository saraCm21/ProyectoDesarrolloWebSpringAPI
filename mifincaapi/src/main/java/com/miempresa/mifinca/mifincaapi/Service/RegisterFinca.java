package com.miempresa.mifinca.mifincaapi.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.miempresa.mifinca.mifincaapi.Model.Finca;
import java.util.Random;

@Service
public class RegisterFinca {
    @Autowired
    private FincaService fincaService;

    /**
     * Crea una nueva finca y la guarda en la base de datos.
     * 
     * @param finca el objeto Finca a guardar
     * @return el objeto Finca guardado
     */

     public Finca createFinca(Finca finca, String codigoPropietario, String codigoCapataz,
            String codigoVendedor) {
        
            String codigo = "%06d".formatted(new Random().nextInt(1000000));
            finca.setCodigoFinca(codigo);
            Finca guardado = fincaService.crearFincaConCodigos(finca, codigoPropietario, codigoCapataz, codigoVendedor);

        return guardado;
    }
}