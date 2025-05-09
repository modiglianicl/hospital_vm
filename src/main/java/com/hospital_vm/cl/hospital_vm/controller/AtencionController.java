package com.hospital_vm.cl.hospital_vm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_vm.cl.hospital_vm.model.Atencion;
import com.hospital_vm.cl.hospital_vm.service.AtencionService;
import com.hospital_vm.cl.hospital_vm.service.PacienteService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/atenciones")
public class AtencionController {

    @Autowired
    AtencionService atencionService;
    @Autowired
    PacienteService pacienteService;

    @PostMapping("/")
    public String crearAtencion(@RequestBody Atencion atencion) {
        try {
            atencionService.nuevaAtencion(atencion);
            return "ok!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Nop";
        }

    }
    
}
