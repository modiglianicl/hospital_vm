package com.hospital_vm.cl.hospital_vm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_vm.cl.hospital_vm.model.Atencion;
import com.hospital_vm.cl.hospital_vm.service.AtencionService;
import com.hospital_vm.cl.hospital_vm.service.PacienteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/atenciones")
public class AtencionController {

    @Autowired
    AtencionService atencionService;
    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public List<Atencion> verAtenciones() {
        return atencionService.findAllAtenciones();
    }

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
