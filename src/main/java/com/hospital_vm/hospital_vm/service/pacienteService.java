package com.hospital_vm.hospital_vm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital_vm.hospital_vm.model.Paciente;
import com.hospital_vm.hospital_vm.repository.pacienteRepository;

@Service
public class pacienteService {

    @Autowired
    pacienteRepository pacienteRepository;

    public List<Paciente> obtenerPacientes(){

        try {
            List<Paciente> pacientes = pacienteRepository.findAll();
            return pacientes;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }

    }

    public Optional<Paciente> obtenerPacientePorId(Integer id){

        return pacienteRepository.findById(id);
    }


    public Paciente obtenerPacientePorRut(String rut){
        Optional<Paciente> pacienteExistente = pacienteRepository.findByRutPaciente(rut);
        if(pacienteExistente.isPresent()){
            return pacienteExistente.get();
        } else {
            throw new RuntimeException("Paciente con rut " + rut + " no existe");
        }
    }

    public Paciente crearPaciente(Paciente nuevoPaciente){

        Optional<Paciente> pacienteExistente = pacienteRepository.findByRutPaciente(nuevoPaciente.getRutPaciente());
        if(pacienteExistente.isPresent()){
            throw new RuntimeException("Paciente ya existe con ese rut.");
         }

         return pacienteRepository.save(nuevoPaciente);


    }

    public Paciente actualizarPaciente(Paciente pacienteActualizado, String rut){
        Optional<Paciente> pacienteExistente = pacienteRepository.findByRutPaciente(rut);
        
        if(!pacienteExistente.isPresent()){
            throw new RuntimeException("Paciente con rut " + rut + " no existe");
        }

        pacienteExistente.get().setApellidoPaciente(pacienteActualizado.getApellidoPaciente());
        pacienteExistente.get().setCorreoPaciente(pacienteActualizado.getCorreoPaciente());
        pacienteExistente.get().setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
        pacienteExistente.get().setNombrePaciente(pacienteActualizado.getApellidoPaciente());
        pacienteRepository.save(pacienteExistente.get());
        return pacienteExistente.get();

    }

    public void borrarPacientePorRur(String rut){
        Optional<Paciente> pacienteExistente = pacienteRepository.findByRutPaciente(rut);
        if(pacienteExistente.isPresent()){
            pacienteRepository.delete(pacienteExistente.get());
        } else {
            throw new RuntimeException("Paciente con rut " + rut + " no existe");
        }
    }

    public Paciente parcharPaciente(String rut ,Paciente paciente){
        Optional<Paciente> pacienteParchar = pacienteRepository.findByRutPaciente(rut);
        if(pacienteParchar.isPresent()){
            if(paciente.getApellidoPaciente() != null){
                pacienteParchar.get().setApellidoPaciente(paciente.getApellidoPaciente());
            }
            if(paciente.getCorreoPaciente() != null){
                pacienteParchar.get().setCorreoPaciente(paciente.getCorreoPaciente());
            }
            if(paciente.getFechaNacimiento() != null){
                pacienteParchar.get().setFechaNacimiento(paciente.getFechaNacimiento());
            }
            if(paciente.getNombrePaciente() != null){
                pacienteParchar.get().setNombrePaciente(paciente.getNombrePaciente());
            }
            if(paciente.getApellidoPaciente() != null){
                pacienteParchar.get().setApellidoPaciente(paciente.getApellidoPaciente());
            }
            
            pacienteRepository.save(pacienteParchar.get());
            return pacienteParchar.get();
        } else {
            throw new RuntimeException("Paciente con rut " + rut + " no existe");
        }
    }
}
