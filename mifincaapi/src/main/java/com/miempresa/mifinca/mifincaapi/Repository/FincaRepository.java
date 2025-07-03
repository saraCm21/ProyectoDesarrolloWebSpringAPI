package com.miempresa.mifinca.mifincaapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.miempresa.mifinca.mifincaapi.Model.Finca;

public interface FincaRepository extends JpaRepository<Finca, Integer> {

    void deleteByCodigoFinca(String codigoFinca);
    Finca findByCodigoFinca(String codigoFinca);
    Finca findByNombre(String nombre);

}

// This interface extends JpaRepository to provide CRUD operations for the Finca entity.
// It includes methods to delete a Finca by its codigoFinca, find a Finca by its codigoFinca, and find a Finca by its nombre.