package com.hospital_vm.hospital_vm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital_vm.hospital_vm.model.Atencion;
import com.hospital_vm.hospital_vm.model.Doctor;
import com.hospital_vm.hospital_vm.model.Paciente;
import com.hospital_vm.hospital_vm.repository.atencionRepository;

@Service
public class atencionService {

    @Autowired
    atencionRepository atencionRepository;
    @Autowired
    pacienteService pacienteService;
    @Autowired
    doctorService doctorService;

    public List<Atencion> verAtenciones() {
        return atencionRepository.findAll();
    }

    public void crearAtencion(Atencion nuevaAtencion) {

        try {
            Optional<Paciente> pacienteAtendido = pacienteService
                    .obtenerPacientePorId(nuevaAtencion.getPaciente().getId());
            Optional<Doctor> doctorAtencion = doctorService
                .obtenerDoctorPorId(nuevaAtencion.getDoctor().getIdDoctor());
            if (pacienteAtendido.isPresent() && doctorAtencion.isPresent()) {
                nuevaAtencion.setPaciente(pacienteAtendido.get());
                nuevaAtencion.setDoctor(doctorAtencion.get());
                atencionRepository.save(nuevaAtencion);
            } else {
                throw new RuntimeException("Paciente no existe!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error! : " + e.getMessage());
        }

    }

}
