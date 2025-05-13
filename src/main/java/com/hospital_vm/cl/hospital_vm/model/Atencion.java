package com.hospital_vm.cl.hospital_vm.model;

import java.sql.Date;

import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn; 
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="atencion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Atencion {

    @Id // Ahora esta es la anotación @Id correcta de JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_atencion")
    private Integer id;

    @Column(name="fecha_atencion")
    @Temporal(TemporalType.DATE)
    private Date fechaAtencion;


    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)  // Define la columna de clave foránea
    private Paciente paciente;  // Cada atencion tiene un solo paciente


}