package com.miempresa.mifinca.mifincaapi.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.miempresa.mifinca.mifincaapi.Repository.FincaRepository;
import com.miempresa.mifinca.mifincaapi.Repository.UsuarioRepository;
import com.miempresa.mifinca.mifincaapi.Model.Finca;
import com.miempresa.mifinca.mifincaapi.Model.Usuario;

@Service
public class FincaService {

    @Autowired
    private FincaRepository fincaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Finca crearFincaConCodigos(Finca finca, String codigoPropietario, String codigoCapataz,
            String codigoVendedor) {
        Usuario propietario = usuarioRepository.findByCodigoUsuario(codigoPropietario);
        Usuario capataz = usuarioRepository.findByCodigoUsuario(codigoCapataz);
        Usuario vendedor = usuarioRepository.findByCodigoUsuario(codigoVendedor);

        if (propietario != null)
            finca.setPropietarioId(propietario.getIdUsuario());
        if (capataz != null)
            finca.setCapatazId(capataz.getIdUsuario());
        if (vendedor != null)
            finca.setVendedorId(vendedor.getIdUsuario());

        return fincaRepository.save(finca);
    }
}