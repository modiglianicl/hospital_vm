package com.hospital_vm.cl.hospital_vm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital_vm.cl.hospital_vm.model.Paciente;
import com.hospital_vm.cl.hospital_vm.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public List<Paciente> findAllPacientes(){
        return pacienteRepository.findAll();
    }

    public Paciente findPacienteById(Integer id){
        return pacienteRepository.findById(id).get();
    }

    public Paciente savePaciente(Paciente nuevoPaciente){
        return pacienteRepository.save(nuevoPaciente);
    }

    public void deletePaciente(Integer id){
        pacienteRepository.deleteById(id);
    }

}
