package com.hospital_vm.hospital_vm.model;


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer id;

    @Column(name = "rut_paciente" , nullable = false , unique = true, length=13)
    private String rutPaciente;

    @Column(name = "nombre_paciente", nullable = false)
    private String nombrePaciente;

    @Column(name = "apellido_paciente", nullable = false)
    private String apellidoPaciente;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fechaNacimiento;

    @Column(name = "correo_paciente", nullable = true)
    private String correoPaciente;

    @OneToMany(mappedBy = "paciente" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Atencion> atenciones;

}
