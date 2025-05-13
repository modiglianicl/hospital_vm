package com.hospital_vm.cl.hospital_vm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_vm.cl.hospital_vm.model.Atencion;
import com.hospital_vm.cl.hospital_vm.model.Paciente;
import com.hospital_vm.cl.hospital_vm.service.AtencionService;
import com.hospital_vm.cl.hospital_vm.service.PacienteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/atenciones")
public class AtencionController {

    @Autowired
    AtencionService atencionService;
    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Atencion>> verAtenciones() {
        List<Atencion> atenciones = atencionService.findAllAtenciones();

        return ResponseEntity.ok(atenciones);
    }

    @PostMapping
    public ResponseEntity<Atencion> crearAtencion(@RequestBody Atencion atencion) {
        try {
            Atencion atencionCreada = atencionService.nuevaAtencion(atencion);
            return new ResponseEntity<>(atencionCreada, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atencion> actualizarAtencion(@RequestBody Atencion atencion, @PathVariable Integer id) {
        try {
            Atencion atencionActualizada = atencionService.actualizarAtencion(atencion, id);
            return ResponseEntity.ok(atencionActualizada);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar/{idAtencion}")
    public ResponseEntity<Atencion> actualizarAtencion(@PathVariable Integer idAtencion,@RequestBody Atencion atencion) {

        try {
            Atencion atencionActualizada = atencionService.actualizarAtencion(idAtencion,atencion);
            return ResponseEntity.ok(atencionActualizada);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
