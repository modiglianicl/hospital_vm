package com.hospital_vm.hospital_vm.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atencion")
    private Integer idAtencion;

    @Column(name = "fecha_atencion" , nullable = false)
    private LocalDate fechaAtencion;

    @Column(name="observacion_atencion")
    private String observacionAtencion;

    @ManyToOne
    @JoinColumn(name = "id_paciente" , nullable = false)
    // @JsonIgnore : esta anotacion hace que trascienda el paciente, no muestra el objeto
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_doctor" , nullable = false)
    @JsonIgnoreProperties("atenciones")
    
    private Doctor doctor;

}
