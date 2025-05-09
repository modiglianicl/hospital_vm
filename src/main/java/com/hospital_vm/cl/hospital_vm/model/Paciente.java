package com.hospital_vm.cl.hospital_vm.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_paciente")
    private Integer id;

    @Column(unique = true , nullable = false , length = 13)
    private String rut;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable=false)
    private String apellido;

    @Column(nullable=true)
    private Date fechaNacimiento;

    @Column(nullable=false)
    private String correo;

    // Relación uno a muchos con Atencion
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atencion> atenciones;  // Un paciente tiene muchas atenciones
}
