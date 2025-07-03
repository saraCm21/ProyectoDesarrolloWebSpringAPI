package com.miempresa.mifinca.mifincaapi.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fincas")
@Getter
@Setter
@NoArgsConstructor
public class Finca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_finca")
    private Integer idFinca;

    @Column(name = "codigo_finca", length = 20)
    private String codigoFinca;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "numHectareas")
    private Integer numHectareas;

    @Column(name = "metrosCuadrados")
    private Integer metrosCuadrados;

    @Column(name = "propietario_id")
    private Integer propietarioId;

    @Column(name = "capataz_id")
    private Integer capatazId;

    @Column(name = "vendedor_id")
    private Integer vendedorId;

    @Column(name = "pais", length = 50)
    private String pais;

    @Column(name = "departamento", length = 50)
    private String departamento;

    @Column(name = "ciudad", length = 50)
    private String ciudad;

    @Column(name = "siProduceLeche")
    private Boolean siProduceLeche;

    @Column(name = "siProduceCereales")
    private Boolean siProduceCereales;

    @Column(name = "siProduceFrutas")
    private Boolean siProduceFrutas;

    @Column(name = "siProduceVerduras")
    private Boolean siProduceVerduras;
}
