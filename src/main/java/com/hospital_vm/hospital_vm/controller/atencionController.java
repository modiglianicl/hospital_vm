package com.hospital_vm.hospital_vm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_vm.hospital_vm.model.Atencion;
import com.hospital_vm.hospital_vm.service.atencionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1/atenciones")
public class atencionController {

    @Autowired
    atencionService atencionService;

    @GetMapping()
    public ResponseEntity<List<Atencion>> getAtenciones() {
        List<Atencion> atenciones = atencionService.verAtenciones();
        return ResponseEntity.ok(atenciones);
    }
    


    @PostMapping
    public ResponseEntity<Atencion> crearAtencion(@RequestBody Atencion nuevaAtencion) {
        try {
            atencionService.crearAtencion(nuevaAtencion);
            return ResponseEntity.ok(nuevaAtencion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
}
