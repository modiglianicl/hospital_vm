package com.hospital_vm.hospital_vm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital_vm.hospital_vm.model.Paciente;

@Repository
public interface pacienteRepository extends JpaRepository<Paciente,Integer>{
    public Optional<Paciente> findByRutPaciente(String rut);
}
