package com.miempresa.mifinca.mifincaapi.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.miempresa.mifinca.mifincaapi.Service.RegisterFincaService;
import com.miempresa.mifinca.mifincaapi.Dto.CreateFincaRequest;
import com.miempresa.mifinca.mifincaapi.Model.Finca;
import com.miempresa.mifinca.mifincaapi.Dto.MensajeResponse;
import com.miempresa.mifinca.mifincaapi.Repository.FincaRepository;

@RestController
@RequestMapping("/api/fincas")
public class FincaController {
    @Autowired
    private RegisterFincaService createFincaService;

    @Autowired
    private FincaRepository fincaRepository;


    @PostMapping("/crear")
    public ResponseEntity<?> crearFinca(@RequestBody CreateFincaRequest request) {
        try {
            Finca finca = createFincaService.createFinca(
                request.getFinca(),
                request.getCodigoPropietario(),
                request.getCodigoCapataz(),
                request.getCodigoVendedor()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(finca);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MensajeResponse("Error al crear la finca: " + e.getMessage()));
        }
    }

    @GetMapping("/{codigoFinca}")
    public ResponseEntity<?> obtenerFinca(@PathVariable String codigoFinca) {
        Finca finca = fincaRepository.findByCodigoFinca(codigoFinca);
        if (finca != null) {
            return ResponseEntity.ok(finca);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MensajeResponse("Finca no encontrada"));
        }
    }   

    @DeleteMapping("/{codigoFinca}")
    public ResponseEntity<?> eliminarFinca(@PathVariable String codigoFinca) {
        Finca finca = fincaRepository.findByCodigoFinca(codigoFinca);
        if (finca != null) {
            fincaRepository.deleteByCodigoFinca(codigoFinca);
            return ResponseEntity.ok(new MensajeResponse("Finca eliminada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MensajeResponse("Finca no encontrada"));
        }
    }

    @PutMapping("/{codigoFinca}")
    public ResponseEntity<?> editarFinca(
            @PathVariable String codigoFinca,
            @RequestBody Finca fincaActualizada) {

        Finca fincaExistente = fincaRepository.findByCodigoFinca(codigoFinca);

        if (fincaExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MensajeResponse("Finca no encontrada"));
        }

        // Solo actualiza los campos si vienen en la petición
        if (fincaActualizada.getNombre() != null)
            fincaExistente.setNombre(fincaActualizada.getNombre());

        if (fincaActualizada.getNumHectareas() != null)
            fincaExistente.setNumHectareas(fincaActualizada.getNumHectareas());

        if (fincaActualizada.getMetrosCuadrados() != null)
            fincaExistente.setMetrosCuadrados(fincaActualizada.getMetrosCuadrados());

        if (fincaActualizada.getSiProduceLeche() != null)
            fincaExistente.setSiProduceLeche(fincaActualizada.getSiProduceLeche());

        if (fincaActualizada.getSiProduceCereales() != null)
            fincaExistente.setSiProduceCereales(fincaActualizada.getSiProduceCereales());

        if (fincaActualizada.getSiProduceFrutas() != null)
            fincaExistente.setSiProduceFrutas(fincaActualizada.getSiProduceFrutas());

        if (fincaActualizada.getSiProduceVerduras() != null)
            fincaExistente.setSiProduceVerduras(fincaActualizada.getSiProduceVerduras());

        fincaRepository.save(fincaExistente);

        return ResponseEntity.ok(new MensajeResponse("Finca actualizada correctamente"));
    }
}