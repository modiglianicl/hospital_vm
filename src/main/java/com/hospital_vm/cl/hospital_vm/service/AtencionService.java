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

    public Atencion actualizarAtencion(Atencion atencion, Integer id){
        Optional<Atencion> atencionExistente = atencionRepository.findById(id);

        if(atencionExistente.isPresent()){
            atencionExistente.get().setFechaAtencion(atencion.getFechaAtencion());
            return atencionExistente.get();
        }

        throw new RuntimeException("La atencion no existe!");
    }

    public Atencion actualizarAtencion(Integer idAtencion,Atencion atencion){
        Optional<Atencion> atencionExistente = atencionRepository.findById(idAtencion);
        Optional<Paciente> pacienteExistente = pacienteRepository.findById(atencion.getPaciente().getId());


        if(pacienteExistente.isPresent() && atencionExistente.isPresent()){
            atencionExistente.get().setPaciente(pacienteExistente.get());;
            atencionExistente.get().setFechaAtencion(atencion.getFechaAtencion());
            System.out.println(atencionExistente.get().getPaciente().getNombre());
            System.out.println(atencionExistente.get().getPaciente().getId());
            return atencionExistente.get();
        } else {
            throw new RuntimeException("La atencion o el paciente no existen!");
        }

    }
}
