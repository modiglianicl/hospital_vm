package com.hospital_vm.hospital_vm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_vm.hospital_vm.model.Doctor;
import com.hospital_vm.hospital_vm.service.doctorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/doctores")

public class doctorController {

    @Autowired
    doctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> getDoctores() {
        List<Doctor> doctores = doctorService.verDoctores();
        return ResponseEntity.ok(doctores);
    }
    


}
