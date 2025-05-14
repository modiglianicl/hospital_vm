package com.hospital_vm.hospital_vm.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital_vm.hospital_vm.model.Doctor;
import com.hospital_vm.hospital_vm.repository.doctorRepository;

@Service
public class doctorService {

    @Autowired
    private doctorRepository doctorRepository;

    public List<Doctor> verDoctores(){
        return doctorRepository.findAll();
    }

    public Optional<Doctor> obtenerDoctorPorId(Integer id){
        try {
            Optional<Doctor> doctorExistente = doctorRepository.findById(id);
            return doctorExistente;
        } catch (Exception e) {
            throw new RuntimeException("Doctor no existe.");
        }
    }

    public Doctor crearDoctor(Doctor nuevoDoctor){
        try {
            if(doctorRepository.existsByRutDoctor(nuevoDoctor.getRutDoctor())){
                throw new RuntimeException("Doctor ya existe con ese rut!");
            } else {
                doctorRepository.save(nuevoDoctor);
                return nuevoDoctor;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
