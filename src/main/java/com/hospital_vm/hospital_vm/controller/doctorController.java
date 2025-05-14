package com.hospital_vm.hospital_vm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_vm.hospital_vm.model.Doctor;
import com.hospital_vm.hospital_vm.service.doctorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/doctores")

public class doctorController {

    @Autowired
    private doctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> getDoctores() {
        List<Doctor> doctores = doctorService.verDoctores();
        return ResponseEntity.ok(doctores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Integer id) {
        try {
            Doctor doctorExistente = doctorService.obtenerDoctorPorId(id).get();
            return ResponseEntity.ok(doctorExistente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> createDoctor(@RequestBody Doctor nuevoDoctor) {
        try {
            Doctor doctorCreado = doctorService.crearDoctor(nuevoDoctor);
            return new ResponseEntity<>(doctorCreado,HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    
    


}
