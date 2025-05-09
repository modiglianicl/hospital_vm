package com.hospital_vm.cl.hospital_vm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital_vm.cl.hospital_vm.model.Atencion;
import com.hospital_vm.cl.hospital_vm.model.Paciente;
import com.hospital_vm.cl.hospital_vm.repository.AtencionRepository;
import com.hospital_vm.cl.hospital_vm.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AtencionService {

    @Autowired
    AtencionRepository atencionRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    public Atencion nuevaAtencion(Atencion nuevaAtencion){

        Paciente pacienteRecibido = nuevaAtencion.getPaciente();

        Optional<Paciente> pacienteExistente = pacienteRepository.findById(pacienteRecibido.getId());

        // Chequeamos si el paciente existe (por eso el isempty)
        if(pacienteExistente.isEmpty()){
            throw new RuntimeException("El paciente no existe!");
        }

        // Se registra el paciente en la atencion y se guarda en la bd
        nuevaAtencion.setPaciente(pacienteExistente.get());
        atencionRepository.save(nuevaAtencion);
        return nuevaAtencion;
    }

    public List<Atencion> findAllAtenciones() {
        return atencionRepository.findAll();
    }
}
