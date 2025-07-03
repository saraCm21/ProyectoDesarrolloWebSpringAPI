package com.miempresa.mifinca.mifincaapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.miempresa.mifinca.mifincaapi.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    void deleteByCodigoUsuario(String codigoUsuario);
    Usuario findByCodigoUsuario(String codigoUsuario);
    Usuario findByUsername(String username);
    Usuario findByUsernameAndPassword(String username, String password);
    Usuario findByEmail(String email);
    
}

