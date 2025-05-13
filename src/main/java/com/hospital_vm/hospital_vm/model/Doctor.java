package com.hospital_vm.hospital_vm.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor")
    private Integer idDoctor;

    @Column(name = "rut_doctor" , nullable = false , length = 13)
    private String rutDoctor;

    @Column(name = "nombre_doctor" , nullable = false)
    private String nombreDoctor;
    
    @Column(name = "apellido_doctor" , nullable = false)
    private String apellidoDoctor;

    @Column(name = "especializacion_doctor" , nullable = false)
    private String especializacionDoctor;

    @OneToMany(mappedBy = "doctor" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Atencion> atenciones;




}
